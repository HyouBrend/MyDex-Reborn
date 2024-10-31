package jcifs.dcerpc;

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
public class DcerpcBind extends DcerpcMessage {
    static final String[] result_message = {"0", "DCERPC_BIND_ERR_ABSTRACT_SYNTAX_NOT_SUPPORTED", "DCERPC_BIND_ERR_PROPOSED_TRANSFER_SYNTAXES_NOT_SUPPORTED", "DCERPC_BIND_ERR_LOCAL_LIMIT_EXCEEDED"};
    DcerpcBinding binding;
    int max_recv;
    int max_xmit;

    @Override // jcifs.dcerpc.DcerpcMessage
    public int getOpnum() {
        return 0;
    }

    static String getResultMessage(int i) {
        if (i < 4) {
            return result_message[i];
        }
        return "0x" + Hexdump.toHexString(i, 4);
    }

    @Override // jcifs.dcerpc.DcerpcMessage
    public DcerpcException getResult() {
        if (this.result != 0) {
            return new DcerpcException(getResultMessage(this.result));
        }
        return null;
    }

    public DcerpcBind() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DcerpcBind(DcerpcBinding dcerpcBinding, DcerpcHandle dcerpcHandle) {
        this.binding = dcerpcBinding;
        this.max_xmit = dcerpcHandle.max_xmit;
        this.max_recv = dcerpcHandle.max_recv;
        this.ptype = 11;
        this.flags = 3;
    }

    @Override // jcifs.dcerpc.DcerpcMessage
    public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
        ndrBuffer.enc_ndr_short(this.max_xmit);
        ndrBuffer.enc_ndr_short(this.max_recv);
        ndrBuffer.enc_ndr_long(0);
        ndrBuffer.enc_ndr_small(1);
        ndrBuffer.enc_ndr_small(0);
        ndrBuffer.enc_ndr_short(0);
        ndrBuffer.enc_ndr_short(0);
        ndrBuffer.enc_ndr_small(1);
        ndrBuffer.enc_ndr_small(0);
        this.binding.uuid.encode(ndrBuffer);
        ndrBuffer.enc_ndr_short(this.binding.major);
        ndrBuffer.enc_ndr_short(this.binding.minor);
        DCERPC_UUID_SYNTAX_NDR.encode(ndrBuffer);
        ndrBuffer.enc_ndr_long(2);
    }

    @Override // jcifs.dcerpc.DcerpcMessage
    public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
        ndrBuffer.dec_ndr_short();
        ndrBuffer.dec_ndr_short();
        ndrBuffer.dec_ndr_long();
        ndrBuffer.advance(ndrBuffer.dec_ndr_short());
        ndrBuffer.align(4);
        ndrBuffer.dec_ndr_small();
        ndrBuffer.align(4);
        this.result = ndrBuffer.dec_ndr_short();
        ndrBuffer.dec_ndr_short();
        ndrBuffer.advance(20);
    }
}
