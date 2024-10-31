package jcifs.dcerpc.ndr;

/* loaded from: classes2.dex */
public class NdrSmall extends NdrObject {
    public int value;

    public NdrSmall(int i) {
        this.value = i & 255;
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void encode(NdrBuffer ndrBuffer) throws NdrException {
        ndrBuffer.enc_ndr_small(this.value);
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void decode(NdrBuffer ndrBuffer) throws NdrException {
        this.value = ndrBuffer.dec_ndr_small();
    }
}
