package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.samr;
import jcifs.dcerpc.rpc;

/* loaded from: classes2.dex */
public class MsrpcSamrOpenDomain extends samr.SamrOpenDomain {
    public MsrpcSamrOpenDomain(SamrPolicyHandle samrPolicyHandle, int i, rpc.sid_t sid_tVar, SamrDomainHandle samrDomainHandle) {
        super(samrPolicyHandle, i, sid_tVar, samrDomainHandle);
        this.ptype = 0;
        this.flags = 3;
    }
}
