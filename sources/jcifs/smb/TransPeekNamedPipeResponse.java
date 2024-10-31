package jcifs.smb;

/* loaded from: classes2.dex */
class TransPeekNamedPipeResponse extends SmbComTransactionResponse {
    static final int STATUS_CONNECTION_OK = 3;
    static final int STATUS_DISCONNECTED = 1;
    static final int STATUS_LISTENING = 2;
    static final int STATUS_SERVER_END_CLOSED = 4;
    int available;
    private int head;
    private SmbNamedPipe pipe;
    int status;

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readSetupWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int writeDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int writeParametersWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int writeSetupWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransPeekNamedPipeResponse(SmbNamedPipe smbNamedPipe) {
        this.pipe = smbNamedPipe;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        this.available = readInt2(bArr, i);
        int i3 = i + 2;
        this.head = readInt2(bArr, i3);
        this.status = readInt2(bArr, i3 + 2);
        return 6;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("TransPeekNamedPipeResponse[" + super.toString() + "]");
    }
}
