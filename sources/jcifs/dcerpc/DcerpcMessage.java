package jcifs.dcerpc;

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrObject;

/* loaded from: classes2.dex */
public abstract class DcerpcMessage extends NdrObject implements DcerpcConstants {
    protected int ptype = -1;
    protected int flags = 0;
    protected int length = 0;
    protected int call_id = 0;
    protected int alloc_hint = 0;
    protected int result = 0;

    public abstract void decode_out(NdrBuffer ndrBuffer) throws NdrException;

    public abstract void encode_in(NdrBuffer ndrBuffer) throws NdrException;

    public abstract int getOpnum();

    public boolean isFlagSet(int i) {
        return (this.flags & i) == i;
    }

    public void unsetFlag(int i) {
        this.flags = (~i) & this.flags;
    }

    public void setFlag(int i) {
        this.flags = i | this.flags;
    }

    public DcerpcException getResult() {
        if (this.result != 0) {
            return new DcerpcException(this.result);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void encode_header(NdrBuffer ndrBuffer) {
        ndrBuffer.enc_ndr_small(5);
        ndrBuffer.enc_ndr_small(0);
        ndrBuffer.enc_ndr_small(this.ptype);
        ndrBuffer.enc_ndr_small(this.flags);
        ndrBuffer.enc_ndr_long(16);
        ndrBuffer.enc_ndr_short(this.length);
        ndrBuffer.enc_ndr_short(0);
        ndrBuffer.enc_ndr_long(this.call_id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void decode_header(NdrBuffer ndrBuffer) throws NdrException {
        if (ndrBuffer.dec_ndr_small() != 5 || ndrBuffer.dec_ndr_small() != 0) {
            throw new NdrException("DCERPC version not supported");
        }
        this.ptype = ndrBuffer.dec_ndr_small();
        this.flags = ndrBuffer.dec_ndr_small();
        if (ndrBuffer.dec_ndr_long() != 16) {
            throw new NdrException("Data representation not supported");
        }
        this.length = ndrBuffer.dec_ndr_short();
        if (ndrBuffer.dec_ndr_short() != 0) {
            throw new NdrException("DCERPC authentication not supported");
        }
        this.call_id = ndrBuffer.dec_ndr_long();
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void encode(NdrBuffer ndrBuffer) throws NdrException {
        int index = ndrBuffer.getIndex();
        ndrBuffer.advance(16);
        int i = 0;
        if (this.ptype == 0) {
            int index2 = ndrBuffer.getIndex();
            ndrBuffer.enc_ndr_long(0);
            ndrBuffer.enc_ndr_short(0);
            ndrBuffer.enc_ndr_short(getOpnum());
            i = index2;
        }
        encode_in(ndrBuffer);
        this.length = ndrBuffer.getIndex() - index;
        if (this.ptype == 0) {
            ndrBuffer.setIndex(i);
            int i2 = this.length - i;
            this.alloc_hint = i2;
            ndrBuffer.enc_ndr_long(i2);
        }
        ndrBuffer.setIndex(index);
        encode_header(ndrBuffer);
        ndrBuffer.setIndex(index + this.length);
    }

    @Override // jcifs.dcerpc.ndr.NdrObject
    public void decode(NdrBuffer ndrBuffer) throws NdrException {
        decode_header(ndrBuffer);
        int i = this.ptype;
        if (i != 12 && i != 2 && i != 3 && i != 13) {
            throw new NdrException("Unexpected ptype: " + this.ptype);
        }
        if (i == 2 || i == 3) {
            this.alloc_hint = ndrBuffer.dec_ndr_long();
            ndrBuffer.dec_ndr_short();
            ndrBuffer.dec_ndr_short();
        }
        int i2 = this.ptype;
        if (i2 == 3 || i2 == 13) {
            this.result = ndrBuffer.dec_ndr_long();
        } else {
            decode_out(ndrBuffer);
        }
    }
}
