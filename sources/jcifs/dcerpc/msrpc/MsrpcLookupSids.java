package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.lsarpc;
import jcifs.smb.SID;

/* loaded from: classes2.dex */
public class MsrpcLookupSids extends lsarpc.LsarLookupSids {
    SID[] sids;

    public MsrpcLookupSids(LsaPolicyHandle lsaPolicyHandle, SID[] sidArr) {
        super(lsaPolicyHandle, new LsarSidArrayX(sidArr), new lsarpc.LsarRefDomainList(), new lsarpc.LsarTransNameArray(), (short) 1, sidArr.length);
        this.sids = sidArr;
        this.ptype = 0;
        this.flags = 3;
    }
}
