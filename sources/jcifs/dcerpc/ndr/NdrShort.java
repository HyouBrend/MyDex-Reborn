package jcifs.dcerpc.ndr;

/* loaded from: classes2.dex */
public class NdrShort extends NdrObject {
    public int value;

    public NdrShort(int i) {
        this.value = i & 255;
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void encode(NdrBuffer ndrBuffer) throws NdrException {
        ndrBuffer.enc_ndr_short(this.value);
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void decode(NdrBuffer ndrBuffer) throws NdrException {
        this.value = ndrBuffer.dec_ndr_short();
    }
}
