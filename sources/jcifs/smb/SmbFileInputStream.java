package jcifs.smb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import jcifs.util.transport.TransportException;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class SmbFileInputStream extends InputStream {
    private int access;
    SmbFile file;
    private long fp;
    private int openFlags;
    private int readSize;
    private byte[] tmp;

    public SmbFileInputStream(String str) throws SmbException, MalformedURLException, UnknownHostException {
        this(new SmbFile(str));
    }

    public SmbFileInputStream(SmbFile smbFile) throws SmbException, MalformedURLException, UnknownHostException {
        this(smbFile, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbFileInputStream(SmbFile smbFile, int i) throws SmbException, MalformedURLException, UnknownHostException {
        this.tmp = new byte[1];
        this.file = smbFile;
        this.openFlags = i & 65535;
        this.access = 65535 & (i >>> 16);
        if (smbFile.type != 16) {
            smbFile.open(i, this.access, 128, 0);
            this.openFlags &= -81;
        } else {
            smbFile.connect0();
        }
        this.readSize = Math.min(smbFile.tree.session.transport.rcv_buf_size - 70, smbFile.tree.session.transport.server.maxBufferSize - 70);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected IOException seToIoe(SmbException smbException) {
        Throwable rootCause = smbException.getRootCause();
        SmbException smbException2 = smbException;
        if (rootCause instanceof TransportException) {
            TransportException transportException = (TransportException) rootCause;
            rootCause = transportException.getRootCause();
            smbException2 = transportException;
        }
        if (!(rootCause instanceof InterruptedException)) {
            return smbException2;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException(rootCause.getMessage());
        interruptedIOException.initCause(rootCause);
        return interruptedIOException;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.file.close();
            this.tmp = null;
        } catch (SmbException e) {
            throw seToIoe(e);
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tmp, 0, 1) == -1) {
            return -1;
        }
        return this.tmp[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return readDirect(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e1, code lost:
    
        return (int) (r17.fp - r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int readDirect(byte[] r18, int r19, int r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.SmbFileInputStream.readDirect(byte[], int, int):int");
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.file.type != 16) {
            return 0;
        }
        try {
            SmbFile smbFile = this.file;
            SmbNamedPipe smbNamedPipe = (SmbNamedPipe) smbFile;
            smbFile.open(32, smbNamedPipe.pipeType & 16711680, 128, 0);
            TransPeekNamedPipe transPeekNamedPipe = new TransPeekNamedPipe(this.file.unc, this.file.fid);
            TransPeekNamedPipeResponse transPeekNamedPipeResponse = new TransPeekNamedPipeResponse(smbNamedPipe);
            smbNamedPipe.send(transPeekNamedPipe, transPeekNamedPipeResponse);
            if (transPeekNamedPipeResponse.status != 1 && transPeekNamedPipeResponse.status != 4) {
                return transPeekNamedPipeResponse.available;
            }
            this.file.opened = false;
            return 0;
        } catch (SmbException e) {
            throw seToIoe(e);
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        this.fp += j;
        return j;
    }
}
