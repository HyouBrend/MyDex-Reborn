package jcifs.smb;

/* loaded from: classes2.dex */
class SmbComClose extends ServerMessageBlock {
    private int fid;
    private long lastWriteTime;

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
    public SmbComClose(int i, long j) {
        this.fid = i;
        this.lastWriteTime = j;
        this.command = (byte) 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.fid, bArr, i);
        writeUTime(this.lastWriteTime, bArr, i + 2);
        return 6;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComClose[" + super.toString() + ",fid=" + this.fid + ",lastWriteTime=" + this.lastWriteTime + "]");
    }
}
