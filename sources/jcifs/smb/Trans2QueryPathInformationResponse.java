package jcifs.smb;

import java.util.Date;
import jcifs.util.Hexdump;
import kotlin.UByte;

/* loaded from: classes2.dex */
class Trans2QueryPathInformationResponse extends SmbComTransactionResponse {
    static final int SMB_QUERY_FILE_BASIC_INFO = 257;
    static final int SMB_QUERY_FILE_STANDARD_INFO = 258;
    Info info;
    private int informationLevel;

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        return 2;
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
    public class SmbQueryFileBasicInfo implements Info {
        int attributes;
        long changeTime;
        long createTime;
        long lastAccessTime;
        long lastWriteTime;

        @Override // jcifs.smb.Info
        public long getSize() {
            return 0L;
        }

        SmbQueryFileBasicInfo() {
        }

        @Override // jcifs.smb.Info
        public int getAttributes() {
            return this.attributes;
        }

        @Override // jcifs.smb.Info
        public long getCreateTime() {
            return this.createTime;
        }

        @Override // jcifs.smb.Info
        public long getLastWriteTime() {
            return this.lastWriteTime;
        }

        public String toString() {
            return new String("SmbQueryFileBasicInfo[createTime=" + new Date(this.createTime) + ",lastAccessTime=" + new Date(this.lastAccessTime) + ",lastWriteTime=" + new Date(this.lastWriteTime) + ",changeTime=" + new Date(this.changeTime) + ",attributes=0x" + Hexdump.toHexString(this.attributes, 4) + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class SmbQueryFileStandardInfo implements Info {
        long allocationSize;
        boolean deletePending;
        boolean directory;
        long endOfFile;
        int numberOfLinks;

        @Override // jcifs.smb.Info
        public int getAttributes() {
            return 0;
        }

        @Override // jcifs.smb.Info
        public long getCreateTime() {
            return 0L;
        }

        @Override // jcifs.smb.Info
        public long getLastWriteTime() {
            return 0L;
        }

        SmbQueryFileStandardInfo() {
        }

        @Override // jcifs.smb.Info
        public long getSize() {
            return this.endOfFile;
        }

        public String toString() {
            return new String("SmbQueryInfoStandard[allocationSize=" + this.allocationSize + ",endOfFile=" + this.endOfFile + ",numberOfLinks=" + this.numberOfLinks + ",deletePending=" + this.deletePending + ",directory=" + this.directory + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2QueryPathInformationResponse(int i) {
        this.informationLevel = i;
        this.subCommand = (byte) 5;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        int i3 = this.informationLevel;
        if (i3 == 257) {
            return readSmbQueryFileBasicInfoWireFormat(bArr, i);
        }
        if (i3 != SMB_QUERY_FILE_STANDARD_INFO) {
            return 0;
        }
        return readSmbQueryFileStandardInfoWireFormat(bArr, i);
    }

    int readSmbQueryFileStandardInfoWireFormat(byte[] bArr, int i) {
        SmbQueryFileStandardInfo smbQueryFileStandardInfo = new SmbQueryFileStandardInfo();
        smbQueryFileStandardInfo.allocationSize = readInt8(bArr, i);
        int i2 = i + 8;
        smbQueryFileStandardInfo.endOfFile = readInt8(bArr, i2);
        int i3 = i2 + 8;
        smbQueryFileStandardInfo.numberOfLinks = readInt4(bArr, i3);
        int i4 = i3 + 4;
        int i5 = i4 + 1;
        smbQueryFileStandardInfo.deletePending = (bArr[i4] & UByte.MAX_VALUE) > 0;
        int i6 = i5 + 1;
        smbQueryFileStandardInfo.directory = (bArr[i5] & UByte.MAX_VALUE) > 0;
        this.info = smbQueryFileStandardInfo;
        return i6 - i;
    }

    int readSmbQueryFileBasicInfoWireFormat(byte[] bArr, int i) {
        SmbQueryFileBasicInfo smbQueryFileBasicInfo = new SmbQueryFileBasicInfo();
        smbQueryFileBasicInfo.createTime = readTime(bArr, i);
        int i2 = i + 8;
        smbQueryFileBasicInfo.lastAccessTime = readTime(bArr, i2);
        int i3 = i2 + 8;
        smbQueryFileBasicInfo.lastWriteTime = readTime(bArr, i3);
        int i4 = i3 + 8;
        smbQueryFileBasicInfo.changeTime = readTime(bArr, i4);
        int i5 = i4 + 8;
        smbQueryFileBasicInfo.attributes = readInt2(bArr, i5);
        this.info = smbQueryFileBasicInfo;
        return (i5 + 2) - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2QueryPathInformationResponse[" + super.toString() + "]");
    }
}
