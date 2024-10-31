package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.lsarpc;
import jcifs.smb.SID;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LsarSidArrayX extends lsarpc.LsarSidArray {
    /* JADX INFO: Access modifiers changed from: package-private */
    public LsarSidArrayX(SID[] sidArr) {
        this.num_sids = sidArr.length;
        this.sids = new lsarpc.LsarSidPtr[sidArr.length];
        for (int i = 0; i < sidArr.length; i++) {
            this.sids[i] = new lsarpc.LsarSidPtr();
            this.sids[i].sid = sidArr[i];
        }
    }
}
