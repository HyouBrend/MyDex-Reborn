package jcifs.netbios;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NodeStatusRequest extends NameServicePacket {
    @Override // jcifs.netbios.NameServicePacket
    int readBodyWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.netbios.NameServicePacket
    int readRDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.netbios.NameServicePacket
    int writeRDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NodeStatusRequest(Name name) {
        this.questionName = name;
        this.questionType = 33;
        this.isRecurDesired = false;
        this.isBroadcast = false;
    }

    @Override // jcifs.netbios.NameServicePacket
    int writeBodyWireFormat(byte[] bArr, int i) {
        int i2 = this.questionName.hexCode;
        this.questionName.hexCode = 0;
        int writeQuestionSectionWireFormat = writeQuestionSectionWireFormat(bArr, i);
        this.questionName.hexCode = i2;
        return writeQuestionSectionWireFormat;
    }

    @Override // jcifs.netbios.NameServicePacket
    public String toString() {
        return new String("NodeStatusRequest[" + super.toString() + "]");
    }
}
