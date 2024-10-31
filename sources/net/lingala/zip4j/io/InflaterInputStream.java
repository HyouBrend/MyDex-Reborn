package net.lingala.zip4j.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.Inflater;
import kotlin.UByte;
import net.lingala.zip4j.unzip.UnzipEngine;

/* loaded from: classes2.dex */
public class InflaterInputStream extends PartInputStream {
    private byte[] buff;
    private long bytesWritten;
    private Inflater inflater;
    private byte[] oneByteBuff;
    private long uncompressedSize;
    private UnzipEngine unzipEngine;

    public InflaterInputStream(RandomAccessFile randomAccessFile, long j, long j2, UnzipEngine unzipEngine) {
        super(randomAccessFile, j, j2, unzipEngine);
        this.oneByteBuff = new byte[1];
        this.inflater = new Inflater(true);
        this.buff = new byte[4096];
        this.unzipEngine = unzipEngine;
        this.bytesWritten = 0L;
        this.uncompressedSize = unzipEngine.getFileHeader().getUncompressedSize();
    }

    @Override // net.lingala.zip4j.io.PartInputStream, net.lingala.zip4j.io.BaseInputStream, java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByteBuff, 0, 1) == -1) {
            return -1;
        }
        return this.oneByteBuff[0] & UByte.MAX_VALUE;
    }

    @Override // net.lingala.zip4j.io.PartInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("input buffer is null");
        }
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x003d, code lost:
    
        return -1;
     */
    @Override // net.lingala.zip4j.io.PartInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            if (r7 == 0) goto L8a
            if (r8 < 0) goto L84
            if (r9 < 0) goto L84
            int r0 = r7.length
            int r0 = r0 - r8
            if (r9 > r0) goto L84
            if (r9 != 0) goto Le
            r7 = 0
            return r7
        Le:
            long r0 = r6.bytesWritten     // Catch: java.util.zip.DataFormatException -> L45
            long r2 = r6.uncompressedSize     // Catch: java.util.zip.DataFormatException -> L45
            r4 = -1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 < 0) goto L18
            return r4
        L18:
            java.util.zip.Inflater r0 = r6.inflater     // Catch: java.util.zip.DataFormatException -> L45
            int r0 = r0.inflate(r7, r8, r9)     // Catch: java.util.zip.DataFormatException -> L45
            if (r0 != 0) goto L3e
            java.util.zip.Inflater r0 = r6.inflater     // Catch: java.util.zip.DataFormatException -> L45
            boolean r0 = r0.finished()     // Catch: java.util.zip.DataFormatException -> L45
            if (r0 != 0) goto L3d
            java.util.zip.Inflater r0 = r6.inflater     // Catch: java.util.zip.DataFormatException -> L45
            boolean r0 = r0.needsDictionary()     // Catch: java.util.zip.DataFormatException -> L45
            if (r0 == 0) goto L31
            goto L3d
        L31:
            java.util.zip.Inflater r0 = r6.inflater     // Catch: java.util.zip.DataFormatException -> L45
            boolean r0 = r0.needsInput()     // Catch: java.util.zip.DataFormatException -> L45
            if (r0 == 0) goto L18
            r6.fill()     // Catch: java.util.zip.DataFormatException -> L45
            goto L18
        L3d:
            return r4
        L3e:
            long r7 = r6.bytesWritten     // Catch: java.util.zip.DataFormatException -> L45
            long r1 = (long) r0     // Catch: java.util.zip.DataFormatException -> L45
            long r7 = r7 + r1
            r6.bytesWritten = r7     // Catch: java.util.zip.DataFormatException -> L45
            return r0
        L45:
            r7 = move-exception
            java.lang.String r8 = r7.getMessage()
            if (r8 == 0) goto L51
            java.lang.String r7 = r7.getMessage()
            goto L53
        L51:
            java.lang.String r7 = "Invalid ZLIB data format"
        L53:
            net.lingala.zip4j.unzip.UnzipEngine r8 = r6.unzipEngine
            if (r8 == 0) goto L7e
            net.lingala.zip4j.model.LocalFileHeader r8 = r8.getLocalFileHeader()
            boolean r8 = r8.isEncrypted()
            if (r8 == 0) goto L7e
            net.lingala.zip4j.unzip.UnzipEngine r8 = r6.unzipEngine
            net.lingala.zip4j.model.LocalFileHeader r8 = r8.getLocalFileHeader()
            int r8 = r8.getEncryptionMethod()
            if (r8 != 0) goto L7e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            java.lang.String r7 = " - Wrong Password?"
            r8.append(r7)
            java.lang.String r7 = r8.toString()
        L7e:
            java.io.IOException r8 = new java.io.IOException
            r8.<init>(r7)
            throw r8
        L84:
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
            r7.<init>()
            throw r7
        L8a:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "input buffer is null"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.io.InflaterInputStream.read(byte[], int, int):int");
    }

    private void fill() throws IOException {
        byte[] bArr = this.buff;
        int read = super.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException("Unexpected end of ZLIB input stream");
        }
        this.inflater.setInput(this.buff, 0, read);
    }

    @Override // net.lingala.zip4j.io.PartInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("negative skip length");
        }
        int min = (int) Math.min(j, 2147483647L);
        byte[] bArr = new byte[512];
        int i = 0;
        while (i < min) {
            int i2 = min - i;
            if (i2 > 512) {
                i2 = 512;
            }
            int read = read(bArr, 0, i2);
            if (read == -1) {
                break;
            }
            i += read;
        }
        return i;
    }

    @Override // net.lingala.zip4j.io.PartInputStream, net.lingala.zip4j.io.BaseInputStream
    public void seek(long j) throws IOException {
        super.seek(j);
    }

    @Override // net.lingala.zip4j.io.PartInputStream, net.lingala.zip4j.io.BaseInputStream, java.io.InputStream
    public int available() {
        return !this.inflater.finished() ? 1 : 0;
    }

    @Override // net.lingala.zip4j.io.PartInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
    }

    @Override // net.lingala.zip4j.io.PartInputStream, net.lingala.zip4j.io.BaseInputStream
    public UnzipEngine getUnzipEngine() {
        return super.getUnzipEngine();
    }
}
