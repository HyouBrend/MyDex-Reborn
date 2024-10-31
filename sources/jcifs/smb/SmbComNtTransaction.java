package jcifs.smb;

/* loaded from: classes2.dex */
abstract class SmbComNtTransaction extends SmbComTransaction {
    private static final int NTT_PRIMARY_SETUP_OFFSET = 69;
    private static final int NTT_SECONDARY_PARAMETER_OFFSET = 51;
    static final int NT_TRANSACT_QUERY_SECURITY_DESC = 6;
    int function;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComNtTransaction() {
        this.primarySetupOffset = 69;
        this.secondaryParameterOffset = 51;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        int i2;
        int writeSetupWireFormat;
        if (this.command != -95) {
            i2 = i + 1;
            bArr[i] = this.maxSetupCount;
        } else {
            i2 = i + 1;
            bArr[i] = 0;
        }
        int i3 = i2 + 1;
        bArr[i2] = 0;
        int i4 = i3 + 1;
        bArr[i3] = 0;
        writeInt4(this.totalParameterCount, bArr, i4);
        int i5 = i4 + 4;
        writeInt4(this.totalDataCount, bArr, i5);
        int i6 = i5 + 4;
        if (this.command != -95) {
            writeInt4(this.maxParameterCount, bArr, i6);
            int i7 = i6 + 4;
            writeInt4(this.maxDataCount, bArr, i7);
            i6 = i7 + 4;
        }
        writeInt4(this.parameterCount, bArr, i6);
        int i8 = i6 + 4;
        writeInt4(this.parameterCount == 0 ? 0 : this.parameterOffset, bArr, i8);
        int i9 = i8 + 4;
        if (this.command == -95) {
            writeInt4(this.parameterDisplacement, bArr, i9);
            i9 += 4;
        }
        writeInt4(this.dataCount, bArr, i9);
        int i10 = i9 + 4;
        writeInt4(this.dataCount == 0 ? 0 : this.dataOffset, bArr, i10);
        int i11 = i10 + 4;
        if (this.command == -95) {
            writeInt4(this.dataDisplacement, bArr, i11);
            int i12 = i11 + 4;
            writeSetupWireFormat = i12 + 1;
            bArr[i12] = 0;
        } else {
            int i13 = i11 + 1;
            bArr[i11] = (byte) this.setupCount;
            writeInt2(this.function, bArr, i13);
            int i14 = i13 + 2;
            writeSetupWireFormat = i14 + writeSetupWireFormat(bArr, i14);
        }
        return writeSetupWireFormat - i;
    }
}
