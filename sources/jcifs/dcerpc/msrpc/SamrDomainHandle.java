package jcifs.dcerpc.msrpc;

import java.io.IOException;
import jcifs.dcerpc.DcerpcHandle;
import jcifs.dcerpc.rpc;
import jcifs.smb.SmbException;

/* loaded from: classes2.dex */
public class SamrDomainHandle extends rpc.policy_handle {
    public void close() throws IOException {
    }

    public SamrDomainHandle(DcerpcHandle dcerpcHandle, SamrPolicyHandle samrPolicyHandle, int i, rpc.sid_t sid_tVar) throws IOException {
        MsrpcSamrOpenDomain msrpcSamrOpenDomain = new MsrpcSamrOpenDomain(samrPolicyHandle, i, sid_tVar, this);
        dcerpcHandle.sendrecv(msrpcSamrOpenDomain);
        if (msrpcSamrOpenDomain.retval != 0) {
            throw new SmbException(msrpcSamrOpenDomain.retval, false);
        }
    }
}
