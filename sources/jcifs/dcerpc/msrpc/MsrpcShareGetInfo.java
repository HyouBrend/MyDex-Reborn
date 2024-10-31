package jcifs.dcerpc.msrpc;

import java.io.IOException;
import jcifs.dcerpc.msrpc.srvsvc;
import jcifs.smb.ACE;
import jcifs.smb.SecurityDescriptor;

/* loaded from: classes2.dex */
public class MsrpcShareGetInfo extends srvsvc.ShareGetInfo {
    public MsrpcShareGetInfo(String str, String str2) {
        super(str, str2, 502, new srvsvc.ShareInfo502());
        this.ptype = 0;
        this.flags = 3;
    }

    public ACE[] getSecurity() throws IOException {
        srvsvc.ShareInfo502 shareInfo502 = (srvsvc.ShareInfo502) this.info;
        if (shareInfo502.security_descriptor != null) {
            return new SecurityDescriptor(shareInfo502.security_descriptor, 0, shareInfo502.sd_size).aces;
        }
        return null;
    }
}
