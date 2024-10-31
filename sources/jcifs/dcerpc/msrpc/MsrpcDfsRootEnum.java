package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.msrpc.netdfs;
import jcifs.dcerpc.ndr.NdrLong;
import jcifs.smb.FileEntry;
import jcifs.smb.SmbShareInfo;

/* loaded from: classes2.dex */
public class MsrpcDfsRootEnum extends netdfs.NetrDfsEnumEx {
    public MsrpcDfsRootEnum(String str) {
        super(str, 200, 65535, new netdfs.DfsEnumStruct(), new NdrLong(0));
        this.info.level = this.level;
        this.info.e = new netdfs.DfsEnumArray200();
        this.ptype = 0;
        this.flags = 3;
    }

    public FileEntry[] getEntries() {
        netdfs.DfsEnumArray200 dfsEnumArray200 = (netdfs.DfsEnumArray200) this.info.e;
        SmbShareInfo[] smbShareInfoArr = new SmbShareInfo[dfsEnumArray200.count];
        for (int i = 0; i < dfsEnumArray200.count; i++) {
            smbShareInfoArr[i] = new SmbShareInfo(dfsEnumArray200.s[i].dfs_name, 0, null);
        }
        return smbShareInfoArr;
    }
}
