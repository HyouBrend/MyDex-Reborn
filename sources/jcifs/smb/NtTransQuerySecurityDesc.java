package jcifs.smb;

import jcifs.util.Hexdump;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NtTransQuerySecurityDesc extends SmbComNtTransaction {
    int fid;
    int securityInformation;

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
    @Override // jcifs.smb.SmbComTransaction
    public int writeSetupWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NtTransQuerySecurityDesc(int i, int i2) {
        this.fid = i;
        this.securityInformation = i2;
        this.command = (byte) -96;
        this.function = 6;
        this.setupCount = 0;
        this.totalDataCount = 0;
        this.maxParameterCount = 4;
        this.maxDataCount = 32768;
        this.maxSetupCount = (byte) 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeParametersWireFormat(byte[] bArr, int i) {
        writeInt2(this.fid, bArr, i);
        int i2 = i + 2;
        int i3 = i2 + 1;
        bArr[i2] = 0;
        int i4 = i3 + 1;
        bArr[i3] = 0;
        writeInt4(this.securityInformation, bArr, i4);
        return (i4 + 4) - i;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("NtTransQuerySecurityDesc[" + super.toString() + ",fid=0x" + Hexdump.toHexString(this.fid, 4) + ",securityInformation=0x" + Hexdump.toHexString(this.securityInformation, 8) + "]");
    }
}
