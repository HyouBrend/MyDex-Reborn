package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.DcerpcMessage;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrLong;
import jcifs.dcerpc.ndr.NdrObject;

/* loaded from: classes2.dex */
public class netdfs {
    public static final int DFS_STORAGE_STATE_ACTIVE = 4;
    public static final int DFS_STORAGE_STATE_OFFLINE = 1;
    public static final int DFS_STORAGE_STATE_ONLINE = 2;
    public static final int DFS_VOLUME_FLAVOR_AD_BLOB = 512;
    public static final int DFS_VOLUME_FLAVOR_STANDALONE = 256;

    public static String getSyntax() {
        return "4fc742e0-4a10-11cf-8273-00aa004ae673:3.0";
    }

    /* loaded from: classes2.dex */
    public static class DfsInfo1 extends NdrObject {
        public String entry_path;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.entry_path, 1);
            if (this.entry_path != null) {
                ndrBuffer.deferred.enc_ndr_string(this.entry_path);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            if (ndrBuffer.dec_ndr_long() != 0) {
                this.entry_path = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsEnumArray1 extends NdrObject {
        public int count;
        public DfsInfo1[] s;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.s, 1);
            if (this.s != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 4);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.s[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.count = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long * 4);
                if (this.s == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.s = new DfsInfo1[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    DfsInfo1[] dfsInfo1Arr = this.s;
                    if (dfsInfo1Arr[i2] == null) {
                        dfsInfo1Arr[i2] = new DfsInfo1();
                    }
                    this.s[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsStorageInfo extends NdrObject {
        public String server_name;
        public String share_name;
        public int state;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.state);
            ndrBuffer.enc_ndr_referent(this.server_name, 1);
            ndrBuffer.enc_ndr_referent(this.share_name, 1);
            if (this.server_name != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.server_name);
            }
            if (this.share_name != null) {
                ndrBuffer.deferred.enc_ndr_string(this.share_name);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.state = ndrBuffer.dec_ndr_long();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.server_name = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long2 != 0) {
                this.share_name = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsInfo3 extends NdrObject {
        public String comment;
        public int num_stores;
        public String path;
        public int state;
        public DfsStorageInfo[] stores;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.path, 1);
            ndrBuffer.enc_ndr_referent(this.comment, 1);
            ndrBuffer.enc_ndr_long(this.state);
            ndrBuffer.enc_ndr_long(this.num_stores);
            ndrBuffer.enc_ndr_referent(this.stores, 1);
            if (this.path != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.path);
            }
            if (this.comment != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.comment);
            }
            if (this.stores != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.num_stores;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 12);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.stores[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            this.state = ndrBuffer.dec_ndr_long();
            this.num_stores = ndrBuffer.dec_ndr_long();
            int dec_ndr_long3 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.path = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long2 != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.comment = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long3 != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long4 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long4 * 12);
                if (this.stores == null) {
                    if (dec_ndr_long4 < 0 || dec_ndr_long4 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.stores = new DfsStorageInfo[dec_ndr_long4];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long4; i2++) {
                    DfsStorageInfo[] dfsStorageInfoArr = this.stores;
                    if (dfsStorageInfoArr[i2] == null) {
                        dfsStorageInfoArr[i2] = new DfsStorageInfo();
                    }
                    this.stores[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsEnumArray3 extends NdrObject {
        public int count;
        public DfsInfo3[] s;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.s, 1);
            if (this.s != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 20);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.s[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.count = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long * 20);
                if (this.s == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.s = new DfsInfo3[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    DfsInfo3[] dfsInfo3Arr = this.s;
                    if (dfsInfo3Arr[i2] == null) {
                        dfsInfo3Arr[i2] = new DfsInfo3();
                    }
                    this.s[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsInfo200 extends NdrObject {
        public String dfs_name;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.dfs_name, 1);
            if (this.dfs_name != null) {
                ndrBuffer.deferred.enc_ndr_string(this.dfs_name);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            if (ndrBuffer.dec_ndr_long() != 0) {
                this.dfs_name = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsEnumArray200 extends NdrObject {
        public int count;
        public DfsInfo200[] s;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.s, 1);
            if (this.s != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 4);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.s[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.count = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long * 4);
                if (this.s == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.s = new DfsInfo200[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    DfsInfo200[] dfsInfo200Arr = this.s;
                    if (dfsInfo200Arr[i2] == null) {
                        dfsInfo200Arr[i2] = new DfsInfo200();
                    }
                    this.s[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsInfo300 extends NdrObject {
        public String dfs_name;
        public int flags;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.flags);
            ndrBuffer.enc_ndr_referent(this.dfs_name, 1);
            if (this.dfs_name != null) {
                ndrBuffer.deferred.enc_ndr_string(this.dfs_name);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.flags = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                this.dfs_name = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsEnumArray300 extends NdrObject {
        public int count;
        public DfsInfo300[] s;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.s, 1);
            if (this.s != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 8);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.s[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.count = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long * 8);
                if (this.s == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.s = new DfsInfo300[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    DfsInfo300[] dfsInfo300Arr = this.s;
                    if (dfsInfo300Arr[i2] == null) {
                        dfsInfo300Arr[i2] = new DfsInfo300();
                    }
                    this.s[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DfsEnumStruct extends NdrObject {
        public NdrObject e;
        public int level;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.level);
            ndrBuffer.enc_ndr_long(this.level);
            ndrBuffer.enc_ndr_referent(this.e, 1);
            if (this.e != null) {
                this.e.encode(ndrBuffer.deferred);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.level = ndrBuffer.dec_ndr_long();
            ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.e == null) {
                    this.e = new DfsEnumArray1();
                }
                this.e.decode(ndrBuffer.deferred);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class NetrDfsEnumEx extends DcerpcMessage {
        public String dfs_name;
        public DfsEnumStruct info;
        public int level;
        public int prefmaxlen;
        public int retval;
        public NdrLong totalentries;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 21;
        }

        public NetrDfsEnumEx(String str, int i, int i2, DfsEnumStruct dfsEnumStruct, NdrLong ndrLong) {
            this.dfs_name = str;
            this.level = i;
            this.prefmaxlen = i2;
            this.info = dfsEnumStruct;
            this.totalentries = ndrLong;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_string(this.dfs_name);
            ndrBuffer.enc_ndr_long(this.level);
            ndrBuffer.enc_ndr_long(this.prefmaxlen);
            ndrBuffer.enc_ndr_referent(this.info, 1);
            DfsEnumStruct dfsEnumStruct = this.info;
            if (dfsEnumStruct != null) {
                dfsEnumStruct.encode(ndrBuffer);
            }
            ndrBuffer.enc_ndr_referent(this.totalentries, 1);
            NdrLong ndrLong = this.totalentries;
            if (ndrLong != null) {
                ndrLong.encode(ndrBuffer);
            }
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.info == null) {
                    this.info = new DfsEnumStruct();
                }
                this.info.decode(ndrBuffer);
            }
            if (ndrBuffer.dec_ndr_long() != 0) {
                this.totalentries.decode(ndrBuffer);
            }
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }
}
