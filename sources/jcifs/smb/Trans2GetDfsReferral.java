package jcifs.smb;

/* loaded from: classes2.dex */
class Trans2GetDfsReferral extends SmbComTransaction {
    private int maxReferralLevel = 3;

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

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2GetDfsReferral(String str) {
        this.path = str;
        this.command = (byte) 50;
        this.subCommand = (byte) 16;
        this.totalDataCount = 0;
        this.maxParameterCount = 0;
        this.maxDataCount = 4096;
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
        writeInt2(this.maxReferralLevel, bArr, i);
        int i2 = i + 2;
        return (i2 + writeString(this.path, bArr, i2)) - i;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2GetDfsReferral[" + super.toString() + ",maxReferralLevel=0x" + this.maxReferralLevel + ",filename=" + this.path + "]");
    }
}
