package jcifs.smb;

import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComWrite extends ServerMessageBlock {
    private byte[] b;
    private int count;
    private int fid;
    private int off;
    private int offset;
    private int remaining;

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
    public SmbComWrite() {
        this.command = (byte) 11;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComWrite(int i, int i2, int i3, byte[] bArr, int i4, int i5) {
        this.fid = i;
        this.count = i5;
        this.offset = i2;
        this.remaining = i3;
        this.b = bArr;
        this.off = i4;
        this.command = (byte) 11;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParam(int i, long j, int i2, byte[] bArr, int i3, int i4) {
        this.fid = i;
        this.offset = (int) (j & InternalZipConstants.ZIP_64_LIMIT);
        this.remaining = i2;
        this.b = bArr;
        this.off = i3;
        this.count = i4;
        this.digest = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.fid, bArr, i);
        int i2 = i + 2;
        writeInt2(this.count, bArr, i2);
        int i3 = i2 + 2;
        writeInt4(this.offset, bArr, i3);
        int i4 = i3 + 4;
        writeInt2(this.remaining, bArr, i4);
        return (i4 + 2) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = 1;
        writeInt2(this.count, bArr, i2);
        int i3 = i2 + 2;
        System.arraycopy(this.b, this.off, bArr, i3, this.count);
        return (i3 + this.count) - i;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComWrite[" + super.toString() + ",fid=" + this.fid + ",count=" + this.count + ",offset=" + this.offset + ",remaining=" + this.remaining + "]");
    }
}
