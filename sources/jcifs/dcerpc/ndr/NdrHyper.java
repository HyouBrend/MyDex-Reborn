package jcifs.dcerpc.ndr;

/* loaded from: classes2.dex */
public class NdrHyper extends NdrObject {
    public long value;

    public NdrHyper(long j) {
        this.value = j;
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void encode(NdrBuffer ndrBuffer) throws NdrException {
        ndrBuffer.enc_ndr_hyper(this.value);
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void decode(NdrBuffer ndrBuffer) throws NdrException {
        this.value = ndrBuffer.dec_ndr_hyper();
    }
}
