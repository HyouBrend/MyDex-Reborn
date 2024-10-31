package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.samr;

/* loaded from: classes2.dex */
public class MsrpcSamrConnect4 extends samr.SamrConnect4 {
    public MsrpcSamrConnect4(String str, int i, SamrPolicyHandle samrPolicyHandle) {
        super(str, 2, i, samrPolicyHandle);
        this.ptype = 0;
        this.flags = 3;
    }
}
