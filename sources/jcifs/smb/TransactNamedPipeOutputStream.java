package jcifs.smb;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TransactNamedPipeOutputStream extends SmbFileOutputStream {
    private boolean dcePipe;
    private String path;
    private SmbNamedPipe pipe;
    private byte[] tmp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransactNamedPipeOutputStream(SmbNamedPipe smbNamedPipe) throws IOException {
        super(smbNamedPipe, false, (smbNamedPipe.pipeType & (-65281)) | 32);
        this.tmp = new byte[1];
        this.pipe = smbNamedPipe;
        this.dcePipe = (smbNamedPipe.pipeType & SmbNamedPipe.PIPE_TYPE_DCE_TRANSACT) == 1536;
        this.path = smbNamedPipe.unc;
    }

    @Override // jcifs.smb.SmbFileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.pipe.close();
    }

    @Override // jcifs.smb.SmbFileOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.tmp;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // jcifs.smb.SmbFileOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // jcifs.smb.SmbFileOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 0) {
            i2 = 0;
        }
        if ((this.pipe.pipeType & 256) == 256) {
            this.pipe.send(new TransWaitNamedPipe(this.path), new TransWaitNamedPipeResponse());
            this.pipe.send(new TransCallNamedPipe(this.path, bArr, i, i2), new TransCallNamedPipeResponse(this.pipe));
        } else if ((this.pipe.pipeType & 512) == 512) {
            ensureOpen();
            TransTransactNamedPipe transTransactNamedPipe = new TransTransactNamedPipe(this.pipe.fid, bArr, i, i2);
            if (this.dcePipe) {
                transTransactNamedPipe.maxDataCount = 1024;
            }
            this.pipe.send(transTransactNamedPipe, new TransTransactNamedPipeResponse(this.pipe));
        }
    }
}
