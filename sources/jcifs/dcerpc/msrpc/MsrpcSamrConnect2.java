package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.samr;

/* loaded from: classes2.dex */
public class MsrpcSamrConnect2 extends samr.SamrConnect2 {
    public MsrpcSamrConnect2(String str, int i, SamrPolicyHandle samrPolicyHandle) {
        super(str, i, samrPolicyHandle);
        this.ptype = 0;
        this.flags = 3;
    }
}
