package jcifs.netbios;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.StringTokenizer;
import jcifs.Config;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NameServiceClient implements Runnable {
    static final int DEFAULT_RCV_BUF_SIZE = 576;
    static final int DEFAULT_RETRY_COUNT = 2;
    static final int DEFAULT_RETRY_TIMEOUT = 3000;
    static final int DEFAULT_SND_BUF_SIZE = 576;
    static final int DEFAULT_SO_TIMEOUT = 5000;
    static final int NAME_SERVICE_UDP_PORT = 137;
    static final int RESOLVER_BCAST = 2;
    static final int RESOLVER_LMHOSTS = 1;
    static final int RESOLVER_WINS = 3;
    private final Object LOCK;
    InetAddress baddr;
    private int closeTimeout;
    private DatagramPacket in;
    InetAddress laddr;
    private int lport;
    private int nextNameTrnId;
    private DatagramPacket out;
    private byte[] rcv_buf;
    private int[] resolveOrder;
    private HashMap responseTable;
    private byte[] snd_buf;
    private DatagramSocket socket;
    private Thread thread;
    private static final int SND_BUF_SIZE = Config.getInt("jcifs.netbios.snd_buf_size", 576);
    private static final int RCV_BUF_SIZE = Config.getInt("jcifs.netbios.rcv_buf_size", 576);
    private static final int SO_TIMEOUT = Config.getInt("jcifs.netbios.soTimeout", 5000);
    private static final int RETRY_COUNT = Config.getInt("jcifs.netbios.retryCount", 2);
    private static final int RETRY_TIMEOUT = Config.getInt("jcifs.netbios.retryTimeout", 3000);
    private static final int LPORT = Config.getInt("jcifs.netbios.lport", 0);
    private static final InetAddress LADDR = Config.getInetAddress("jcifs.netbios.laddr", null);
    private static final String RO = Config.getProperty("jcifs.resolveOrder");
    private static LogStream log = LogStream.getInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public NameServiceClient() {
        this(LPORT, LADDR);
    }

    NameServiceClient(int i, InetAddress inetAddress) {
        int i2;
        this.LOCK = new Object();
        this.responseTable = new HashMap();
        this.nextNameTrnId = 0;
        this.lport = i;
        this.laddr = inetAddress;
        try {
            this.baddr = Config.getInetAddress("jcifs.netbios.baddr", InetAddress.getByName("255.255.255.255"));
        } catch (UnknownHostException unused) {
        }
        int i3 = SND_BUF_SIZE;
        this.snd_buf = new byte[i3];
        int i4 = RCV_BUF_SIZE;
        this.rcv_buf = new byte[i4];
        this.out = new DatagramPacket(this.snd_buf, i3, this.baddr, NAME_SERVICE_UDP_PORT);
        this.in = new DatagramPacket(this.rcv_buf, i4);
        String str = RO;
        if (str == null || str.length() == 0) {
            if (NbtAddress.getWINSAddress() == null) {
                this.resolveOrder = r10;
                int[] iArr = {1, 2};
                return;
            } else {
                this.resolveOrder = r10;
                int[] iArr2 = {1, 3, 2};
                return;
            }
        }
        int[] iArr3 = new int[3];
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int i5 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (trim.equalsIgnoreCase("LMHOSTS")) {
                i2 = i5 + 1;
                iArr3[i5] = 1;
            } else if (trim.equalsIgnoreCase("WINS")) {
                if (NbtAddress.getWINSAddress() == null) {
                    if (LogStream.level > 1) {
                        log.println("NetBIOS resolveOrder specifies WINS however the jcifs.netbios.wins property has not been set");
                    }
                } else {
                    i2 = i5 + 1;
                    iArr3[i5] = 3;
                }
            } else if (trim.equalsIgnoreCase("BCAST")) {
                i2 = i5 + 1;
                iArr3[i5] = 2;
            } else if (!trim.equalsIgnoreCase("DNS") && LogStream.level > 1) {
                log.println("unknown resolver method: " + trim);
            }
            i5 = i2;
        }
        int[] iArr4 = new int[i5];
        this.resolveOrder = iArr4;
        System.arraycopy(iArr3, 0, iArr4, 0, i5);
    }

    int getNextNameTrnId() {
        int i = this.nextNameTrnId + 1;
        this.nextNameTrnId = i;
        if ((i & 65535) == 0) {
            this.nextNameTrnId = 1;
        }
        return this.nextNameTrnId;
    }

    void ensureOpen(int i) throws IOException {
        this.closeTimeout = 0;
        int i2 = SO_TIMEOUT;
        if (i2 != 0) {
            this.closeTimeout = Math.max(i2, i);
        }
        if (this.socket == null) {
            this.socket = new DatagramSocket(this.lport, this.laddr);
            Thread thread = new Thread(this, "JCIFS-NameServiceClient");
            this.thread = thread;
            thread.setDaemon(true);
            this.thread.start();
        }
    }

    void tryClose() {
        synchronized (this.LOCK) {
            DatagramSocket datagramSocket = this.socket;
            if (datagramSocket != null) {
                datagramSocket.close();
                this.socket = null;
            }
            this.thread = null;
            this.responseTable.clear();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.thread == Thread.currentThread()) {
            try {
                try {
                    this.in.setLength(RCV_BUF_SIZE);
                    this.socket.setSoTimeout(this.closeTimeout);
                    this.socket.receive(this.in);
                    if (LogStream.level > 3) {
                        log.println("NetBIOS: new data read from socket");
                    }
                    NameServicePacket nameServicePacket = (NameServicePacket) this.responseTable.get(new Integer(NameServicePacket.readNameTrnId(this.rcv_buf, 0)));
                    if (nameServicePacket != null && !nameServicePacket.received) {
                        synchronized (nameServicePacket) {
                            nameServicePacket.readWireFormat(this.rcv_buf, 0);
                            nameServicePacket.received = true;
                            if (LogStream.level > 3) {
                                log.println(nameServicePacket);
                                Hexdump.hexdump(log, this.rcv_buf, 0, this.in.getLength());
                            }
                            nameServicePacket.notify();
                        }
                    }
                } catch (SocketTimeoutException unused) {
                } catch (Exception e) {
                    if (LogStream.level > 2) {
                        e.printStackTrace(log);
                    }
                }
            } finally {
                tryClose();
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:71:0x00b3
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    void send(jcifs.netbios.NameServicePacket r11, jcifs.netbios.NameServicePacket r12, int r13) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.netbios.NameServiceClient.send(jcifs.netbios.NameServicePacket, jcifs.netbios.NameServicePacket, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NbtAddress[] getAllByName(Name name, InetAddress inetAddress) throws UnknownHostException {
        int i;
        NameQueryRequest nameQueryRequest = new NameQueryRequest(name);
        NameQueryResponse nameQueryResponse = new NameQueryResponse();
        if (inetAddress == null) {
            inetAddress = NbtAddress.getWINSAddress();
        }
        nameQueryRequest.addr = inetAddress;
        nameQueryRequest.isBroadcast = nameQueryRequest.addr == null;
        if (nameQueryRequest.isBroadcast) {
            nameQueryRequest.addr = this.baddr;
            i = RETRY_COUNT;
        } else {
            nameQueryRequest.isBroadcast = false;
            i = 1;
        }
        do {
            try {
                send(nameQueryRequest, nameQueryResponse, RETRY_TIMEOUT);
                if (!nameQueryResponse.received || nameQueryResponse.resultCode != 0) {
                    i--;
                    if (i <= 0) {
                        break;
                    }
                } else {
                    return nameQueryResponse.addrEntry;
                }
            } catch (IOException e) {
                if (LogStream.level > 1) {
                    e.printStackTrace(log);
                }
                throw new UnknownHostException(name.name);
            }
        } while (nameQueryRequest.isBroadcast);
        throw new UnknownHostException(name.name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00dc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public jcifs.netbios.NbtAddress getByName(jcifs.netbios.Name r8, java.net.InetAddress r9) throws java.net.UnknownHostException {
        /*
            Method dump skipped, instructions count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.netbios.NameServiceClient.getByName(jcifs.netbios.Name, java.net.InetAddress):jcifs.netbios.NbtAddress");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NbtAddress[] getNodeStatus(NbtAddress nbtAddress) throws UnknownHostException {
        NodeStatusResponse nodeStatusResponse = new NodeStatusResponse(nbtAddress);
        NodeStatusRequest nodeStatusRequest = new NodeStatusRequest(new Name("*\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000", 0, null));
        nodeStatusRequest.addr = nbtAddress.getInetAddress();
        int i = RETRY_COUNT;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                try {
                    send(nodeStatusRequest, nodeStatusResponse, RETRY_TIMEOUT);
                    if (nodeStatusResponse.received && nodeStatusResponse.resultCode == 0) {
                        int hashCode = nodeStatusRequest.addr.hashCode();
                        for (int i3 = 0; i3 < nodeStatusResponse.addressArray.length; i3++) {
                            nodeStatusResponse.addressArray[i3].hostName.srcHashCode = hashCode;
                        }
                        return nodeStatusResponse.addressArray;
                    }
                    i = i2;
                } catch (IOException e) {
                    if (LogStream.level > 1) {
                        e.printStackTrace(log);
                    }
                    throw new UnknownHostException(nbtAddress.toString());
                }
            } else {
                throw new UnknownHostException(nbtAddress.hostName.name);
            }
        }
    }
}
