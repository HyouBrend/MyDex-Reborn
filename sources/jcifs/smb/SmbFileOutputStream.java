package jcifs.smb;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class SmbFileOutputStream extends OutputStream {
    private int access;
    private boolean append;
    private SmbFile file;
    private long fp;
    private int openFlags;
    private SmbComWrite req;
    private SmbComWriteAndX reqx;
    private SmbComWriteResponse rsp;
    private SmbComWriteAndXResponse rspx;
    private byte[] tmp;
    private boolean useNTSmbs;
    private int writeSize;

    public SmbFileOutputStream(String str) throws SmbException, MalformedURLException, UnknownHostException {
        this(str, false);
    }

    public SmbFileOutputStream(SmbFile smbFile) throws SmbException, MalformedURLException, UnknownHostException {
        this(smbFile, false);
    }

    public SmbFileOutputStream(String str, boolean z) throws SmbException, MalformedURLException, UnknownHostException {
        this(new SmbFile(str), z);
    }

    public SmbFileOutputStream(SmbFile smbFile, boolean z) throws SmbException, MalformedURLException, UnknownHostException {
        this(smbFile, z, z ? 22 : 82);
    }

    public SmbFileOutputStream(String str, int i) throws SmbException, MalformedURLException, UnknownHostException {
        this(new SmbFile(str, "", null, i), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbFileOutputStream(SmbFile smbFile, boolean z, int i) throws SmbException, MalformedURLException, UnknownHostException {
        this.tmp = new byte[1];
        this.file = smbFile;
        this.append = z;
        this.openFlags = i;
        this.access = (i >>> 16) & 65535;
        if (z) {
            try {
                this.fp = smbFile.length();
            } catch (SmbAuthException e) {
                throw e;
            } catch (SmbException unused) {
                this.fp = 0L;
            }
        }
        if ((smbFile instanceof SmbNamedPipe) && smbFile.unc.startsWith("\\pipe\\")) {
            smbFile.unc = smbFile.unc.substring(5);
            smbFile.send(new TransWaitNamedPipe("\\pipe" + smbFile.unc), new TransWaitNamedPipeResponse());
        }
        smbFile.open(i, this.access | 2, 128, 0);
        this.openFlags &= -81;
        this.writeSize = smbFile.tree.session.transport.snd_buf_size - 70;
        boolean hasCapability = smbFile.tree.session.transport.hasCapability(16);
        this.useNTSmbs = hasCapability;
        if (hasCapability) {
            this.reqx = new SmbComWriteAndX();
            this.rspx = new SmbComWriteAndXResponse();
        } else {
            this.req = new SmbComWrite();
            this.rsp = new SmbComWriteResponse();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.file.close();
        this.tmp = null;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.tmp;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public boolean isOpen() {
        return this.file.isOpen();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureOpen() throws IOException {
        if (this.file.isOpen()) {
            return;
        }
        this.file.open(this.openFlags, this.access | 2, 128, 0);
        if (this.append) {
            this.fp = this.file.length();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.file.isOpen()) {
            SmbFile smbFile = this.file;
            if (smbFile instanceof SmbNamedPipe) {
                smbFile.send(new TransWaitNamedPipe("\\pipe" + this.file.unc), new TransWaitNamedPipeResponse());
            }
        }
        writeDirect(bArr, i, i2, 0);
    }

    public void writeDirect(byte[] bArr, int i, int i2, int i3) throws IOException {
        if (i2 <= 0) {
            return;
        }
        if (this.tmp == null) {
            throw new IOException("Bad file descriptor");
        }
        ensureOpen();
        LogStream logStream = SmbFile.log;
        if (LogStream.level >= 4) {
            SmbFile.log.println("write: fid=" + this.file.fid + ",off=" + i + ",len=" + i2);
        }
        do {
            int i4 = this.writeSize;
            if (i2 <= i4) {
                i4 = i2;
            }
            if (this.useNTSmbs) {
                this.reqx.setParam(this.file.fid, this.fp, i2 - i4, bArr, i, i4);
                if ((i3 & 1) != 0) {
                    this.reqx.setParam(this.file.fid, this.fp, i2, bArr, i, i4);
                    this.reqx.writeMode = 8;
                } else {
                    this.reqx.writeMode = 0;
                }
                this.file.send(this.reqx, this.rspx);
                this.fp += this.rspx.count;
                i2 = (int) (i2 - this.rspx.count);
                i = (int) (i + this.rspx.count);
            } else {
                this.req.setParam(this.file.fid, this.fp, i2 - i4, bArr, i, i4);
                this.fp += this.rsp.count;
                i2 = (int) (i2 - this.rsp.count);
                i = (int) (i + this.rsp.count);
                this.file.send(this.req, this.rsp);
            }
        } while (i2 > 0);
    }
}
