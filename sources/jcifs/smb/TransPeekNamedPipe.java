package jcifs.smb;

/* loaded from: classes2.dex */
class TransPeekNamedPipe extends SmbComTransaction {
    private int fid;

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

    @Override // jcifs.smb.SmbComTransaction
    int writeDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeParametersWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransPeekNamedPipe(String str, int i) {
        this.name = str;
        this.fid = i;
        this.command = (byte) 37;
        this.subCommand = (byte) 35;
        this.timeout = -1;
        this.maxParameterCount = 6;
        this.maxDataCount = 1;
        this.maxSetupCount = (byte) 0;
        this.setupCount = 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransaction
    public int writeSetupWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = this.subCommand;
        bArr[i2] = 0;
        writeInt2(this.fid, bArr, i2 + 1);
        return 4;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("TransPeekNamedPipe[" + super.toString() + ",pipeName=" + this.name + "]");
    }
}
