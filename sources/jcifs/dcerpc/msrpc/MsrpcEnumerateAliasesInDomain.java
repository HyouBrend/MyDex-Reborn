package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.samr;

/* loaded from: classes2.dex */
public class MsrpcEnumerateAliasesInDomain extends samr.SamrEnumerateAliasesInDomain {
    public MsrpcEnumerateAliasesInDomain(SamrDomainHandle samrDomainHandle, int i, samr.SamrSamArray samrSamArray) {
        super(samrDomainHandle, 0, i, null, 0);
        this.sam = samrSamArray;
        this.ptype = 0;
        this.flags = 3;
    }
}
