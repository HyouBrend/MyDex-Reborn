package jcifs.smb;

import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
class SmbComRename extends ServerMessageBlock {
    private String newFileName;
    private String oldFileName;
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
    public SmbComRename(String str, String str2) {
        this.command = (byte) 7;
        this.oldFileName = str;
        this.newFileName = str2;
        this.searchAttributes = 22;
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
        int writeString = i2 + writeString(this.oldFileName, bArr, i2);
        int i3 = writeString + 1;
        bArr[writeString] = 4;
        if (this.useUnicode) {
            bArr[i3] = 0;
            i3++;
        }
        return (i3 + writeString(this.newFileName, bArr, i3)) - i;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComRename[" + super.toString() + ",searchAttributes=0x" + Hexdump.toHexString(this.searchAttributes, 4) + ",oldFileName=" + this.oldFileName + ",newFileName=" + this.newFileName + "]");
    }
}
