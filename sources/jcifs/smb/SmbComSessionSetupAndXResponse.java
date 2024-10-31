package jcifs.smb;

/* loaded from: classes2.dex */
class SmbComSessionSetupAndXResponse extends AndXServerMessageBlock {
    byte[] blob;
    boolean isLoggedInAsGuest;
    private String nativeLanMan;
    private String nativeOs;
    private String primaryDomain;

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
    public SmbComSessionSetupAndXResponse(ServerMessageBlock serverMessageBlock) {
        super(serverMessageBlock);
        this.nativeOs = "";
        this.nativeLanMan = "";
        this.primaryDomain = "";
        this.blob = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        this.isLoggedInAsGuest = (bArr[i] & 1) == 1;
        int i2 = i + 2;
        if (this.extendedSecurity) {
            int readInt2 = readInt2(bArr, i2);
            i2 += 2;
            this.blob = new byte[readInt2];
        }
        return i2 - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        int i2;
        if (this.extendedSecurity) {
            byte[] bArr2 = this.blob;
            System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
            i2 = this.blob.length + i;
        } else {
            i2 = i;
        }
        String readString = readString(bArr, i2);
        this.nativeOs = readString;
        int stringWireLength = i2 + stringWireLength(readString, i2);
        String readString2 = readString(bArr, stringWireLength, i + this.byteCount, 255, this.useUnicode);
        this.nativeLanMan = readString2;
        int stringWireLength2 = stringWireLength + stringWireLength(readString2, stringWireLength);
        if (!this.extendedSecurity) {
            String readString3 = readString(bArr, stringWireLength2, i + this.byteCount, 255, this.useUnicode);
            this.primaryDomain = readString3;
            stringWireLength2 += stringWireLength(readString3, stringWireLength2);
        }
        return stringWireLength2 - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComSessionSetupAndXResponse[" + super.toString() + ",isLoggedInAsGuest=" + this.isLoggedInAsGuest + ",nativeOs=" + this.nativeOs + ",nativeLanMan=" + this.nativeLanMan + ",primaryDomain=" + this.primaryDomain + "]");
    }
}
