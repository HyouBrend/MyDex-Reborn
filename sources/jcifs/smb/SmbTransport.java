package jcifs.smb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import jcifs.UniAddress;
import jcifs.netbios.Name;
import jcifs.netbios.NbtAddress;
import jcifs.netbios.NbtException;
import jcifs.netbios.SessionRequestPacket;
import jcifs.util.Encdec;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;
import jcifs.util.transport.Request;
import jcifs.util.transport.Response;
import jcifs.util.transport.Transport;
import jcifs.util.transport.TransportException;
import kotlin.UByte;
import kotlin.UShort;

/* loaded from: classes2.dex */
public class SmbTransport extends Transport implements SmbConstants {
    UniAddress address;
    InputStream in;
    InetAddress localAddr;
    int localPort;
    int mid;
    OutputStream out;
    int port;
    Socket socket;
    static final byte[] BUF = new byte[65535];
    static final SmbComNegotiate NEGOTIATE_REQUEST = new SmbComNegotiate();
    static LogStream log = LogStream.getInstance();
    static HashMap dfsRoots = null;
    byte[] sbuf = new byte[512];
    SmbComBlankResponse key = new SmbComBlankResponse();
    long sessionExpiration = System.currentTimeMillis() + SO_TIMEOUT;
    LinkedList referrals = new LinkedList();
    SigningDigest digest = null;
    LinkedList sessions = new LinkedList();
    ServerData server = new ServerData();
    int flags2 = FLAGS2;
    int maxMpxCount = MAX_MPX_COUNT;
    int snd_buf_size = SND_BUF_SIZE;
    int rcv_buf_size = RCV_BUF_SIZE;
    int capabilities = CAPABILITIES;
    int sessionKey = 0;
    boolean useUnicode = USE_UNICODE;
    String tconHostName = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized SmbTransport getSmbTransport(UniAddress uniAddress, int i) {
        SmbTransport smbTransport;
        synchronized (SmbTransport.class) {
            smbTransport = getSmbTransport(uniAddress, i, LADDR, LPORT, null);
        }
        return smbTransport;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized SmbTransport getSmbTransport(UniAddress uniAddress, int i, InetAddress inetAddress, int i2, String str) {
        synchronized (SmbTransport.class) {
            synchronized (CONNECTIONS) {
                if (SSN_LIMIT != 1) {
                    ListIterator listIterator = CONNECTIONS.listIterator();
                    while (listIterator.hasNext()) {
                        SmbTransport smbTransport = (SmbTransport) listIterator.next();
                        if (smbTransport.matches(uniAddress, i, inetAddress, i2, str) && (SSN_LIMIT == 0 || smbTransport.sessions.size() < SSN_LIMIT)) {
                            return smbTransport;
                        }
                    }
                }
                SmbTransport smbTransport2 = new SmbTransport(uniAddress, i, inetAddress, i2);
                CONNECTIONS.add(0, smbTransport2);
                return smbTransport2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ServerData {
        int capabilities;
        boolean encryptedPasswords;
        byte[] encryptionKey;
        int encryptionKeyLength;
        byte flags;
        int flags2;
        byte[] guid;
        int maxBufferSize;
        int maxMpxCount;
        int maxNumberVcs;
        int maxRawSize;
        String oemDomainName;
        int security;
        int securityMode;
        long serverTime;
        int serverTimeZone;
        int sessionKey;
        boolean signaturesEnabled;
        boolean signaturesRequired;

        ServerData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbTransport(UniAddress uniAddress, int i, InetAddress inetAddress, int i2) {
        this.address = uniAddress;
        this.port = i;
        this.localAddr = inetAddress;
        this.localPort = i2;
    }

    synchronized SmbSession getSmbSession() {
        return getSmbSession(new NtlmPasswordAuthentication(null, null, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized SmbSession getSmbSession(NtlmPasswordAuthentication ntlmPasswordAuthentication) {
        ListIterator listIterator = this.sessions.listIterator();
        while (listIterator.hasNext()) {
            SmbSession smbSession = (SmbSession) listIterator.next();
            if (smbSession.matches(ntlmPasswordAuthentication)) {
                smbSession.auth = ntlmPasswordAuthentication;
                return smbSession;
            }
        }
        if (SO_TIMEOUT > 0) {
            long j = this.sessionExpiration;
            long currentTimeMillis = System.currentTimeMillis();
            if (j < currentTimeMillis) {
                this.sessionExpiration = SO_TIMEOUT + currentTimeMillis;
                ListIterator listIterator2 = this.sessions.listIterator();
                while (listIterator2.hasNext()) {
                    SmbSession smbSession2 = (SmbSession) listIterator2.next();
                    if (smbSession2.expiration < currentTimeMillis) {
                        smbSession2.logoff(false);
                    }
                }
            }
        }
        SmbSession smbSession3 = new SmbSession(this.address, this.port, this.localAddr, this.localPort, ntlmPasswordAuthentication);
        smbSession3.transport = this;
        this.sessions.add(smbSession3);
        return smbSession3;
    }

    boolean matches(UniAddress uniAddress, int i, InetAddress inetAddress, int i2, String str) {
        InetAddress inetAddress2;
        int i3;
        if (str == null) {
            str = uniAddress.getHostName();
        }
        String str2 = this.tconHostName;
        return (str2 == null || str.equalsIgnoreCase(str2)) && uniAddress.equals(this.address) && (i == 0 || i == (i3 = this.port) || (i == 445 && i3 == 139)) && ((inetAddress == (inetAddress2 = this.localAddr) || (inetAddress != null && inetAddress.equals(inetAddress2))) && i2 == this.localPort);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasCapability(int i) throws SmbException {
        try {
            connect(RESPONSE_TIMEOUT);
            return (this.capabilities & i) == i;
        } catch (IOException e) {
            throw new SmbException(e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSignatureSetupRequired(NtlmPasswordAuthentication ntlmPasswordAuthentication) {
        return ((this.flags2 & 4) == 0 || this.digest != null || ntlmPasswordAuthentication == NtlmPasswordAuthentication.NULL || NtlmPasswordAuthentication.NULL.equals(ntlmPasswordAuthentication)) ? false : true;
    }

    void ssn139() throws IOException {
        String nextCalledName;
        Name name = new Name(this.address.firstCalledName(), 32, null);
        do {
            Socket socket = new Socket();
            this.socket = socket;
            if (this.localAddr != null) {
                socket.bind(new InetSocketAddress(this.localAddr, this.localPort));
            }
            this.socket.connect(new InetSocketAddress(this.address.getHostAddress(), 139), CONN_TIMEOUT);
            this.socket.setSoTimeout(SO_TIMEOUT);
            this.out = this.socket.getOutputStream();
            this.in = this.socket.getInputStream();
            SessionRequestPacket sessionRequestPacket = new SessionRequestPacket(name, NbtAddress.getLocalName());
            OutputStream outputStream = this.out;
            byte[] bArr = this.sbuf;
            outputStream.write(bArr, 0, sessionRequestPacket.writeWireFormat(bArr, 0));
            if (readn(this.in, this.sbuf, 0, 4) < 4) {
                try {
                    this.socket.close();
                } catch (IOException unused) {
                }
                throw new SmbException("EOF during NetBIOS session request");
            }
            int i = this.sbuf[0] & UByte.MAX_VALUE;
            if (i == -1) {
                disconnect(true);
                throw new NbtException(2, -1);
            }
            if (i == 130) {
                if (LogStream.level >= 4) {
                    log.println("session established ok with " + this.address);
                    return;
                }
                return;
            }
            if (i == 131) {
                int read = this.in.read() & 255;
                if (read == 128 || read == 130) {
                    this.socket.close();
                    nextCalledName = this.address.nextCalledName();
                    name.name = nextCalledName;
                } else {
                    disconnect(true);
                    throw new NbtException(2, read);
                }
            } else {
                disconnect(true);
                throw new NbtException(2, 0);
            }
        } while (nextCalledName != null);
        throw new IOException("Failed to establish session with " + this.address);
    }

    private void negotiate(int i, ServerMessageBlock serverMessageBlock) throws IOException {
        synchronized (this.sbuf) {
            try {
                if (i == 139) {
                    ssn139();
                } else {
                    if (i == 0) {
                        i = SmbConstants.DEFAULT_PORT;
                    }
                    Socket socket = new Socket();
                    this.socket = socket;
                    if (this.localAddr != null) {
                        socket.bind(new InetSocketAddress(this.localAddr, this.localPort));
                    }
                    this.socket.connect(new InetSocketAddress(this.address.getHostAddress(), i), CONN_TIMEOUT);
                    this.socket.setSoTimeout(SO_TIMEOUT);
                    this.out = this.socket.getOutputStream();
                    this.in = this.socket.getInputStream();
                }
                int i2 = this.mid + 1;
                this.mid = i2;
                if (i2 == 32000) {
                    this.mid = 1;
                }
                SmbComNegotiate smbComNegotiate = NEGOTIATE_REQUEST;
                smbComNegotiate.mid = this.mid;
                int encode = smbComNegotiate.encode(this.sbuf, 4);
                Encdec.enc_uint32be(encode & 65535, this.sbuf, 0);
                if (LogStream.level >= 4) {
                    log.println(smbComNegotiate);
                    if (LogStream.level >= 6) {
                        Hexdump.hexdump(log, this.sbuf, 4, encode);
                    }
                }
                this.out.write(this.sbuf, 0, encode + 4);
                this.out.flush();
                if (peekKey() == null) {
                    throw new IOException("transport closed in negotiate");
                }
                int dec_uint16be = Encdec.dec_uint16be(this.sbuf, 2) & UShort.MAX_VALUE;
                if (dec_uint16be >= 33) {
                    int i3 = dec_uint16be + 4;
                    byte[] bArr = this.sbuf;
                    if (i3 <= bArr.length) {
                        readn(this.in, bArr, 36, dec_uint16be - 32);
                        serverMessageBlock.decode(this.sbuf, 4);
                        if (LogStream.level >= 4) {
                            log.println(serverMessageBlock);
                            if (LogStream.level >= 6) {
                                Hexdump.hexdump(log, this.sbuf, 4, encode);
                            }
                        }
                    }
                }
                throw new IOException("Invalid payload size: " + dec_uint16be);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void connect() throws SmbException {
        try {
            super.connect(RESPONSE_TIMEOUT);
        } catch (TransportException e) {
            throw new SmbException("Failed to connect: " + this.address, e);
        }
    }

    @Override // jcifs.util.transport.Transport
    protected void doConnect() throws IOException {
        SmbComNegotiateResponse smbComNegotiateResponse = new SmbComNegotiateResponse(this.server);
        int i = 139;
        try {
            negotiate(this.port, smbComNegotiateResponse);
        } catch (ConnectException unused) {
            int i2 = this.port;
            if (i2 != 0 && i2 != 445) {
                i = SmbConstants.DEFAULT_PORT;
            }
            this.port = i;
            negotiate(i, smbComNegotiateResponse);
        } catch (NoRouteToHostException unused2) {
            int i3 = this.port;
            if (i3 != 0 && i3 != 445) {
                i = SmbConstants.DEFAULT_PORT;
            }
            this.port = i;
            negotiate(i, smbComNegotiateResponse);
        }
        if (smbComNegotiateResponse.dialectIndex > 10) {
            throw new SmbException("This client does not support the negotiated dialect.");
        }
        if ((this.server.capabilities & Integer.MIN_VALUE) != Integer.MIN_VALUE && this.server.encryptionKeyLength != 8 && LM_COMPATIBILITY == 0) {
            throw new SmbException("Unexpected encryption key length: " + this.server.encryptionKeyLength);
        }
        this.tconHostName = this.address.getHostName();
        if (this.server.signaturesRequired || (this.server.signaturesEnabled && SIGNPREF)) {
            this.flags2 |= 4;
        } else {
            this.flags2 &= 65531;
        }
        int min = Math.min(this.maxMpxCount, this.server.maxMpxCount);
        this.maxMpxCount = min;
        if (min < 1) {
            this.maxMpxCount = 1;
        }
        this.snd_buf_size = Math.min(this.snd_buf_size, this.server.maxBufferSize);
        this.capabilities &= this.server.capabilities;
        if ((this.server.capabilities & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            this.capabilities |= Integer.MIN_VALUE;
        }
        if ((this.capabilities & 4) == 0) {
            if (FORCE_UNICODE) {
                this.capabilities |= 4;
            } else {
                this.useUnicode = false;
                this.flags2 &= 32767;
            }
        }
    }

    @Override // jcifs.util.transport.Transport
    protected void doDisconnect(boolean z) throws IOException {
        ListIterator listIterator = this.sessions.listIterator();
        while (true) {
            try {
                if (listIterator.hasNext()) {
                    ((SmbSession) listIterator.next()).logoff(z);
                } else {
                    this.socket.shutdownOutput();
                    this.out.close();
                    this.in.close();
                    this.socket.close();
                    return;
                }
            } finally {
                this.digest = null;
                this.socket = null;
                this.tconHostName = null;
            }
        }
    }

    @Override // jcifs.util.transport.Transport
    protected void makeKey(Request request) throws IOException {
        int i = this.mid + 1;
        this.mid = i;
        if (i == 32000) {
            this.mid = 1;
        }
        ((ServerMessageBlock) request).mid = this.mid;
    }

    @Override // jcifs.util.transport.Transport
    protected Request peekKey() throws IOException {
        while (readn(this.in, this.sbuf, 0, 4) >= 4) {
            byte[] bArr = this.sbuf;
            if (bArr[0] != -123) {
                if (readn(this.in, bArr, 4, 32) < 32) {
                    return null;
                }
                if (LogStream.level >= 4) {
                    log.println("New data read: " + this);
                    Hexdump.hexdump(log, this.sbuf, 4, 32);
                }
                while (true) {
                    byte[] bArr2 = this.sbuf;
                    if (bArr2[0] != 0 || bArr2[1] != 0 || bArr2[4] != -1 || bArr2[5] != 83 || bArr2[6] != 77 || bArr2[7] != 66) {
                        int i = 0;
                        while (i < 35) {
                            byte[] bArr3 = this.sbuf;
                            int i2 = i + 1;
                            bArr3[i] = bArr3[i2];
                            i = i2;
                        }
                        int read = this.in.read();
                        if (read == -1) {
                            return null;
                        }
                        this.sbuf[35] = (byte) read;
                    } else {
                        this.key.mid = Encdec.dec_uint16le(bArr2, 34) & UShort.MAX_VALUE;
                        return this.key;
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
    
        if (jcifs.util.LogStream.level < 6) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
    
        jcifs.util.Hexdump.hexdump(jcifs.smb.SmbTransport.log, jcifs.smb.SmbTransport.BUF, 4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
    
        r5.out.write(jcifs.smb.SmbTransport.BUF, 0, r2 + 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0014, code lost:
    
        if (jcifs.util.LogStream.level >= 4) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
    
        jcifs.smb.SmbTransport.log.println(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
    
        if ((r6 instanceof jcifs.smb.AndXServerMessageBlock) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
    
        r6 = ((jcifs.smb.AndXServerMessageBlock) r6).andx;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
    
        if (r6 != null) goto L23;
     */
    @Override // jcifs.util.transport.Transport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doSend(jcifs.util.transport.Request r6) throws java.io.IOException {
        /*
            r5 = this;
            byte[] r0 = jcifs.smb.SmbTransport.BUF
            monitor-enter(r0)
            jcifs.smb.ServerMessageBlock r6 = (jcifs.smb.ServerMessageBlock) r6     // Catch: java.lang.Throwable -> L3b
            r1 = 4
            int r2 = r6.encode(r0, r1)     // Catch: java.lang.Throwable -> L3b
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            r4 = 0
            jcifs.util.Encdec.enc_uint32be(r3, r0, r4)     // Catch: java.lang.Throwable -> L3b
            int r3 = jcifs.util.LogStream.level     // Catch: java.lang.Throwable -> L3b
            if (r3 < r1) goto L31
        L16:
            jcifs.util.LogStream r3 = jcifs.smb.SmbTransport.log     // Catch: java.lang.Throwable -> L3b
            r3.println(r6)     // Catch: java.lang.Throwable -> L3b
            boolean r3 = r6 instanceof jcifs.smb.AndXServerMessageBlock     // Catch: java.lang.Throwable -> L3b
            if (r3 == 0) goto L25
            jcifs.smb.AndXServerMessageBlock r6 = (jcifs.smb.AndXServerMessageBlock) r6     // Catch: java.lang.Throwable -> L3b
            jcifs.smb.ServerMessageBlock r6 = r6.andx     // Catch: java.lang.Throwable -> L3b
            if (r6 != 0) goto L16
        L25:
            int r6 = jcifs.util.LogStream.level     // Catch: java.lang.Throwable -> L3b
            r3 = 6
            if (r6 < r3) goto L31
            jcifs.util.LogStream r6 = jcifs.smb.SmbTransport.log     // Catch: java.lang.Throwable -> L3b
            byte[] r3 = jcifs.smb.SmbTransport.BUF     // Catch: java.lang.Throwable -> L3b
            jcifs.util.Hexdump.hexdump(r6, r3, r1, r2)     // Catch: java.lang.Throwable -> L3b
        L31:
            java.io.OutputStream r6 = r5.out     // Catch: java.lang.Throwable -> L3b
            byte[] r3 = jcifs.smb.SmbTransport.BUF     // Catch: java.lang.Throwable -> L3b
            int r2 = r2 + r1
            r6.write(r3, r4, r2)     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3b
            return
        L3b:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3b
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbTransport.doSend(jcifs.util.transport.Request):void");
    }

    protected void doSend0(Request request) throws IOException {
        try {
            doSend(request);
        } catch (IOException e) {
            if (LogStream.level > 2) {
                e.printStackTrace(log);
            }
            try {
                disconnect(true);
            } catch (IOException e2) {
                e2.printStackTrace(log);
            }
            throw e;
        }
    }

    @Override // jcifs.util.transport.Transport
    protected void doRecv(Response response) throws IOException {
        ServerMessageBlock serverMessageBlock = (ServerMessageBlock) response;
        serverMessageBlock.useUnicode = this.useUnicode;
        serverMessageBlock.extendedSecurity = (this.capabilities & Integer.MIN_VALUE) == Integer.MIN_VALUE;
        byte[] bArr = BUF;
        synchronized (bArr) {
            System.arraycopy(this.sbuf, 0, bArr, 0, 36);
            int dec_uint16be = Encdec.dec_uint16be(bArr, 2) & UShort.MAX_VALUE;
            if (dec_uint16be < 33 || dec_uint16be + 4 > this.rcv_buf_size) {
                throw new IOException("Invalid payload size: " + dec_uint16be);
            }
            int dec_uint32le = Encdec.dec_uint32le(bArr, 9) & (-1);
            if (serverMessageBlock.command == 46 && (dec_uint32le == 0 || dec_uint32le == -2147483643)) {
                SmbComReadAndXResponse smbComReadAndXResponse = (SmbComReadAndXResponse) serverMessageBlock;
                readn(this.in, bArr, 36, 27);
                serverMessageBlock.decode(bArr, 4);
                int i = smbComReadAndXResponse.dataOffset - 59;
                if (smbComReadAndXResponse.byteCount > 0 && i > 0 && i < 4) {
                    readn(this.in, bArr, 63, i);
                }
                if (smbComReadAndXResponse.dataLength > 0) {
                    readn(this.in, smbComReadAndXResponse.b, smbComReadAndXResponse.off, smbComReadAndXResponse.dataLength);
                }
            } else {
                readn(this.in, bArr, 36, dec_uint16be - 32);
                serverMessageBlock.decode(bArr, 4);
                if (serverMessageBlock instanceof SmbComTransactionResponse) {
                    ((SmbComTransactionResponse) serverMessageBlock).nextElement();
                }
            }
            if (this.digest != null && serverMessageBlock.errorCode == 0) {
                this.digest.verify(bArr, 4, serverMessageBlock);
            }
            if (LogStream.level >= 4) {
                log.println(response);
                if (LogStream.level >= 6) {
                    Hexdump.hexdump(log, bArr, 4, dec_uint16be);
                }
            }
        }
    }

    @Override // jcifs.util.transport.Transport
    protected void doSkip() throws IOException {
        int dec_uint16be = Encdec.dec_uint16be(this.sbuf, 2) & UShort.MAX_VALUE;
        if (dec_uint16be < 33 || dec_uint16be + 4 > this.rcv_buf_size) {
            this.in.skip(r0.available());
        } else {
            this.in.skip(dec_uint16be - 32);
        }
    }

    void checkStatus(ServerMessageBlock serverMessageBlock, ServerMessageBlock serverMessageBlock2) throws SmbException {
        serverMessageBlock2.errorCode = SmbException.getStatusByCode(serverMessageBlock2.errorCode);
        int i = serverMessageBlock2.errorCode;
        if (i != 0) {
            switch (i) {
                case -2147483643:
                case NtStatus.NT_STATUS_MORE_PROCESSING_REQUIRED /* -1073741802 */:
                case 0:
                    break;
                case NtStatus.NT_STATUS_ACCESS_DENIED /* -1073741790 */:
                case NtStatus.NT_STATUS_WRONG_PASSWORD /* -1073741718 */:
                case NtStatus.NT_STATUS_TRUSTED_DOMAIN_FAILURE /* -1073741428 */:
                case NtStatus.NT_STATUS_ACCOUNT_LOCKED_OUT /* -1073741260 */:
                    break;
                case NtStatus.NT_STATUS_PATH_NOT_COVERED /* -1073741225 */:
                    if (serverMessageBlock.auth == null) {
                        throw new SmbException(serverMessageBlock2.errorCode, (Throwable) null);
                    }
                    DfsReferral dfsReferrals = getDfsReferrals(serverMessageBlock.auth, serverMessageBlock.path, 1);
                    if (dfsReferrals == null) {
                        throw new SmbException(serverMessageBlock2.errorCode, (Throwable) null);
                    }
                    SmbFile.dfs.insert(serverMessageBlock.path, dfsReferrals);
                    throw dfsReferrals;
                default:
                    switch (i) {
                        case NtStatus.NT_STATUS_LOGON_FAILURE /* -1073741715 */:
                        case NtStatus.NT_STATUS_ACCOUNT_RESTRICTION /* -1073741714 */:
                        case NtStatus.NT_STATUS_INVALID_LOGON_HOURS /* -1073741713 */:
                        case NtStatus.NT_STATUS_INVALID_WORKSTATION /* -1073741712 */:
                        case NtStatus.NT_STATUS_PASSWORD_EXPIRED /* -1073741711 */:
                        case NtStatus.NT_STATUS_ACCOUNT_DISABLED /* -1073741710 */:
                            break;
                        default:
                            throw new SmbException(serverMessageBlock2.errorCode, (Throwable) null);
                    }
            }
            throw new SmbAuthException(serverMessageBlock2.errorCode);
        }
        if (serverMessageBlock2.verifyFailed) {
            throw new SmbException("Signature verification failed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void send(ServerMessageBlock serverMessageBlock, ServerMessageBlock serverMessageBlock2) throws SmbException {
        connect();
        serverMessageBlock.flags2 |= this.flags2;
        serverMessageBlock.useUnicode = this.useUnicode;
        serverMessageBlock.response = serverMessageBlock2;
        if (serverMessageBlock.digest == null) {
            serverMessageBlock.digest = this.digest;
        }
        try {
            if (serverMessageBlock2 == null) {
                doSend0(serverMessageBlock);
                return;
            }
            if (serverMessageBlock instanceof SmbComTransaction) {
                serverMessageBlock2.command = serverMessageBlock.command;
                SmbComTransaction smbComTransaction = (SmbComTransaction) serverMessageBlock;
                SmbComTransactionResponse smbComTransactionResponse = (SmbComTransactionResponse) serverMessageBlock2;
                smbComTransaction.maxBufferSize = this.snd_buf_size;
                smbComTransactionResponse.reset();
                try {
                    BufferCache.getBuffers(smbComTransaction, smbComTransactionResponse);
                    smbComTransaction.nextElement();
                    if (smbComTransaction.hasMoreElements()) {
                        SmbComBlankResponse smbComBlankResponse = new SmbComBlankResponse();
                        super.sendrecv(smbComTransaction, smbComBlankResponse, RESPONSE_TIMEOUT);
                        if (smbComBlankResponse.errorCode != 0) {
                            checkStatus(smbComTransaction, smbComBlankResponse);
                        }
                        smbComTransaction.nextElement();
                    } else {
                        makeKey(smbComTransaction);
                    }
                    synchronized (this) {
                        serverMessageBlock2.received = false;
                        smbComTransactionResponse.isReceived = false;
                        try {
                            try {
                                this.response_map.put(smbComTransaction, smbComTransactionResponse);
                                do {
                                    doSend0(smbComTransaction);
                                    if (!smbComTransaction.hasMoreElements()) {
                                        break;
                                    }
                                } while (smbComTransaction.nextElement() != null);
                                long j = RESPONSE_TIMEOUT;
                                smbComTransactionResponse.expiration = System.currentTimeMillis() + j;
                                while (smbComTransactionResponse.hasMoreElements()) {
                                    wait(j);
                                    j = smbComTransactionResponse.expiration - System.currentTimeMillis();
                                    if (j <= 0) {
                                        throw new TransportException(this + " timedout waiting for response to " + smbComTransaction);
                                    }
                                }
                                if (serverMessageBlock2.errorCode != 0) {
                                    checkStatus(smbComTransaction, smbComTransactionResponse);
                                }
                            } catch (InterruptedException e) {
                                throw new TransportException(e);
                            }
                        } finally {
                            this.response_map.remove(smbComTransaction);
                        }
                    }
                } finally {
                    BufferCache.releaseBuffer(smbComTransaction.txn_buf);
                    BufferCache.releaseBuffer(smbComTransactionResponse.txn_buf);
                }
            } else {
                serverMessageBlock2.command = serverMessageBlock.command;
                super.sendrecv(serverMessageBlock, serverMessageBlock2, RESPONSE_TIMEOUT);
            }
            checkStatus(serverMessageBlock, serverMessageBlock2);
        } catch (SmbException e2) {
            throw e2;
        } catch (IOException e3) {
            throw new SmbException(e3.getMessage(), e3);
        }
    }

    @Override // jcifs.util.transport.Transport
    public String toString() {
        return super.toString() + "[" + this.address + ":" + this.port + "]";
    }

    void dfsPathSplit(String str, String[] strArr) {
        int length = strArr.length - 1;
        int length2 = str.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i != length) {
            if (i2 == length2 || str.charAt(i2) == '\\') {
                strArr[i] = str.substring(i3, i2);
                i3 = i2 + 1;
                i++;
            }
            int i4 = i2 + 1;
            if (i2 >= length2) {
                while (i < strArr.length) {
                    strArr[i] = "";
                    i++;
                }
                return;
            }
            i2 = i4;
        }
        strArr[length] = str.substring(i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DfsReferral getDfsReferrals(NtlmPasswordAuthentication ntlmPasswordAuthentication, String str, int i) throws SmbException {
        SmbTree smbTree = getSmbSession(ntlmPasswordAuthentication).getSmbTree("IPC$", null);
        Trans2GetDfsReferralResponse trans2GetDfsReferralResponse = new Trans2GetDfsReferralResponse();
        smbTree.send(new Trans2GetDfsReferral(str), trans2GetDfsReferralResponse);
        if (trans2GetDfsReferralResponse.numReferrals == 0) {
            return null;
        }
        if (i == 0 || trans2GetDfsReferralResponse.numReferrals < i) {
            i = trans2GetDfsReferralResponse.numReferrals;
        }
        DfsReferral dfsReferral = new DfsReferral();
        String[] strArr = new String[4];
        long currentTimeMillis = System.currentTimeMillis() + (Dfs.TTL * 1000);
        int i2 = 0;
        while (true) {
            dfsReferral.resolveHashes = ntlmPasswordAuthentication.hashesExternal;
            dfsReferral.ttl = trans2GetDfsReferralResponse.referrals[i2].ttl;
            dfsReferral.expiration = currentTimeMillis;
            if (str.equals("")) {
                dfsReferral.server = trans2GetDfsReferralResponse.referrals[i2].path.substring(1).toLowerCase();
            } else {
                dfsPathSplit(trans2GetDfsReferralResponse.referrals[i2].node, strArr);
                dfsReferral.server = strArr[1];
                dfsReferral.share = strArr[2];
                dfsReferral.path = strArr[3];
            }
            dfsReferral.pathConsumed = trans2GetDfsReferralResponse.pathConsumed;
            i2++;
            if (i2 != i) {
                dfsReferral.append(new DfsReferral());
                dfsReferral = dfsReferral.next;
            } else {
                return dfsReferral.next;
            }
        }
    }

    DfsReferral[] __getDfsReferrals(NtlmPasswordAuthentication ntlmPasswordAuthentication, String str, int i) throws SmbException {
        SmbTree smbTree = getSmbSession(ntlmPasswordAuthentication).getSmbTree("IPC$", null);
        Trans2GetDfsReferralResponse trans2GetDfsReferralResponse = new Trans2GetDfsReferralResponse();
        smbTree.send(new Trans2GetDfsReferral(str), trans2GetDfsReferralResponse);
        if (i == 0 || trans2GetDfsReferralResponse.numReferrals < i) {
            i = trans2GetDfsReferralResponse.numReferrals;
        }
        DfsReferral[] dfsReferralArr = new DfsReferral[i];
        String[] strArr = new String[4];
        long currentTimeMillis = System.currentTimeMillis() + (Dfs.TTL * 1000);
        for (int i2 = 0; i2 < i; i2++) {
            DfsReferral dfsReferral = new DfsReferral();
            dfsReferral.resolveHashes = ntlmPasswordAuthentication.hashesExternal;
            dfsReferral.ttl = trans2GetDfsReferralResponse.referrals[i2].ttl;
            dfsReferral.expiration = currentTimeMillis;
            if (str.equals("")) {
                dfsReferral.server = trans2GetDfsReferralResponse.referrals[i2].path.substring(1).toLowerCase();
            } else {
                dfsPathSplit(trans2GetDfsReferralResponse.referrals[i2].node, strArr);
                dfsReferral.server = strArr[1];
                dfsReferral.share = strArr[2];
                dfsReferral.path = strArr[3];
            }
            dfsReferral.pathConsumed = trans2GetDfsReferralResponse.pathConsumed;
            dfsReferralArr[i2] = dfsReferral;
        }
        return dfsReferralArr;
    }
}
