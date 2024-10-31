package jcifs.dcerpc.msrpc;

import jcifs.dcerpc.DcerpcMessage;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrObject;
import jcifs.dcerpc.ndr.NdrSmall;
import jcifs.dcerpc.rpc;

/* loaded from: classes2.dex */
public class lsarpc {
    public static final int POLICY_INFO_ACCOUNT_DOMAIN = 5;
    public static final int POLICY_INFO_AUDIT_EVENTS = 2;
    public static final int POLICY_INFO_DNS_DOMAIN = 12;
    public static final int POLICY_INFO_MODIFICATION = 9;
    public static final int POLICY_INFO_PRIMARY_DOMAIN = 3;
    public static final int POLICY_INFO_SERVER_ROLE = 6;
    public static final int SID_NAME_ALIAS = 4;
    public static final int SID_NAME_DELETED = 6;
    public static final int SID_NAME_DOMAIN = 3;
    public static final int SID_NAME_DOM_GRP = 2;
    public static final int SID_NAME_INVALID = 7;
    public static final int SID_NAME_UNKNOWN = 8;
    public static final int SID_NAME_USER = 1;
    public static final int SID_NAME_USE_NONE = 0;
    public static final int SID_NAME_WKN_GRP = 5;

    public static String getSyntax() {
        return "12345778-1234-abcd-ef00-0123456789ab:0.0";
    }

    /* loaded from: classes2.dex */
    public static class LsarQosInfo extends NdrObject {
        public byte context_mode;
        public byte effective_only;
        public short impersonation_level;
        public int length;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.length);
            ndrBuffer.enc_ndr_short(this.impersonation_level);
            ndrBuffer.enc_ndr_small(this.context_mode);
            ndrBuffer.enc_ndr_small(this.effective_only);
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.length = ndrBuffer.dec_ndr_long();
            this.impersonation_level = (short) ndrBuffer.dec_ndr_short();
            this.context_mode = (byte) ndrBuffer.dec_ndr_small();
            this.effective_only = (byte) ndrBuffer.dec_ndr_small();
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarObjectAttributes extends NdrObject {
        public int attributes;
        public int length;
        public rpc.unicode_string object_name;
        public NdrSmall root_directory;
        public int security_descriptor;
        public LsarQosInfo security_quality_of_service;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.length);
            ndrBuffer.enc_ndr_referent(this.root_directory, 1);
            ndrBuffer.enc_ndr_referent(this.object_name, 1);
            ndrBuffer.enc_ndr_long(this.attributes);
            ndrBuffer.enc_ndr_long(this.security_descriptor);
            ndrBuffer.enc_ndr_referent(this.security_quality_of_service, 1);
            if (this.root_directory != null) {
                ndrBuffer = ndrBuffer.deferred;
                this.root_directory.encode(ndrBuffer);
            }
            if (this.object_name != null) {
                ndrBuffer = ndrBuffer.deferred;
                this.object_name.encode(ndrBuffer);
            }
            if (this.security_quality_of_service != null) {
                this.security_quality_of_service.encode(ndrBuffer.deferred);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.length = ndrBuffer.dec_ndr_long();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            this.attributes = ndrBuffer.dec_ndr_long();
            this.security_descriptor = ndrBuffer.dec_ndr_long();
            int dec_ndr_long3 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                ndrBuffer = ndrBuffer.deferred;
                this.root_directory.decode(ndrBuffer);
            }
            if (dec_ndr_long2 != 0) {
                if (this.object_name == null) {
                    this.object_name = new rpc.unicode_string();
                }
                ndrBuffer = ndrBuffer.deferred;
                this.object_name.decode(ndrBuffer);
            }
            if (dec_ndr_long3 != 0) {
                if (this.security_quality_of_service == null) {
                    this.security_quality_of_service = new LsarQosInfo();
                }
                this.security_quality_of_service.decode(ndrBuffer.deferred);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarDomainInfo extends NdrObject {
        public rpc.unicode_string name;
        public rpc.sid_t sid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_short(this.name.length);
            ndrBuffer.enc_ndr_short(this.name.maximum_length);
            ndrBuffer.enc_ndr_referent(this.name.buffer, 1);
            ndrBuffer.enc_ndr_referent(this.sid, 1);
            if (this.name.buffer != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.name.length / 2;
                ndrBuffer2.enc_ndr_long(this.name.maximum_length / 2);
                ndrBuffer2.enc_ndr_long(0);
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 2);
                ndrBuffer = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    ndrBuffer.enc_ndr_short(this.name.buffer[i3]);
                }
            }
            if (this.sid != null) {
                this.sid.encode(ndrBuffer.deferred);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.align(4);
            if (this.name == null) {
                this.name = new rpc.unicode_string();
            }
            this.name.length = (short) ndrBuffer.dec_ndr_short();
            this.name.maximum_length = (short) ndrBuffer.dec_ndr_short();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long3 = ndrBuffer2.dec_ndr_long();
                ndrBuffer2.dec_ndr_long();
                int dec_ndr_long4 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long4 * 2);
                if (this.name.buffer == null) {
                    if (dec_ndr_long3 < 0 || dec_ndr_long3 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.name.buffer = new short[dec_ndr_long3];
                }
                ndrBuffer = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long4; i2++) {
                    this.name.buffer[i2] = (short) ndrBuffer.dec_ndr_short();
                }
            }
            if (dec_ndr_long2 != 0) {
                if (this.sid == null) {
                    this.sid = new rpc.sid_t();
                }
                this.sid.decode(ndrBuffer.deferred);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarDnsDomainInfo extends NdrObject {
        public rpc.unicode_string dns_domain;
        public rpc.unicode_string dns_forest;
        public rpc.uuid_t domain_guid;
        public rpc.unicode_string name;
        public rpc.sid_t sid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_short(this.name.length);
            ndrBuffer.enc_ndr_short(this.name.maximum_length);
            ndrBuffer.enc_ndr_referent(this.name.buffer, 1);
            ndrBuffer.enc_ndr_short(this.dns_domain.length);
            ndrBuffer.enc_ndr_short(this.dns_domain.maximum_length);
            ndrBuffer.enc_ndr_referent(this.dns_domain.buffer, 1);
            ndrBuffer.enc_ndr_short(this.dns_forest.length);
            ndrBuffer.enc_ndr_short(this.dns_forest.maximum_length);
            ndrBuffer.enc_ndr_referent(this.dns_forest.buffer, 1);
            ndrBuffer.enc_ndr_long(this.domain_guid.time_low);
            ndrBuffer.enc_ndr_short(this.domain_guid.time_mid);
            ndrBuffer.enc_ndr_short(this.domain_guid.time_hi_and_version);
            ndrBuffer.enc_ndr_small(this.domain_guid.clock_seq_hi_and_reserved);
            ndrBuffer.enc_ndr_small(this.domain_guid.clock_seq_low);
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            ndrBuffer.enc_ndr_referent(this.sid, 1);
            if (this.name.buffer != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i2 = this.name.length / 2;
                ndrBuffer2.enc_ndr_long(this.name.maximum_length / 2);
                ndrBuffer2.enc_ndr_long(0);
                ndrBuffer2.enc_ndr_long(i2);
                int i3 = ndrBuffer2.index;
                ndrBuffer2.advance(i2 * 2);
                ndrBuffer = ndrBuffer2.derive(i3);
                for (int i4 = 0; i4 < i2; i4++) {
                    ndrBuffer.enc_ndr_short(this.name.buffer[i4]);
                }
            }
            if (this.dns_domain.buffer != null) {
                NdrBuffer ndrBuffer3 = ndrBuffer.deferred;
                int i5 = this.dns_domain.length / 2;
                ndrBuffer3.enc_ndr_long(this.dns_domain.maximum_length / 2);
                ndrBuffer3.enc_ndr_long(0);
                ndrBuffer3.enc_ndr_long(i5);
                int i6 = ndrBuffer3.index;
                ndrBuffer3.advance(i5 * 2);
                ndrBuffer = ndrBuffer3.derive(i6);
                for (int i7 = 0; i7 < i5; i7++) {
                    ndrBuffer.enc_ndr_short(this.dns_domain.buffer[i7]);
                }
            }
            if (this.dns_forest.buffer != null) {
                NdrBuffer ndrBuffer4 = ndrBuffer.deferred;
                int i8 = this.dns_forest.length / 2;
                ndrBuffer4.enc_ndr_long(this.dns_forest.maximum_length / 2);
                ndrBuffer4.enc_ndr_long(0);
                ndrBuffer4.enc_ndr_long(i8);
                int i9 = ndrBuffer4.index;
                ndrBuffer4.advance(i8 * 2);
                ndrBuffer = ndrBuffer4.derive(i9);
                for (int i10 = 0; i10 < i8; i10++) {
                    ndrBuffer.enc_ndr_short(this.dns_forest.buffer[i10]);
                }
            }
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i11 = 0; i11 < 6; i11++) {
                derive.enc_ndr_small(this.domain_guid.node[i11]);
            }
            if (this.sid != null) {
                this.sid.encode(derive.deferred);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.align(4);
            if (this.name == null) {
                this.name = new rpc.unicode_string();
            }
            this.name.length = (short) ndrBuffer.dec_ndr_short();
            this.name.maximum_length = (short) ndrBuffer.dec_ndr_short();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            ndrBuffer.align(4);
            if (this.dns_domain == null) {
                this.dns_domain = new rpc.unicode_string();
            }
            this.dns_domain.length = (short) ndrBuffer.dec_ndr_short();
            this.dns_domain.maximum_length = (short) ndrBuffer.dec_ndr_short();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            ndrBuffer.align(4);
            if (this.dns_forest == null) {
                this.dns_forest = new rpc.unicode_string();
            }
            this.dns_forest.length = (short) ndrBuffer.dec_ndr_short();
            this.dns_forest.maximum_length = (short) ndrBuffer.dec_ndr_short();
            int dec_ndr_long3 = ndrBuffer.dec_ndr_long();
            ndrBuffer.align(4);
            if (this.domain_guid == null) {
                this.domain_guid = new rpc.uuid_t();
            }
            this.domain_guid.time_low = ndrBuffer.dec_ndr_long();
            this.domain_guid.time_mid = (short) ndrBuffer.dec_ndr_short();
            this.domain_guid.time_hi_and_version = (short) ndrBuffer.dec_ndr_short();
            this.domain_guid.clock_seq_hi_and_reserved = (byte) ndrBuffer.dec_ndr_small();
            this.domain_guid.clock_seq_low = (byte) ndrBuffer.dec_ndr_small();
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            int dec_ndr_long4 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long5 = ndrBuffer2.dec_ndr_long();
                ndrBuffer2.dec_ndr_long();
                int dec_ndr_long6 = ndrBuffer2.dec_ndr_long();
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long6 * 2);
                if (this.name.buffer == null) {
                    if (dec_ndr_long5 < 0 || dec_ndr_long5 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.name.buffer = new short[dec_ndr_long5];
                }
                ndrBuffer = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < dec_ndr_long6; i3++) {
                    this.name.buffer[i3] = (short) ndrBuffer.dec_ndr_short();
                }
            }
            if (dec_ndr_long2 != 0) {
                NdrBuffer ndrBuffer3 = ndrBuffer.deferred;
                int dec_ndr_long7 = ndrBuffer3.dec_ndr_long();
                ndrBuffer3.dec_ndr_long();
                int dec_ndr_long8 = ndrBuffer3.dec_ndr_long();
                int i4 = ndrBuffer3.index;
                ndrBuffer3.advance(dec_ndr_long8 * 2);
                if (this.dns_domain.buffer == null) {
                    if (dec_ndr_long7 < 0 || dec_ndr_long7 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.dns_domain.buffer = new short[dec_ndr_long7];
                }
                ndrBuffer = ndrBuffer3.derive(i4);
                for (int i5 = 0; i5 < dec_ndr_long8; i5++) {
                    this.dns_domain.buffer[i5] = (short) ndrBuffer.dec_ndr_short();
                }
            }
            if (dec_ndr_long3 != 0) {
                NdrBuffer ndrBuffer4 = ndrBuffer.deferred;
                int dec_ndr_long9 = ndrBuffer4.dec_ndr_long();
                ndrBuffer4.dec_ndr_long();
                int dec_ndr_long10 = ndrBuffer4.dec_ndr_long();
                int i6 = ndrBuffer4.index;
                ndrBuffer4.advance(dec_ndr_long10 * 2);
                if (this.dns_forest.buffer == null) {
                    if (dec_ndr_long9 < 0 || dec_ndr_long9 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.dns_forest.buffer = new short[dec_ndr_long9];
                }
                ndrBuffer = ndrBuffer4.derive(i6);
                for (int i7 = 0; i7 < dec_ndr_long10; i7++) {
                    this.dns_forest.buffer[i7] = (short) ndrBuffer.dec_ndr_short();
                }
            }
            if (this.domain_guid.node == null) {
                this.domain_guid.node = new byte[6];
            }
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i8 = 0; i8 < 6; i8++) {
                this.domain_guid.node[i8] = (byte) derive.dec_ndr_small();
            }
            if (dec_ndr_long4 != 0) {
                if (this.sid == null) {
                    this.sid = new rpc.sid_t();
                }
                this.sid.decode(derive.deferred);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarSidPtr extends NdrObject {
        public rpc.sid_t sid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_referent(this.sid, 1);
            if (this.sid != null) {
                this.sid.encode(ndrBuffer.deferred);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.sid == null) {
                    this.sid = new rpc.sid_t();
                }
                this.sid.decode(ndrBuffer.deferred);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarSidArray extends NdrObject {
        public int num_sids;
        public LsarSidPtr[] sids;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.num_sids);
            ndrBuffer.enc_ndr_referent(this.sids, 1);
            if (this.sids != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.num_sids;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 4);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.sids[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.num_sids = ndrBuffer.dec_ndr_long();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long * 4);
                if (this.sids == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.sids = new LsarSidPtr[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    LsarSidPtr[] lsarSidPtrArr = this.sids;
                    if (lsarSidPtrArr[i2] == null) {
                        lsarSidPtrArr[i2] = new LsarSidPtr();
                    }
                    this.sids[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarTranslatedSid extends NdrObject {
        public int rid;
        public int sid_index;
        public int sid_type;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_short(this.sid_type);
            ndrBuffer.enc_ndr_long(this.rid);
            ndrBuffer.enc_ndr_long(this.sid_index);
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.sid_type = ndrBuffer.dec_ndr_short();
            this.rid = ndrBuffer.dec_ndr_long();
            this.sid_index = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarTransSidArray extends NdrObject {
        public int count;
        public LsarTranslatedSid[] sids;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.sids, 1);
            if (this.sids != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 12);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.sids[i3].encode(derive);
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
                if (this.sids == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.sids = new LsarTranslatedSid[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    LsarTranslatedSid[] lsarTranslatedSidArr = this.sids;
                    if (lsarTranslatedSidArr[i2] == null) {
                        lsarTranslatedSidArr[i2] = new LsarTranslatedSid();
                    }
                    this.sids[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarTrustInformation extends NdrObject {
        public rpc.unicode_string name;
        public rpc.sid_t sid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_short(this.name.length);
            ndrBuffer.enc_ndr_short(this.name.maximum_length);
            ndrBuffer.enc_ndr_referent(this.name.buffer, 1);
            ndrBuffer.enc_ndr_referent(this.sid, 1);
            if (this.name.buffer != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.name.length / 2;
                ndrBuffer2.enc_ndr_long(this.name.maximum_length / 2);
                ndrBuffer2.enc_ndr_long(0);
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 2);
                ndrBuffer = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    ndrBuffer.enc_ndr_short(this.name.buffer[i3]);
                }
            }
            if (this.sid != null) {
                this.sid.encode(ndrBuffer.deferred);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.align(4);
            if (this.name == null) {
                this.name = new rpc.unicode_string();
            }
            this.name.length = (short) ndrBuffer.dec_ndr_short();
            this.name.maximum_length = (short) ndrBuffer.dec_ndr_short();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            int dec_ndr_long2 = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long3 = ndrBuffer2.dec_ndr_long();
                ndrBuffer2.dec_ndr_long();
                int dec_ndr_long4 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long4 * 2);
                if (this.name.buffer == null) {
                    if (dec_ndr_long3 < 0 || dec_ndr_long3 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.name.buffer = new short[dec_ndr_long3];
                }
                ndrBuffer = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long4; i2++) {
                    this.name.buffer[i2] = (short) ndrBuffer.dec_ndr_short();
                }
            }
            if (dec_ndr_long2 != 0) {
                if (this.sid == null) {
                    this.sid = new rpc.sid_t();
                }
                this.sid.decode(ndrBuffer.deferred);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarRefDomainList extends NdrObject {
        public int count;
        public LsarTrustInformation[] domains;
        public int max_count;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.domains, 1);
            ndrBuffer.enc_ndr_long(this.max_count);
            if (this.domains != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 12);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.domains[i3].encode(derive);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.count = ndrBuffer.dec_ndr_long();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            this.max_count = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long2 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long2 * 12);
                if (this.domains == null) {
                    if (dec_ndr_long2 < 0 || dec_ndr_long2 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.domains = new LsarTrustInformation[dec_ndr_long2];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long2; i2++) {
                    LsarTrustInformation[] lsarTrustInformationArr = this.domains;
                    if (lsarTrustInformationArr[i2] == null) {
                        lsarTrustInformationArr[i2] = new LsarTrustInformation();
                    }
                    this.domains[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarTranslatedName extends NdrObject {
        public rpc.unicode_string name;
        public int sid_index;
        public short sid_type;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_short(this.sid_type);
            ndrBuffer.enc_ndr_short(this.name.length);
            ndrBuffer.enc_ndr_short(this.name.maximum_length);
            ndrBuffer.enc_ndr_referent(this.name.buffer, 1);
            ndrBuffer.enc_ndr_long(this.sid_index);
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
            this.sid_type = (short) ndrBuffer.dec_ndr_short();
            ndrBuffer.align(4);
            if (this.name == null) {
                this.name = new rpc.unicode_string();
            }
            this.name.length = (short) ndrBuffer.dec_ndr_short();
            this.name.maximum_length = (short) ndrBuffer.dec_ndr_short();
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            this.sid_index = ndrBuffer.dec_ndr_long();
            if (dec_ndr_long != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long2 = ndrBuffer2.dec_ndr_long();
                ndrBuffer2.dec_ndr_long();
                int dec_ndr_long3 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long3 * 2);
                if (this.name.buffer == null) {
                    if (dec_ndr_long2 < 0 || dec_ndr_long2 > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.name.buffer = new short[dec_ndr_long2];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long3; i2++) {
                    this.name.buffer[i2] = (short) derive.dec_ndr_short();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarTransNameArray extends NdrObject {
        public int count;
        public LsarTranslatedName[] names;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.count);
            ndrBuffer.enc_ndr_referent(this.names, 1);
            if (this.names != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.count;
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 16);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    this.names[i3].encode(derive);
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
                ndrBuffer2.advance(dec_ndr_long * 16);
                if (this.names == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.names = new LsarTranslatedName[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long; i2++) {
                    LsarTranslatedName[] lsarTranslatedNameArr = this.names;
                    if (lsarTranslatedNameArr[i2] == null) {
                        lsarTranslatedNameArr[i2] = new LsarTranslatedName();
                    }
                    this.names[i2].decode(derive);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarClose extends DcerpcMessage {
        public rpc.policy_handle handle;
        public int retval;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 0;
        }

        public LsarClose(rpc.policy_handle policy_handleVar) {
            this.handle = policy_handleVar;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.encode(ndrBuffer);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarQueryInformationPolicy extends DcerpcMessage {
        public rpc.policy_handle handle;
        public NdrObject info;
        public short level;
        public int retval;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 7;
        }

        public LsarQueryInformationPolicy(rpc.policy_handle policy_handleVar, short s, NdrObject ndrObject) {
            this.handle = policy_handleVar;
            this.level = s;
            this.info = ndrObject;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.encode(ndrBuffer);
            ndrBuffer.enc_ndr_short(this.level);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            if (ndrBuffer.dec_ndr_long() != 0) {
                ndrBuffer.dec_ndr_short();
                this.info.decode(ndrBuffer);
            }
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarLookupSids extends DcerpcMessage {
        public int count;
        public LsarRefDomainList domains;
        public rpc.policy_handle handle;
        public short level;
        public LsarTransNameArray names;
        public int retval;
        public LsarSidArray sids;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 15;
        }

        public LsarLookupSids(rpc.policy_handle policy_handleVar, LsarSidArray lsarSidArray, LsarRefDomainList lsarRefDomainList, LsarTransNameArray lsarTransNameArray, short s, int i) {
            this.handle = policy_handleVar;
            this.sids = lsarSidArray;
            this.domains = lsarRefDomainList;
            this.names = lsarTransNameArray;
            this.level = s;
            this.count = i;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.encode(ndrBuffer);
            this.sids.encode(ndrBuffer);
            this.names.encode(ndrBuffer);
            ndrBuffer.enc_ndr_short(this.level);
            ndrBuffer.enc_ndr_long(this.count);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            if (ndrBuffer.dec_ndr_long() != 0) {
                if (this.domains == null) {
                    this.domains = new LsarRefDomainList();
                }
                this.domains.decode(ndrBuffer);
            }
            this.names.decode(ndrBuffer);
            this.count = ndrBuffer.dec_ndr_long();
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarOpenPolicy2 extends DcerpcMessage {
        public int desired_access;
        public LsarObjectAttributes object_attributes;
        public rpc.policy_handle policy_handle;
        public int retval;
        public String system_name;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 44;
        }

        public LsarOpenPolicy2(String str, LsarObjectAttributes lsarObjectAttributes, int i, rpc.policy_handle policy_handleVar) {
            this.system_name = str;
            this.object_attributes = lsarObjectAttributes;
            this.desired_access = i;
            this.policy_handle = policy_handleVar;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.enc_ndr_referent(this.system_name, 1);
            String str = this.system_name;
            if (str != null) {
                ndrBuffer.enc_ndr_string(str);
            }
            this.object_attributes.encode(ndrBuffer);
            ndrBuffer.enc_ndr_long(this.desired_access);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            this.policy_handle.decode(ndrBuffer);
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }

    /* loaded from: classes2.dex */
    public static class LsarQueryInformationPolicy2 extends DcerpcMessage {
        public rpc.policy_handle handle;
        public NdrObject info;
        public short level;
        public int retval;

        @Override // jcifs.dcerpc.DcerpcMessage
        public int getOpnum() {
            return 46;
        }

        public LsarQueryInformationPolicy2(rpc.policy_handle policy_handleVar, short s, NdrObject ndrObject) {
            this.handle = policy_handleVar;
            this.level = s;
            this.info = ndrObject;
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void encode_in(NdrBuffer ndrBuffer) throws NdrException {
            this.handle.encode(ndrBuffer);
            ndrBuffer.enc_ndr_short(this.level);
        }

        @Override // jcifs.dcerpc.DcerpcMessage
        public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
            if (ndrBuffer.dec_ndr_long() != 0) {
                ndrBuffer.dec_ndr_short();
                this.info.decode(ndrBuffer);
            }
            this.retval = ndrBuffer.dec_ndr_long();
        }
    }
}
