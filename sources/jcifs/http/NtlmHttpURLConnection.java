package jcifs.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jcifs.Config;
import jcifs.ntlmssp.NtlmMessage;
import jcifs.ntlmssp.Type1Message;
import jcifs.ntlmssp.Type2Message;
import jcifs.ntlmssp.Type3Message;
import jcifs.util.Base64;

/* loaded from: classes2.dex */
public class NtlmHttpURLConnection extends HttpURLConnection {
    private static final String DEFAULT_DOMAIN;
    private String authMethod;
    private String authProperty;
    private ByteArrayOutputStream cachedOutput;
    private HttpURLConnection connection;
    private boolean handshakeComplete;
    private Map headerFields;
    private Map requestProperties;
    private static final int MAX_REDIRECTS = Integer.parseInt(System.getProperty("http.maxRedirects", "20"));
    private static final int LM_COMPATIBILITY = Config.getInt("jcifs.smb.lmCompatibility", 0);

    static {
        String property = System.getProperty("http.auth.ntlm.domain");
        if (property == null) {
            property = Type3Message.getDefaultDomain();
        }
        DEFAULT_DOMAIN = property;
    }

    public NtlmHttpURLConnection(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.connection = httpURLConnection;
        this.requestProperties = new HashMap();
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        if (this.connected) {
            return;
        }
        this.connection.connect();
        this.connected = true;
    }

    private void handshake() throws IOException {
        if (this.handshakeComplete) {
            return;
        }
        doHandshake();
        this.handshakeComplete = true;
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.connection.getURL();
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getContentLength();
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getContentType();
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getContentEncoding();
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getExpiration();
    }

    @Override // java.net.URLConnection
    public long getDate() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getDate();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getLastModified();
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getHeaderField(str);
    }

    private Map getHeaderFields0() {
        Map map = this.headerFields;
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap();
        String headerFieldKey = this.connection.getHeaderFieldKey(0);
        String headerField = this.connection.getHeaderField(0);
        int i = 1;
        while (true) {
            if (headerFieldKey == null && headerField == null) {
                break;
            }
            List list = (List) hashMap.get(headerFieldKey);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(headerFieldKey, list);
            }
            list.add(headerField);
            headerFieldKey = this.connection.getHeaderFieldKey(i);
            headerField = this.connection.getHeaderField(i);
            i++;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
        }
        Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
        this.headerFields = unmodifiableMap;
        return unmodifiableMap;
    }

    @Override // java.net.URLConnection
    public Map getHeaderFields() {
        Map map = this.headerFields;
        if (map != null) {
            return map;
        }
        try {
            handshake();
        } catch (IOException unused) {
        }
        return getHeaderFields0();
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getHeaderFieldDate(str, j);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getHeaderFieldKey(i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getHeaderField(i);
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getContent();
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getContent(clsArr);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.connection.getPermission();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getInputStream();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        try {
            connect();
        } catch (IOException unused) {
        }
        OutputStream outputStream = this.connection.getOutputStream();
        this.cachedOutput = new ByteArrayOutputStream();
        return new CacheStream(outputStream, this.cachedOutput);
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.connection.toString();
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean z) {
        this.connection.setDoInput(z);
        this.doInput = z;
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.connection.getDoInput();
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.connection.setDoOutput(z);
        this.doOutput = z;
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.connection.getDoOutput();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.connection.setAllowUserInteraction(z);
        this.allowUserInteraction = z;
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.connection.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.connection.setUseCaches(z);
        this.useCaches = z;
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.connection.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.connection.setIfModifiedSince(j);
        this.ifModifiedSince = j;
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        return this.connection.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.connection.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.connection.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        str.getClass();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        boolean z = false;
        Iterator it = this.requestProperties.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (str.equalsIgnoreCase((String) entry.getKey())) {
                entry.setValue(arrayList);
                z = true;
                break;
            }
        }
        if (!z) {
            this.requestProperties.put(str, arrayList);
        }
        this.connection.setRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        str.getClass();
        List list = null;
        Iterator it = this.requestProperties.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (str.equalsIgnoreCase((String) entry.getKey())) {
                list = (List) entry.getValue();
                list.add(str2);
                break;
            }
        }
        if (list == null) {
            list = new ArrayList();
            list.add(str2);
            this.requestProperties.put(str, list);
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            stringBuffer.append(it2.next());
            if (it2.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        this.connection.setRequestProperty(str, stringBuffer.toString());
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.connection.getRequestProperty(str);
    }

    @Override // java.net.URLConnection
    public Map getRequestProperties() {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.requestProperties.entrySet()) {
            hashMap.put(entry.getKey(), Collections.unmodifiableList((List) entry.getValue()));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.connection.setInstanceFollowRedirects(z);
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.connection.getInstanceFollowRedirects();
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.connection.setRequestMethod(str);
        this.method = str;
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.connection.getRequestMethod();
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getResponseCode();
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getResponseMessage();
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        this.connection.disconnect();
        this.handshakeComplete = false;
        this.connected = false;
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.connection.usingProxy();
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        try {
            handshake();
        } catch (IOException unused) {
        }
        return this.connection.getErrorStream();
    }

    private int parseResponseCode() throws IOException {
        try {
            String headerField = this.connection.getHeaderField(0);
            int indexOf = headerField.indexOf(32);
            while (headerField.charAt(indexOf) == ' ') {
                indexOf++;
            }
            return Integer.parseInt(headerField.substring(indexOf, indexOf + 3));
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c0, code lost:
    
        throw new java.io.IOException("Unable to negotiate NTLM authentication.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doHandshake() throws java.io.IOException {
        /*
            r12 = this;
            r12.connect()
            r0 = 0
            int r1 = r12.parseResponseCode()     // Catch: java.lang.Throwable -> Lc1
            r2 = 407(0x197, float:5.7E-43)
            r3 = 401(0x191, float:5.62E-43)
            if (r1 == r3) goto L13
            if (r1 == r2) goto L13
            r12.cachedOutput = r0
            return
        L13:
            jcifs.ntlmssp.NtlmMessage r1 = r12.attemptNegotiation(r1)     // Catch: java.lang.Throwable -> Lc1
            jcifs.ntlmssp.Type1Message r1 = (jcifs.ntlmssp.Type1Message) r1     // Catch: java.lang.Throwable -> Lc1
            if (r1 != 0) goto L1e
            r12.cachedOutput = r0
            return
        L1e:
            r4 = 0
        L1f:
            int r5 = jcifs.http.NtlmHttpURLConnection.MAX_REDIRECTS     // Catch: java.lang.Throwable -> Lc1
            if (r4 >= r5) goto Lb9
            java.net.HttpURLConnection r6 = r12.connection     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r7 = r12.authProperty     // Catch: java.lang.Throwable -> Lc1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc1
            r8.<init>()     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r9 = r12.authMethod     // Catch: java.lang.Throwable -> Lc1
            r8.append(r9)     // Catch: java.lang.Throwable -> Lc1
            r9 = 32
            r8.append(r9)     // Catch: java.lang.Throwable -> Lc1
            byte[] r10 = r1.toByteArray()     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r10 = jcifs.util.Base64.encode(r10)     // Catch: java.lang.Throwable -> Lc1
            r8.append(r10)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> Lc1
            r6.setRequestProperty(r7, r8)     // Catch: java.lang.Throwable -> Lc1
            java.net.HttpURLConnection r6 = r12.connection     // Catch: java.lang.Throwable -> Lc1
            r6.connect()     // Catch: java.lang.Throwable -> Lc1
            int r6 = r12.parseResponseCode()     // Catch: java.lang.Throwable -> Lc1
            if (r6 == r3) goto L58
            if (r6 == r2) goto L58
            r12.cachedOutput = r0
            return
        L58:
            jcifs.ntlmssp.NtlmMessage r6 = r12.attemptNegotiation(r6)     // Catch: java.lang.Throwable -> Lc1
            jcifs.ntlmssp.Type3Message r6 = (jcifs.ntlmssp.Type3Message) r6     // Catch: java.lang.Throwable -> Lc1
            if (r6 != 0) goto L63
            r12.cachedOutput = r0
            return
        L63:
            java.net.HttpURLConnection r7 = r12.connection     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r8 = r12.authProperty     // Catch: java.lang.Throwable -> Lc1
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc1
            r10.<init>()     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r11 = r12.authMethod     // Catch: java.lang.Throwable -> Lc1
            r10.append(r11)     // Catch: java.lang.Throwable -> Lc1
            r10.append(r9)     // Catch: java.lang.Throwable -> Lc1
            byte[] r6 = r6.toByteArray()     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r6 = jcifs.util.Base64.encode(r6)     // Catch: java.lang.Throwable -> Lc1
            r10.append(r6)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r6 = r10.toString()     // Catch: java.lang.Throwable -> Lc1
            r7.setRequestProperty(r8, r6)     // Catch: java.lang.Throwable -> Lc1
            java.net.HttpURLConnection r6 = r12.connection     // Catch: java.lang.Throwable -> Lc1
            r6.connect()     // Catch: java.lang.Throwable -> Lc1
            java.io.ByteArrayOutputStream r6 = r12.cachedOutput     // Catch: java.lang.Throwable -> Lc1
            if (r6 == 0) goto La1
            boolean r6 = r12.doOutput     // Catch: java.lang.Throwable -> Lc1
            if (r6 == 0) goto La1
            java.net.HttpURLConnection r6 = r12.connection     // Catch: java.lang.Throwable -> Lc1
            java.io.OutputStream r6 = r6.getOutputStream()     // Catch: java.lang.Throwable -> Lc1
            java.io.ByteArrayOutputStream r7 = r12.cachedOutput     // Catch: java.lang.Throwable -> Lc1
            r7.writeTo(r6)     // Catch: java.lang.Throwable -> Lc1
            r6.flush()     // Catch: java.lang.Throwable -> Lc1
        La1:
            int r6 = r12.parseResponseCode()     // Catch: java.lang.Throwable -> Lc1
            if (r6 == r3) goto Lac
            if (r6 == r2) goto Lac
            r12.cachedOutput = r0
            return
        Lac:
            int r4 = r4 + 1
            boolean r6 = r12.allowUserInteraction     // Catch: java.lang.Throwable -> Lc1
            if (r6 == 0) goto Lb9
            if (r4 >= r5) goto Lb9
            r12.reconnect()     // Catch: java.lang.Throwable -> Lc1
            goto L1f
        Lb9:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r2 = "Unable to negotiate NTLM authentication."
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Lc1
            throw r1     // Catch: java.lang.Throwable -> Lc1
        Lc1:
            r1 = move-exception
            r12.cachedOutput = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.http.NtlmHttpURLConnection.doHandshake():void");
    }

    private NtlmMessage attemptNegotiation(int i) throws IOException {
        String str;
        String str2;
        String str3;
        String str4;
        PasswordAuthentication requestPasswordAuthentication;
        this.authProperty = null;
        this.authMethod = null;
        InputStream errorStream = this.connection.getErrorStream();
        if (errorStream != null && errorStream.available() != 0) {
            do {
            } while (errorStream.read(new byte[1024], 0, 1024) != -1);
        }
        if (i == 401) {
            this.authProperty = "Authorization";
            str = "WWW-Authenticate";
        } else {
            this.authProperty = "Proxy-Authorization";
            str = "Proxy-Authenticate";
        }
        List list = (List) getHeaderFields0().get(str);
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String str5 = (String) it.next();
            if (str5.startsWith("NTLM")) {
                if (str5.length() == 4) {
                    this.authMethod = "NTLM";
                    break;
                }
                if (str5.indexOf(32) == 4) {
                    this.authMethod = "NTLM";
                    str2 = str5.substring(5).trim();
                    break;
                }
            } else if (!str5.startsWith("Negotiate")) {
                continue;
            } else {
                if (str5.length() == 9) {
                    this.authMethod = "Negotiate";
                    break;
                }
                if (str5.indexOf(32) == 9) {
                    this.authMethod = "Negotiate";
                    str2 = str5.substring(10).trim();
                    break;
                }
            }
        }
        str2 = null;
        if (this.authMethod == null) {
            return null;
        }
        Type2Message type2Message = str2 != null ? new Type2Message(Base64.decode(str2)) : null;
        reconnect();
        if (type2Message == null) {
            Type1Message type1Message = new Type1Message();
            if (LM_COMPATIBILITY <= 2) {
                return type1Message;
            }
            type1Message.setFlag(4, true);
            return type1Message;
        }
        String str6 = DEFAULT_DOMAIN;
        String defaultUser = Type3Message.getDefaultUser();
        String defaultPassword = Type3Message.getDefaultPassword();
        String userInfo = this.url.getUserInfo();
        if (userInfo != null) {
            String decode = URLDecoder.decode(userInfo);
            int indexOf = decode.indexOf(58);
            String substring = indexOf != -1 ? decode.substring(0, indexOf) : decode;
            if (indexOf != -1) {
                defaultPassword = decode.substring(indexOf + 1);
            }
            int indexOf2 = substring.indexOf(92);
            if (indexOf2 == -1) {
                indexOf2 = substring.indexOf(47);
            }
            if (indexOf2 != -1) {
                str6 = substring.substring(0, indexOf2);
            }
            defaultUser = indexOf2 != -1 ? substring.substring(indexOf2 + 1) : substring;
        }
        String str7 = str6;
        if (defaultUser == null) {
            if (!this.allowUserInteraction) {
                return null;
            }
            try {
                URL url = getURL();
                String protocol = url.getProtocol();
                int port = url.getPort();
                if (port == -1) {
                    port = "https".equalsIgnoreCase(protocol) ? jcifs.https.Handler.DEFAULT_HTTPS_PORT : 80;
                }
                requestPasswordAuthentication = Authenticator.requestPasswordAuthentication(null, port, protocol, "", this.authMethod);
            } catch (Exception unused) {
            }
            if (requestPasswordAuthentication == null) {
                return null;
            }
            defaultUser = requestPasswordAuthentication.getUserName();
            str4 = new String(requestPasswordAuthentication.getPassword());
            str3 = defaultUser;
            return new Type3Message(type2Message, str4, str7, str3, Type3Message.getDefaultWorkstation(), 0);
        }
        str3 = defaultUser;
        str4 = defaultPassword;
        return new Type3Message(type2Message, str4, str7, str3, Type3Message.getDefaultWorkstation(), 0);
    }

    private void reconnect() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.connection.getURL().openConnection();
        this.connection = httpURLConnection;
        httpURLConnection.setRequestMethod(this.method);
        this.headerFields = null;
        for (Map.Entry entry : this.requestProperties.entrySet()) {
            String str = (String) entry.getKey();
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next());
                if (it.hasNext()) {
                    stringBuffer.append(", ");
                }
            }
            this.connection.setRequestProperty(str, stringBuffer.toString());
        }
        this.connection.setAllowUserInteraction(this.allowUserInteraction);
        this.connection.setDoInput(this.doInput);
        this.connection.setDoOutput(this.doOutput);
        this.connection.setIfModifiedSince(this.ifModifiedSince);
        this.connection.setUseCaches(this.useCaches);
    }

    /* loaded from: classes2.dex */
    private static class CacheStream extends OutputStream {
        private final OutputStream collector;
        private final OutputStream stream;

        public CacheStream(OutputStream outputStream, OutputStream outputStream2) {
            this.stream = outputStream;
            this.collector = outputStream2;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.stream.close();
            this.collector.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            this.stream.flush();
            this.collector.flush();
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.stream.write(bArr);
            this.collector.write(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.stream.write(bArr, i, i2);
            this.collector.write(bArr, i, i2);
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.stream.write(i);
            this.collector.write(i);
        }
    }
}
