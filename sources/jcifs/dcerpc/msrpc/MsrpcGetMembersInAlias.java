package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.lsarpc;
import jcifs.dcerpc.msrpc.samr;

/* loaded from: classes2.dex */
public class MsrpcGetMembersInAlias extends samr.SamrGetMembersInAlias {
    public MsrpcGetMembersInAlias(SamrAliasHandle samrAliasHandle, lsarpc.LsarSidArray lsarSidArray) {
        super(samrAliasHandle, lsarSidArray);
        this.sids = lsarSidArray;
        this.ptype = 0;
        this.flags = 3;
    }
}
