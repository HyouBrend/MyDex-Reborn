package jcifs.smb;

import java.io.UnsupportedEncodingException;
import org.apache.commons.net.tftp.TFTP;

/* loaded from: classes2.dex */
class NetShareEnum extends SmbComTransaction {
    private static final String DESCR = "WrLeh\u0000B13BWz\u0000";

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
    public NetShareEnum() {
        this.command = (byte) 37;
        this.subCommand = (byte) 0;
        this.name = new String("\\PIPE\\LANMAN");
        this.maxParameterCount = 8;
        this.maxSetupCount = (byte) 0;
        this.setupCount = 0;
        this.timeout = TFTP.DEFAULT_TIMEOUT;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeParametersWireFormat(byte[] bArr, int i) {
        try {
            byte[] bytes = DESCR.getBytes("ASCII");
            writeInt2(0L, bArr, i);
            int i2 = i + 2;
            System.arraycopy(bytes, 0, bArr, i2, bytes.length);
            int length = i2 + bytes.length;
            writeInt2(1L, bArr, length);
            int i3 = length + 2;
            writeInt2(this.maxDataCount, bArr, i3);
            return (i3 + 2) - i;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("NetShareEnum[" + super.toString() + "]");
    }
}
