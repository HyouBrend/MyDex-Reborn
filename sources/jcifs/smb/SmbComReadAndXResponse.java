package jcifs.smb;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComReadAndXResponse extends AndXServerMessageBlock {
    byte[] b;
    int dataCompactionMode;
    int dataLength;
    int dataOffset;
    int off;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

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
    public SmbComReadAndXResponse() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComReadAndXResponse(byte[] bArr, int i) {
        this.b = bArr;
        this.off = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParam(byte[] bArr, int i) {
        this.b = bArr;
        this.off = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        int i2 = i + 2;
        this.dataCompactionMode = readInt2(bArr, i2);
        int i3 = i2 + 4;
        this.dataLength = readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.dataOffset = readInt2(bArr, i4);
        return (i4 + 12) - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComReadAndXResponse[" + super.toString() + ",dataCompactionMode=" + this.dataCompactionMode + ",dataLength=" + this.dataLength + ",dataOffset=" + this.dataOffset + "]");
    }
}
