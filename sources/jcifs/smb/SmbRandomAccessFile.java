package jcifs.smb;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import jcifs.util.Encdec;
import kotlin.UByte;
import kotlin.UShort;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class SmbRandomAccessFile implements DataOutput, DataInput {
    private static final int WRITE_OPTIONS = 2114;
    private int access;
    private int ch;
    private SmbFile file;
    private long fp;
    private int openFlags;
    private int options;
    private int readSize;
    private byte[] tmp;
    private int writeSize;
    private SmbComWriteAndXResponse write_andx_resp;

    public SmbRandomAccessFile(String str, String str2, int i) throws SmbException, MalformedURLException, UnknownHostException {
        this(new SmbFile(str, "", null, i), str2);
    }

    public SmbRandomAccessFile(SmbFile smbFile, String str) throws SmbException, MalformedURLException, UnknownHostException {
        this.access = 0;
        this.options = 0;
        this.tmp = new byte[8];
        this.write_andx_resp = null;
        this.file = smbFile;
        if (str.equals(InternalZipConstants.READ_MODE)) {
            this.openFlags = 17;
        } else if (str.equals(InternalZipConstants.WRITE_MODE)) {
            this.openFlags = 23;
            this.write_andx_resp = new SmbComWriteAndXResponse();
            this.options = WRITE_OPTIONS;
            this.access = 3;
        } else {
            throw new IllegalArgumentException("Invalid mode");
        }
        smbFile.open(this.openFlags, this.access, 128, this.options);
        this.readSize = smbFile.tree.session.transport.rcv_buf_size - 70;
        this.writeSize = smbFile.tree.session.transport.snd_buf_size - 70;
        this.fp = 0L;
    }

    public int read() throws SmbException {
        if (read(this.tmp, 0, 1) == -1) {
            return -1;
        }
        return this.tmp[0] & UByte.MAX_VALUE;
    }

    public int read(byte[] bArr) throws SmbException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws SmbException {
        int i3;
        int i4;
        if (i2 <= 0) {
            return 0;
        }
        long j = this.fp;
        if (!this.file.isOpen()) {
            this.file.open(this.openFlags, 0, 128, this.options);
        }
        SmbComReadAndXResponse smbComReadAndXResponse = new SmbComReadAndXResponse(bArr, i);
        do {
            i3 = this.readSize;
            if (i2 <= i3) {
                i3 = i2;
            }
            this.file.send(new SmbComReadAndX(this.file.fid, this.fp, i3, null), smbComReadAndXResponse);
            i4 = smbComReadAndXResponse.dataLength;
            if (i4 <= 0) {
                long j2 = this.fp;
                return (int) (j2 - j > 0 ? j2 - j : -1L);
            }
            this.fp += i4;
            i2 -= i4;
            smbComReadAndXResponse.off += i4;
            if (i2 <= 0) {
                break;
            }
        } while (i4 == i3);
        return (int) (this.fp - j);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws SmbException {
        readFully(bArr, 0, bArr.length);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) throws SmbException {
        int i3 = 0;
        do {
            int read = read(bArr, i + i3, i2 - i3);
            if (read < 0) {
                throw new SmbException("EOF");
            }
            i3 += read;
            this.fp += read;
        } while (i3 < i2);
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) throws SmbException {
        if (i <= 0) {
            return 0;
        }
        this.fp += i;
        return i;
    }

    @Override // java.io.DataOutput
    public void write(int i) throws SmbException {
        byte[] bArr = this.tmp;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.DataOutput
    public void write(byte[] bArr) throws SmbException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws SmbException {
        if (i2 <= 0) {
            return;
        }
        if (!this.file.isOpen()) {
            this.file.open(this.openFlags, 0, 128, this.options);
        }
        int i3 = i;
        int i4 = i2;
        do {
            int i5 = this.writeSize;
            int i6 = i4 > i5 ? i5 : i4;
            this.file.send(new SmbComWriteAndX(this.file.fid, this.fp, i4 - i6, bArr, i3, i6, null), this.write_andx_resp);
            this.fp += this.write_andx_resp.count;
            i4 = (int) (i4 - this.write_andx_resp.count);
            i3 = (int) (i3 + this.write_andx_resp.count);
        } while (i4 > 0);
    }

    public long getFilePointer() throws SmbException {
        return this.fp;
    }

    public void seek(long j) throws SmbException {
        this.fp = j;
    }

    public long length() throws SmbException {
        return this.file.length();
    }

    public void setLength(long j) throws SmbException {
        if (!this.file.isOpen()) {
            this.file.open(this.openFlags, 0, 128, this.options);
        }
        this.file.send(new SmbComWrite(this.file.fid, (int) (j & InternalZipConstants.ZIP_64_LIMIT), 0, this.tmp, 0, 0), new SmbComWriteResponse());
    }

    public void close() throws SmbException {
        this.file.close();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() throws SmbException {
        if (read(this.tmp, 0, 1) >= 0) {
            return this.tmp[0] != 0;
        }
        throw new SmbException("EOF");
    }

    @Override // java.io.DataInput
    public final byte readByte() throws SmbException {
        if (read(this.tmp, 0, 1) < 0) {
            throw new SmbException("EOF");
        }
        return this.tmp[0];
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws SmbException {
        if (read(this.tmp, 0, 1) < 0) {
            throw new SmbException("EOF");
        }
        return this.tmp[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.DataInput
    public final short readShort() throws SmbException {
        if (read(this.tmp, 0, 2) < 0) {
            throw new SmbException("EOF");
        }
        return Encdec.dec_uint16be(this.tmp, 0);
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws SmbException {
        if (read(this.tmp, 0, 2) < 0) {
            throw new SmbException("EOF");
        }
        return Encdec.dec_uint16be(this.tmp, 0) & UShort.MAX_VALUE;
    }

    @Override // java.io.DataInput
    public final char readChar() throws SmbException {
        if (read(this.tmp, 0, 2) < 0) {
            throw new SmbException("EOF");
        }
        return (char) Encdec.dec_uint16be(this.tmp, 0);
    }

    @Override // java.io.DataInput
    public final int readInt() throws SmbException {
        if (read(this.tmp, 0, 4) < 0) {
            throw new SmbException("EOF");
        }
        return Encdec.dec_uint32be(this.tmp, 0);
    }

    @Override // java.io.DataInput
    public final long readLong() throws SmbException {
        if (read(this.tmp, 0, 8) < 0) {
            throw new SmbException("EOF");
        }
        return Encdec.dec_uint64be(this.tmp, 0);
    }

    @Override // java.io.DataInput
    public final float readFloat() throws SmbException {
        if (read(this.tmp, 0, 4) < 0) {
            throw new SmbException("EOF");
        }
        return Encdec.dec_floatbe(this.tmp, 0);
    }

    @Override // java.io.DataInput
    public final double readDouble() throws SmbException {
        if (read(this.tmp, 0, 8) < 0) {
            throw new SmbException("EOF");
        }
        return Encdec.dec_doublebe(this.tmp, 0);
    }

    @Override // java.io.DataInput
    public final String readLine() throws SmbException {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        int i = -1;
        while (!z) {
            i = read();
            if (i != -1 && i != 10) {
                if (i == 13) {
                    long j = this.fp;
                    if (read() != 10) {
                        this.fp = j;
                    }
                } else {
                    stringBuffer.append((char) i);
                }
            }
            z = true;
        }
        if (i == -1 && stringBuffer.length() == 0) {
            return null;
        }
        return stringBuffer.toString();
    }

    @Override // java.io.DataInput
    public final String readUTF() throws SmbException {
        int readUnsignedShort = readUnsignedShort();
        byte[] bArr = new byte[readUnsignedShort];
        read(bArr, 0, readUnsignedShort);
        try {
            return Encdec.dec_utf8(bArr, 0, readUnsignedShort);
        } catch (IOException e) {
            throw new SmbException("", e);
        }
    }

    @Override // java.io.DataOutput
    public final void writeBoolean(boolean z) throws SmbException {
        byte[] bArr = this.tmp;
        bArr[0] = z ? (byte) 1 : (byte) 0;
        write(bArr, 0, 1);
    }

    @Override // java.io.DataOutput
    public final void writeByte(int i) throws SmbException {
        byte[] bArr = this.tmp;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.DataOutput
    public final void writeShort(int i) throws SmbException {
        Encdec.enc_uint16be((short) i, this.tmp, 0);
        write(this.tmp, 0, 2);
    }

    @Override // java.io.DataOutput
    public final void writeChar(int i) throws SmbException {
        Encdec.enc_uint16be((short) i, this.tmp, 0);
        write(this.tmp, 0, 2);
    }

    @Override // java.io.DataOutput
    public final void writeInt(int i) throws SmbException {
        Encdec.enc_uint32be(i, this.tmp, 0);
        write(this.tmp, 0, 4);
    }

    @Override // java.io.DataOutput
    public final void writeLong(long j) throws SmbException {
        Encdec.enc_uint64be(j, this.tmp, 0);
        write(this.tmp, 0, 8);
    }

    @Override // java.io.DataOutput
    public final void writeFloat(float f) throws SmbException {
        Encdec.enc_floatbe(f, this.tmp, 0);
        write(this.tmp, 0, 4);
    }

    @Override // java.io.DataOutput
    public final void writeDouble(double d) throws SmbException {
        Encdec.enc_doublebe(d, this.tmp, 0);
        write(this.tmp, 0, 8);
    }

    @Override // java.io.DataOutput
    public final void writeBytes(String str) throws SmbException {
        byte[] bytes = str.getBytes();
        write(bytes, 0, bytes.length);
    }

    @Override // java.io.DataOutput
    public final void writeChars(String str) throws SmbException {
        int length = str.length();
        int i = length * 2;
        byte[] bArr = new byte[i];
        char[] cArr = new char[length];
        str.getChars(0, length, cArr, 0);
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char c = cArr[i3];
            bArr[i2] = (byte) (c >>> '\b');
            i2 = i4 + 1;
            bArr[i4] = (byte) (c >>> 0);
        }
        write(bArr, 0, i);
    }

    @Override // java.io.DataOutput
    public final void writeUTF(String str) throws SmbException {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            i += charAt > 127 ? charAt > 2047 ? 3 : 2 : 1;
        }
        byte[] bArr = new byte[i];
        writeShort(i);
        try {
            Encdec.enc_utf8(str, bArr, 0, i);
            write(bArr, 0, i);
        } catch (IOException e) {
            throw new SmbException("", e);
        }
    }
}
