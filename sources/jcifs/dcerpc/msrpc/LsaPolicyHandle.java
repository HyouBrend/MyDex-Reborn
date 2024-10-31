package jcifs.dcerpc.msrpc;

import java.io.IOException;
import jcifs.dcerpc.DcerpcHandle;
import jcifs.dcerpc.rpc;
import jcifs.smb.SmbException;

/* loaded from: classes2.dex */
public class LsaPolicyHandle extends rpc.policy_handle {
    public void close() throws IOException {
    }

    public LsaPolicyHandle(DcerpcHandle dcerpcHandle, String str, int i) throws IOException {
        MsrpcLsarOpenPolicy2 msrpcLsarOpenPolicy2 = new MsrpcLsarOpenPolicy2(str == null ? "\\\\" : str, i, this);
        dcerpcHandle.sendrecv(msrpcLsarOpenPolicy2);
        if (msrpcLsarOpenPolicy2.retval != 0) {
            throw new SmbException(msrpcLsarOpenPolicy2.retval, false);
        }
    }
}
