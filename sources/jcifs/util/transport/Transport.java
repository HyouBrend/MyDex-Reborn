package jcifs.util.transport;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public abstract class Transport implements Runnable {
    static int id;
    static LogStream log = LogStream.getInstance();
    String name;
    protected HashMap response_map;
    int state = 0;
    TransportException te;
    Thread thread;

    protected abstract void doConnect() throws Exception;

    protected abstract void doDisconnect(boolean z) throws IOException;

    protected abstract void doRecv(Response response) throws IOException;

    protected abstract void doSend(Request request) throws IOException;

    protected abstract void doSkip() throws IOException;

    protected abstract void makeKey(Request request) throws IOException;

    protected abstract Request peekKey() throws IOException;

    public Transport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transport");
        int i = id;
        id = i + 1;
        sb.append(i);
        this.name = sb.toString();
        this.response_map = new HashMap(4);
    }

    public static int readn(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read <= 0) {
                break;
            }
            i3 += read;
        }
        return i3;
    }

    public synchronized void sendrecv(Request request, Response response, long j) throws IOException {
        makeKey(request);
        response.isReceived = false;
        try {
            try {
                this.response_map.put(request, response);
                doSend(request);
                response.expiration = System.currentTimeMillis() + j;
                while (!response.isReceived) {
                    wait(j);
                    j = response.expiration - System.currentTimeMillis();
                    if (j <= 0) {
                        throw new TransportException(this.name + " timedout waiting for response to " + request);
                    }
                }
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
            } catch (InterruptedException e3) {
                throw new TransportException(e3);
            }
        } finally {
            this.response_map.remove(request);
        }
    }

    private void loop() {
        Request peekKey;
        while (this.thread == Thread.currentThread()) {
            try {
                peekKey = peekKey();
            } catch (Exception e) {
                String message = e.getMessage();
                boolean z = message != null && message.equals("Read timed out");
                boolean z2 = !z;
                if (!z && LogStream.level >= 3) {
                    e.printStackTrace(log);
                }
                try {
                    disconnect(z2);
                } catch (IOException e2) {
                    e2.printStackTrace(log);
                }
            }
            if (peekKey == null) {
                throw new IOException("end of stream");
            }
            synchronized (this) {
                Response response = (Response) this.response_map.get(peekKey);
                if (response == null) {
                    if (LogStream.level >= 4) {
                        log.println("Invalid key, skipping message");
                    }
                    doSkip();
                } else {
                    doRecv(response);
                    response.isReceived = true;
                    notifyAll();
                }
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    public synchronized void connect(long j) throws TransportException {
        try {
            try {
                int i = this.state;
                if (i != 0) {
                    if (i != 3) {
                        if (i == 4) {
                            this.state = 0;
                            throw new TransportException("Connection in error", this.te);
                        }
                        TransportException transportException = new TransportException("Invalid state: " + this.state);
                        this.state = 0;
                        throw transportException;
                    }
                    if (i != 0 && i != 3 && i != 4) {
                        if (LogStream.level >= 1) {
                            log.println("Invalid state: " + this.state);
                        }
                        this.state = 0;
                        this.thread = null;
                    }
                    return;
                }
                this.state = 1;
                this.te = null;
                Thread thread = new Thread(this, this.name);
                this.thread = thread;
                thread.setDaemon(true);
                synchronized (this.thread) {
                    this.thread.start();
                    this.thread.wait(j);
                    int i2 = this.state;
                    if (i2 == 1) {
                        this.state = 0;
                        this.thread = null;
                        throw new TransportException("Connection timeout");
                    }
                    if (i2 == 2) {
                        TransportException transportException2 = this.te;
                        if (transportException2 == null) {
                            this.state = 3;
                            return;
                        } else {
                            this.state = 4;
                            this.thread = null;
                            throw transportException2;
                        }
                    }
                    if (i2 != 0 && i2 != 3 && i2 != 4) {
                        if (LogStream.level >= 1) {
                            log.println("Invalid state: " + this.state);
                        }
                        this.state = 0;
                        this.thread = null;
                    }
                }
            } catch (InterruptedException e) {
                this.state = 0;
                this.thread = null;
                throw new TransportException(e);
            }
        } catch (Throwable th) {
            int i3 = this.state;
            if (i3 != 0 && i3 != 3 && i3 != 4) {
                if (LogStream.level >= 1) {
                    log.println("Invalid state: " + this.state);
                }
                this.state = 0;
                this.thread = null;
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004b A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004d A[Catch: all -> 0x0050, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:11:0x0011, B:13:0x0015, B:14:0x002d, B:19:0x004d, B:22:0x0044, B:24:0x0033, B:28:0x003e), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void disconnect(boolean r6) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.state     // Catch: java.lang.Throwable -> L50
            if (r0 == 0) goto L4e
            r1 = 2
            r2 = 0
            r3 = 1
            r4 = 0
            if (r0 == r1) goto L32
            r1 = 3
            if (r0 == r1) goto L33
            r6 = 4
            if (r0 == r6) goto L41
            int r6 = jcifs.util.LogStream.level     // Catch: java.lang.Throwable -> L50
            if (r6 < r3) goto L2d
            jcifs.util.LogStream r6 = jcifs.util.transport.Transport.log     // Catch: java.lang.Throwable -> L50
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r0.<init>()     // Catch: java.lang.Throwable -> L50
            java.lang.String r1 = "Invalid state: "
            r0.append(r1)     // Catch: java.lang.Throwable -> L50
            int r1 = r5.state     // Catch: java.lang.Throwable -> L50
            r0.append(r1)     // Catch: java.lang.Throwable -> L50
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L50
            r6.println(r0)     // Catch: java.lang.Throwable -> L50
        L2d:
            r5.thread = r4     // Catch: java.lang.Throwable -> L50
            r5.state = r2     // Catch: java.lang.Throwable -> L50
            goto L49
        L32:
            r6 = 1
        L33:
            java.util.HashMap r0 = r5.response_map     // Catch: java.lang.Throwable -> L50
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L50
            if (r0 == 0) goto L3e
            if (r6 != 0) goto L3e
            goto L49
        L3e:
            r5.doDisconnect(r6)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L50
        L41:
            r6 = r4
            goto L44
        L43:
            r6 = move-exception
        L44:
            r5.thread = r4     // Catch: java.lang.Throwable -> L50
            r5.state = r2     // Catch: java.lang.Throwable -> L50
            r4 = r6
        L49:
            if (r4 != 0) goto L4d
            monitor-exit(r5)
            return
        L4d:
            throw r4     // Catch: java.lang.Throwable -> L50
        L4e:
            monitor-exit(r5)
            return
        L50:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.util.transport.Transport.disconnect(boolean):void");
    }

    @Override // java.lang.Runnable
    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            doConnect();
            synchronized (currentThread) {
                if (currentThread != this.thread) {
                    return;
                }
                this.state = 2;
                currentThread.notify();
                loop();
            }
        } catch (Exception e) {
            synchronized (currentThread) {
                if (currentThread != this.thread) {
                    if (LogStream.level >= 2) {
                        e.printStackTrace(log);
                    }
                } else {
                    this.te = new TransportException(e);
                    this.state = 2;
                    currentThread.notify();
                }
            }
        } catch (Throwable th) {
            synchronized (currentThread) {
                if (currentThread != this.thread) {
                    return;
                }
                this.state = 2;
                currentThread.notify();
                throw th;
            }
        }
    }

    public String toString() {
        return this.name;
    }
}
