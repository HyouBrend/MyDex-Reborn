package jcifs.smb;

/* loaded from: classes2.dex */
class Trans2SetFileInformation extends SmbComTransaction {
    static final int SMB_FILE_BASIC_INFO = 257;
    private int attributes;
    private long createTime;
    private int fid;
    private long lastWriteTime;

    @Override // jcifs.smb.SmbComTransaction
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int readSetupWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2SetFileInformation(int i, int i2, long j, long j2) {
        this.fid = i;
        this.attributes = i2;
        this.createTime = j;
        this.lastWriteTime = j2;
        this.command = (byte) 50;
        this.subCommand = (byte) 8;
        this.maxParameterCount = 6;
        this.maxDataCount = 0;
        this.maxSetupCount = (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransaction
    public int writeSetupWireFormat(byte[] bArr, int i) {
        bArr[i] = this.subCommand;
        bArr[i + 1] = 0;
        return 2;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeParametersWireFormat(byte[] bArr, int i) {
        writeInt2(this.fid, bArr, i);
        int i2 = i + 2;
        writeInt2(257L, bArr, i2);
        int i3 = i2 + 2;
        writeInt2(0L, bArr, i3);
        return (i3 + 2) - i;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeDataWireFormat(byte[] bArr, int i) {
        writeTime(this.createTime, bArr, i);
        int i2 = i + 8;
        writeInt8(0L, bArr, i2);
        int i3 = i2 + 8;
        writeTime(this.lastWriteTime, bArr, i3);
        int i4 = i3 + 8;
        writeInt8(0L, bArr, i4);
        int i5 = i4 + 8;
        writeInt2(this.attributes | 128, bArr, i5);
        int i6 = i5 + 2;
        writeInt8(0L, bArr, i6);
        return (i6 + 6) - i;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2SetFileInformation[" + super.toString() + ",fid=" + this.fid + "]");
    }
}
