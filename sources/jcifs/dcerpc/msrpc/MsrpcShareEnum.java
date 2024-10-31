package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.srvsvc;
import jcifs.smb.FileEntry;
import jcifs.smb.SmbShareInfo;

/* loaded from: classes2.dex */
public class MsrpcShareEnum extends srvsvc.ShareEnumAll {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MsrpcShareInfo1 extends SmbShareInfo {
        MsrpcShareInfo1(srvsvc.ShareInfo1 shareInfo1) {
            this.netName = shareInfo1.netname;
            this.type = shareInfo1.type;
            this.remark = shareInfo1.remark;
        }
    }

    public MsrpcShareEnum(String str) {
        super("\\\\" + str, 1, new srvsvc.ShareInfoCtr1(), -1, 0, 0);
        this.ptype = 0;
        this.flags = 3;
    }

    public FileEntry[] getEntries() {
        srvsvc.ShareInfoCtr1 shareInfoCtr1 = (srvsvc.ShareInfoCtr1) this.info;
        MsrpcShareInfo1[] msrpcShareInfo1Arr = new MsrpcShareInfo1[shareInfoCtr1.count];
        for (int i = 0; i < shareInfoCtr1.count; i++) {
            msrpcShareInfo1Arr[i] = new MsrpcShareInfo1(shareInfoCtr1.array[i]);
        }
        return msrpcShareInfo1Arr;
    }
}
