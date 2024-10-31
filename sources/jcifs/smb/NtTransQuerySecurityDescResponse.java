package jcifs.smb;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NtTransQuerySecurityDescResponse extends SmbComNtTransactionResponse {
    SecurityDescriptor securityDescriptor;

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

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        this.length = readInt4(bArr, i);
        return 4;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        if (this.errorCode != 0) {
            return 4;
        }
        try {
            SecurityDescriptor securityDescriptor = new SecurityDescriptor();
            this.securityDescriptor = securityDescriptor;
            return (securityDescriptor.decode(bArr, i, i2) + i) - i;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("NtTransQuerySecurityResponse[" + super.toString() + "]");
    }
}
