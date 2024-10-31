package jcifs.smb;

/* loaded from: classes2.dex */
class Trans2QueryFSInformationResponse extends SmbComTransactionResponse {
    static final int SMB_FS_FULL_SIZE_INFORMATION = 1007;
    static final int SMB_INFO_ALLOCATION = 1;
    static final int SMB_QUERY_FS_SIZE_INFO = 259;
    AllocInfo info;
    private int informationLevel;

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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class SmbInfoAllocation implements AllocInfo {
        long alloc;
        int bytesPerSect;
        long free;
        int sectPerAlloc;

        SmbInfoAllocation() {
        }

        @Override // jcifs.smb.AllocInfo
        public long getCapacity() {
            return this.alloc * this.sectPerAlloc * this.bytesPerSect;
        }

        @Override // jcifs.smb.AllocInfo
        public long getFree() {
            return this.free * this.sectPerAlloc * this.bytesPerSect;
        }

        public String toString() {
            return new String("SmbInfoAllocation[alloc=" + this.alloc + ",free=" + this.free + ",sectPerAlloc=" + this.sectPerAlloc + ",bytesPerSect=" + this.bytesPerSect + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2QueryFSInformationResponse(int i) {
        this.informationLevel = i;
        this.command = (byte) 50;
        this.subCommand = (byte) 3;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        int i3 = this.informationLevel;
        if (i3 == 1) {
            return readSmbInfoAllocationWireFormat(bArr, i);
        }
        if (i3 == SMB_QUERY_FS_SIZE_INFO) {
            return readSmbQueryFSSizeInfoWireFormat(bArr, i);
        }
        if (i3 != 1007) {
            return 0;
        }
        return readFsFullSizeInformationWireFormat(bArr, i);
    }

    int readSmbInfoAllocationWireFormat(byte[] bArr, int i) {
        SmbInfoAllocation smbInfoAllocation = new SmbInfoAllocation();
        int i2 = i + 4;
        smbInfoAllocation.sectPerAlloc = readInt4(bArr, i2);
        smbInfoAllocation.alloc = readInt4(bArr, r1);
        smbInfoAllocation.free = readInt4(bArr, r1);
        int i3 = i2 + 4 + 4 + 4;
        smbInfoAllocation.bytesPerSect = readInt2(bArr, i3);
        this.info = smbInfoAllocation;
        return (i3 + 4) - i;
    }

    int readSmbQueryFSSizeInfoWireFormat(byte[] bArr, int i) {
        SmbInfoAllocation smbInfoAllocation = new SmbInfoAllocation();
        smbInfoAllocation.alloc = readInt8(bArr, i);
        int i2 = i + 8;
        smbInfoAllocation.free = readInt8(bArr, i2);
        int i3 = i2 + 8;
        smbInfoAllocation.sectPerAlloc = readInt4(bArr, i3);
        int i4 = i3 + 4;
        smbInfoAllocation.bytesPerSect = readInt4(bArr, i4);
        this.info = smbInfoAllocation;
        return (i4 + 4) - i;
    }

    int readFsFullSizeInformationWireFormat(byte[] bArr, int i) {
        SmbInfoAllocation smbInfoAllocation = new SmbInfoAllocation();
        smbInfoAllocation.alloc = readInt8(bArr, i);
        int i2 = i + 8;
        smbInfoAllocation.free = readInt8(bArr, i2);
        int i3 = i2 + 8 + 8;
        smbInfoAllocation.sectPerAlloc = readInt4(bArr, i3);
        int i4 = i3 + 4;
        smbInfoAllocation.bytesPerSect = readInt4(bArr, i4);
        this.info = smbInfoAllocation;
        return (i4 + 4) - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2QueryFSInformationResponse[" + super.toString() + "]");
    }
}
