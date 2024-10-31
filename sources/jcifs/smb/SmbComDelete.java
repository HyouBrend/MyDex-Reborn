package jcifs.smb;

import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
class SmbComDelete extends ServerMessageBlock {
    private int searchAttributes;

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
    public SmbComDelete(String str) {
        this.path = str;
        this.command = (byte) 6;
        this.searchAttributes = 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.searchAttributes, bArr, i);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = 4;
        return (i2 + writeString(this.path, bArr, i2)) - i;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComDelete[" + super.toString() + ",searchAttributes=0x" + Hexdump.toHexString(this.searchAttributes, 4) + ",fileName=" + this.path + "]");
    }
}
