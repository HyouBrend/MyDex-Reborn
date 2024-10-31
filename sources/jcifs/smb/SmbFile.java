package jcifs.smb;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.libraries.places.api.model.PlaceTypes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jcifs.Config;
import jcifs.UniAddress;
import jcifs.dcerpc.DcerpcHandle;
import jcifs.dcerpc.msrpc.MsrpcDfsRootEnum;
import jcifs.dcerpc.msrpc.MsrpcShareEnum;
import jcifs.dcerpc.msrpc.MsrpcShareGetInfo;
import jcifs.netbios.NbtAddress;
import jcifs.util.LogStream;
import kotlin.UByte;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class SmbFile extends URLConnection implements SmbConstants {
    public static final int ATTR_ARCHIVE = 32;
    static final int ATTR_COMPRESSED = 2048;
    public static final int ATTR_DIRECTORY = 16;
    static final int ATTR_GET_MASK = 32767;
    public static final int ATTR_HIDDEN = 2;
    static final int ATTR_NORMAL = 128;
    public static final int ATTR_READONLY = 1;
    static final int ATTR_SET_MASK = 12455;
    public static final int ATTR_SYSTEM = 4;
    static final int ATTR_TEMPORARY = 256;
    public static final int ATTR_VOLUME = 8;
    static final int DEFAULT_ATTR_EXPIRATION_PERIOD = 5000;
    public static final int FILE_NO_SHARE = 0;
    public static final int FILE_SHARE_DELETE = 4;
    public static final int FILE_SHARE_READ = 1;
    public static final int FILE_SHARE_WRITE = 2;
    static final int HASH_DOT = 46;
    static final int HASH_DOT_DOT = 1472;
    static final int O_APPEND = 4;
    static final int O_CREAT = 16;
    static final int O_EXCL = 32;
    static final int O_RDONLY = 1;
    static final int O_RDWR = 3;
    static final int O_TRUNC = 64;
    static final int O_WRONLY = 2;
    public static final int TYPE_COMM = 64;
    public static final int TYPE_FILESYSTEM = 1;
    public static final int TYPE_NAMED_PIPE = 16;
    public static final int TYPE_PRINTER = 32;
    public static final int TYPE_SERVER = 4;
    public static final int TYPE_SHARE = 8;
    public static final int TYPE_WORKGROUP = 2;
    static long attrExpirationPeriod;
    protected static Dfs dfs;
    static boolean ignoreCopyToException;
    static LogStream log = LogStream.getInstance();
    int addressIndex;
    UniAddress[] addresses;
    private long attrExpiration;
    private int attributes;
    NtlmPasswordAuthentication auth;
    private SmbComBlankResponse blank_resp;
    private String canon;
    private long createTime;
    private DfsReferral dfsReferral;
    int fid;
    private boolean isExists;
    private long lastModified;
    boolean opened;
    private String share;
    private int shareAccess;
    private long size;
    private long sizeExpiration;
    SmbTree tree;
    int tree_num;
    int type;
    String unc;

    static {
        try {
            Class.forName("jcifs.Config");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        attrExpirationPeriod = Config.getLong("jcifs.smb.client.attrExpirationPeriod", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        ignoreCopyToException = Config.getBoolean("jcifs.smb.client.ignoreCopyToException", true);
        dfs = new Dfs();
    }

    public SmbFile(String str) throws MalformedURLException {
        this(new URL((URL) null, str, Handler.SMB_HANDLER));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SmbFile(jcifs.smb.SmbFile r5, java.lang.String r6) throws java.net.MalformedURLException, java.net.UnknownHostException {
        /*
            r4 = this;
            boolean r0 = r5.isWorkgroup0()
            if (r0 == 0) goto L20
            java.net.URL r0 = new java.net.URL
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "smb://"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            java.net.URLStreamHandler r2 = jcifs.smb.Handler.SMB_HANDLER
            r0.<init>(r1, r6, r2)
            goto L29
        L20:
            java.net.URL r0 = new java.net.URL
            java.net.URL r1 = r5.url
            java.net.URLStreamHandler r2 = jcifs.smb.Handler.SMB_HANDLER
            r0.<init>(r1, r6, r2)
        L29:
            jcifs.smb.NtlmPasswordAuthentication r5 = r5.auth
            r4.<init>(r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbFile.<init>(jcifs.smb.SmbFile, java.lang.String):void");
    }

    public SmbFile(String str, String str2) throws MalformedURLException {
        this(new URL(new URL((URL) null, str, Handler.SMB_HANDLER), str2, Handler.SMB_HANDLER));
    }

    public SmbFile(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws MalformedURLException {
        this(new URL((URL) null, str, Handler.SMB_HANDLER), ntlmPasswordAuthentication);
    }

    public SmbFile(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication, int i) throws MalformedURLException {
        this(new URL((URL) null, str, Handler.SMB_HANDLER), ntlmPasswordAuthentication);
        if ((i & (-8)) != 0) {
            throw new RuntimeException("Illegal shareAccess parameter");
        }
        this.shareAccess = i;
    }

    public SmbFile(String str, String str2, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws MalformedURLException {
        this(new URL(new URL((URL) null, str, Handler.SMB_HANDLER), str2, Handler.SMB_HANDLER), ntlmPasswordAuthentication);
    }

    public SmbFile(String str, String str2, NtlmPasswordAuthentication ntlmPasswordAuthentication, int i) throws MalformedURLException {
        this(new URL(new URL((URL) null, str, Handler.SMB_HANDLER), str2, Handler.SMB_HANDLER), ntlmPasswordAuthentication);
        if ((i & (-8)) != 0) {
            throw new RuntimeException("Illegal shareAccess parameter");
        }
        this.shareAccess = i;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SmbFile(jcifs.smb.SmbFile r5, java.lang.String r6, int r7) throws java.net.MalformedURLException, java.net.UnknownHostException {
        /*
            r4 = this;
            boolean r0 = r5.isWorkgroup0()
            if (r0 == 0) goto L20
            java.net.URL r0 = new java.net.URL
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "smb://"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            java.net.URLStreamHandler r2 = jcifs.smb.Handler.SMB_HANDLER
            r0.<init>(r1, r6, r2)
            goto L29
        L20:
            java.net.URL r0 = new java.net.URL
            java.net.URL r1 = r5.url
            java.net.URLStreamHandler r2 = jcifs.smb.Handler.SMB_HANDLER
            r0.<init>(r1, r6, r2)
        L29:
            jcifs.smb.NtlmPasswordAuthentication r5 = r5.auth
            r4.<init>(r0, r5)
            r5 = r7 & (-8)
            if (r5 != 0) goto L35
            r4.shareAccess = r7
            return
        L35:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Illegal shareAccess parameter"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbFile.<init>(jcifs.smb.SmbFile, java.lang.String, int):void");
    }

    public SmbFile(URL url) {
        this(url, new NtlmPasswordAuthentication(url.getUserInfo()));
    }

    public SmbFile(URL url, NtlmPasswordAuthentication ntlmPasswordAuthentication) {
        super(url);
        this.shareAccess = 7;
        this.blank_resp = null;
        this.dfsReferral = null;
        this.tree = null;
        this.auth = ntlmPasswordAuthentication == null ? new NtlmPasswordAuthentication(url.getUserInfo()) : ntlmPasswordAuthentication;
        getUncPath0();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    SmbFile(jcifs.smb.SmbFile r6, java.lang.String r7, int r8, int r9, long r10, long r12, long r14) throws java.net.MalformedURLException, java.net.UnknownHostException {
        /*
            r5 = this;
            boolean r0 = r6.isWorkgroup0()
            java.lang.String r1 = "/"
            if (r0 == 0) goto L25
            java.net.URL r0 = new java.net.URL
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "smb://"
            r3.append(r4)
            r3.append(r7)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            java.net.URLStreamHandler r3 = jcifs.smb.Handler.SMB_HANDLER
            r0.<init>(r2, r1, r3)
            goto L42
        L25:
            java.net.URL r0 = new java.net.URL
            java.net.URL r2 = r6.url
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            r4 = r9 & 16
            if (r4 <= 0) goto L36
            goto L38
        L36:
            java.lang.String r1 = ""
        L38:
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r2, r1)
        L42:
            r5.<init>(r0)
            jcifs.smb.NtlmPasswordAuthentication r0 = r6.auth
            r5.auth = r0
            java.lang.String r0 = r6.share
            if (r0 == 0) goto L55
            jcifs.smb.SmbTree r0 = r6.tree
            r5.tree = r0
            jcifs.smb.DfsReferral r0 = r6.dfsReferral
            r5.dfsReferral = r0
        L55:
            int r0 = r7.length()
            r1 = 1
            int r0 = r0 - r1
            char r2 = r7.charAt(r0)
            r3 = 47
            if (r2 != r3) goto L68
            r2 = 0
            java.lang.String r7 = r7.substring(r2, r0)
        L68:
            java.lang.String r0 = r6.share
            java.lang.String r2 = "\\"
            if (r0 != 0) goto L71
            r5.unc = r2
            goto La3
        L71:
            java.lang.String r0 = r6.unc
            boolean r0 = r0.equals(r2)
            r2 = 92
            if (r0 == 0) goto L8d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r2)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.unc = r6
            goto La3
        L8d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = r6.unc
            r0.append(r6)
            r0.append(r2)
            r0.append(r7)
            java.lang.String r6 = r0.toString()
            r5.unc = r6
        La3:
            r5.type = r8
            r5.attributes = r9
            r5.createTime = r10
            r5.lastModified = r12
            r5.size = r14
            r5.isExists = r1
            long r6 = java.lang.System.currentTimeMillis()
            long r8 = jcifs.smb.SmbFile.attrExpirationPeriod
            long r6 = r6 + r8
            r5.sizeExpiration = r6
            r5.attrExpiration = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbFile.<init>(jcifs.smb.SmbFile, java.lang.String, int, int, long, long, long):void");
    }

    private SmbComBlankResponse blank_resp() {
        if (this.blank_resp == null) {
            this.blank_resp = new SmbComBlankResponse();
        }
        return this.blank_resp;
    }

    void resolveDfs(ServerMessageBlock serverMessageBlock) throws SmbException {
        SmbException smbException;
        byte b;
        boolean z = serverMessageBlock instanceof SmbComClose;
        if (z) {
            return;
        }
        connect0();
        DfsReferral resolve = dfs.resolve(this.tree.session.transport.tconHostName, this.tree.share, this.unc, this.auth);
        if (resolve != null) {
            SmbException smbException2 = null;
            String str = (serverMessageBlock == null || (((b = serverMessageBlock.command) == 37 || b == 50) && (((SmbComTransaction) serverMessageBlock).subCommand & UByte.MAX_VALUE) == 16)) ? null : "A:";
            DfsReferral dfsReferral = resolve;
            while (true) {
                try {
                    if (LogStream.level >= 2) {
                        log.println("DFS redirect: " + dfsReferral);
                    }
                    SmbTransport smbTransport = SmbTransport.getSmbTransport(UniAddress.getByName(dfsReferral.server), this.url.getPort());
                    smbTransport.connect();
                    this.tree = smbTransport.getSmbSession(this.auth).getSmbTree(dfsReferral.share, str);
                    if (dfsReferral != resolve && dfsReferral.key != null) {
                        dfsReferral.map.put(dfsReferral.key, dfsReferral);
                        break;
                    }
                    break;
                } catch (IOException e) {
                    if (e instanceof SmbException) {
                        smbException = (SmbException) e;
                    } else {
                        smbException = new SmbException(dfsReferral.server, e);
                    }
                    dfsReferral = dfsReferral.next;
                    if (dfsReferral == resolve) {
                        smbException2 = smbException;
                        break;
                    }
                }
            }
            if (smbException2 != null) {
                throw smbException2;
            }
            if (LogStream.level >= 3) {
                log.println(dfsReferral);
            }
            this.dfsReferral = dfsReferral;
            if (dfsReferral.pathConsumed < 0) {
                dfsReferral.pathConsumed = 0;
            } else if (dfsReferral.pathConsumed > this.unc.length()) {
                dfsReferral.pathConsumed = this.unc.length();
            }
            String substring = this.unc.substring(dfsReferral.pathConsumed);
            if (substring.equals("")) {
                substring = "\\";
            }
            if (!dfsReferral.path.equals("")) {
                substring = "\\" + dfsReferral.path + substring;
            }
            this.unc = substring;
            if (serverMessageBlock != null && serverMessageBlock.path != null && serverMessageBlock.path.endsWith("\\") && !substring.endsWith("\\")) {
                substring = substring + "\\";
            }
            if (serverMessageBlock != null) {
                serverMessageBlock.path = substring;
                serverMessageBlock.flags2 |= 4096;
                return;
            }
            return;
        }
        if (this.tree.inDomainDfs && !(serverMessageBlock instanceof NtTransQuerySecurityDesc) && !z && !(serverMessageBlock instanceof SmbComFindClose2)) {
            throw new SmbException(NtStatus.NT_STATUS_NOT_FOUND, false);
        }
        if (serverMessageBlock != null) {
            serverMessageBlock.flags2 &= -4097;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void send(ServerMessageBlock serverMessageBlock, ServerMessageBlock serverMessageBlock2) throws SmbException {
        while (true) {
            resolveDfs(serverMessageBlock);
            try {
                this.tree.send(serverMessageBlock, serverMessageBlock2);
                return;
            } catch (DfsReferral e) {
                if (e.resolveHashes) {
                    throw e;
                }
                serverMessageBlock.reset();
            }
        }
    }

    static String queryLookup(String str, String str2) {
        char[] charArray = str.toCharArray();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < charArray.length; i3++) {
            char c = charArray[i3];
            if (c == '&') {
                if (i > i2 && new String(charArray, i2, i - i2).equalsIgnoreCase(str2)) {
                    int i4 = i + 1;
                    return new String(charArray, i4, i3 - i4);
                }
                i2 = i3 + 1;
            } else if (c == '=') {
                i = i3;
            }
        }
        if (i <= i2 || !new String(charArray, i2, i - i2).equalsIgnoreCase(str2)) {
            return null;
        }
        int i5 = i + 1;
        return new String(charArray, i5, charArray.length - i5);
    }

    UniAddress getAddress() throws UnknownHostException {
        int i = this.addressIndex;
        if (i == 0) {
            return getFirstAddress();
        }
        return this.addresses[i - 1];
    }

    UniAddress getFirstAddress() throws UnknownHostException {
        this.addressIndex = 0;
        String host = this.url.getHost();
        String path = this.url.getPath();
        String query = this.url.getQuery();
        if (query != null) {
            String queryLookup = queryLookup(query, "server");
            if (queryLookup != null && queryLookup.length() > 0) {
                this.addresses = r1;
                UniAddress[] uniAddressArr = {UniAddress.getByName(queryLookup)};
                return getNextAddress();
            }
            String queryLookup2 = queryLookup(query, PlaceTypes.ADDRESS);
            if (queryLookup2 != null && queryLookup2.length() > 0) {
                byte[] address = InetAddress.getByName(queryLookup2).getAddress();
                this.addresses = r3;
                UniAddress[] uniAddressArr2 = {new UniAddress(InetAddress.getByAddress(host, address))};
                return getNextAddress();
            }
        }
        if (host.length() == 0) {
            try {
                NbtAddress byName = NbtAddress.getByName(NbtAddress.MASTER_BROWSER_NAME, 1, null);
                this.addresses = r2;
                UniAddress[] uniAddressArr3 = {UniAddress.getByName(byName.getHostAddress())};
            } catch (UnknownHostException e) {
                NtlmPasswordAuthentication.initDefaults();
                if (NtlmPasswordAuthentication.DEFAULT_DOMAIN.equals("?")) {
                    throw e;
                }
                this.addresses = UniAddress.getAllByName(NtlmPasswordAuthentication.DEFAULT_DOMAIN, true);
            }
        } else if (path.length() == 0 || path.equals(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            this.addresses = UniAddress.getAllByName(host, true);
        } else {
            this.addresses = UniAddress.getAllByName(host, false);
        }
        return getNextAddress();
    }

    UniAddress getNextAddress() {
        int i = this.addressIndex;
        UniAddress[] uniAddressArr = this.addresses;
        if (i >= uniAddressArr.length) {
            return null;
        }
        this.addressIndex = i + 1;
        return uniAddressArr[i];
    }

    boolean hasNextAddress() {
        return this.addressIndex < this.addresses.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void connect0() throws SmbException {
        try {
            connect();
        } catch (UnknownHostException e) {
            throw new SmbException("Failed to connect to server", e);
        } catch (SmbException e2) {
            throw e2;
        } catch (IOException e3) {
            throw new SmbException("Failed to connect to server", e3);
        }
    }

    void doConnect() throws IOException {
        SmbTransport smbTransport;
        UniAddress address = getAddress();
        SmbTree smbTree = this.tree;
        if (smbTree != null) {
            smbTransport = smbTree.session.transport;
        } else {
            smbTransport = SmbTransport.getSmbTransport(address, this.url.getPort());
            this.tree = smbTransport.getSmbSession(this.auth).getSmbTree(this.share, null);
        }
        String serverWithDfs = getServerWithDfs();
        SmbTree smbTree2 = this.tree;
        smbTree2.inDomainDfs = dfs.resolve(serverWithDfs, smbTree2.share, null, this.auth) != null;
        if (this.tree.inDomainDfs) {
            this.tree.connectionState = 2;
        }
        try {
            if (LogStream.level >= 3) {
                log.println("doConnect: " + address);
            }
            this.tree.treeConnect(null, null);
        } catch (SmbAuthException e) {
            if (this.share == null) {
                SmbTree smbTree3 = smbTransport.getSmbSession(NtlmPasswordAuthentication.NULL).getSmbTree(null, null);
                this.tree = smbTree3;
                smbTree3.treeConnect(null, null);
                return;
            }
            NtlmPasswordAuthentication requestNtlmPasswordAuthentication = NtlmAuthenticator.requestNtlmPasswordAuthentication(this.url.toString(), e);
            if (requestNtlmPasswordAuthentication != null) {
                this.auth = requestNtlmPasswordAuthentication;
                SmbTree smbTree4 = smbTransport.getSmbSession(requestNtlmPasswordAuthentication).getSmbTree(this.share, null);
                this.tree = smbTree4;
                smbTree4.inDomainDfs = dfs.resolve(serverWithDfs, smbTree4.share, null, this.auth) != null;
                if (this.tree.inDomainDfs) {
                    this.tree.connectionState = 2;
                }
                this.tree.treeConnect(null, null);
                return;
            }
            if (LogStream.level >= 1 && hasNextAddress()) {
                e.printStackTrace(log);
            }
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        if (isConnected()) {
            return;
        }
        getUncPath0();
        getFirstAddress();
        while (true) {
            try {
                doConnect();
                return;
            } catch (SmbAuthException e) {
                throw e;
            } catch (SmbException e2) {
                if (getNextAddress() == null) {
                    throw e2;
                }
                if (LogStream.level >= 3) {
                    e2.printStackTrace(log);
                }
            }
        }
    }

    boolean isConnected() {
        SmbTree smbTree = this.tree;
        return smbTree != null && smbTree.connectionState == 2;
    }

    int open0(int i, int i2, int i3, int i4) throws SmbException {
        connect0();
        if (LogStream.level >= 3) {
            log.println("open0: " + this.unc);
        }
        if (this.tree.session.transport.hasCapability(16)) {
            SmbComNTCreateAndXResponse smbComNTCreateAndXResponse = new SmbComNTCreateAndXResponse();
            SmbComNTCreateAndX smbComNTCreateAndX = new SmbComNTCreateAndX(this.unc, i, i2, this.shareAccess, i3, i4, null);
            if (this instanceof SmbNamedPipe) {
                smbComNTCreateAndX.flags0 |= 22;
                smbComNTCreateAndX.desiredAccess |= 131072;
                smbComNTCreateAndXResponse.isExtended = true;
            }
            send(smbComNTCreateAndX, smbComNTCreateAndXResponse);
            int i5 = smbComNTCreateAndXResponse.fid;
            this.attributes = smbComNTCreateAndXResponse.extFileAttributes & ATTR_GET_MASK;
            this.attrExpiration = System.currentTimeMillis() + attrExpirationPeriod;
            this.isExists = true;
            return i5;
        }
        SmbComOpenAndXResponse smbComOpenAndXResponse = new SmbComOpenAndXResponse();
        send(new SmbComOpenAndX(this.unc, i2, i, null), smbComOpenAndXResponse);
        return smbComOpenAndXResponse.fid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void open(int i, int i2, int i3, int i4) throws SmbException {
        if (isOpen()) {
            return;
        }
        this.fid = open0(i, i2, i3, i4);
        this.opened = true;
        this.tree_num = this.tree.tree_num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isOpen() {
        return this.opened && isConnected() && this.tree_num == this.tree.tree_num;
    }

    void close(int i, long j) throws SmbException {
        if (LogStream.level >= 3) {
            log.println("close: " + i);
        }
        send(new SmbComClose(i, j), blank_resp());
    }

    void close(long j) throws SmbException {
        if (isOpen()) {
            close(this.fid, j);
            this.opened = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() throws SmbException {
        close(0L);
    }

    public Principal getPrincipal() {
        return this.auth;
    }

    public String getName() {
        getUncPath0();
        if (this.canon.length() > 1) {
            int length = this.canon.length() - 2;
            while (this.canon.charAt(length) != '/') {
                length--;
            }
            return this.canon.substring(length + 1);
        }
        if (this.share != null) {
            return this.share + '/';
        }
        if (this.url.getHost().length() <= 0) {
            return "smb://";
        }
        return this.url.getHost() + '/';
    }

    public String getParent() {
        String authority = this.url.getAuthority();
        if (authority.length() <= 0) {
            return "smb://";
        }
        StringBuffer stringBuffer = new StringBuffer("smb://");
        stringBuffer.append(authority);
        getUncPath0();
        if (this.canon.length() > 1) {
            stringBuffer.append(this.canon);
        } else {
            stringBuffer.append('/');
        }
        String stringBuffer2 = stringBuffer.toString();
        int length = stringBuffer2.length() - 2;
        while (stringBuffer2.charAt(length) != '/') {
            length--;
        }
        return stringBuffer2.substring(0, length + 1);
    }

    public String getPath() {
        return this.url.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0049, code lost:
    
        if (r5 == 1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x004c, code lost:
    
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x004e, code lost:
    
        if (r5 <= 1) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0054, code lost:
    
        if (r1[r5 - 1] != '/') goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0056, code lost:
    
        r4 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String getUncPath0() {
        /*
            Method dump skipped, instructions count: 195
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbFile.getUncPath0():java.lang.String");
    }

    public String getUncPath() {
        getUncPath0();
        if (this.share == null) {
            return "\\\\" + this.url.getHost();
        }
        return "\\\\" + this.url.getHost() + this.canon.replace('/', '\\');
    }

    public String getCanonicalPath() {
        String authority = this.url.getAuthority();
        getUncPath0();
        if (authority.length() <= 0) {
            return "smb://";
        }
        return "smb://" + this.url.getAuthority() + this.canon;
    }

    public String getShare() {
        return this.share;
    }

    String getServerWithDfs() {
        DfsReferral dfsReferral = this.dfsReferral;
        if (dfsReferral != null) {
            return dfsReferral.server;
        }
        return getServer();
    }

    public String getServer() {
        String host = this.url.getHost();
        if (host.length() == 0) {
            return null;
        }
        return host;
    }

    public int getType() throws SmbException {
        int nameType;
        if (this.type == 0) {
            if (getUncPath0().length() > 1) {
                this.type = 1;
            } else if (this.share != null) {
                connect0();
                if (this.share.equals("IPC$")) {
                    this.type = 16;
                } else if (this.tree.service.equals("LPT1:")) {
                    this.type = 32;
                } else if (this.tree.service.equals("COMM")) {
                    this.type = 64;
                } else {
                    this.type = 8;
                }
            } else if (this.url.getAuthority() == null || this.url.getAuthority().length() == 0) {
                this.type = 2;
            } else {
                try {
                    UniAddress address = getAddress();
                    if ((address.getAddress() instanceof NbtAddress) && ((nameType = ((NbtAddress) address.getAddress()).getNameType()) == 29 || nameType == 27)) {
                        this.type = 2;
                        return 2;
                    }
                    this.type = 4;
                } catch (UnknownHostException e) {
                    throw new SmbException(this.url.toString(), e);
                }
            }
        }
        return this.type;
    }

    boolean isWorkgroup0() throws UnknownHostException {
        int nameType;
        if (this.type == 2 || this.url.getHost().length() == 0) {
            this.type = 2;
            return true;
        }
        getUncPath0();
        if (this.share != null) {
            return false;
        }
        UniAddress address = getAddress();
        if ((address.getAddress() instanceof NbtAddress) && ((nameType = ((NbtAddress) address.getAddress()).getNameType()) == 29 || nameType == 27)) {
            this.type = 2;
            return true;
        }
        this.type = 4;
        return false;
    }

    Info queryPath(String str, int i) throws SmbException {
        connect0();
        if (LogStream.level >= 3) {
            log.println("queryPath: " + str);
        }
        if (this.tree.session.transport.hasCapability(16)) {
            Trans2QueryPathInformationResponse trans2QueryPathInformationResponse = new Trans2QueryPathInformationResponse(i);
            send(new Trans2QueryPathInformation(str, i), trans2QueryPathInformationResponse);
            return trans2QueryPathInformationResponse.info;
        }
        SmbComQueryInformationResponse smbComQueryInformationResponse = new SmbComQueryInformationResponse(this.tree.session.transport.server.serverTimeZone * 1000 * 60);
        send(new SmbComQueryInformation(str), smbComQueryInformationResponse);
        return smbComQueryInformationResponse;
    }

    public boolean exists() throws SmbException {
        if (this.attrExpiration > System.currentTimeMillis()) {
            return this.isExists;
        }
        this.attributes = 17;
        this.createTime = 0L;
        this.lastModified = 0L;
        this.isExists = false;
        try {
            if (this.url.getHost().length() != 0) {
                if (this.share == null) {
                    if (getType() == 2) {
                        UniAddress.getByName(this.url.getHost(), true);
                    } else {
                        UniAddress.getByName(this.url.getHost()).getHostName();
                    }
                } else {
                    if (getUncPath0().length() != 1 && !this.share.equalsIgnoreCase("IPC$")) {
                        Info queryPath = queryPath(getUncPath0(), 257);
                        this.attributes = queryPath.getAttributes();
                        this.createTime = queryPath.getCreateTime();
                        this.lastModified = queryPath.getLastWriteTime();
                    }
                    connect0();
                }
            }
            this.isExists = true;
        } catch (UnknownHostException unused) {
        } catch (SmbException e) {
            switch (e.getNtStatus()) {
                case NtStatus.NT_STATUS_NO_SUCH_FILE /* -1073741809 */:
                case NtStatus.NT_STATUS_OBJECT_NAME_INVALID /* -1073741773 */:
                case NtStatus.NT_STATUS_OBJECT_NAME_NOT_FOUND /* -1073741772 */:
                case NtStatus.NT_STATUS_OBJECT_PATH_NOT_FOUND /* -1073741766 */:
                    break;
                default:
                    throw e;
            }
        }
        this.attrExpiration = System.currentTimeMillis() + attrExpirationPeriod;
        return this.isExists;
    }

    public boolean canRead() throws SmbException {
        if (getType() == 16) {
            return true;
        }
        return exists();
    }

    public boolean canWrite() throws SmbException {
        if (getType() == 16) {
            return true;
        }
        return exists() && (this.attributes & 1) == 0;
    }

    public boolean isDirectory() throws SmbException {
        if (getUncPath0().length() == 1) {
            return true;
        }
        return exists() && (this.attributes & 16) == 16;
    }

    public boolean isFile() throws SmbException {
        if (getUncPath0().length() == 1) {
            return false;
        }
        exists();
        return (this.attributes & 16) == 0;
    }

    public boolean isHidden() throws SmbException {
        if (this.share == null) {
            return false;
        }
        if (getUncPath0().length() == 1) {
            return this.share.endsWith("$");
        }
        exists();
        return (this.attributes & 2) == 2;
    }

    public String getDfsPath() throws SmbException {
        resolveDfs(null);
        if (this.dfsReferral == null) {
            return null;
        }
        String replace = ("smb:/" + this.dfsReferral.server + InternalZipConstants.ZIP_FILE_SEPARATOR + this.dfsReferral.share + this.unc).replace('\\', '/');
        if (!isDirectory()) {
            return replace;
        }
        return replace + '/';
    }

    public long createTime() throws SmbException {
        if (getUncPath0().length() <= 1) {
            return 0L;
        }
        exists();
        return this.createTime;
    }

    public long lastModified() throws SmbException {
        if (getUncPath0().length() <= 1) {
            return 0L;
        }
        exists();
        return this.lastModified;
    }

    public String[] list() throws SmbException {
        return list("*", 22, null, null);
    }

    public String[] list(SmbFilenameFilter smbFilenameFilter) throws SmbException {
        return list("*", 22, smbFilenameFilter, null);
    }

    public SmbFile[] listFiles() throws SmbException {
        return listFiles("*", 22, null, null);
    }

    public SmbFile[] listFiles(String str) throws SmbException {
        return listFiles(str, 22, null, null);
    }

    public SmbFile[] listFiles(SmbFilenameFilter smbFilenameFilter) throws SmbException {
        return listFiles("*", 22, smbFilenameFilter, null);
    }

    public SmbFile[] listFiles(SmbFileFilter smbFileFilter) throws SmbException {
        return listFiles("*", 22, null, smbFileFilter);
    }

    String[] list(String str, int i, SmbFilenameFilter smbFilenameFilter, SmbFileFilter smbFileFilter) throws SmbException {
        ArrayList arrayList = new ArrayList();
        doEnum(arrayList, false, str, i, smbFilenameFilter, smbFileFilter);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    SmbFile[] listFiles(String str, int i, SmbFilenameFilter smbFilenameFilter, SmbFileFilter smbFileFilter) throws SmbException {
        ArrayList arrayList = new ArrayList();
        doEnum(arrayList, true, str, i, smbFilenameFilter, smbFileFilter);
        return (SmbFile[]) arrayList.toArray(new SmbFile[arrayList.size()]);
    }

    void doEnum(ArrayList arrayList, boolean z, String str, int i, SmbFilenameFilter smbFilenameFilter, SmbFileFilter smbFileFilter) throws SmbException {
        if (smbFileFilter != null && (smbFileFilter instanceof DosFileFilter)) {
            DosFileFilter dosFileFilter = (DosFileFilter) smbFileFilter;
            if (dosFileFilter.wildcard != null) {
                str = dosFileFilter.wildcard;
            }
            i = dosFileFilter.attributes;
        }
        String str2 = str;
        int i2 = i;
        try {
            if (this.url.getHost().length() != 0 && getType() != 2) {
                if (this.share == null) {
                    doShareEnum(arrayList, z, str2, i2, smbFilenameFilter, smbFileFilter);
                    return;
                } else {
                    doFindFirstNext(arrayList, z, str2, i2, smbFilenameFilter, smbFileFilter);
                    return;
                }
            }
            doNetServerEnum(arrayList, z, str2, i2, smbFilenameFilter, smbFileFilter);
        } catch (MalformedURLException e) {
            throw new SmbException(this.url.toString(), e);
        } catch (UnknownHostException e2) {
            throw new SmbException(this.url.toString(), e2);
        }
    }

    void doShareEnum(ArrayList arrayList, boolean z, String str, int i, SmbFilenameFilter smbFilenameFilter, SmbFileFilter smbFileFilter) throws SmbException, UnknownHostException, MalformedURLException {
        Iterator it;
        FileEntry[] doNetShareEnum;
        SmbFilenameFilter smbFilenameFilter2 = smbFilenameFilter;
        if (this.url.getPath().lastIndexOf(47) != r0.length() - 1) {
            throw new SmbException(this.url.toString() + " directory must end with '/'");
        }
        if (getType() != 4) {
            throw new SmbException("The requested list operations is invalid: " + this.url.toString());
        }
        HashMap hashMap = new HashMap();
        if (dfs.isTrustedDomain(getServer(), this.auth)) {
            try {
                for (FileEntry fileEntry : doDfsRootEnum()) {
                    if (!hashMap.containsKey(fileEntry)) {
                        hashMap.put(fileEntry, fileEntry);
                    }
                }
            } catch (IOException e) {
                if (LogStream.level >= 4) {
                    e.printStackTrace(log);
                }
            }
        }
        UniAddress firstAddress = getFirstAddress();
        IOException iOException = null;
        loop0: while (firstAddress != null) {
            try {
                doConnect();
                try {
                    doNetShareEnum = doMsrpcShareEnum();
                } catch (IOException e2) {
                    if (LogStream.level >= 3) {
                        e2.printStackTrace(log);
                    }
                    doNetShareEnum = doNetShareEnum();
                }
                for (FileEntry fileEntry2 : doNetShareEnum) {
                    if (!hashMap.containsKey(fileEntry2)) {
                        hashMap.put(fileEntry2, fileEntry2);
                    }
                }
                break loop0;
            } catch (IOException e3) {
                iOException = e3;
                if (LogStream.level >= 3) {
                    iOException.printStackTrace(log);
                }
                firstAddress = getNextAddress();
            }
        }
        if (iOException != null && hashMap.isEmpty()) {
            if (!(iOException instanceof SmbException)) {
                throw new SmbException(this.url.toString(), iOException);
            }
            throw ((SmbException) iOException);
        }
        Iterator it2 = hashMap.keySet().iterator();
        while (it2.hasNext()) {
            FileEntry fileEntry3 = (FileEntry) it2.next();
            String name = fileEntry3.getName();
            if (smbFilenameFilter2 == null || smbFilenameFilter2.accept(this, name)) {
                if (name.length() > 0) {
                    it = it2;
                    SmbFile smbFile = new SmbFile(this, name, fileEntry3.getType(), 17, 0L, 0L, 0L);
                    if (smbFileFilter == null || smbFileFilter.accept(smbFile)) {
                        if (z) {
                            arrayList.add(smbFile);
                        } else {
                            arrayList.add(name);
                        }
                    }
                } else {
                    it = it2;
                }
                it2 = it;
                smbFilenameFilter2 = smbFilenameFilter;
            }
        }
    }

    FileEntry[] doDfsRootEnum() throws IOException {
        DcerpcHandle handle = DcerpcHandle.getHandle("ncacn_np:" + getAddress().getHostAddress() + "[\\PIPE\\netdfs]", this.auth);
        try {
            MsrpcDfsRootEnum msrpcDfsRootEnum = new MsrpcDfsRootEnum(getServer());
            handle.sendrecv(msrpcDfsRootEnum);
            if (msrpcDfsRootEnum.retval != 0) {
                throw new SmbException(msrpcDfsRootEnum.retval, true);
            }
            return msrpcDfsRootEnum.getEntries();
        } finally {
            try {
                handle.close();
            } catch (IOException e) {
                if (LogStream.level >= 4) {
                    e.printStackTrace(log);
                }
            }
        }
    }

    FileEntry[] doMsrpcShareEnum() throws IOException {
        MsrpcShareEnum msrpcShareEnum = new MsrpcShareEnum(this.url.getHost());
        DcerpcHandle handle = DcerpcHandle.getHandle("ncacn_np:" + getAddress().getHostAddress() + "[\\PIPE\\srvsvc]", this.auth);
        try {
            handle.sendrecv(msrpcShareEnum);
            if (msrpcShareEnum.retval != 0) {
                throw new SmbException(msrpcShareEnum.retval, true);
            }
            return msrpcShareEnum.getEntries();
        } finally {
            try {
                handle.close();
            } catch (IOException e) {
                if (LogStream.level >= 4) {
                    e.printStackTrace(log);
                }
            }
        }
    }

    FileEntry[] doNetShareEnum() throws SmbException {
        ServerMessageBlock netShareEnum = new NetShareEnum();
        NetShareEnumResponse netShareEnumResponse = new NetShareEnumResponse();
        send(netShareEnum, netShareEnumResponse);
        if (netShareEnumResponse.status != 0) {
            throw new SmbException(netShareEnumResponse.status, true);
        }
        return netShareEnumResponse.results;
    }

    void doNetServerEnum(ArrayList arrayList, boolean z, String str, int i, SmbFilenameFilter smbFilenameFilter, SmbFileFilter smbFileFilter) throws SmbException, UnknownHostException, MalformedURLException {
        NetServerEnum2 netServerEnum2;
        NetServerEnum2Response netServerEnum2Response;
        int i2;
        NetServerEnum2Response netServerEnum2Response2;
        int i3;
        NetServerEnum2 netServerEnum22;
        SmbFile smbFile = this;
        SmbFilenameFilter smbFilenameFilter2 = smbFilenameFilter;
        int type = smbFile.url.getHost().length() == 0 ? 0 : getType();
        if (type == 0) {
            connect0();
            netServerEnum2 = new NetServerEnum2(smbFile.tree.session.transport.server.oemDomainName, Integer.MIN_VALUE);
            netServerEnum2Response = new NetServerEnum2Response();
        } else if (type == 2) {
            netServerEnum2 = new NetServerEnum2(smbFile.url.getHost(), -1);
            netServerEnum2Response = new NetServerEnum2Response();
        } else {
            throw new SmbException("The requested list operations is invalid: " + this.url.toString());
        }
        NetServerEnum2 netServerEnum23 = netServerEnum2;
        NetServerEnum2Response netServerEnum2Response3 = netServerEnum2Response;
        while (true) {
            smbFile.send(netServerEnum23, netServerEnum2Response3);
            if (netServerEnum2Response3.status != 0 && netServerEnum2Response3.status != 234) {
                throw new SmbException(netServerEnum2Response3.status, true);
            }
            boolean z2 = netServerEnum2Response3.status == 234;
            int i4 = netServerEnum2Response3.numEntries;
            if (z2) {
                i4--;
            }
            int i5 = i4;
            int i6 = 0;
            while (i6 < i5) {
                FileEntry fileEntry = netServerEnum2Response3.results[i6];
                String name = fileEntry.getName();
                if ((smbFilenameFilter2 == null || smbFilenameFilter2.accept(smbFile, name)) && name.length() > 0) {
                    i2 = i6;
                    netServerEnum2Response2 = netServerEnum2Response3;
                    i3 = i5;
                    netServerEnum22 = netServerEnum23;
                    SmbFile smbFile2 = new SmbFile(this, name, fileEntry.getType(), 17, 0L, 0L, 0L);
                    if (smbFileFilter == null || smbFileFilter.accept(smbFile2)) {
                        if (z) {
                            arrayList.add(smbFile2);
                        } else {
                            arrayList.add(name);
                        }
                    }
                } else {
                    i2 = i6;
                    netServerEnum2Response2 = netServerEnum2Response3;
                    i3 = i5;
                    netServerEnum22 = netServerEnum23;
                }
                i6 = i2 + 1;
                smbFile = this;
                netServerEnum23 = netServerEnum22;
                i5 = i3;
                netServerEnum2Response3 = netServerEnum2Response2;
                smbFilenameFilter2 = smbFilenameFilter;
            }
            NetServerEnum2Response netServerEnum2Response4 = netServerEnum2Response3;
            NetServerEnum2 netServerEnum24 = netServerEnum23;
            if (getType() != 2) {
                return;
            }
            netServerEnum24.subCommand = (byte) -41;
            netServerEnum24.reset(0, netServerEnum2Response4.lastName);
            netServerEnum2Response4.reset();
            if (!z2) {
                return;
            }
            smbFile = this;
            netServerEnum2Response3 = netServerEnum2Response4;
            netServerEnum23 = netServerEnum24;
            smbFilenameFilter2 = smbFilenameFilter;
        }
    }

    void doFindFirstNext(ArrayList arrayList, boolean z, String str, int i, SmbFilenameFilter smbFilenameFilter, SmbFileFilter smbFileFilter) throws SmbException, UnknownHostException, MalformedURLException {
        Trans2FindNext2 trans2FindNext2;
        int i2;
        int i3;
        int hashCode;
        SmbFilenameFilter smbFilenameFilter2 = smbFilenameFilter;
        String uncPath0 = getUncPath0();
        if (this.url.getPath().lastIndexOf(47) != r2.length() - 1) {
            throw new SmbException(this.url.toString() + " directory must end with '/'");
        }
        Trans2FindFirst2 trans2FindFirst2 = new Trans2FindFirst2(uncPath0, str, i);
        Trans2FindFirst2Response trans2FindFirst2Response = new Trans2FindFirst2Response();
        int i4 = 3;
        if (LogStream.level >= 3) {
            log.println("doFindFirstNext: " + trans2FindFirst2.path);
        }
        send(trans2FindFirst2, trans2FindFirst2Response);
        int i5 = trans2FindFirst2Response.sid;
        Trans2FindNext2 trans2FindNext22 = new Trans2FindNext2(i5, trans2FindFirst2Response.resumeKey, trans2FindFirst2Response.lastName);
        trans2FindFirst2Response.subCommand = (byte) 2;
        while (true) {
            int i6 = 0;
            while (i6 < trans2FindFirst2Response.numEntries) {
                FileEntry fileEntry = trans2FindFirst2Response.results[i6];
                String name = fileEntry.getName();
                if ((name.length() >= i4 || !(((hashCode = name.hashCode()) == HASH_DOT || hashCode == HASH_DOT_DOT) && (name.equals(".") || name.equals("..")))) && ((smbFilenameFilter2 == null || smbFilenameFilter2.accept(this, name)) && name.length() > 0)) {
                    trans2FindNext2 = trans2FindNext22;
                    i2 = i6;
                    i3 = i5;
                    SmbFile smbFile = new SmbFile(this, name, 1, fileEntry.getAttributes(), fileEntry.createTime(), fileEntry.lastModified(), fileEntry.length());
                    if (smbFileFilter == null || smbFileFilter.accept(smbFile)) {
                        if (z) {
                            arrayList.add(smbFile);
                        } else {
                            arrayList.add(name);
                        }
                    }
                } else {
                    trans2FindNext2 = trans2FindNext22;
                    i2 = i6;
                    i3 = i5;
                }
                i6 = i2 + 1;
                i5 = i3;
                trans2FindNext22 = trans2FindNext2;
                i4 = 3;
                smbFilenameFilter2 = smbFilenameFilter;
            }
            Trans2FindNext2 trans2FindNext23 = trans2FindNext22;
            int i7 = i5;
            if (!trans2FindFirst2Response.isEndOfSearch && trans2FindFirst2Response.numEntries != 0) {
                trans2FindNext23.reset(trans2FindFirst2Response.resumeKey, trans2FindFirst2Response.lastName);
                trans2FindFirst2Response.reset();
                send(trans2FindNext23, trans2FindFirst2Response);
                trans2FindNext22 = trans2FindNext23;
                i5 = i7;
                i4 = 3;
                smbFilenameFilter2 = smbFilenameFilter;
            } else {
                try {
                    send(new SmbComFindClose2(i7), blank_resp());
                    return;
                } catch (SmbException e) {
                    if (LogStream.level >= 4) {
                        e.printStackTrace(log);
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void renameTo(SmbFile smbFile) throws SmbException {
        if (getUncPath0().length() == 1 || smbFile.getUncPath0().length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        resolveDfs(null);
        smbFile.resolveDfs(null);
        if (!this.tree.equals(smbFile.tree)) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        if (LogStream.level >= 3) {
            log.println("renameTo: " + this.unc + " -> " + smbFile.unc);
        }
        this.sizeExpiration = 0L;
        this.attrExpiration = 0L;
        smbFile.attrExpiration = 0L;
        send(new SmbComRename(this.unc, smbFile.unc), blank_resp());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class WriterThread extends Thread {
        byte[] b;
        SmbFile dest;
        SmbException e;
        int n;
        long off;
        boolean ready;
        SmbComWrite req;
        SmbComWriteAndX reqx;
        ServerMessageBlock resp;
        boolean useNTSmbs;

        WriterThread() throws SmbException {
            super("JCIFS-WriterThread");
            this.e = null;
            boolean hasCapability = SmbFile.this.tree.session.transport.hasCapability(16);
            this.useNTSmbs = hasCapability;
            if (hasCapability) {
                this.reqx = new SmbComWriteAndX();
                this.resp = new SmbComWriteAndXResponse();
            } else {
                this.req = new SmbComWrite();
                this.resp = new SmbComWriteResponse();
            }
            this.ready = false;
        }

        synchronized void write(byte[] bArr, int i, SmbFile smbFile, long j) {
            this.b = bArr;
            this.n = i;
            this.dest = smbFile;
            this.off = j;
            this.ready = false;
            notify();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            synchronized (this) {
                while (true) {
                    try {
                        try {
                            notify();
                            this.ready = true;
                            while (this.ready) {
                                wait();
                            }
                            if (this.n == -1) {
                                return;
                            }
                            if (this.useNTSmbs) {
                                SmbComWriteAndX smbComWriteAndX = this.reqx;
                                int i = this.dest.fid;
                                long j = this.off;
                                int i2 = this.n;
                                smbComWriteAndX.setParam(i, j, i2, this.b, 0, i2);
                                this.dest.send(this.reqx, this.resp);
                            } else {
                                SmbComWrite smbComWrite = this.req;
                                int i3 = this.dest.fid;
                                long j2 = this.off;
                                int i4 = this.n;
                                smbComWrite.setParam(i3, j2, i4, this.b, 0, i4);
                                this.dest.send(this.req, this.resp);
                            }
                        } catch (Exception e) {
                            this.e = new SmbException("WriterThread", e);
                            notify();
                            return;
                        }
                    } catch (SmbException e2) {
                        this.e = e2;
                        notify();
                        return;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x016d, code lost:
    
        if (r0 != 1) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x016f, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0171, code lost:
    
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0197, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void copyTo0(jcifs.smb.SmbFile r27, byte[][] r28, int r29, jcifs.smb.SmbFile.WriterThread r30, jcifs.smb.SmbComReadAndX r31, jcifs.smb.SmbComReadAndXResponse r32) throws jcifs.smb.SmbException {
        /*
            Method dump skipped, instructions count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbFile.copyTo0(jcifs.smb.SmbFile, byte[][], int, jcifs.smb.SmbFile$WriterThread, jcifs.smb.SmbComReadAndX, jcifs.smb.SmbComReadAndXResponse):void");
    }

    public void copyTo(SmbFile smbFile) throws SmbException {
        if (this.share == null || smbFile.share == null) {
            throw new SmbException("Invalid operation for workgroups or servers");
        }
        SmbComReadAndX smbComReadAndX = new SmbComReadAndX();
        SmbComReadAndXResponse smbComReadAndXResponse = new SmbComReadAndXResponse();
        connect0();
        smbFile.connect0();
        resolveDfs(null);
        try {
            if (getAddress().equals(smbFile.getAddress())) {
                String str = this.canon;
                if (str.regionMatches(true, 0, smbFile.canon, 0, Math.min(str.length(), smbFile.canon.length()))) {
                    throw new SmbException("Source and destination paths overlap.");
                }
            }
        } catch (UnknownHostException unused) {
        }
        WriterThread writerThread = new WriterThread();
        writerThread.setDaemon(true);
        writerThread.start();
        SmbTransport smbTransport = this.tree.session.transport;
        SmbTransport smbTransport2 = smbFile.tree.session.transport;
        if (smbTransport.snd_buf_size < smbTransport2.snd_buf_size) {
            smbTransport2.snd_buf_size = smbTransport.snd_buf_size;
        } else {
            smbTransport.snd_buf_size = smbTransport2.snd_buf_size;
        }
        int min = Math.min(smbTransport.rcv_buf_size - 70, smbTransport.snd_buf_size - 70);
        try {
            copyTo0(smbFile, (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 2, min), min, writerThread, smbComReadAndX, smbComReadAndXResponse);
        } finally {
            writerThread.write(null, -1, null, 0L);
        }
    }

    public void delete() throws SmbException {
        exists();
        getUncPath0();
        delete(this.unc);
    }

    void delete(String str) throws SmbException {
        if (getUncPath0().length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        if (System.currentTimeMillis() > this.attrExpiration) {
            this.attributes = 17;
            this.createTime = 0L;
            this.lastModified = 0L;
            this.isExists = false;
            Info queryPath = queryPath(getUncPath0(), 257);
            this.attributes = queryPath.getAttributes();
            this.createTime = queryPath.getCreateTime();
            this.lastModified = queryPath.getLastWriteTime();
            this.attrExpiration = System.currentTimeMillis() + attrExpirationPeriod;
            this.isExists = true;
        }
        if ((1 & this.attributes) != 0) {
            setReadWrite();
        }
        if (LogStream.level >= 3) {
            log.println("delete: " + str);
        }
        if ((this.attributes & 16) != 0) {
            try {
                for (SmbFile smbFile : listFiles("*", 22, null, null)) {
                    smbFile.delete();
                }
            } catch (SmbException e) {
                if (e.getNtStatus() != -1073741809) {
                    throw e;
                }
            }
            send(new SmbComDeleteDirectory(str), blank_resp());
        } else {
            send(new SmbComDelete(str), blank_resp());
        }
        this.sizeExpiration = 0L;
        this.attrExpiration = 0L;
    }

    public long length() throws SmbException {
        if (this.sizeExpiration > System.currentTimeMillis()) {
            return this.size;
        }
        if (getType() == 8) {
            Trans2QueryFSInformationResponse trans2QueryFSInformationResponse = new Trans2QueryFSInformationResponse(1);
            send(new Trans2QueryFSInformation(1), trans2QueryFSInformationResponse);
            this.size = trans2QueryFSInformationResponse.info.getCapacity();
        } else if (getUncPath0().length() > 1 && this.type != 16) {
            this.size = queryPath(getUncPath0(), 258).getSize();
        } else {
            this.size = 0L;
        }
        this.sizeExpiration = System.currentTimeMillis() + attrExpirationPeriod;
        return this.size;
    }

    public long getDiskFreeSpace() throws SmbException {
        if (getType() != 8 && this.type != 1) {
            return 0L;
        }
        try {
            return queryFSInformation(1007);
        } catch (SmbException e) {
            int ntStatus = e.getNtStatus();
            if (ntStatus == -1073741823 || ntStatus == -1073741821) {
                return queryFSInformation(1);
            }
            throw e;
        }
    }

    private long queryFSInformation(int i) throws SmbException {
        Trans2QueryFSInformationResponse trans2QueryFSInformationResponse = new Trans2QueryFSInformationResponse(i);
        send(new Trans2QueryFSInformation(i), trans2QueryFSInformationResponse);
        if (this.type == 8) {
            this.size = trans2QueryFSInformationResponse.info.getCapacity();
            this.sizeExpiration = System.currentTimeMillis() + attrExpirationPeriod;
        }
        return trans2QueryFSInformationResponse.info.getFree();
    }

    public void mkdir() throws SmbException {
        String uncPath0 = getUncPath0();
        if (uncPath0.length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        if (LogStream.level >= 3) {
            log.println("mkdir: " + uncPath0);
        }
        send(new SmbComCreateDirectory(uncPath0), blank_resp());
        this.sizeExpiration = 0L;
        this.attrExpiration = 0L;
    }

    public void mkdirs() throws SmbException {
        try {
            SmbFile smbFile = new SmbFile(getParent(), this.auth);
            if (!smbFile.exists()) {
                smbFile.mkdirs();
            }
            mkdir();
        } catch (IOException unused) {
        }
    }

    public void createNewFile() throws SmbException {
        if (getUncPath0().length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        close(open0(51, 0, 128, 0), 0L);
    }

    void setPathInformation(int i, long j, long j2) throws SmbException {
        exists();
        int i2 = this.attributes & 16;
        int open0 = open0(1, 256, i2, i2 != 0 ? 1 : 64);
        send(new Trans2SetFileInformation(open0, i | i2, j, j2), new Trans2SetFileInformationResponse());
        close(open0, 0L);
        this.attrExpiration = 0L;
    }

    public void setCreateTime(long j) throws SmbException {
        if (getUncPath0().length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        setPathInformation(0, j, 0L);
    }

    public void setLastModified(long j) throws SmbException {
        if (getUncPath0().length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        setPathInformation(0, 0L, j);
    }

    public int getAttributes() throws SmbException {
        if (getUncPath0().length() == 1) {
            return 0;
        }
        exists();
        return this.attributes & ATTR_GET_MASK;
    }

    public void setAttributes(int i) throws SmbException {
        if (getUncPath0().length() == 1) {
            throw new SmbException("Invalid operation for workgroups, servers, or shares");
        }
        setPathInformation(i & ATTR_SET_MASK, 0L, 0L);
    }

    public void setReadOnly() throws SmbException {
        setAttributes(getAttributes() | 1);
    }

    public void setReadWrite() throws SmbException {
        setAttributes(getAttributes() & (-2));
    }

    public URL toURL() throws MalformedURLException {
        return this.url;
    }

    public int hashCode() {
        int hashCode;
        try {
            hashCode = getAddress().hashCode();
        } catch (UnknownHostException unused) {
            hashCode = getServer().toUpperCase().hashCode();
        }
        getUncPath0();
        return hashCode + this.canon.toUpperCase().hashCode();
    }

    protected boolean pathNamesPossiblyEqual(String str, String str2) {
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = str2.lastIndexOf(47);
        int length = str.length() - lastIndexOf;
        int length2 = str2.length() - lastIndexOf2;
        if (length > 1 && str.charAt(lastIndexOf + 1) == '.') {
            return true;
        }
        if (length2 <= 1 || str2.charAt(lastIndexOf2 + 1) != '.') {
            return length == length2 && str.regionMatches(true, lastIndexOf, str2, lastIndexOf2, length);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SmbFile)) {
            return false;
        }
        SmbFile smbFile = (SmbFile) obj;
        if (this == smbFile) {
            return true;
        }
        if (!pathNamesPossiblyEqual(this.url.getPath(), smbFile.url.getPath())) {
            return false;
        }
        getUncPath0();
        smbFile.getUncPath0();
        if (!this.canon.equalsIgnoreCase(smbFile.canon)) {
            return false;
        }
        try {
            return getAddress().equals(smbFile.getAddress());
        } catch (UnknownHostException unused) {
            return getServer().equalsIgnoreCase(smbFile.getServer());
        }
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.url.toString();
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        try {
            return (int) (length() & InternalZipConstants.ZIP_64_LIMIT);
        } catch (SmbException unused) {
            return 0;
        }
    }

    @Override // java.net.URLConnection
    public long getDate() {
        try {
            return lastModified();
        } catch (SmbException unused) {
            return 0L;
        }
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        try {
            return lastModified();
        } catch (SmbException unused) {
            return 0L;
        }
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        return new SmbFileInputStream(this);
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return new SmbFileOutputStream(this);
    }

    private void processAces(ACE[] aceArr, boolean z) throws IOException {
        String serverWithDfs = getServerWithDfs();
        int i = 0;
        if (z) {
            int length = aceArr.length;
            SID[] sidArr = new SID[length];
            for (int i2 = 0; i2 < aceArr.length; i2++) {
                sidArr[i2] = aceArr[i2].sid;
            }
            while (i < length) {
                int i3 = length - i;
                if (i3 > 64) {
                    i3 = 64;
                }
                SID.resolveSids(serverWithDfs, this.auth, sidArr, i, i3);
                i += 64;
            }
            return;
        }
        while (i < aceArr.length) {
            aceArr[i].sid.origin_server = serverWithDfs;
            aceArr[i].sid.origin_auth = this.auth;
            i++;
        }
    }

    public ACE[] getSecurity(boolean z) throws IOException {
        int open0 = open0(1, 131072, 0, isDirectory() ? 1 : 0);
        ServerMessageBlock ntTransQuerySecurityDesc = new NtTransQuerySecurityDesc(open0, 4);
        NtTransQuerySecurityDescResponse ntTransQuerySecurityDescResponse = new NtTransQuerySecurityDescResponse();
        try {
            send(ntTransQuerySecurityDesc, ntTransQuerySecurityDescResponse);
            close(open0, 0L);
            ACE[] aceArr = ntTransQuerySecurityDescResponse.securityDescriptor.aces;
            if (aceArr != null) {
                processAces(aceArr, z);
            }
            return aceArr;
        } catch (Throwable th) {
            close(open0, 0L);
            throw th;
        }
    }

    public ACE[] getShareSecurity(boolean z) throws IOException {
        this.url.getPath();
        resolveDfs(null);
        String serverWithDfs = getServerWithDfs();
        MsrpcShareGetInfo msrpcShareGetInfo = new MsrpcShareGetInfo(serverWithDfs, this.tree.share);
        DcerpcHandle handle = DcerpcHandle.getHandle("ncacn_np:" + serverWithDfs + "[\\PIPE\\srvsvc]", this.auth);
        try {
            handle.sendrecv(msrpcShareGetInfo);
            if (msrpcShareGetInfo.retval != 0) {
                throw new SmbException(msrpcShareGetInfo.retval, true);
            }
            ACE[] security = msrpcShareGetInfo.getSecurity();
            if (security != null) {
                processAces(security, z);
            }
            return security;
        } finally {
            try {
                handle.close();
            } catch (IOException e) {
                if (LogStream.level >= 1) {
                    e.printStackTrace(log);
                }
            }
        }
    }

    public ACE[] getSecurity() throws IOException {
        return getSecurity(false);
    }
}
