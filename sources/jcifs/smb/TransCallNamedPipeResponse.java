package jcifs.smb;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TransCallNamedPipeResponse extends SmbComTransactionResponse {
    private SmbNamedPipe pipe;

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
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
    public TransCallNamedPipeResponse(SmbNamedPipe smbNamedPipe) {
        this.pipe = smbNamedPipe;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        if (this.pipe.pipeIn != null) {
            TransactNamedPipeInputStream transactNamedPipeInputStream = (TransactNamedPipeInputStream) this.pipe.pipeIn;
            synchronized (transactNamedPipeInputStream.lock) {
                transactNamedPipeInputStream.receive(bArr, i, i2);
                transactNamedPipeInputStream.lock.notify();
            }
        }
        return i2;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("TransCallNamedPipeResponse[" + super.toString() + "]");
    }
}
