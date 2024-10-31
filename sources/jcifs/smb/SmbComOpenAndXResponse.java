package jcifs.smb;

/* loaded from: classes2.dex */
class SmbComOpenAndXResponse extends AndXServerMessageBlock {
    int action;
    int dataSize;
    int deviceState;
    int fid;
    int fileAttributes;
    int fileType;
    int grantedAccess;
    long lastWriteTime;
    int serverFid;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        this.fid = readInt2(bArr, i);
        int i2 = i + 2;
        this.fileAttributes = readInt2(bArr, i2);
        int i3 = i2 + 2;
        this.lastWriteTime = readUTime(bArr, i3);
        int i4 = i3 + 4;
        this.dataSize = readInt4(bArr, i4);
        int i5 = i4 + 4;
        this.grantedAccess = readInt2(bArr, i5);
        int i6 = i5 + 2;
        this.fileType = readInt2(bArr, i6);
        int i7 = i6 + 2;
        this.deviceState = readInt2(bArr, i7);
        int i8 = i7 + 2;
        this.action = readInt2(bArr, i8);
        int i9 = i8 + 2;
        this.serverFid = readInt4(bArr, i9);
        return (i9 + 6) - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComOpenAndXResponse[" + super.toString() + ",fid=" + this.fid + ",fileAttributes=" + this.fileAttributes + ",lastWriteTime=" + this.lastWriteTime + ",dataSize=" + this.dataSize + ",grantedAccess=" + this.grantedAccess + ",fileType=" + this.fileType + ",deviceState=" + this.deviceState + ",action=" + this.action + ",serverFid=" + this.serverFid + "]");
    }
}
