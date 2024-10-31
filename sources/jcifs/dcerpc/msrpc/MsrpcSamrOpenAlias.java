package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.samr;

/* loaded from: classes2.dex */
public class MsrpcSamrOpenAlias extends samr.SamrOpenAlias {
    public MsrpcSamrOpenAlias(SamrDomainHandle samrDomainHandle, int i, int i2, SamrAliasHandle samrAliasHandle) {
        super(samrDomainHandle, i, i2, samrAliasHandle);
        this.ptype = 0;
        this.flags = 3;
    }
}
