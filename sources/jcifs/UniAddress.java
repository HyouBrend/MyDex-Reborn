package jcifs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import jcifs.netbios.Lmhosts;
import jcifs.netbios.NbtAddress;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class UniAddress {
    private static final int RESOLVER_BCAST = 1;
    private static final int RESOLVER_DNS = 2;
    private static final int RESOLVER_LMHOSTS = 3;
    private static final int RESOLVER_WINS = 0;
    private static InetAddress baddr;
    private static LogStream log = LogStream.getInstance();
    private static int[] resolveOrder;
    Object addr;
    String calledName;

    static {
        int i;
        String property = Config.getProperty("jcifs.resolveOrder");
        InetAddress wINSAddress = NbtAddress.getWINSAddress();
        try {
            baddr = Config.getInetAddress("jcifs.netbios.baddr", InetAddress.getByName("255.255.255.255"));
        } catch (UnknownHostException unused) {
        }
        if (property == null || property.length() == 0) {
            if (wINSAddress == null) {
                resolveOrder = r0;
                int[] iArr = {3, 2, 1};
                return;
            } else {
                resolveOrder = r0;
                int[] iArr2 = {3, 0, 2, 1};
                return;
            }
        }
        int[] iArr3 = new int[4];
        StringTokenizer stringTokenizer = new StringTokenizer(property, ",");
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (trim.equalsIgnoreCase("LMHOSTS")) {
                i = i2 + 1;
                iArr3[i2] = 3;
            } else if (trim.equalsIgnoreCase("WINS")) {
                if (wINSAddress == null) {
                    if (LogStream.level > 1) {
                        log.println("UniAddress resolveOrder specifies WINS however the jcifs.netbios.wins property has not been set");
                    }
                } else {
                    i = i2 + 1;
                    iArr3[i2] = 0;
                }
            } else if (trim.equalsIgnoreCase("BCAST")) {
                i = i2 + 1;
                iArr3[i2] = 1;
            } else if (trim.equalsIgnoreCase("DNS")) {
                i = i2 + 1;
                iArr3[i2] = 2;
            } else if (LogStream.level > 1) {
                log.println("unknown resolver method: " + trim);
            }
            i2 = i;
        }
        int[] iArr4 = new int[i2];
        resolveOrder = iArr4;
        System.arraycopy(iArr3, 0, iArr4, 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Sem {
        int count;

        Sem(int i) {
            this.count = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class QueryThread extends Thread {
        NbtAddress ans;
        String host;
        String scope;
        Sem sem;
        InetAddress svr;
        int type;
        UnknownHostException uhe;

        QueryThread(Sem sem, String str, int i, String str2, InetAddress inetAddress) {
            super("JCIFS-QueryThread: " + str);
            this.ans = null;
            this.sem = sem;
            this.host = str;
            this.type = i;
            this.scope = str2;
            this.svr = inetAddress;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    try {
                        this.ans = NbtAddress.getByName(this.host, this.type, this.scope, this.svr);
                        synchronized (this.sem) {
                            Sem sem = this.sem;
                            sem.count--;
                            this.sem.notify();
                        }
                    } catch (UnknownHostException e) {
                        this.uhe = e;
                        synchronized (this.sem) {
                            Sem sem2 = this.sem;
                            sem2.count--;
                            this.sem.notify();
                        }
                    }
                } catch (Exception e2) {
                    this.uhe = new UnknownHostException(e2.getMessage());
                    synchronized (this.sem) {
                        Sem sem3 = this.sem;
                        sem3.count--;
                        this.sem.notify();
                    }
                }
            } catch (Throwable th) {
                synchronized (this.sem) {
                    Sem sem4 = this.sem;
                    sem4.count--;
                    this.sem.notify();
                    throw th;
                }
            }
        }
    }

    static NbtAddress lookupServerOrWorkgroup(String str, InetAddress inetAddress) throws UnknownHostException {
        Sem sem = new Sem(2);
        QueryThread queryThread = new QueryThread(sem, str, NbtAddress.isWINS(inetAddress) ? 27 : 29, null, inetAddress);
        QueryThread queryThread2 = new QueryThread(sem, str, 32, null, inetAddress);
        queryThread.setDaemon(true);
        queryThread2.setDaemon(true);
        try {
            synchronized (sem) {
                queryThread.start();
                queryThread2.start();
                while (sem.count > 0 && queryThread.ans == null && queryThread2.ans == null) {
                    sem.wait();
                }
            }
            if (queryThread.ans != null) {
                return queryThread.ans;
            }
            if (queryThread2.ans != null) {
                return queryThread2.ans;
            }
            throw queryThread.uhe;
        } catch (InterruptedException unused) {
            throw new UnknownHostException(str);
        }
    }

    public static UniAddress getByName(String str) throws UnknownHostException {
        return getByName(str, false);
    }

    static boolean isDotQuadIP(String str) {
        if (Character.isDigit(str.charAt(0))) {
            int length = str.length();
            char[] charArray = str.toCharArray();
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                if (!Character.isDigit(charArray[i])) {
                    break;
                }
                if (i3 == length && i2 == 3) {
                    return true;
                }
                if (i3 >= length || charArray[i3] != '.') {
                    i = i3;
                } else {
                    i2++;
                    i = i3 + 1;
                }
            }
        }
        return false;
    }

    static boolean isAllDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static UniAddress getByName(String str, boolean z) throws UnknownHostException {
        return getAllByName(str, z)[0];
    }

    public static UniAddress[] getAllByName(String str, boolean z) throws UnknownHostException {
        int i;
        NbtAddress byName;
        if (str == null || str.length() == 0) {
            throw new UnknownHostException();
        }
        if (isDotQuadIP(str)) {
            return new UniAddress[]{new UniAddress(NbtAddress.getByName(str))};
        }
        int i2 = 0;
        while (true) {
            int[] iArr = resolveOrder;
            if (i2 < iArr.length) {
                try {
                    i = iArr[i2];
                } catch (IOException unused) {
                }
                if (i == 0) {
                    if (str != NbtAddress.MASTER_BROWSER_NAME && str.length() <= 15) {
                        if (z) {
                            byName = lookupServerOrWorkgroup(str, NbtAddress.getWINSAddress());
                        } else {
                            byName = NbtAddress.getByName(str, 32, null, NbtAddress.getWINSAddress());
                        }
                    }
                    i2++;
                } else if (i != 1) {
                    if (i == 2) {
                        if (isAllDigits(str)) {
                            throw new UnknownHostException(str);
                        }
                        InetAddress[] allByName = InetAddress.getAllByName(str);
                        UniAddress[] uniAddressArr = new UniAddress[allByName.length];
                        for (int i3 = 0; i3 < allByName.length; i3++) {
                            uniAddressArr[i3] = new UniAddress(allByName[i3]);
                        }
                        return uniAddressArr;
                    }
                    if (i == 3) {
                        byName = Lmhosts.getByName(str);
                        if (byName != null) {
                            break;
                        }
                        i2++;
                    } else {
                        throw new UnknownHostException(str);
                    }
                } else if (str.length() > 15) {
                    i2++;
                } else if (z) {
                    byName = lookupServerOrWorkgroup(str, baddr);
                } else {
                    byName = NbtAddress.getByName(str, 32, null, baddr);
                }
            } else {
                throw new UnknownHostException(str);
            }
        }
        return new UniAddress[]{new UniAddress(byName)};
    }

    public UniAddress(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        this.addr = obj;
    }

    public int hashCode() {
        return this.addr.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof UniAddress) && this.addr.equals(((UniAddress) obj).addr);
    }

    public String firstCalledName() {
        Object obj = this.addr;
        if (obj instanceof NbtAddress) {
            return ((NbtAddress) obj).firstCalledName();
        }
        String hostName = ((InetAddress) obj).getHostName();
        this.calledName = hostName;
        if (isDotQuadIP(hostName)) {
            this.calledName = NbtAddress.SMBSERVER_NAME;
        } else {
            int indexOf = this.calledName.indexOf(46);
            if (indexOf > 1 && indexOf < 15) {
                this.calledName = this.calledName.substring(0, indexOf).toUpperCase();
            } else if (this.calledName.length() > 15) {
                this.calledName = NbtAddress.SMBSERVER_NAME;
            } else {
                this.calledName = this.calledName.toUpperCase();
            }
        }
        return this.calledName;
    }

    public String nextCalledName() {
        Object obj = this.addr;
        if (obj instanceof NbtAddress) {
            return ((NbtAddress) obj).nextCalledName();
        }
        if (this.calledName == NbtAddress.SMBSERVER_NAME) {
            return null;
        }
        this.calledName = NbtAddress.SMBSERVER_NAME;
        return NbtAddress.SMBSERVER_NAME;
    }

    public Object getAddress() {
        return this.addr;
    }

    public String getHostName() {
        Object obj = this.addr;
        if (obj instanceof NbtAddress) {
            return ((NbtAddress) obj).getHostName();
        }
        return ((InetAddress) obj).getHostName();
    }

    public String getHostAddress() {
        Object obj = this.addr;
        if (obj instanceof NbtAddress) {
            return ((NbtAddress) obj).getHostAddress();
        }
        return ((InetAddress) obj).getHostAddress();
    }

    public String toString() {
        return this.addr.toString();
    }
}
