package jcifs.netbios;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NameQueryRequest extends NameServicePacket {
    @Override // jcifs.netbios.NameServicePacket
    int readRDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.netbios.NameServicePacket
    int writeRDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NameQueryRequest(Name name) {
        this.questionName = name;
        this.questionType = 32;
    }

    @Override // jcifs.netbios.NameServicePacket
    int writeBodyWireFormat(byte[] bArr, int i) {
        return writeQuestionSectionWireFormat(bArr, i);
    }

    @Override // jcifs.netbios.NameServicePacket
    int readBodyWireFormat(byte[] bArr, int i) {
        return readQuestionSectionWireFormat(bArr, i);
    }

    @Override // jcifs.netbios.NameServicePacket
    public String toString() {
        return new String("NameQueryRequest[" + super.toString() + "]");
    }
}
