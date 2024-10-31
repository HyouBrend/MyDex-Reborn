package jcifs.smb;

/* loaded from: classes2.dex */
class SmbComFindClose2 extends ServerMessageBlock {
    private int sid;

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
    public SmbComFindClose2(int i) {
        this.sid = i;
        this.command = (byte) 52;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.sid, bArr, i);
        return 2;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComFindClose2[" + super.toString() + ",sid=" + this.sid + "]");
    }
}
