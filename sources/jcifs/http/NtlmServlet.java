package jcifs.http;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jcifs.Config;
import jcifs.UniAddress;
import jcifs.netbios.NbtAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbSession;
import jcifs.util.Base64;

/* loaded from: classes2.dex */
public abstract class NtlmServlet extends HttpServlet {
    private String defaultDomain;
    private String domainController;
    private boolean enableBasic;
    private boolean insecureBasic;
    private boolean loadBalance;
    private String realm;

    @Override // javax.servlet.GenericServlet, javax.servlet.Servlet
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        Config.setProperty("jcifs.smb.client.soTimeout", "300000");
        Config.setProperty("jcifs.netbios.cachePolicy", "600");
        Enumeration initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String str = (String) initParameterNames.nextElement();
            if (str.startsWith("jcifs.")) {
                Config.setProperty(str, servletConfig.getInitParameter(str));
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
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.servlet.http.HttpServlet
    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        UniAddress byName;
        NtlmPasswordAuthentication ntlmPasswordAuthentication;
        boolean z = this.enableBasic && (this.insecureBasic || httpServletRequest.isSecure());
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && (header.startsWith("NTLM ") || (z && header.startsWith("Basic ")))) {
            if (this.loadBalance) {
                byName = new UniAddress(NbtAddress.getByName(this.domainController, 28, null));
            } else {
                byName = UniAddress.getByName(this.domainController, true);
            }
            if (header.startsWith("NTLM ")) {
                ntlmPasswordAuthentication = NtlmSsp.authenticate(httpServletRequest, httpServletResponse, SmbSession.getChallenge(byName));
                if (ntlmPasswordAuthentication == null) {
                    return;
                }
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
                ntlmPasswordAuthentication = new NtlmPasswordAuthentication(substring3, substring, substring2);
            }
            try {
                SmbSession.logon(byName, ntlmPasswordAuthentication);
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("NtlmHttpAuth", ntlmPasswordAuthentication);
                session.setAttribute("ntlmdomain", ntlmPasswordAuthentication.getDomain());
                session.setAttribute("ntlmuser", ntlmPasswordAuthentication.getUsername());
            } catch (SmbAuthException unused) {
                httpServletResponse.setHeader("WWW-Authenticate", "NTLM");
                if (z) {
                    httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
                }
                httpServletResponse.setHeader("Connection", "close");
                httpServletResponse.setStatus(401);
                httpServletResponse.flushBuffer();
                return;
            }
        } else {
            HttpSession session2 = httpServletRequest.getSession(false);
            if (session2 == null || session2.getAttribute("NtlmHttpAuth") == null) {
                httpServletResponse.setHeader("WWW-Authenticate", "NTLM");
                if (z) {
                    httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
                }
                httpServletResponse.setStatus(401);
                httpServletResponse.flushBuffer();
                return;
            }
        }
        super.service(httpServletRequest, httpServletResponse);
    }
}
