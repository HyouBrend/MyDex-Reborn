package jcifs.smb;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Trans2GetDfsReferralResponse extends SmbComTransactionResponse {
    int flags;
    int numReferrals;
    int pathConsumed;
    Referral[] referrals;

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readSetupWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int writeDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int writeParametersWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int writeSetupWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* loaded from: classes2.dex */
    class Referral {
        private String altPath;
        private int altPathOffset;
        private int flags;
        private int nodeOffset;
        private int pathOffset;
        private int proximity;
        private int serverType;
        private int size;
        int ttl;
        private int version;
        String path = null;
        String node = null;

        Referral() {
        }

        int readWireFormat(byte[] bArr, int i, int i2) {
            int readInt2 = ServerMessageBlock.readInt2(bArr, i);
            this.version = readInt2;
            if (readInt2 != 3 && readInt2 != 1) {
                throw new RuntimeException("Version " + this.version + " referral not supported. Please report this to jcifs at samba dot org.");
            }
            int i3 = i + 2;
            this.size = ServerMessageBlock.readInt2(bArr, i3);
            int i4 = i3 + 2;
            this.serverType = ServerMessageBlock.readInt2(bArr, i4);
            int i5 = i4 + 2;
            this.flags = ServerMessageBlock.readInt2(bArr, i5);
            int i6 = i5 + 2;
            int i7 = this.version;
            if (i7 == 3) {
                this.proximity = ServerMessageBlock.readInt2(bArr, i6);
                int i8 = i6 + 2;
                this.ttl = ServerMessageBlock.readInt2(bArr, i8);
                int i9 = i8 + 2;
                this.pathOffset = ServerMessageBlock.readInt2(bArr, i9);
                int i10 = i9 + 2;
                this.altPathOffset = ServerMessageBlock.readInt2(bArr, i10);
                this.nodeOffset = ServerMessageBlock.readInt2(bArr, i10 + 2);
                Trans2GetDfsReferralResponse trans2GetDfsReferralResponse = Trans2GetDfsReferralResponse.this;
                this.path = trans2GetDfsReferralResponse.readString(bArr, this.pathOffset + i, i2, (trans2GetDfsReferralResponse.flags2 & 32768) != 0);
                int i11 = this.nodeOffset;
                if (i11 > 0) {
                    Trans2GetDfsReferralResponse trans2GetDfsReferralResponse2 = Trans2GetDfsReferralResponse.this;
                    this.node = trans2GetDfsReferralResponse2.readString(bArr, i + i11, i2, (trans2GetDfsReferralResponse2.flags2 & 32768) != 0);
                }
            } else if (i7 == 1) {
                Trans2GetDfsReferralResponse trans2GetDfsReferralResponse3 = Trans2GetDfsReferralResponse.this;
                this.node = trans2GetDfsReferralResponse3.readString(bArr, i6, i2, (trans2GetDfsReferralResponse3.flags2 & 32768) != 0);
            }
            return this.size;
        }

        public String toString() {
            return new String("Referral[version=" + this.version + ",size=" + this.size + ",serverType=" + this.serverType + ",flags=" + this.flags + ",proximity=" + this.proximity + ",ttl=" + this.ttl + ",pathOffset=" + this.pathOffset + ",altPathOffset=" + this.altPathOffset + ",nodeOffset=" + this.nodeOffset + ",path=" + this.path + ",altPath=" + this.altPath + ",node=" + this.node + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2GetDfsReferralResponse() {
        this.subCommand = (byte) 16;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        this.pathConsumed = readInt2(bArr, i);
        int i3 = i + 2;
        if ((this.flags2 & 32768) != 0) {
            this.pathConsumed /= 2;
        }
        this.numReferrals = readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.flags = readInt2(bArr, i4);
        int i5 = i4 + 4;
        this.referrals = new Referral[this.numReferrals];
        for (int i6 = 0; i6 < this.numReferrals; i6++) {
            this.referrals[i6] = new Referral();
            i5 += this.referrals[i6].readWireFormat(bArr, i5, i2);
        }
        return i5 - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2GetDfsReferralResponse[" + super.toString() + ",pathConsumed=" + this.pathConsumed + ",numReferrals=" + this.numReferrals + ",flags=" + this.flags + "]");
    }
}
