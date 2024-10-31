package jcifs.netbios;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import jcifs.Config;
import jcifs.util.Hexdump;
import kotlin.UByte;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public final class NbtAddress {
    private static final HashMap ADDRESS_CACHE;
    static final String ANY_HOSTS_NAME = "*\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
    public static final int B_NODE = 0;
    private static final int CACHE_POLICY;
    private static final NameServiceClient CLIENT;
    private static final int DEFAULT_CACHE_POLICY = 30;
    private static final int FOREVER = -1;
    public static final int H_NODE = 3;
    private static final HashMap LOOKUP_TABLE;
    public static final String MASTER_BROWSER_NAME = "\u0001\u0002__MSBROWSE__\u0002";
    public static final int M_NODE = 2;
    static final InetAddress[] NBNS = Config.getInetAddressArray("jcifs.netbios.wins", ",", new InetAddress[0]);
    public static final int P_NODE = 1;
    public static final String SMBSERVER_NAME = "*SMBSERVER     ";
    static final NbtAddress UNKNOWN_ADDRESS;
    static final byte[] UNKNOWN_MAC_ADDRESS;
    static final Name UNKNOWN_NAME;
    static NbtAddress localhost;
    private static int nbnsIndex;
    int address;
    String calledName;
    boolean groupName;
    Name hostName;
    boolean isActive;
    boolean isBeingDeleted;
    boolean isDataFromNodeStatus;
    boolean isInConflict;
    boolean isPermanent;
    byte[] macAddress;
    int nodeType;

    static {
        NameServiceClient nameServiceClient = new NameServiceClient();
        CLIENT = nameServiceClient;
        CACHE_POLICY = Config.getInt("jcifs.netbios.cachePolicy", 30);
        nbnsIndex = 0;
        HashMap hashMap = new HashMap();
        ADDRESS_CACHE = hashMap;
        LOOKUP_TABLE = new HashMap();
        Name name = new Name("0.0.0.0", 0, null);
        UNKNOWN_NAME = name;
        NbtAddress nbtAddress = new NbtAddress(name, 0, false, 0);
        UNKNOWN_ADDRESS = nbtAddress;
        UNKNOWN_MAC_ADDRESS = new byte[]{0, 0, 0, 0, 0, 0};
        hashMap.put(name, new CacheEntry(name, nbtAddress, -1L));
        InetAddress inetAddress = nameServiceClient.laddr;
        if (inetAddress == null) {
            try {
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException unused) {
                }
            } catch (UnknownHostException unused2) {
                inetAddress = InetAddress.getByName("127.0.0.1");
            }
        }
        String property = Config.getProperty("jcifs.netbios.hostname", null);
        if (property == null || property.length() == 0) {
            byte[] address = inetAddress.getAddress();
            property = "JCIFS" + (address[2] & UByte.MAX_VALUE) + "_" + (address[3] & UByte.MAX_VALUE) + "_" + Hexdump.toHexString((int) (Math.random() * 255.0d), 2);
        }
        Name name2 = new Name(property, 0, Config.getProperty("jcifs.netbios.scope", null));
        NbtAddress nbtAddress2 = new NbtAddress(name2, inetAddress.hashCode(), false, 0, false, false, true, false, UNKNOWN_MAC_ADDRESS);
        localhost = nbtAddress2;
        cacheAddress(name2, nbtAddress2, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class CacheEntry {
        NbtAddress address;
        long expiration;
        Name hostName;

        CacheEntry(Name name, NbtAddress nbtAddress, long j) {
            this.hostName = name;
            this.address = nbtAddress;
            this.expiration = j;
        }
    }

    static void cacheAddress(Name name, NbtAddress nbtAddress) {
        int i = CACHE_POLICY;
        if (i == 0) {
            return;
        }
        cacheAddress(name, nbtAddress, i != -1 ? System.currentTimeMillis() + (i * 1000) : -1L);
    }

    static void cacheAddress(Name name, NbtAddress nbtAddress, long j) {
        if (CACHE_POLICY == 0) {
            return;
        }
        HashMap hashMap = ADDRESS_CACHE;
        synchronized (hashMap) {
            CacheEntry cacheEntry = (CacheEntry) hashMap.get(name);
            if (cacheEntry == null) {
                hashMap.put(name, new CacheEntry(name, nbtAddress, j));
            } else {
                cacheEntry.address = nbtAddress;
                cacheEntry.expiration = j;
            }
        }
    }

    static void cacheAddressArray(NbtAddress[] nbtAddressArr) {
        int i = CACHE_POLICY;
        if (i == 0) {
            return;
        }
        long currentTimeMillis = i != -1 ? System.currentTimeMillis() + (i * 1000) : -1L;
        synchronized (ADDRESS_CACHE) {
            for (int i2 = 0; i2 < nbtAddressArr.length; i2++) {
                HashMap hashMap = ADDRESS_CACHE;
                CacheEntry cacheEntry = (CacheEntry) hashMap.get(nbtAddressArr[i2].hostName);
                if (cacheEntry == null) {
                    NbtAddress nbtAddress = nbtAddressArr[i2];
                    hashMap.put(nbtAddressArr[i2].hostName, new CacheEntry(nbtAddress.hostName, nbtAddress, currentTimeMillis));
                } else {
                    cacheEntry.address = nbtAddressArr[i2];
                    cacheEntry.expiration = currentTimeMillis;
                }
            }
        }
    }

    static NbtAddress getCachedAddress(Name name) {
        NbtAddress nbtAddress;
        if (CACHE_POLICY == 0) {
            return null;
        }
        HashMap hashMap = ADDRESS_CACHE;
        synchronized (hashMap) {
            CacheEntry cacheEntry = (CacheEntry) hashMap.get(name);
            if (cacheEntry != null && cacheEntry.expiration < System.currentTimeMillis() && cacheEntry.expiration >= 0) {
                cacheEntry = null;
            }
            nbtAddress = cacheEntry != null ? cacheEntry.address : null;
        }
        return nbtAddress;
    }

    static NbtAddress doNameQuery(Name name, InetAddress inetAddress) throws UnknownHostException {
        if (name.hexCode == 29 && inetAddress == null) {
            inetAddress = CLIENT.baddr;
        }
        name.srcHashCode = inetAddress != null ? inetAddress.hashCode() : 0;
        NbtAddress cachedAddress = getCachedAddress(name);
        if (cachedAddress == null) {
            cachedAddress = (NbtAddress) checkLookupTable(name);
            try {
                if (cachedAddress == null) {
                    try {
                        cachedAddress = CLIENT.getByName(name, inetAddress);
                    } catch (UnknownHostException unused) {
                        cachedAddress = UNKNOWN_ADDRESS;
                    }
                }
            } finally {
                cacheAddress(name, cachedAddress);
                updateLookupTable(name);
            }
        }
        if (cachedAddress != UNKNOWN_ADDRESS) {
            return cachedAddress;
        }
        throw new UnknownHostException(name.toString());
    }

    private static Object checkLookupTable(Name name) {
        HashMap hashMap;
        HashMap hashMap2 = LOOKUP_TABLE;
        synchronized (hashMap2) {
            if (!hashMap2.containsKey(name)) {
                hashMap2.put(name, name);
                return null;
            }
            while (true) {
                hashMap = LOOKUP_TABLE;
                if (!hashMap.containsKey(name)) {
                    break;
                }
                try {
                    hashMap.wait();
                } catch (InterruptedException unused) {
                }
            }
            NbtAddress cachedAddress = getCachedAddress(name);
            if (cachedAddress == null) {
                synchronized (hashMap) {
                    hashMap.put(name, name);
                }
            }
            return cachedAddress;
        }
    }

    private static void updateLookupTable(Name name) {
        HashMap hashMap = LOOKUP_TABLE;
        synchronized (hashMap) {
            hashMap.remove(name);
            hashMap.notifyAll();
        }
    }

    public static NbtAddress getLocalHost() throws UnknownHostException {
        return localhost;
    }

    public static Name getLocalName() {
        return localhost.hostName;
    }

    public static NbtAddress getByName(String str) throws UnknownHostException {
        return getByName(str, 0, null);
    }

    public static NbtAddress getByName(String str, int i, String str2) throws UnknownHostException {
        return getByName(str, i, str2, null);
    }

    public static NbtAddress getByName(String str, int i, String str2, InetAddress inetAddress) throws UnknownHostException {
        if (str == null || str.length() == 0) {
            return getLocalHost();
        }
        if (!Character.isDigit(str.charAt(0))) {
            return doNameQuery(new Name(str, i, str2), inetAddress);
        }
        char[] charArray = str.toCharArray();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < charArray.length) {
            char c = charArray[i2];
            if (c < '0' || c > '9') {
                return doNameQuery(new Name(str, i, str2), inetAddress);
            }
            int i5 = 0;
            while (c != '.') {
                if (c < '0' || c > '9') {
                    return doNameQuery(new Name(str, i, str2), inetAddress);
                }
                i5 = ((i5 * 10) + c) - 48;
                i2++;
                if (i2 >= charArray.length) {
                    break;
                }
                c = charArray[i2];
            }
            if (i5 > 255) {
                return doNameQuery(new Name(str, i, str2), inetAddress);
            }
            i4 = (i4 << 8) + i5;
            i3++;
            i2++;
        }
        if (i3 != 4 || str.endsWith(".")) {
            return doNameQuery(new Name(str, i, str2), inetAddress);
        }
        return new NbtAddress(UNKNOWN_NAME, i4, false, 0);
    }

    public static NbtAddress[] getAllByName(String str, int i, String str2, InetAddress inetAddress) throws UnknownHostException {
        return CLIENT.getAllByName(new Name(str, i, str2), inetAddress);
    }

    public static NbtAddress[] getAllByAddress(String str) throws UnknownHostException {
        return getAllByAddress(getByName(str, 0, null));
    }

    public static NbtAddress[] getAllByAddress(String str, int i, String str2) throws UnknownHostException {
        return getAllByAddress(getByName(str, i, str2));
    }

    public static NbtAddress[] getAllByAddress(NbtAddress nbtAddress) throws UnknownHostException {
        String str;
        try {
            NbtAddress[] nodeStatus = CLIENT.getNodeStatus(nbtAddress);
            cacheAddressArray(nodeStatus);
            return nodeStatus;
        } catch (UnknownHostException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("no name with type 0x");
            sb.append(Hexdump.toHexString(nbtAddress.hostName.hexCode, 2));
            if (nbtAddress.hostName.scope == null || nbtAddress.hostName.scope.length() == 0) {
                str = " with no scope";
            } else {
                str = " with scope " + nbtAddress.hostName.scope;
            }
            sb.append(str);
            sb.append(" for host ");
            sb.append(nbtAddress.getHostAddress());
            throw new UnknownHostException(sb.toString());
        }
    }

    public static InetAddress getWINSAddress() {
        InetAddress[] inetAddressArr = NBNS;
        if (inetAddressArr.length == 0) {
            return null;
        }
        return inetAddressArr[nbnsIndex];
    }

    public static boolean isWINS(InetAddress inetAddress) {
        int i = 0;
        while (inetAddress != null) {
            InetAddress[] inetAddressArr = NBNS;
            if (i >= inetAddressArr.length) {
                break;
            }
            if (inetAddress.hashCode() == inetAddressArr[i].hashCode()) {
                return true;
            }
            i++;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InetAddress switchWINS() {
        int i = nbnsIndex;
        int i2 = i + 1;
        InetAddress[] inetAddressArr = NBNS;
        int i3 = i2 < inetAddressArr.length ? i + 1 : 0;
        nbnsIndex = i3;
        if (inetAddressArr.length == 0) {
            return null;
        }
        return inetAddressArr[i3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NbtAddress(Name name, int i, boolean z, int i2) {
        this.hostName = name;
        this.address = i;
        this.groupName = z;
        this.nodeType = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NbtAddress(Name name, int i, boolean z, int i2, boolean z2, boolean z3, boolean z4, boolean z5, byte[] bArr) {
        this.hostName = name;
        this.address = i;
        this.groupName = z;
        this.nodeType = i2;
        this.isBeingDeleted = z2;
        this.isInConflict = z3;
        this.isActive = z4;
        this.isPermanent = z5;
        this.macAddress = bArr;
        this.isDataFromNodeStatus = true;
    }

    public String firstCalledName() {
        String str = this.hostName.name;
        this.calledName = str;
        int i = 0;
        if (Character.isDigit(str.charAt(0))) {
            int length = this.calledName.length();
            char[] charArray = this.calledName.toCharArray();
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                int i3 = i + 1;
                if (!Character.isDigit(charArray[i])) {
                    break;
                }
                if (i3 == length && i2 == 3) {
                    this.calledName = SMBSERVER_NAME;
                    break;
                }
                if (i3 >= length || charArray[i3] != '.') {
                    i = i3;
                } else {
                    i2++;
                    i = i3 + 1;
                }
            }
        } else {
            switch (this.hostName.hexCode) {
                case 27:
                case 28:
                case 29:
                    this.calledName = SMBSERVER_NAME;
                    break;
            }
        }
        return this.calledName;
    }

    public String nextCalledName() {
        if (this.calledName == this.hostName.name) {
            this.calledName = SMBSERVER_NAME;
        } else if (this.calledName == SMBSERVER_NAME) {
            try {
                NbtAddress[] nodeStatus = CLIENT.getNodeStatus(this);
                if (this.hostName.hexCode == 29) {
                    for (int i = 0; i < nodeStatus.length; i++) {
                        if (nodeStatus[i].hostName.hexCode == 32) {
                            return nodeStatus[i].hostName.name;
                        }
                    }
                    return null;
                }
                if (this.isDataFromNodeStatus) {
                    this.calledName = null;
                    return this.hostName.name;
                }
            } catch (UnknownHostException unused) {
                this.calledName = null;
            }
        } else {
            this.calledName = null;
        }
        return this.calledName;
    }

    void checkData() throws UnknownHostException {
        if (this.hostName == UNKNOWN_NAME) {
            getAllByAddress(this);
        }
    }

    void checkNodeStatusData() throws UnknownHostException {
        if (this.isDataFromNodeStatus) {
            return;
        }
        getAllByAddress(this);
    }

    public boolean isGroupAddress() throws UnknownHostException {
        checkData();
        return this.groupName;
    }

    public int getNodeType() throws UnknownHostException {
        checkData();
        return this.nodeType;
    }

    public boolean isBeingDeleted() throws UnknownHostException {
        checkNodeStatusData();
        return this.isBeingDeleted;
    }

    public boolean isInConflict() throws UnknownHostException {
        checkNodeStatusData();
        return this.isInConflict;
    }

    public boolean isActive() throws UnknownHostException {
        checkNodeStatusData();
        return this.isActive;
    }

    public boolean isPermanent() throws UnknownHostException {
        checkNodeStatusData();
        return this.isPermanent;
    }

    public byte[] getMacAddress() throws UnknownHostException {
        checkNodeStatusData();
        return this.macAddress;
    }

    public String getHostName() {
        Name name = this.hostName;
        if (name == UNKNOWN_NAME) {
            return getHostAddress();
        }
        return name.name;
    }

    public byte[] getAddress() {
        int i = this.address;
        return new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    public InetAddress getInetAddress() throws UnknownHostException {
        return InetAddress.getByName(getHostAddress());
    }

    public String getHostAddress() {
        return ((this.address >>> 24) & 255) + "." + ((this.address >>> 16) & 255) + "." + ((this.address >>> 8) & 255) + "." + ((this.address >>> 0) & 255);
    }

    public int getNameType() {
        return this.hostName.hexCode;
    }

    public int hashCode() {
        return this.address;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof NbtAddress) && ((NbtAddress) obj).address == this.address;
    }

    public String toString() {
        return this.hostName.toString() + InternalZipConstants.ZIP_FILE_SEPARATOR + getHostAddress();
    }
}
