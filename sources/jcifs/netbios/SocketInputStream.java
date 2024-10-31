package jcifs.netbios;

import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
class SocketInputStream extends InputStream {
    private static final int TMP_BUFFER_SIZE = 256;
    private int bip;
    private InputStream in;
    private int n;
    private SessionServicePacket ssp;
    private int tot;
    private byte[] header = new byte[4];
    private byte[] tmp = new byte[256];

    /* JADX INFO: Access modifiers changed from: package-private */
    public SocketInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (read(this.tmp, 0, 1) < 0) {
            return -1;
        }
        return this.tmp[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        this.tot = 0;
        while (true) {
            int i3 = this.bip;
            if (i3 > 0) {
                int read = this.in.read(bArr, i, Math.min(i2, i3));
                this.n = read;
                if (read == -1) {
                    int i4 = this.tot;
                    return i4 > 0 ? i4 : -1;
                }
                int i5 = this.tot + read;
                this.tot = i5;
                i += read;
                i2 -= read;
                this.bip -= read;
                if (i2 == 0) {
                    return i5;
                }
            } else {
                int readPacketType = SessionServicePacket.readPacketType(this.in, this.header, 0);
                if (readPacketType == -1) {
                    int i6 = this.tot;
                    if (i6 > 0) {
                        return i6;
                    }
                    return -1;
                }
                if (readPacketType == 0) {
                    this.bip = SessionServicePacket.readLength(this.header, 0);
                }
            }
        }
    }

    @Override // java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        long j2 = j;
        while (j2 > 0) {
            int read = read(this.tmp, 0, (int) Math.min(256L, j2));
            if (read < 0) {
                break;
            }
            j2 -= read;
        }
        return j - j2;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int i = this.bip;
        return i > 0 ? i : this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }
}
