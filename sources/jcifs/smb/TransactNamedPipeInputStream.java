package jcifs.smb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import jcifs.util.LogStream;
import kotlin.UByte;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TransactNamedPipeInputStream extends SmbFileInputStream {
    private static final int INIT_PIPE_SIZE = 4096;
    private int beg_idx;
    private boolean dcePipe;
    Object lock;
    private int nxt_idx;
    private byte[] pipe_buf;
    private int used;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransactNamedPipeInputStream(SmbNamedPipe smbNamedPipe) throws SmbException, MalformedURLException, UnknownHostException {
        super(smbNamedPipe, (smbNamedPipe.pipeType & (-65281)) | 32);
        this.pipe_buf = new byte[4096];
        this.dcePipe = (smbNamedPipe.pipeType & SmbNamedPipe.PIPE_TYPE_DCE_TRANSACT) != 1536;
        this.lock = new Object();
    }

    @Override // jcifs.smb.SmbFileInputStream, java.io.InputStream
    public int read() throws IOException {
        int i;
        synchronized (this.lock) {
            while (this.used == 0) {
                try {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new IOException(e.getMessage());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            byte[] bArr = this.pipe_buf;
            int i2 = this.beg_idx;
            i = bArr[i2] & UByte.MAX_VALUE;
            this.beg_idx = (i2 + 1) % bArr.length;
        }
        return i;
    }

    @Override // jcifs.smb.SmbFileInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // jcifs.smb.SmbFileInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 <= 0) {
            return 0;
        }
        synchronized (this.lock) {
            while (true) {
                try {
                    try {
                        i3 = this.used;
                        if (i3 != 0) {
                            break;
                        }
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new IOException(e.getMessage());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            byte[] bArr2 = this.pipe_buf;
            int length = bArr2.length;
            int i4 = this.beg_idx;
            int i5 = length - i4;
            if (i2 > i3) {
                i2 = i3;
            }
            if (i3 > i5 && i2 > i5) {
                System.arraycopy(bArr2, i4, bArr, i, i5);
                System.arraycopy(this.pipe_buf, 0, bArr, i + i5, i2 - i5);
            } else {
                System.arraycopy(bArr2, i4, bArr, i, i2);
            }
            this.used -= i2;
            this.beg_idx = (this.beg_idx + i2) % this.pipe_buf.length;
        }
        return i2;
    }

    @Override // jcifs.smb.SmbFileInputStream, java.io.InputStream
    public int available() throws IOException {
        SmbFile smbFile = this.file;
        LogStream logStream = SmbFile.log;
        if (LogStream.level < 3) {
            return 0;
        }
        SmbFile smbFile2 = this.file;
        SmbFile.log.println("Named Pipe available() does not apply to TRANSACT Named Pipes");
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int receive(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.pipe_buf;
        int length = bArr2.length;
        int i3 = this.used;
        if (i2 > length - i3) {
            int length2 = bArr2.length * 2;
            if (i2 > length2 - i3) {
                length2 = i2 + i3;
            }
            byte[] bArr3 = new byte[length2];
            this.pipe_buf = bArr3;
            int length3 = bArr2.length;
            int i4 = this.beg_idx;
            int i5 = length3 - i4;
            if (i3 > i5) {
                System.arraycopy(bArr2, i4, bArr3, 0, i5);
                System.arraycopy(bArr2, 0, this.pipe_buf, i5, this.used - i5);
            } else {
                System.arraycopy(bArr2, i4, bArr3, 0, i3);
            }
            this.beg_idx = 0;
            this.nxt_idx = this.used;
        }
        byte[] bArr4 = this.pipe_buf;
        int length4 = bArr4.length;
        int i6 = this.nxt_idx;
        int i7 = length4 - i6;
        if (i2 > i7) {
            System.arraycopy(bArr, i, bArr4, i6, i7);
            System.arraycopy(bArr, i + i7, this.pipe_buf, 0, i2 - i7);
        } else {
            System.arraycopy(bArr, i, bArr4, i6, i2);
        }
        this.nxt_idx = (this.nxt_idx + i2) % this.pipe_buf.length;
        this.used += i2;
        return i2;
    }

    public int dce_read(byte[] bArr, int i, int i2) throws IOException {
        return super.read(bArr, i, i2);
    }
}
