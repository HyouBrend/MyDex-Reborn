package jcifs.smb;

import jcifs.util.LogStream;
import kotlin.UByte;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbTree {
    private static int tree_conn_counter;
    int connectionState;
    boolean inDfs;
    boolean inDomainDfs;
    String service;
    String service0;
    SmbSession session;
    String share;
    int tid;
    int tree_num;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbTree(SmbSession smbSession, String str, String str2) {
        this.service = "?????";
        this.session = smbSession;
        this.share = str.toUpperCase();
        if (str2 != null && !str2.startsWith("??")) {
            this.service = str2;
        }
        this.service0 = this.service;
        this.connectionState = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(String str, String str2) {
        return this.share.equalsIgnoreCase(str) && (str2 == null || str2.startsWith("??") || this.service.equalsIgnoreCase(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SmbTree)) {
            return false;
        }
        SmbTree smbTree = (SmbTree) obj;
        return matches(smbTree.share, smbTree.service);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void send(ServerMessageBlock serverMessageBlock, ServerMessageBlock serverMessageBlock2) throws SmbException {
        byte b;
        synchronized (this.session.transport()) {
            if (serverMessageBlock2 != null) {
                serverMessageBlock2.received = false;
            }
            treeConnect(serverMessageBlock, serverMessageBlock2);
            if (serverMessageBlock != null && (serverMessageBlock2 == null || !serverMessageBlock2.received)) {
                if (!this.service.equals("A:") && (b = serverMessageBlock.command) != -94 && b != 4) {
                    if (b == 37 || b == 50) {
                        int i = ((SmbComTransaction) serverMessageBlock).subCommand & UByte.MAX_VALUE;
                        if (i != 0 && i != 16 && i != 35 && i != 38 && i != 104 && i != 215 && i != 83 && i != 84) {
                            throw new SmbException("Invalid operation for " + this.service + " service");
                        }
                    } else if (b != 113) {
                        switch (b) {
                            case 45:
                            case 46:
                            case 47:
                                break;
                            default:
                                throw new SmbException("Invalid operation for " + this.service + " service" + serverMessageBlock);
                        }
                    }
                }
                serverMessageBlock.tid = this.tid;
                if (this.inDfs && !this.service.equals("IPC") && serverMessageBlock.path != null && serverMessageBlock.path.length() > 0) {
                    serverMessageBlock.flags2 = 4096;
                    serverMessageBlock.path = '\\' + this.session.transport().tconHostName + '\\' + this.share + serverMessageBlock.path;
                }
                try {
                    this.session.send(serverMessageBlock, serverMessageBlock2);
                } catch (SmbException e) {
                    if (e.getNtStatus() == -1073741623) {
                        treeDisconnect(true);
                    }
                    throw e;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0027, code lost:
    
        r7.connectionState = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x002a, code lost:
    
        r7.session.transport.connect();
        r3 = "\\\\" + r7.session.transport.tconHostName + '\\' + r7.share;
        r7.service = r7.service0;
        r4 = r7.session.transport;
        r4 = jcifs.smb.SmbTransport.log;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
    
        if (jcifs.util.LogStream.level < 4) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
    
        r4 = r7.session.transport;
        jcifs.smb.SmbTransport.log.println("treeConnect: unc=" + r3 + ",service=" + r7.service);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0085, code lost:
    
        r4 = new jcifs.smb.SmbComTreeConnectAndXResponse(r9);
        r7.session.send(new jcifs.smb.SmbComTreeConnectAndX(r7.session, r3, r7.service, r8), r4);
        r7.tid = r4.tid;
        r7.service = r4.service;
        r7.inDfs = r4.shareIsInDfs;
        r8 = jcifs.smb.SmbTree.tree_conn_counter;
        jcifs.smb.SmbTree.tree_conn_counter = r8 + 1;
        r7.tree_num = r8;
        r7.connectionState = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00af, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b0, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b1, code lost:
    
        treeDisconnect(true);
        r7.connectionState = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b7, code lost:
    
        throw r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void treeConnect(jcifs.smb.ServerMessageBlock r8, jcifs.smb.ServerMessageBlock r9) throws jcifs.smb.SmbException {
        /*
            r7 = this;
            jcifs.smb.SmbSession r0 = r7.session
            jcifs.smb.SmbTransport r0 = r0.transport()
            monitor-enter(r0)
        L7:
            int r1 = r7.connectionState     // Catch: java.lang.Throwable -> Lb8
            r2 = 2
            if (r1 == 0) goto L27
            if (r1 == r2) goto L25
            r2 = 3
            if (r1 != r2) goto L12
            goto L25
        L12:
            jcifs.smb.SmbSession r1 = r7.session     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> Lb8
            jcifs.smb.SmbTransport r1 = r1.transport     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> Lb8
            r1.wait()     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> Lb8
            goto L7
        L1a:
            r8 = move-exception
            jcifs.smb.SmbException r9 = new jcifs.smb.SmbException     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r1 = r8.getMessage()     // Catch: java.lang.Throwable -> Lb8
            r9.<init>(r1, r8)     // Catch: java.lang.Throwable -> Lb8
            throw r9     // Catch: java.lang.Throwable -> Lb8
        L25:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb8
            return
        L27:
            r1 = 1
            r7.connectionState = r1     // Catch: java.lang.Throwable -> Lb8
            jcifs.smb.SmbSession r3 = r7.session     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbTransport r3 = r3.transport     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r3.connect()     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r3.<init>()     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r4 = "\\\\"
            r3.append(r4)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbSession r4 = r7.session     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbTransport r4 = r4.transport     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r4 = r4.tconHostName     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r3.append(r4)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r4 = 92
            r3.append(r4)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r4 = r7.share     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r3.append(r4)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r3 = r3.toString()     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r4 = r7.service0     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r7.service = r4     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbSession r4 = r7.session     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbTransport r4 = r4.transport     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.util.LogStream r4 = jcifs.smb.SmbTransport.log     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            int r4 = jcifs.util.LogStream.level     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r5 = 4
            if (r4 < r5) goto L85
            jcifs.smb.SmbSession r4 = r7.session     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbTransport r4 = r4.transport     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.util.LogStream r4 = jcifs.smb.SmbTransport.log     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r5.<init>()     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r6 = "treeConnect: unc="
            r5.append(r6)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r5.append(r3)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r6 = ",service="
            r5.append(r6)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r6 = r7.service     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r5.append(r6)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r5 = r5.toString()     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r4.println(r5)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
        L85:
            jcifs.smb.SmbComTreeConnectAndXResponse r4 = new jcifs.smb.SmbComTreeConnectAndXResponse     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r4.<init>(r9)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbComTreeConnectAndX r9 = new jcifs.smb.SmbComTreeConnectAndX     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbSession r5 = r7.session     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r6 = r7.service     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r9.<init>(r5, r3, r6, r8)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            jcifs.smb.SmbSession r8 = r7.session     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r8.send(r9, r4)     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            int r8 = r4.tid     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r7.tid = r8     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            java.lang.String r8 = r4.service     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r7.service = r8     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            boolean r8 = r4.shareIsInDfs     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r7.inDfs = r8     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            int r8 = jcifs.smb.SmbTree.tree_conn_counter     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            int r9 = r8 + 1
            jcifs.smb.SmbTree.tree_conn_counter = r9     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r7.tree_num = r8     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            r7.connectionState = r2     // Catch: jcifs.smb.SmbException -> Lb0 java.lang.Throwable -> Lb8
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb8
            return
        Lb0:
            r8 = move-exception
            r7.treeDisconnect(r1)     // Catch: java.lang.Throwable -> Lb8
            r9 = 0
            r7.connectionState = r9     // Catch: java.lang.Throwable -> Lb8
            throw r8     // Catch: java.lang.Throwable -> Lb8
        Lb8:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbTree.treeConnect(jcifs.smb.ServerMessageBlock, jcifs.smb.ServerMessageBlock):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void treeDisconnect(boolean z) {
        synchronized (this.session.transport()) {
            if (this.connectionState != 2) {
                return;
            }
            this.connectionState = 3;
            if (!z && this.tid != 0) {
                try {
                    send(new SmbComTreeDisconnect(), null);
                } catch (SmbException e) {
                    SmbTransport smbTransport = this.session.transport;
                    LogStream logStream = SmbTransport.log;
                    if (LogStream.level > 1) {
                        SmbTransport smbTransport2 = this.session.transport;
                        e.printStackTrace(SmbTransport.log);
                    }
                }
            }
            this.inDfs = false;
            this.inDomainDfs = false;
            this.connectionState = 0;
            this.session.transport.notifyAll();
        }
    }

    public String toString() {
        return "SmbTree[share=" + this.share + ",service=" + this.service + ",tid=" + this.tid + ",inDfs=" + this.inDfs + ",inDomainDfs=" + this.inDomainDfs + ",connectionState=" + this.connectionState + "]";
    }
}
