package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.DcerpcMessage;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrObject;

/* loaded from: classes2.dex */
public class srvsvc {
    public static String getSyntax() {
        return "4b324fc8-1670-01d3-1278-5a47bf6ee188:3.0";
    }

    /* loaded from: classes2.dex */
    public static class ShareInfo0 extends NdrObject {
        public String netname;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.netname, 1);
            if (this.netname != null) {
                ndrBuffer.deferred.enc_ndr_string(this.netname);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            if (ndrBuffer.dec_ndr_long() != 0) {
                this.netname = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareInfoCtr0 extends NdrObject {
        public ShareInfo0[] array;
        public int count;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.array, 1);
            if (this.array != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 4);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.array[i3].encode(derive);
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
                if (this.array == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.array = new ShareInfo0[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    ShareInfo0[] shareInfo0Arr = this.array;
                    if (shareInfo0Arr[i2] == null) {
                        shareInfo0Arr[i2] = new ShareInfo0();
                    }
                    this.array[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareInfo1 extends NdrObject {
        public String netname;
        public String remark;
        public int type;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.netname, 1);
            ndrBuffer.enc_ndr_long(this.type);
            ndrBuffer.enc_ndr_referent(this.remark, 1);
            if (this.netname != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.netname);
            }
            if (this.remark != null) {
                ndrBuffer.deferred.enc_ndr_string(this.remark);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            this.type = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.netname = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long2 != 0) {
                this.remark = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareInfoCtr1 extends NdrObject {
        public ShareInfo1[] array;
        public int count;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.array, 1);
            if (this.array != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 12);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.array[i3].encode(derive);
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
                ndrBuffer2.advance(dec_ndr_long * 12);
                if (this.array == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.array = new ShareInfo1[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    ShareInfo1[] shareInfo1Arr = this.array;
                    if (shareInfo1Arr[i2] == null) {
                        shareInfo1Arr[i2] = new ShareInfo1();
                    }
                    this.array[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareInfo502 extends NdrObject {
        public int current_uses;
        public int max_uses;
        public String netname;
        public String password;
        public String path;
        public int permissions;
        public String remark;
        public int sd_size;
        public byte[] security_descriptor;
        public int type;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.netname, 1);
            ndrBuffer.enc_ndr_long(this.type);
            ndrBuffer.enc_ndr_referent(this.remark, 1);
            ndrBuffer.enc_ndr_long(this.permissions);
            ndrBuffer.enc_ndr_long(this.max_uses);
            ndrBuffer.enc_ndr_long(this.current_uses);
            ndrBuffer.enc_ndr_referent(this.path, 1);
            ndrBuffer.enc_ndr_referent(this.password, 1);
            ndrBuffer.enc_ndr_long(this.sd_size);
            ndrBuffer.enc_ndr_referent(this.security_descriptor, 1);
            if (this.netname != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.netname);
            }
            if (this.remark != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.remark);
            }
            if (this.path != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.path);
            }
            if (this.password != null) {
                ndrBuffer = ndrBuffer.deferred;
                ndrBuffer.enc_ndr_string(this.password);
            }
            if (this.security_descriptor != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.sd_size;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 1);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    derive.enc_ndr_small(this.security_descriptor[i3]);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            this.type = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            this.permissions = ndrBuffer.dec_ndr_long();
            this.max_uses = ndrBuffer.dec_ndr_long();
            this.current_uses = ndrBuffer.dec_ndr_long();
            int dec_ndr_long3 = ndrBuffer.dec_ndr_long();
            int dec_ndr_long4 = ndrBuffer.dec_ndr_long();
            this.sd_size = ndrBuffer.dec_ndr_long();
            int dec_ndr_long5 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.netname = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long2 != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.remark = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long3 != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.path = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long4 != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.password = ndrBuffer.dec_ndr_string();
            }
            if (dec_ndr_long5 != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long6 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long6 * 1);
                if (this.security_descriptor == null) {
                    if (dec_ndr_long6 < 0 || dec_ndr_long6 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.security_descriptor = new byte[dec_ndr_long6];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long6; i2++) {
                    this.security_descriptor[i2] = (byte) derive.dec_ndr_small();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareInfoCtr502 extends NdrObject {
        public ShareInfo502[] array;
        public int count;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.array, 1);
            if (this.array != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 40);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.array[i3].encode(derive);
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
                ndrBuffer2.advance(dec_ndr_long * 40);
                if (this.array == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.array = new ShareInfo502[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    ShareInfo502[] shareInfo502Arr = this.array;
                    if (shareInfo502Arr[i2] == null) {
                        shareInfo502Arr[i2] = new ShareInfo502();
                    }
                    this.array[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareEnumAll extends DcerpcMessage {
        public NdrObject info;
        public int level;
        public int prefmaxlen;
        public int resume_handle;
        public int retval;
        public String servername;
        public int totalentries;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 15;
        }

        public ShareEnumAll(String str, int i, NdrObject ndrObject, int i2, int i3, int i4) {
            this.servername = str;
            this.level = i;
            this.info = ndrObject;
            this.prefmaxlen = i2;
            this.totalentries = i3;
            this.resume_handle = i4;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.servername, 1);
            String str = this.servername;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
            ndrBuffer.enc_ndr_long(this.level);
            ndrBuffer.enc_ndr_long(this.level);
            ndrBuffer.enc_ndr_referent(this.info, 1);
            if (this.info != null) {
                ndrBuffer = ndrBuffer.deferred;
                this.info.encode(ndrBuffer);
            }
            ndrBuffer.enc_ndr_long(this.prefmaxlen);
            ndrBuffer.enc_ndr_long(this.resume_handle);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.level = ndrBuffer.dec_ndr_long();
            ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.info == null) {
                    this.info = new ShareInfoCtr0();
                }
                ndrBuffer = ndrBuffer.deferred;
                this.info.decode(ndrBuffer);
            }
            this.totalentries = ndrBuffer.dec_ndr_long();
            this.resume_handle = ndrBuffer.dec_ndr_long();
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class ShareGetInfo extends DcerpcMessage {
        public NdrObject info;
        public int level;
        public int retval;
        public String servername;
        public String sharename;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 16;
        }

        public ShareGetInfo(String str, String str2, int i, NdrObject ndrObject) {
            this.servername = str;
            this.sharename = str2;
            this.level = i;
            this.info = ndrObject;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.servername, 1);
            String str = this.servername;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
            ndrBuffer.enc_ndr_string(this.sharename);
            ndrBuffer.enc_ndr_long(this.level);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.info == null) {
                    this.info = new ShareInfo0();
                }
                ndrBuffer = ndrBuffer.deferred;
                this.info.decode(ndrBuffer);
            }
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class ServerInfo100 extends NdrObject {
        public String name;
        public int platform_id;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.platform_id);
            ndrBuffer.enc_ndr_referent(this.name, 1);
            if (this.name != null) {
                ndrBuffer.deferred.enc_ndr_string(this.name);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.platform_id = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                this.name = ndrBuffer.deferred.dec_ndr_string();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ServerGetInfo extends DcerpcMessage {
        public NdrObject info;
        public int level;
        public int retval;
        public String servername;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 21;
        }

        public ServerGetInfo(String str, int i, NdrObject ndrObject) {
            this.servername = str;
            this.level = i;
            this.info = ndrObject;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.servername, 1);
            String str = this.servername;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
            ndrBuffer.enc_ndr_long(this.level);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.info == null) {
                    this.info = new ServerInfo100();
                }
                ndrBuffer = ndrBuffer.deferred;
                this.info.decode(ndrBuffer);
            }
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class TimeOfDayInfo extends NdrObject {
        public int day;
        public int elapsedt;
        public int hours;
        public int hunds;
        public int mins;
        public int month;
        public int msecs;
        public int secs;
        public int timezone;
        public int tinterval;
        public int weekday;
        public int year;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.elapsedt);
            ndrBuffer.enc_ndr_long(this.msecs);
            ndrBuffer.enc_ndr_long(this.hours);
            ndrBuffer.enc_ndr_long(this.mins);
            ndrBuffer.enc_ndr_long(this.secs);
            ndrBuffer.enc_ndr_long(this.hunds);
            ndrBuffer.enc_ndr_long(this.timezone);
            ndrBuffer.enc_ndr_long(this.tinterval);
            ndrBuffer.enc_ndr_long(this.day);
            ndrBuffer.enc_ndr_long(this.month);
            ndrBuffer.enc_ndr_long(this.year);
            ndrBuffer.enc_ndr_long(this.weekday);
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.elapsedt = ndrBuffer.dec_ndr_long();
            this.msecs = ndrBuffer.dec_ndr_long();
            this.hours = ndrBuffer.dec_ndr_long();
            this.mins = ndrBuffer.dec_ndr_long();
            this.secs = ndrBuffer.dec_ndr_long();
            this.hunds = ndrBuffer.dec_ndr_long();
            this.timezone = ndrBuffer.dec_ndr_long();
            this.tinterval = ndrBuffer.dec_ndr_long();
            this.day = ndrBuffer.dec_ndr_long();
            this.month = ndrBuffer.dec_ndr_long();
            this.year = ndrBuffer.dec_ndr_long();
            this.weekday = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class RemoteTOD extends DcerpcMessage {
        public TimeOfDayInfo info;
        public int retval;
        public String servername;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 28;
        }

        public RemoteTOD(String str, TimeOfDayInfo timeOfDayInfo) {
            this.servername = str;
            this.info = timeOfDayInfo;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.servername, 1);
            String str = this.servername;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.info == null) {
                    this.info = new TimeOfDayInfo();
                }
                this.info.decode(ndrBuffer);
            }
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }
}
