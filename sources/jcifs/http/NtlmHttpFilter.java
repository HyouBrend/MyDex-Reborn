package jcifs.http;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jcifs.Config;
import jcifs.UniAddress;
import jcifs.smb.NtlmChallenge;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbSession;
import jcifs.util.Base64;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class NtlmHttpFilter implements Filter {
    private static LogStream log = LogStream.getInstance();
    private String defaultDomain;
    private String domainController;
    private boolean enableBasic;
    private boolean insecureBasic;
    private boolean loadBalance;
    private String realm;

    @Override // javax.servlet.Filter
    public void destroy() {
    }

    public FilterConfig getFilterConfig() {
        return null;
    }

    @Override // javax.servlet.Filter
    public void init(FilterConfig filterConfig) throws ServletException {
        Config.setProperty("jcifs.smb.client.soTimeout", "1800000");
        Config.setProperty("jcifs.netbios.cachePolicy", "1200");
        Config.setProperty("jcifs.smb.lmCompatibility", "0");
        Config.setProperty("jcifs.smb.client.useExtendedSecurity", "false");
        Enumeration initParameterNames = filterConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String str = (String) initParameterNames.nextElement();
            if (str.startsWith("jcifs.")) {
                Config.setProperty(str, filterConfig.getInitParameter(str));
            }
        }
        this.defaultDomain = Config.getProperty("jcifs.smb.client.domain");
        String property = Config.getProperty("jcifs.http.domainController");
        this.domainController = property;
        if (property == null) {
            this.domainController = this.defaultDomain;
            this.loadBalance = Config.getBoolean("jcifs.http.loadBalance", true);
        }
        this.enableBasic = Boolean.valueOf(Config.getProperty("jcifs.http.enableBasic")).booleanValue();
        this.insecureBasic = Boolean.valueOf(Config.getProperty("jcifs.http.insecureBasic")).booleanValue();
        String property2 = Config.getProperty("jcifs.http.basicRealm");
        this.realm = property2;
        if (property2 == null) {
            this.realm = "jCIFS";
        }
        int i = Config.getInt("jcifs.util.loglevel", -1);
        if (i != -1) {
            LogStream.setLevel(i);
        }
        if (LogStream.level > 2) {
            try {
                Config.store(log, "JCIFS PROPERTIES");
            } catch (IOException unused) {
            }
        }
    }

    @Override // javax.servlet.Filter
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        NtlmPasswordAuthentication negotiate = negotiate(httpServletRequest, (HttpServletResponse) servletResponse, false);
        if (negotiate == null) {
            return;
        }
        filterChain.doFilter(new NtlmHttpServletRequest(httpServletRequest, negotiate), servletResponse);
    }

    protected NtlmPasswordAuthentication negotiate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean z) throws IOException, ServletException {
        NtlmPasswordAuthentication ntlmPasswordAuthentication;
        NtlmPasswordAuthentication ntlmPasswordAuthentication2;
        UniAddress byName;
        HttpSession session;
        byte[] challenge;
        String header = httpServletRequest.getHeader("Authorization");
        boolean z2 = this.enableBasic && (this.insecureBasic || httpServletRequest.isSecure());
        if (header == null || !(header.startsWith("NTLM ") || (z2 && header.startsWith("Basic ")))) {
            if (z) {
                return null;
            }
            HttpSession session2 = httpServletRequest.getSession(false);
            if (session2 != null && (ntlmPasswordAuthentication = (NtlmPasswordAuthentication) session2.getAttribute("NtlmHttpAuth")) != null) {
                return ntlmPasswordAuthentication;
            }
            httpServletResponse.setHeader("WWW-Authenticate", "NTLM");
            if (z2) {
                httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
            }
            httpServletResponse.setStatus(401);
            httpServletResponse.setContentLength(0);
            httpServletResponse.flushBuffer();
            return null;
        }
        if (header.startsWith("NTLM ")) {
            HttpSession session3 = httpServletRequest.getSession();
            if (this.loadBalance) {
                NtlmChallenge ntlmChallenge = (NtlmChallenge) session3.getAttribute("NtlmHttpChal");
                if (ntlmChallenge == null) {
                    ntlmChallenge = SmbSession.getChallengeForDomain();
                    session3.setAttribute("NtlmHttpChal", ntlmChallenge);
                }
                byName = ntlmChallenge.dc;
                challenge = ntlmChallenge.challenge;
            } else {
                byName = UniAddress.getByName(this.domainController, true);
                challenge = SmbSession.getChallenge(byName);
            }
            ntlmPasswordAuthentication2 = NtlmSsp.authenticate(httpServletRequest, httpServletResponse, challenge);
            if (ntlmPasswordAuthentication2 == null) {
                return null;
            }
            session3.removeAttribute("NtlmHttpChal");
        } else {
            String str = new String(Base64.decode(header.substring(6)), "US-ASCII");
            int indexOf = str.indexOf(58);
            String substring = indexOf != -1 ? str.substring(0, indexOf) : str;
            String substring2 = indexOf != -1 ? str.substring(indexOf + 1) : "";
            int indexOf2 = substring.indexOf(92);
            if (indexOf2 == -1) {
                indexOf2 = substring.indexOf(47);
            }
            String substring3 = indexOf2 != -1 ? substring.substring(0, indexOf2) : this.defaultDomain;
            if (indexOf2 != -1) {
                substring = substring.substring(indexOf2 + 1);
            }
            ntlmPasswordAuthentication2 = new NtlmPasswordAuthentication(substring3, substring, substring2);
            byName = UniAddress.getByName(this.domainController, true);
        }
        try {
            SmbSession.logon(byName, ntlmPasswordAuthentication2);
            if (LogStream.level > 2) {
                log.println("NtlmHttpFilter: " + ntlmPasswordAuthentication2 + " successfully authenticated against " + byName);
            }
            httpServletRequest.getSession().setAttribute("NtlmHttpAuth", ntlmPasswordAuthentication2);
            return ntlmPasswordAuthentication2;
        } catch (SmbAuthException e) {
            if (LogStream.level > 1) {
                log.println("NtlmHttpFilter: " + ntlmPasswordAuthentication2.getName() + ": 0x" + Hexdump.toHexString(e.getNtStatus(), 8) + ": " + e);
            }
            if (e.getNtStatus() == -1073741819 && (session = httpServletRequest.getSession(false)) != null) {
                session.removeAttribute("NtlmHttpAuth");
            }
            httpServletResponse.setHeader("WWW-Authenticate", "NTLM");
            if (z2) {
                httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
            }
            httpServletResponse.setStatus(401);
            httpServletResponse.setContentLength(0);
            httpServletResponse.flushBuffer();
            return null;
        }
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        try {
            init(filterConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
