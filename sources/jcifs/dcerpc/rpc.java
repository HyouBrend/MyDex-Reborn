package jcifs.dcerpc;

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrObject;

/* loaded from: classes2.dex */
public class rpc {

    /* loaded from: classes2.dex */
    public static class uuid_t extends NdrObject {
        public byte clock_seq_hi_and_reserved;
        public byte clock_seq_low;
        public byte[] node;
        public short time_hi_and_version;
        public int time_low;
        public short time_mid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.time_low);
            ndrBuffer.enc_ndr_short(this.time_mid);
            ndrBuffer.enc_ndr_short(this.time_hi_and_version);
            ndrBuffer.enc_ndr_small(this.clock_seq_hi_and_reserved);
            ndrBuffer.enc_ndr_small(this.clock_seq_low);
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i2 = 0; i2 < 6; i2++) {
                derive.enc_ndr_small(this.node[i2]);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.time_low = ndrBuffer.dec_ndr_long();
            this.time_mid = (short) ndrBuffer.dec_ndr_short();
            this.time_hi_and_version = (short) ndrBuffer.dec_ndr_short();
            this.clock_seq_hi_and_reserved = (byte) ndrBuffer.dec_ndr_small();
            this.clock_seq_low = (byte) ndrBuffer.dec_ndr_small();
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            if (this.node == null) {
                this.node = new byte[6];
            }
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i2 = 0; i2 < 6; i2++) {
                this.node[i2] = (byte) derive.dec_ndr_small();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class policy_handle extends NdrObject {
        public int type;
        public uuid_t uuid;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_long(this.type);
            ndrBuffer.enc_ndr_long(this.uuid.time_low);
            ndrBuffer.enc_ndr_short(this.uuid.time_mid);
            ndrBuffer.enc_ndr_short(this.uuid.time_hi_and_version);
            ndrBuffer.enc_ndr_small(this.uuid.clock_seq_hi_and_reserved);
            ndrBuffer.enc_ndr_small(this.uuid.clock_seq_low);
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i2 = 0; i2 < 6; i2++) {
                derive.enc_ndr_small(this.uuid.node[i2]);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.type = ndrBuffer.dec_ndr_long();
            ndrBuffer.align(4);
            if (this.uuid == null) {
                this.uuid = new uuid_t();
            }
            this.uuid.time_low = ndrBuffer.dec_ndr_long();
            this.uuid.time_mid = (short) ndrBuffer.dec_ndr_short();
            this.uuid.time_hi_and_version = (short) ndrBuffer.dec_ndr_short();
            this.uuid.clock_seq_hi_and_reserved = (byte) ndrBuffer.dec_ndr_small();
            this.uuid.clock_seq_low = (byte) ndrBuffer.dec_ndr_small();
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            if (this.uuid.node == null) {
                this.uuid.node = new byte[6];
            }
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i2 = 0; i2 < 6; i2++) {
                this.uuid.node[i2] = (byte) derive.dec_ndr_small();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class unicode_string extends NdrObject {
        public short[] buffer;
        public short length;
        public short maximum_length;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            ndrBuffer.enc_ndr_short(this.length);
            ndrBuffer.enc_ndr_short(this.maximum_length);
            ndrBuffer.enc_ndr_referent(this.buffer, 1);
            if (this.buffer != null) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int i = this.length / 2;
                ndrBuffer2.enc_ndr_long(this.maximum_length / 2);
                ndrBuffer2.enc_ndr_long(0);
                ndrBuffer2.enc_ndr_long(i);
                int i2 = ndrBuffer2.index;
                ndrBuffer2.advance(i * 2);
                NdrBuffer derive = ndrBuffer2.derive(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    derive.enc_ndr_short(this.buffer[i3]);
                }
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            this.length = (short) ndrBuffer.dec_ndr_short();
            this.maximum_length = (short) ndrBuffer.dec_ndr_short();
            if (ndrBuffer.dec_ndr_long() != 0) {
                NdrBuffer ndrBuffer2 = ndrBuffer.deferred;
                int dec_ndr_long = ndrBuffer2.dec_ndr_long();
                ndrBuffer2.dec_ndr_long();
                int dec_ndr_long2 = ndrBuffer2.dec_ndr_long();
                int i = ndrBuffer2.index;
                ndrBuffer2.advance(dec_ndr_long2 * 2);
                if (this.buffer == null) {
                    if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                        throw new NdrException(NdrException.INVALID_CONFORMANCE);
                    }
                    this.buffer = new short[dec_ndr_long];
                }
                NdrBuffer derive = ndrBuffer2.derive(i);
                for (int i2 = 0; i2 < dec_ndr_long2; i2++) {
                    this.buffer[i2] = (short) derive.dec_ndr_short();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class sid_t extends NdrObject {
        public byte[] identifier_authority;
        public byte revision;
        public int[] sub_authority;
        public byte sub_authority_count;

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void encode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            byte b = this.sub_authority_count;
            ndrBuffer.enc_ndr_long(b);
            ndrBuffer.enc_ndr_small(this.revision);
            ndrBuffer.enc_ndr_small(this.sub_authority_count);
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            int i2 = ndrBuffer.index;
            ndrBuffer.advance(b * 4);
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i3 = 0; i3 < 6; i3++) {
                derive.enc_ndr_small(this.identifier_authority[i3]);
            }
            NdrBuffer derive2 = derive.derive(i2);
            for (int i4 = 0; i4 < b; i4++) {
                derive2.enc_ndr_long(this.sub_authority[i4]);
            }
        }

        @Override // jcifs.dcerpc.ndr.NdrObject
        public void decode(NdrBuffer ndrBuffer) throws NdrException {
            ndrBuffer.align(4);
            int dec_ndr_long = ndrBuffer.dec_ndr_long();
            this.revision = (byte) ndrBuffer.dec_ndr_small();
            this.sub_authority_count = (byte) ndrBuffer.dec_ndr_small();
            int i = ndrBuffer.index;
            ndrBuffer.advance(6);
            int i2 = ndrBuffer.index;
            ndrBuffer.advance(dec_ndr_long * 4);
            if (this.identifier_authority == null) {
                this.identifier_authority = new byte[6];
            }
            NdrBuffer derive = ndrBuffer.derive(i);
            for (int i3 = 0; i3 < 6; i3++) {
                this.identifier_authority[i3] = (byte) derive.dec_ndr_small();
            }
            if (this.sub_authority == null) {
                if (dec_ndr_long < 0 || dec_ndr_long > 65535) {
                    throw new NdrException(NdrException.INVALID_CONFORMANCE);
                }
                this.sub_authority = new int[dec_ndr_long];
            }
            NdrBuffer derive2 = derive.derive(i2);
            for (int i4 = 0; i4 < dec_ndr_long; i4++) {
                this.sub_authority[i4] = derive2.dec_ndr_long();
            }
        }
    }
}
