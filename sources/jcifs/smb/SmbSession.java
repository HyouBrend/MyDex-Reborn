package jcifs.smb;

import androidx.work.PeriodicWorkRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Vector;
import jcifs.Config;
import jcifs.UniAddress;
import jcifs.netbios.NbtAddress;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public final class SmbSession {
    static int dc_list_counter;
    static long dc_list_expiration;
    private UniAddress address;
    NtlmPasswordAuthentication auth;
    long expiration;
    private InetAddress localAddr;
    private int localPort;
    private int port;
    int uid;
    private static final String LOGON_SHARE = Config.getProperty("jcifs.smb.client.logonShare", null);
    private static final int LOOKUP_RESP_LIMIT = Config.getInt("jcifs.netbios.lookupRespLimit", 3);
    private static final String DOMAIN = Config.getProperty("jcifs.smb.client.domain", null);
    private static final String USERNAME = Config.getProperty("jcifs.smb.client.username", null);
    private static final int CACHE_POLICY = Config.getInt("jcifs.netbios.cachePolicy", 600) * 60;
    static NbtAddress[] dc_list = null;
    SmbTransport transport = null;
    String netbiosName = null;
    Vector trees = new Vector();
    int connectionState = 0;

    private static NtlmChallenge interrogate(NbtAddress nbtAddress) throws SmbException {
        UniAddress uniAddress = new UniAddress(nbtAddress);
        SmbTransport smbTransport = SmbTransport.getSmbTransport(uniAddress, 0);
        if (USERNAME == null) {
            smbTransport.connect();
            LogStream logStream = SmbTransport.log;
            if (LogStream.level >= 3) {
                SmbTransport.log.println("Default credentials (jcifs.smb.client.username/password) not specified. SMB signing may not work propertly.  Skipping DC interrogation.");
            }
        } else {
            smbTransport.getSmbSession(NtlmPasswordAuthentication.DEFAULT).getSmbTree(LOGON_SHARE, null).treeConnect(null, null);
        }
        return new NtlmChallenge(smbTransport.server.encryptionKey, uniAddress);
    }

    public static NtlmChallenge getChallengeForDomain() throws SmbException, UnknownHostException {
        String str = DOMAIN;
        if (str == null) {
            throw new SmbException("A domain was not specified");
        }
        synchronized (str) {
            long currentTimeMillis = System.currentTimeMillis();
            int i = 1;
            while (true) {
                if (dc_list_expiration < currentTimeMillis) {
                    NbtAddress[] allByName = NbtAddress.getAllByName(DOMAIN, 28, null, null);
                    dc_list_expiration = (CACHE_POLICY * 1000) + currentTimeMillis;
                    if (allByName != null && allByName.length > 0) {
                        dc_list = allByName;
                    } else {
                        dc_list_expiration = currentTimeMillis + PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
                        LogStream logStream = SmbTransport.log;
                        if (LogStream.level >= 2) {
                            SmbTransport.log.println("Failed to retrieve DC list from WINS");
                        }
                    }
                }
                int min = Math.min(dc_list.length, LOOKUP_RESP_LIMIT);
                for (int i2 = 0; i2 < min; i2++) {
                    int i3 = dc_list_counter;
                    dc_list_counter = i3 + 1;
                    int i4 = i3 % min;
                    NbtAddress nbtAddress = dc_list[i4];
                    if (nbtAddress != null) {
                        try {
                            return interrogate(nbtAddress);
                        } catch (SmbException e) {
                            LogStream logStream2 = SmbTransport.log;
                            if (LogStream.level >= 2) {
                                SmbTransport.log.println("Failed validate DC: " + dc_list[i4]);
                                LogStream logStream3 = SmbTransport.log;
                                if (LogStream.level > 2) {
                                    e.printStackTrace(SmbTransport.log);
                                }
                            }
                            dc_list[i4] = null;
                        }
                    }
                }
                dc_list_expiration = 0L;
                int i5 = i - 1;
                if (i <= 0) {
                    dc_list_expiration = currentTimeMillis + PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
                    throw new UnknownHostException("Failed to negotiate with a suitable domain controller for " + DOMAIN);
                }
                i = i5;
            }
        }
    }

    public static byte[] getChallenge(UniAddress uniAddress) throws SmbException, UnknownHostException {
        return getChallenge(uniAddress, 0);
    }

    public static byte[] getChallenge(UniAddress uniAddress, int i) throws SmbException, UnknownHostException {
        SmbTransport smbTransport = SmbTransport.getSmbTransport(uniAddress, i);
        smbTransport.connect();
        return smbTransport.server.encryptionKey;
    }

    public static void logon(UniAddress uniAddress, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbException {
        logon(uniAddress, 0, ntlmPasswordAuthentication);
    }

    public static void logon(UniAddress uniAddress, int i, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbException {
        SmbSession smbSession = SmbTransport.getSmbTransport(uniAddress, i).getSmbSession(ntlmPasswordAuthentication);
        String str = LOGON_SHARE;
        SmbTree smbTree = smbSession.getSmbTree(str, null);
        if (str == null) {
            smbTree.treeConnect(null, null);
        } else {
            smbTree.send(new Trans2FindFirst2("\\", "*", 16), new Trans2FindFirst2Response());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbSession(UniAddress uniAddress, int i, InetAddress inetAddress, int i2, NtlmPasswordAuthentication ntlmPasswordAuthentication) {
        this.address = uniAddress;
        this.port = i;
        this.localAddr = inetAddress;
        this.localPort = i2;
        this.auth = ntlmPasswordAuthentication;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized SmbTree getSmbTree(String str, String str2) {
        if (str == null) {
            str = "IPC$";
        }
        Enumeration elements = this.trees.elements();
        while (elements.hasMoreElements()) {
            SmbTree smbTree = (SmbTree) elements.nextElement();
            if (smbTree.matches(str, str2)) {
                return smbTree;
            }
        }
        SmbTree smbTree2 = new SmbTree(this, str, str2);
        this.trees.addElement(smbTree2);
        return smbTree2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(NtlmPasswordAuthentication ntlmPasswordAuthentication) {
        NtlmPasswordAuthentication ntlmPasswordAuthentication2 = this.auth;
        return ntlmPasswordAuthentication2 == ntlmPasswordAuthentication || ntlmPasswordAuthentication2.equals(ntlmPasswordAuthentication);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized SmbTransport transport() {
        if (this.transport == null) {
            this.transport = SmbTransport.getSmbTransport(this.address, this.port, this.localAddr, this.localPort, null);
        }
        return this.transport;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void send(ServerMessageBlock serverMessageBlock, ServerMessageBlock serverMessageBlock2) throws SmbException {
        synchronized (transport()) {
            if (serverMessageBlock2 != null) {
                serverMessageBlock2.received = false;
            }
            this.expiration = System.currentTimeMillis() + SmbTransport.SO_TIMEOUT;
            sessionSetup(serverMessageBlock, serverMessageBlock2);
            if (serverMessageBlock2 == null || !serverMessageBlock2.received) {
                if (serverMessageBlock instanceof SmbComTreeConnectAndX) {
                    SmbComTreeConnectAndX smbComTreeConnectAndX = (SmbComTreeConnectAndX) serverMessageBlock;
                    if (this.netbiosName != null && smbComTreeConnectAndX.path.endsWith("\\IPC$")) {
                        smbComTreeConnectAndX.path = "\\\\" + this.netbiosName + "\\IPC$";
                    }
                }
                serverMessageBlock.uid = this.uid;
                serverMessageBlock.auth = this.auth;
                try {
                    this.transport.send(serverMessageBlock, serverMessageBlock2);
                } catch (SmbException e) {
                    if (serverMessageBlock instanceof SmbComTreeConnectAndX) {
                        logoff(true);
                    }
                    serverMessageBlock.digest = null;
                    throw e;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0135, code lost:
    
        if (r16.auth == jcifs.smb.NtlmPasswordAuthentication.ANONYMOUS) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x013f, code lost:
    
        if (r16.transport.hasCapability(Integer.MIN_VALUE) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0141, code lost:
    
        r10 = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0149, code lost:
    
        r10 = new jcifs.smb.SmbComSessionSetupAndX(r16, r17, r16.auth);
        r13 = new jcifs.smb.SmbComSessionSetupAndXResponse(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0161, code lost:
    
        if (r16.transport.isSignatureSetupRequired(r16.auth) == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0167, code lost:
    
        if (r16.auth.hashesExternal == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x016d, code lost:
    
        if (jcifs.smb.NtlmPasswordAuthentication.DEFAULT_PASSWORD == "") goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x016f, code lost:
    
        r16.transport.getSmbSession(jcifs.smb.NtlmPasswordAuthentication.DEFAULT).getSmbTree(jcifs.smb.SmbSession.LOGON_SHARE, null).treeConnect(null, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0181, code lost:
    
        r10.digest = new jcifs.smb.SigningDigest(r16.auth.getSigningKey(r16.transport.server.encryptionKey), false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0194, code lost:
    
        r10.auth = r16.auth;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0198, code lost:
    
        r16.transport.send(r10, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x01c6, code lost:
    
        r16.uid = r13.uid;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01cc, code lost:
    
        if (r10.digest == null) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01ce, code lost:
    
        r16.transport.digest = r10.digest;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01d4, code lost:
    
        r16.connectionState = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01e7, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01e8, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x019e, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01e9, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01f2, code lost:
    
        r16.transport.notifyAll();
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01f7, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x01eb, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01ec, code lost:
    
        logoff(true);
        r16.connectionState = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01f1, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0028, code lost:
    
        r16.connectionState = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x002b, code lost:
    
        r16.transport.connect();
        r6 = jcifs.smb.SmbTransport.log;
        r7 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0035, code lost:
    
        if (jcifs.util.LogStream.level < 4) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0037, code lost:
    
        jcifs.smb.SmbTransport.log.println("sessionSetup: accountName=" + r16.auth.username + ",primaryDomain=" + r16.auth.domain);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005d, code lost:
    
        r16.uid = 0;
        r6 = 10;
        r9 = r0;
        r0 = null;
        r11 = null;
        r10 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
    
        if (r10 == r6) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006e, code lost:
    
        if (r10 != 20) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0070, code lost:
    
        if (r11 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0077, code lost:
    
        if ((r16.transport.flags2 & r7) == 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0079, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007c, code lost:
    
        r11 = new jcifs.smb.NtlmContext(r16.auth, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007b, code lost:
    
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0084, code lost:
    
        r13 = jcifs.smb.SmbTransport.log;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
    
        if (jcifs.util.LogStream.level < r7) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008a, code lost:
    
        jcifs.smb.SmbTransport.log.println(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0093, code lost:
    
        if (r11.isEstablished() == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0095, code lost:
    
        r16.netbiosName = r11.getNetbiosName();
        r16.connectionState = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a1, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01d8, code lost:
    
        if (r10 != 0) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01e1, code lost:
    
        r6 = 10;
        r7 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01e0, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a4, code lost:
    
        r9 = r11.initSecContext(r9, 0, r9.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00a9, code lost:
    
        if (r9 == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ab, code lost:
    
        r13 = new jcifs.smb.SmbComSessionSetupAndX(r16, null, r9);
        r9 = new jcifs.smb.SmbComSessionSetupAndXResponse(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00bd, code lost:
    
        if (r16.transport.isSignatureSetupRequired(r16.auth) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00bf, code lost:
    
        r14 = r11.getSigningKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00c3, code lost:
    
        if (r14 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00c5, code lost:
    
        r13.digest = new jcifs.smb.SigningDigest(r14, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00cc, code lost:
    
        r13.uid = r16.uid;
        r16.uid = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d2, code lost:
    
        r16.transport.send(r13, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00f7, code lost:
    
        r16.uid = r9.uid;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00fd, code lost:
    
        if (r13.digest == null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ff, code lost:
    
        r16.transport.digest = r13.digest;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0105, code lost:
    
        r9 = r9.blob;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0109, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x010a, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00d8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00d9, code lost:
    
        r16.transport.disconnect(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0111, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0112, code lost:
    
        r16.transport.disconnect(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0117, code lost:
    
        r16.uid = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0119, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0130, code lost:
    
        throw new jcifs.smb.SmbException("Unexpected session setup state: " + r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void sessionSetup(jcifs.smb.ServerMessageBlock r17, jcifs.smb.ServerMessageBlock r18) throws jcifs.smb.SmbException {
        /*
            Method dump skipped, instructions count: 507
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbSession.sessionSetup(jcifs.smb.ServerMessageBlock, jcifs.smb.ServerMessageBlock):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logoff(boolean z) {
        synchronized (transport()) {
            if (this.connectionState != 2) {
                return;
            }
            this.connectionState = 3;
            this.netbiosName = null;
            Enumeration elements = this.trees.elements();
            while (elements.hasMoreElements()) {
                ((SmbTree) elements.nextElement()).treeDisconnect(z);
            }
            if (!z && this.transport.server.security != 0) {
                SmbComLogoffAndX smbComLogoffAndX = new SmbComLogoffAndX(null);
                smbComLogoffAndX.uid = this.uid;
                try {
                    this.transport.send(smbComLogoffAndX, null);
                } catch (SmbException unused) {
                }
                this.uid = 0;
            }
            this.connectionState = 0;
            this.transport.notifyAll();
        }
    }

    public String toString() {
        return "SmbSession[accountName=" + this.auth.username + ",primaryDomain=" + this.auth.domain + ",uid=" + this.uid + ",connectionState=" + this.connectionState + "]";
    }
}
