package jcifs.dcerpc.msrpc;

import java.io.IOException;
import jcifs.dcerpc.DcerpcHandle;
import jcifs.dcerpc.rpc;
import jcifs.smb.SmbException;

/* loaded from: classes2.dex */
public class SamrAliasHandle extends rpc.policy_handle {
    public void close() throws IOException {
    }

    public SamrAliasHandle(DcerpcHandle dcerpcHandle, SamrDomainHandle samrDomainHandle, int i, int i2) throws IOException {
        MsrpcSamrOpenAlias msrpcSamrOpenAlias = new MsrpcSamrOpenAlias(samrDomainHandle, i, i2, this);
        dcerpcHandle.sendrecv(msrpcSamrOpenAlias);
        if (msrpcSamrOpenAlias.retval != 0) {
            throw new SmbException(msrpcSamrOpenAlias.retval, false);
        }
    }
}
