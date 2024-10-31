package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.DcerpcMessage;
import jcifs.dcerpc.msrpc.lsarpc;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrObject;
import jcifs.dcerpc.rpc;

/* loaded from: classes2.dex */
public class samr {
    public static final int ACB_AUTOLOCK = 1024;
    public static final int ACB_DISABLED = 1;
    public static final int ACB_DOMTRUST = 64;
    public static final int ACB_DONT_REQUIRE_PREAUTH = 65536;
    public static final int ACB_ENC_TXT_PWD_ALLOWED = 2048;
    public static final int ACB_HOMDIRREQ = 2;
    public static final int ACB_MNS = 32;
    public static final int ACB_NORMAL = 16;
    public static final int ACB_NOT_DELEGATED = 16384;
    public static final int ACB_PWNOEXP = 512;
    public static final int ACB_PWNOTREQ = 4;
    public static final int ACB_SMARTCARD_REQUIRED = 4096;
    public static final int ACB_SVRTRUST = 256;
    public static final int ACB_TEMPDUP = 8;
    public static final int ACB_TRUSTED_FOR_DELEGATION = 8192;
    public static final int ACB_USE_DES_KEY_ONLY = 32768;
    public static final int ACB_WSTRUST = 128;
    public static final int SE_GROUP_ENABLED = 4;
    public static final int SE_GROUP_ENABLED_BY_DEFAULT = 2;
    public static final int SE_GROUP_LOGON_ID = -1073741824;
    public static final int SE_GROUP_MANDATORY = 1;
    public static final int SE_GROUP_OWNER = 8;
    public static final int SE_GROUP_RESOURCE = 536870912;
    public static final int SE_GROUP_USE_FOR_DENY_ONLY = 16;

    public static String getSyntax() {
        return "12345778-1234-abcd-ef00-0123456789ac:1.0";
    }

    /* loaded from: classes2.dex */
    public static class SamrCloseHandle extends DcerpcMessage {
        public rpc.policy_handle handle;
        public int retval;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 1;
        }

        public SamrCloseHandle(rpc.policy_handle policy_handleVar) {
            this.handle = policy_handleVar;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.encode(ndrBuffer);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrConnect2 extends DcerpcMessage {
        public int access_mask;
        public rpc.policy_handle handle;
        public int retval;
        public String system_name;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 57;
        }

        public SamrConnect2(String str, int i, rpc.policy_handle policy_handleVar) {
            this.system_name = str;
            this.access_mask = i;
            this.handle = policy_handleVar;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.system_name, 1);
            String str = this.system_name;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
            ndrBuffer.enc_ndr_long(this.access_mask);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrConnect4 extends DcerpcMessage {
        public int access_mask;
        public rpc.policy_handle handle;
        public int retval;
        public String system_name;
        public int unknown;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 62;
        }

        public SamrConnect4(String str, int i, int i2, rpc.policy_handle policy_handleVar) {
            this.system_name = str;
            this.unknown = i;
            this.access_mask = i2;
            this.handle = policy_handleVar;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.system_name, 1);
            String str = this.system_name;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
            ndrBuffer.enc_ndr_long(this.unknown);
            ndrBuffer.enc_ndr_long(this.access_mask);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrOpenDomain extends DcerpcMessage {
        public int access_mask;
        public rpc.policy_handle domain_handle;
        public rpc.policy_handle handle;
        public int retval;
        public rpc.sid_t sid;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 7;
        }

        public SamrOpenDomain(rpc.policy_handle policy_handleVar, int i, rpc.sid_t sid_tVar, rpc.policy_handle policy_handleVar2) {
            this.handle = policy_handleVar;
            this.access_mask = i;
            this.sid = sid_tVar;
            this.domain_handle = policy_handleVar2;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.encode(ndrBuffer);
            ndrBuffer.enc_ndr_long(this.access_mask);
            this.sid.encode(ndrBuffer);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.domain_handle.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrSamEntry extends NdrObject {
        public int idx;
        public rpc.unicode_string name;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.idx);
            ndrBuffer.enc_ndr_short(this.name.length);
            ndrBuffer.enc_ndr_short(this.name.maximum_length);
            ndrBuffer.enc_ndr_referent(this.name.buffer, 1);
            if (this.name.buffer != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.name.length / 2;
                ndrBuffer2.enc_ndr_long(this.name.maximum_length / 2);
                ndrBuffer2.enc_ndr_long(0);
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 2);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    derive.enc_ndr_short(this.name.buffer[i3]);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.idx = ndrBuffer.dec_ndr_long();
            ndrBuffer.align(4);
            if (this.name == null) {
                this.name = new rpc.unicode_string();
            }
            this.name.length = (short) ndrBuffer.dec_ndr_short();
            this.name.maximum_length = (short) ndrBuffer.dec_ndr_short();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                ndrBuffer2.dec_ndr_long();
                int dec_ndr_long2 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long2 * 2);
                if (this.name.buffer == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.name.buffer = new short[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long2; i2++) {
                    this.name.buffer[i2] = (short) derive.dec_ndr_short();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrSamArray extends NdrObject {
        public int count;
        public SamrSamEntry[] entries;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.entries, 1);
            if (this.entries != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 12);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.entries[i3].encode(derive);
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
                if (this.entries == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.entries = new SamrSamEntry[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    SamrSamEntry[] samrSamEntryArr = this.entries;
                    if (samrSamEntryArr[i2] == null) {
                        samrSamEntryArr[i2] = new SamrSamEntry();
                    }
                    this.entries[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrEnumerateAliasesInDomain extends DcerpcMessage {
        public int acct_flags;
        public rpc.policy_handle domain_handle;
        public int num_entries;
        public int resume_handle;
        public int retval;
        public SamrSamArray sam;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 15;
        }

        public SamrEnumerateAliasesInDomain(rpc.policy_handle policy_handleVar, int i, int i2, SamrSamArray samrSamArray, int i3) {
            this.domain_handle = policy_handleVar;
            this.resume_handle = i;
            this.acct_flags = i2;
            this.sam = samrSamArray;
            this.num_entries = i3;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.domain_handle.encode(ndrBuffer);
            ndrBuffer.enc_ndr_long(this.resume_handle);
            ndrBuffer.enc_ndr_long(this.acct_flags);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.resume_handle = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.sam == null) {
                    this.sam = new SamrSamArray();
                }
                this.sam.decode(ndrBuffer);
            }
            this.num_entries = ndrBuffer.dec_ndr_long();
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrOpenAlias extends DcerpcMessage {
        public int access_mask;
        public rpc.policy_handle alias_handle;
        public rpc.policy_handle domain_handle;
        public int retval;
        public int rid;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 27;
        }

        public SamrOpenAlias(rpc.policy_handle policy_handleVar, int i, int i2, rpc.policy_handle policy_handleVar2) {
            this.domain_handle = policy_handleVar;
            this.access_mask = i;
            this.rid = i2;
            this.alias_handle = policy_handleVar2;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.domain_handle.encode(ndrBuffer);
            ndrBuffer.enc_ndr_long(this.access_mask);
            ndrBuffer.enc_ndr_long(this.rid);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.alias_handle.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrGetMembersInAlias extends DcerpcMessage {
        public rpc.policy_handle alias_handle;
        public int retval;
        public lsarpc.LsarSidArray sids;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 33;
        }

        public SamrGetMembersInAlias(rpc.policy_handle policy_handleVar, lsarpc.LsarSidArray lsarSidArray) {
            this.alias_handle = policy_handleVar;
            this.sids = lsarSidArray;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.alias_handle.encode(ndrBuffer);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.sids.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrRidWithAttribute extends NdrObject {
        public int attributes;
        public int rid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.rid);
            ndrBuffer.enc_ndr_long(this.attributes);
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.rid = ndrBuffer.dec_ndr_long();
            this.attributes = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class SamrRidWithAttributeArray extends NdrObject {
        public int count;
        public SamrRidWithAttribute[] rids;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.rids, 1);
            if (this.rids != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 8);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.rids[i3].encode(derive);
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
                if (this.rids == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.rids = new SamrRidWithAttribute[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    SamrRidWithAttribute[] samrRidWithAttributeArr = this.rids;
                    if (samrRidWithAttributeArr[i2] == null) {
                        samrRidWithAttributeArr[i2] = new SamrRidWithAttribute();
                    }
                    this.rids[i2].decode(derive);
                }
            }
        }
    }
}
