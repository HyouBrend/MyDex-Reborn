package org.apache.commons.net.telnet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;

/* loaded from: classes2.dex */
final class TelnetInputStream extends BufferedInputStream implements Runnable {
    private static final int EOF = -1;
    private static final int WOULD_BLOCK = -2;
    static final int _STATE_CR = 8;
    static final int _STATE_DATA = 0;
    static final int _STATE_DO = 4;
    static final int _STATE_DONT = 5;
    static final int _STATE_IAC = 1;
    static final int _STATE_IAC_SB = 9;
    static final int _STATE_SB = 6;
    static final int _STATE_SE = 7;
    static final int _STATE_WILL = 2;
    static final int _STATE_WONT = 3;
    private int __bytesAvailable;
    private final TelnetClient __client;
    private boolean __hasReachedEOF;
    private IOException __ioException;
    private boolean __isClosed;
    private final int[] __queue;
    private int __queueHead;
    private int __queueTail;
    private boolean __readIsWaiting;
    private int __receiveState;
    private final int[] __suboption;
    private int __suboption_count;
    private final Thread __thread;
    private boolean __threaded;

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TelnetInputStream(InputStream inputStream, TelnetClient telnetClient, boolean z) {
        super(inputStream);
        this.__suboption = new int[256];
        this.__suboption_count = 0;
        this.__client = telnetClient;
        this.__receiveState = 0;
        this.__isClosed = true;
        this.__hasReachedEOF = false;
        this.__queue = new int[2049];
        this.__queueHead = 0;
        this.__queueTail = 0;
        this.__bytesAvailable = 0;
        this.__ioException = null;
        this.__readIsWaiting = false;
        this.__threaded = false;
        if (z) {
            this.__thread = new Thread(this);
        } else {
            this.__thread = null;
        }
    }

    TelnetInputStream(InputStream inputStream, TelnetClient telnetClient) {
        this(inputStream, telnetClient, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _start() {
        if (this.__thread == null) {
            return;
        }
        this.__isClosed = false;
        int priority = Thread.currentThread().getPriority() + 1;
        if (priority > 10) {
            priority = 10;
        }
        this.__thread.setPriority(priority);
        this.__thread.setDaemon(true);
        this.__thread.start();
        this.__threaded = true;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0028. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:99:0x00b6. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00e6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int __read(boolean r6) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.telnet.TelnetInputStream.__read(boolean):int");
    }

    private void __processChar(int i) throws InterruptedException {
        synchronized (this.__queue) {
            while (true) {
                int i2 = this.__bytesAvailable;
                int[] iArr = this.__queue;
                if (i2 >= iArr.length - 1) {
                    if (this.__threaded) {
                        iArr.notify();
                        try {
                            this.__queue.wait();
                        } catch (InterruptedException e) {
                            throw e;
                        }
                    } else {
                        throw new IllegalStateException("Queue is full! Cannot process another character.");
                    }
                } else {
                    if (this.__readIsWaiting && this.__threaded) {
                        iArr.notify();
                    }
                    int[] iArr2 = this.__queue;
                    int i3 = this.__queueTail;
                    iArr2[i3] = i;
                    this.__bytesAvailable++;
                    int i4 = i3 + 1;
                    this.__queueTail = i4;
                    if (i4 >= iArr2.length) {
                        this.__queueTail = 0;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
    
        r8.__readIsWaiting = false;
     */
    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read() throws java.io.IOException {
        /*
            r8 = this;
            int[] r0 = r8.__queue
            monitor-enter(r0)
        L3:
            java.io.IOException r1 = r8.__ioException     // Catch: java.lang.Throwable -> L95
            if (r1 != 0) goto L91
            int r1 = r8.__bytesAvailable     // Catch: java.lang.Throwable -> L95
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L75
            boolean r1 = r8.__hasReachedEOF     // Catch: java.lang.Throwable -> L95
            r4 = -1
            if (r1 == 0) goto L14
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
            return r4
        L14:
            boolean r1 = r8.__threaded     // Catch: java.lang.Throwable -> L95
            if (r1 == 0) goto L2f
            int[] r1 = r8.__queue     // Catch: java.lang.Throwable -> L95
            r1.notify()     // Catch: java.lang.Throwable -> L95
            r8.__readIsWaiting = r3     // Catch: java.lang.InterruptedException -> L27 java.lang.Throwable -> L95
            int[] r1 = r8.__queue     // Catch: java.lang.InterruptedException -> L27 java.lang.Throwable -> L95
            r1.wait()     // Catch: java.lang.InterruptedException -> L27 java.lang.Throwable -> L95
            r8.__readIsWaiting = r2     // Catch: java.lang.InterruptedException -> L27 java.lang.Throwable -> L95
            goto L3
        L27:
            java.io.InterruptedIOException r1 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L95
            java.lang.String r2 = "Fatal thread interruption during read."
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L95
            throw r1     // Catch: java.lang.Throwable -> L95
        L2f:
            r8.__readIsWaiting = r3     // Catch: java.lang.Throwable -> L95
            r1 = 1
        L32:
            int r1 = r8.__read(r1)     // Catch: java.io.InterruptedIOException -> L5d java.lang.Throwable -> L95
            r5 = -2
            if (r1 >= 0) goto L3d
            if (r1 == r5) goto L3d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
            return r1
        L3d:
            if (r1 == r5) goto L49
            r8.__processChar(r1)     // Catch: java.lang.InterruptedException -> L43 java.lang.Throwable -> L95
            goto L49
        L43:
            boolean r1 = r8.__isClosed     // Catch: java.lang.Throwable -> L95
            if (r1 == 0) goto L49
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
            return r4
        L49:
            int r1 = super.available()     // Catch: java.lang.Throwable -> L95
            if (r1 <= 0) goto L5a
            int r1 = r8.__bytesAvailable     // Catch: java.lang.Throwable -> L95
            int[] r5 = r8.__queue     // Catch: java.lang.Throwable -> L95
            int r5 = r5.length     // Catch: java.lang.Throwable -> L95
            int r5 = r5 - r3
            if (r1 < r5) goto L58
            goto L5a
        L58:
            r1 = 0
            goto L32
        L5a:
            r8.__readIsWaiting = r2     // Catch: java.lang.Throwable -> L95
            goto L3
        L5d:
            r1 = move-exception
            int[] r2 = r8.__queue     // Catch: java.lang.Throwable -> L95
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L95
            r8.__ioException = r1     // Catch: java.lang.Throwable -> L72
            int[] r1 = r8.__queue     // Catch: java.lang.Throwable -> L72
            r1.notifyAll()     // Catch: java.lang.Throwable -> L72
            int[] r1 = r8.__queue     // Catch: java.lang.InterruptedException -> L6f java.lang.Throwable -> L72
            r5 = 100
            r1.wait(r5)     // Catch: java.lang.InterruptedException -> L6f java.lang.Throwable -> L72
        L6f:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L72
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
            return r4
        L72:
            r1 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L72
            throw r1     // Catch: java.lang.Throwable -> L95
        L75:
            int[] r4 = r8.__queue     // Catch: java.lang.Throwable -> L95
            int r5 = r8.__queueHead     // Catch: java.lang.Throwable -> L95
            r6 = r4[r5]     // Catch: java.lang.Throwable -> L95
            int r5 = r5 + r3
            r8.__queueHead = r5     // Catch: java.lang.Throwable -> L95
            int r7 = r4.length     // Catch: java.lang.Throwable -> L95
            if (r5 < r7) goto L83
            r8.__queueHead = r2     // Catch: java.lang.Throwable -> L95
        L83:
            int r1 = r1 - r3
            r8.__bytesAvailable = r1     // Catch: java.lang.Throwable -> L95
            if (r1 != 0) goto L8f
            boolean r1 = r8.__threaded     // Catch: java.lang.Throwable -> L95
            if (r1 == 0) goto L8f
            r4.notify()     // Catch: java.lang.Throwable -> L95
        L8f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
            return r6
        L91:
            r2 = 0
            r8.__ioException = r2     // Catch: java.lang.Throwable -> L95
            throw r1     // Catch: java.lang.Throwable -> L95
        L95:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.telnet.TelnetInputStream.read():int");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 < 1) {
            return 0;
        }
        synchronized (this.__queue) {
            int i4 = this.__bytesAvailable;
            if (i2 > i4) {
                i2 = i4;
            }
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        int i5 = i;
        while (true) {
            i3 = i5 + 1;
            bArr[i5] = (byte) read;
            i2--;
            if (i2 <= 0 || (read = read()) == -1) {
                break;
            }
            i5 = i3;
        }
        return i3 - i;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int i;
        synchronized (this.__queue) {
            i = this.__bytesAvailable;
        }
        return i;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        synchronized (this.__queue) {
            this.__hasReachedEOF = true;
            this.__isClosed = true;
            Thread thread = this.__thread;
            if (thread != null && thread.isAlive()) {
                this.__thread.interrupt();
            }
            this.__queue.notifyAll();
        }
        this.__threaded = false;
    }

    @Override // java.lang.Runnable
    public void run() {
        int __read;
        while (!this.__isClosed) {
            try {
                try {
                    __read = __read(true);
                } catch (InterruptedIOException e) {
                    synchronized (this.__queue) {
                        this.__ioException = e;
                        this.__queue.notifyAll();
                        try {
                            this.__queue.wait(100L);
                        } catch (InterruptedException unused) {
                            if (this.__isClosed) {
                                break;
                            }
                        }
                    }
                } catch (RuntimeException unused2) {
                    super.close();
                }
                if (__read < 0) {
                    break;
                }
                try {
                    __processChar(__read);
                } catch (InterruptedException unused3) {
                    if (this.__isClosed) {
                        break;
                    }
                }
            } catch (IOException e2) {
                synchronized (this.__queue) {
                    this.__ioException = e2;
                }
            }
        }
        synchronized (this.__queue) {
            this.__isClosed = true;
            this.__hasReachedEOF = true;
            this.__queue.notify();
        }
        this.__threaded = false;
    }
}
