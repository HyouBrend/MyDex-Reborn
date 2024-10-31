package jcifs.smb;

import jcifs.util.Hexdump;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Trans2FindNext2 extends SmbComTransaction {
    private String filename;
    private int flags;
    private int informationLevel;
    private int resumeKey;
    private int sid;

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
    public Trans2FindNext2(int i, int i2, String str) {
        this.sid = i;
        this.resumeKey = i2;
        this.filename = str;
        this.command = (byte) 50;
        this.subCommand = (byte) 2;
        this.informationLevel = 260;
        this.flags = 0;
        this.maxParameterCount = 8;
        this.maxDataCount = Trans2FindFirst2.LIST_SIZE;
        this.maxSetupCount = (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransaction
    public void reset(int i, String str) {
        super.reset();
        this.resumeKey = i;
        this.filename = str;
        this.flags2 = 0;
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
        writeInt2(this.sid, bArr, i);
        int i2 = i + 2;
        writeInt2(Trans2FindFirst2.LIST_COUNT, bArr, i2);
        int i3 = i2 + 2;
        writeInt2(this.informationLevel, bArr, i3);
        int i4 = i3 + 2;
        writeInt4(this.resumeKey, bArr, i4);
        int i5 = i4 + 4;
        writeInt2(this.flags, bArr, i5);
        int i6 = i5 + 2;
        return (i6 + writeString(this.filename, bArr, i6)) - i;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2FindNext2[" + super.toString() + ",sid=" + this.sid + ",searchCount=" + Trans2FindFirst2.LIST_SIZE + ",informationLevel=0x" + Hexdump.toHexString(this.informationLevel, 3) + ",resumeKey=0x" + Hexdump.toHexString(this.resumeKey, 4) + ",flags=0x" + Hexdump.toHexString(this.flags, 2) + ",filename=" + this.filename + "]");
    }
}
