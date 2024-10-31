package jcifs.smb;

import jcifs.Config;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComWriteAndX extends AndXServerMessageBlock {
    private byte[] b;
    private int dataLength;
    private int dataOffset;
    private int fid;
    private int off;
    private long offset;
    private int pad;
    private int remaining;
    int writeMode;
    private static final int READ_ANDX_BATCH_LIMIT = Config.getInt("jcifs.smb.client.WriteAndX.ReadAndX", 1);
    private static final int CLOSE_BATCH_LIMIT = Config.getInt("jcifs.smb.client.WriteAndX.Close", 1);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComWriteAndX() {
        super(null);
        this.command = (byte) 47;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComWriteAndX(int i, long j, int i2, byte[] bArr, int i3, int i4, ServerMessageBlock serverMessageBlock) {
        super(serverMessageBlock);
        this.fid = i;
        this.offset = j;
        this.remaining = i2;
        this.b = bArr;
        this.off = i3;
        this.dataLength = i4;
        this.command = (byte) 47;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParam(int i, long j, int i2, byte[] bArr, int i3, int i4) {
        this.fid = i;
        this.offset = j;
        this.remaining = i2;
        this.b = bArr;
        this.off = i3;
        this.dataLength = i4;
        this.digest = null;
    }

    @Override // jcifs.smb.AndXServerMessageBlock
    int getBatchLimit(byte b) {
        if (b == 46) {
            return READ_ANDX_BATCH_LIMIT;
        }
        if (b == 4) {
            return CLOSE_BATCH_LIMIT;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        int i2 = (i - this.headerStart) + 26;
        this.dataOffset = i2;
        int i3 = (i2 - this.headerStart) % 4;
        this.pad = i3;
        int i4 = i3 == 0 ? 0 : 4 - i3;
        this.pad = i4;
        this.dataOffset += i4;
        writeInt2(this.fid, bArr, i);
        int i5 = i + 2;
        writeInt4(this.offset, bArr, i5);
        int i6 = i5 + 4;
        int i7 = 0;
        while (i7 < 4) {
            bArr[i6] = -1;
            i7++;
            i6++;
        }
        writeInt2(this.writeMode, bArr, i6);
        int i8 = i6 + 2;
        writeInt2(this.remaining, bArr, i8);
        int i9 = i8 + 2;
        int i10 = i9 + 1;
        bArr[i9] = 0;
        int i11 = i10 + 1;
        bArr[i10] = 0;
        writeInt2(this.dataLength, bArr, i11);
        int i12 = i11 + 2;
        writeInt2(this.dataOffset, bArr, i12);
        int i13 = i12 + 2;
        writeInt4(this.offset >> 32, bArr, i13);
        return (i13 + 4) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int i2 = i;
        while (true) {
            int i3 = this.pad;
            this.pad = i3 - 1;
            if (i3 > 0) {
                bArr[i2] = -18;
                i2++;
            } else {
                System.arraycopy(this.b, this.off, bArr, i2, this.dataLength);
                return (i2 + this.dataLength) - i;
            }
        }
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComWriteAndX[" + super.toString() + ",fid=" + this.fid + ",offset=" + this.offset + ",writeMode=" + this.writeMode + ",remaining=" + this.remaining + ",dataLength=" + this.dataLength + ",dataOffset=" + this.dataOffset + "]");
    }
}
