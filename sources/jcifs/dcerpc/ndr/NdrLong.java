package jcifs.dcerpc.ndr;

/* loaded from: classes2.dex */
public class NdrLong extends NdrObject {
    public int value;

    public NdrLong(int i) {
        this.value = i;
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void encode(NdrBuffer ndrBuffer) throws NdrException {
        ndrBuffer.enc_ndr_long(this.value);
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void decode(NdrBuffer ndrBuffer) throws NdrException {
        this.value = ndrBuffer.dec_ndr_long();
    }
}
