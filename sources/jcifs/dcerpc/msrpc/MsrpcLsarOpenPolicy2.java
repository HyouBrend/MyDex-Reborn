package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.lsarpc;

/* loaded from: classes2.dex */
public class MsrpcLsarOpenPolicy2 extends lsarpc.LsarOpenPolicy2 {
    public MsrpcLsarOpenPolicy2(String str, int i, LsaPolicyHandle lsaPolicyHandle) {
        super(str, new lsarpc.LsarObjectAttributes(), i, lsaPolicyHandle);
        this.object_attributes.length = 24;
        lsarpc.LsarQosInfo lsarQosInfo = new lsarpc.LsarQosInfo();
        lsarQosInfo.length = 12;
        lsarQosInfo.impersonation_level = (short) 2;
        lsarQosInfo.context_mode = (byte) 1;
        lsarQosInfo.effective_only = (byte) 0;
        this.object_attributes.security_quality_of_service = lsarQosInfo;
        this.ptype = 0;
        this.flags = 3;
    }
}
