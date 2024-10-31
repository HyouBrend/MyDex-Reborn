package jcifs.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jcifs.Config;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.util.LogStream;
import jcifs.util.MimeMap;

/* loaded from: classes2.dex */
public class NetworkExplorer extends HttpServlet {
    private static LogStream log = LogStream.getInstance();
    private boolean credentialsSupplied;
    private String defaultDomain;
    private boolean enableBasic;
    private boolean insecureBasic;
    private MimeMap mimeMap;
    private NtlmSsp ntlmSsp;
    private String realm;
    private String style;

    @Override // javax.servlet.GenericServlet
    public void init() throws ServletException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[1024];
        Config.setProperty("jcifs.smb.client.soTimeout", "600000");
        Config.setProperty("jcifs.smb.client.attrExpirationPeriod", "300000");
        Enumeration initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String str = (String) initParameterNames.nextElement();
            if (str.startsWith("jcifs.")) {
                Config.setProperty(str, getInitParameter(str));
            }
        }
        if (Config.getProperty("jcifs.smb.client.username") == null) {
            this.ntlmSsp = new NtlmSsp();
        } else {
            this.credentialsSupplied = true;
        }
        try {
            this.mimeMap = new MimeMap();
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("jcifs/http/ne.css");
            while (true) {
                int read = resourceAsStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    stringBuffer.append(new String(bArr, 0, read, "ISO8859_1"));
                }
            }
            this.style = stringBuffer.toString();
            this.enableBasic = Config.getBoolean("jcifs.http.enableBasic", false);
            this.insecureBasic = Config.getBoolean("jcifs.http.insecureBasic", false);
            String property = Config.getProperty("jcifs.http.basicRealm");
            this.realm = property;
            if (property == null) {
                this.realm = "jCIFS";
            }
            this.defaultDomain = Config.getProperty("jcifs.smb.client.domain");
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
        } catch (IOException e) {
            throw new ServletException(e.getMessage());
        }
    }

    protected void doFile(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SmbFile smbFile) throws IOException {
        String substring;
        byte[] bArr = new byte[8192];
        SmbFileInputStream smbFileInputStream = new SmbFileInputStream(smbFile);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String path = smbFile.getPath();
        httpServletResponse.setContentType("text/plain");
        int lastIndexOf = path.lastIndexOf(46);
        if (lastIndexOf > 0 && (substring = path.substring(lastIndexOf + 1)) != null && substring.length() > 1 && substring.length() < 6) {
            httpServletResponse.setContentType(this.mimeMap.getMimeType(substring));
        }
        httpServletResponse.setHeader("Content-Length", smbFile.length() + "");
        httpServletResponse.setHeader("Accept-Ranges", "Bytes");
        while (true) {
            int read = smbFileInputStream.read(bArr);
            if (read == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, read);
            }
        }
    }

    protected int compareNames(SmbFile smbFile, String str, SmbFile smbFile2) throws IOException {
        if (smbFile.isDirectory() != smbFile2.isDirectory()) {
            return smbFile.isDirectory() ? -1 : 1;
        }
        return str.compareToIgnoreCase(smbFile2.getName());
    }

    protected int compareSizes(SmbFile smbFile, String str, SmbFile smbFile2) throws IOException {
        if (smbFile.isDirectory() != smbFile2.isDirectory()) {
            return smbFile.isDirectory() ? -1 : 1;
        }
        if (smbFile.isDirectory()) {
            return str.compareToIgnoreCase(smbFile2.getName());
        }
        long length = smbFile.length() - smbFile2.length();
        if (length == 0) {
            return str.compareToIgnoreCase(smbFile2.getName());
        }
        return length > 0 ? -1 : 1;
    }

    protected int compareTypes(SmbFile smbFile, String str, SmbFile smbFile2) throws IOException {
        if (smbFile.isDirectory() != smbFile2.isDirectory()) {
            return smbFile.isDirectory() ? -1 : 1;
        }
        String name = smbFile2.getName();
        if (smbFile.isDirectory()) {
            return str.compareToIgnoreCase(name);
        }
        int lastIndexOf = str.lastIndexOf(46);
        String substring = lastIndexOf == -1 ? "" : str.substring(lastIndexOf + 1);
        int lastIndexOf2 = name.lastIndexOf(46);
        int compareToIgnoreCase = substring.compareToIgnoreCase(lastIndexOf2 != -1 ? name.substring(lastIndexOf2 + 1) : "");
        return compareToIgnoreCase == 0 ? str.compareToIgnoreCase(name) : compareToIgnoreCase;
    }

    protected int compareDates(SmbFile smbFile, String str, SmbFile smbFile2) throws IOException {
        if (smbFile.isDirectory() != smbFile2.isDirectory()) {
            return smbFile.isDirectory() ? -1 : 1;
        }
        if (smbFile.isDirectory()) {
            return str.compareToIgnoreCase(smbFile2.getName());
        }
        return smbFile.lastModified() > smbFile2.lastModified() ? -1 : 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x025b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doDirectory(javax.servlet.http.HttpServletRequest r19, javax.servlet.http.HttpServletResponse r20, jcifs.smb.SmbFile r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 923
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.http.NetworkExplorer.doDirectory(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, jcifs.smb.SmbFile):void");
    }

    private String parseServerAndShare(String str) {
        char[] cArr = new char[256];
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        while (i < length && str.charAt(i) == '/') {
            i++;
        }
        if (i == length) {
            return null;
        }
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '/') {
                break;
            }
            cArr[i2] = charAt;
            i++;
            i2++;
        }
        while (i < length && str.charAt(i) == '/') {
            i++;
        }
        if (i < length) {
            int i3 = i2 + 1;
            cArr[i2] = '/';
            while (true) {
                i2 = i3 + 1;
                int i4 = i + 1;
                char charAt2 = str.charAt(i);
                cArr[i3] = charAt2;
                if (i4 >= length || charAt2 == '/') {
                    break;
                }
                i3 = i2;
                i = i4;
            }
        }
        return new String(cArr, 0, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0066, code lost:
    
        if (r11.startsWith("Basic ") != false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b4 A[Catch: DfsReferral -> 0x01ba, SmbAuthException -> 0x01bc, TRY_LEAVE, TryCatch #5 {DfsReferral -> 0x01ba, SmbAuthException -> 0x01bc, blocks: (B:46:0x01b0, B:50:0x01b4), top: B:43:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0166 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // javax.servlet.http.HttpServlet
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doGet(javax.servlet.http.HttpServletRequest r21, javax.servlet.http.HttpServletResponse r22) throws java.io.IOException, javax.servlet.ServletException {
        /*
            Method dump skipped, instructions count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.http.NetworkExplorer.doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse):void");
    }
}
