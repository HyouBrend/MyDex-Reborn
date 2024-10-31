package jcifs.smb;

import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
class Trans2QueryPathInformation extends SmbComTransaction {
    private int informationLevel;

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
    public Trans2QueryPathInformation(String str, int i) {
        this.path = str;
        this.informationLevel = i;
        this.command = (byte) 50;
        this.subCommand = (byte) 5;
        this.totalDataCount = 0;
        this.maxParameterCount = 2;
        this.maxDataCount = 40;
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
        writeInt2(this.informationLevel, bArr, i);
        int i2 = i + 2;
        int i3 = i2 + 1;
        bArr[i2] = 0;
        int i4 = i3 + 1;
        bArr[i3] = 0;
        int i5 = i4 + 1;
        bArr[i4] = 0;
        int i6 = i5 + 1;
        bArr[i5] = 0;
        return (i6 + writeString(this.path, bArr, i6)) - i;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2QueryPathInformation[" + super.toString() + ",informationLevel=0x" + Hexdump.toHexString(this.informationLevel, 3) + ",filename=" + this.path + "]");
    }
}
