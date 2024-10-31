package jcifs.smb;

import java.io.UnsupportedEncodingException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComNegotiate extends ServerMessageBlock {
    private static final String DIALECTS = "\u0002NT LM 0.12\u0000";

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
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComNegotiate() {
        this.command = (byte) 114;
        this.flags2 = DEFAULT_FLAGS2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        try {
            byte[] bytes = DIALECTS.getBytes("ASCII");
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            return bytes.length;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComNegotiate[" + super.toString() + ",wordCount=" + this.wordCount + ",dialects=NT LM 0.12]");
    }
}
