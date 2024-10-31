package jcifs.smb;

import jcifs.Config;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComReadAndX extends AndXServerMessageBlock {
    private static final int BATCH_LIMIT = Config.getInt("jcifs.smb.client.ReadAndX.Close", 1);
    private int fid;
    int maxCount;
    int minCount;
    private long offset;
    private int openTimeout;
    int remaining;

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
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComReadAndX() {
        super(null);
        this.command = (byte) 46;
        this.openTimeout = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComReadAndX(int i, long j, int i2, ServerMessageBlock serverMessageBlock) {
        super(serverMessageBlock);
        this.fid = i;
        this.offset = j;
        this.minCount = i2;
        this.maxCount = i2;
        this.command = (byte) 46;
        this.openTimeout = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParam(int i, long j, int i2) {
        this.fid = i;
        this.offset = j;
        this.minCount = i2;
        this.maxCount = i2;
    }

    @Override // jcifs.smb.AndXServerMessageBlock
    int getBatchLimit(byte b) {
        if (b == 4) {
            return BATCH_LIMIT;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.fid, bArr, i);
        int i2 = i + 2;
        writeInt4(this.offset, bArr, i2);
        int i3 = i2 + 4;
        writeInt2(this.maxCount, bArr, i3);
        int i4 = i3 + 2;
        writeInt2(this.minCount, bArr, i4);
        int i5 = i4 + 2;
        writeInt4(this.openTimeout, bArr, i5);
        int i6 = i5 + 4;
        writeInt2(this.remaining, bArr, i6);
        int i7 = i6 + 2;
        writeInt4(this.offset >> 32, bArr, i7);
        return (i7 + 4) - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComReadAndX[" + super.toString() + ",fid=" + this.fid + ",offset=" + this.offset + ",maxCount=" + this.maxCount + ",minCount=" + this.minCount + ",openTimeout=" + this.openTimeout + ",remaining=" + this.remaining + ",offset=" + this.offset + "]");
    }
}
