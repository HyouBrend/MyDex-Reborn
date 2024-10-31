package jcifs.smb;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import jcifs.util.LogStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Trans2FindFirst2Response extends SmbComTransactionResponse {
    static final int SMB_FILE_BOTH_DIRECTORY_INFO = 260;
    static final int SMB_FILE_NAMES_INFO = 259;
    static final int SMB_FIND_FILE_DIRECTORY_INFO = 257;
    static final int SMB_FIND_FILE_FULL_DIRECTORY_INFO = 258;
    static final int SMB_INFO_QUERY_EAS_FROM_LIST = 3;
    static final int SMB_INFO_QUERY_EA_SIZE = 2;
    static final int SMB_INFO_STANDARD = 1;
    int eaErrorOffset;
    boolean isEndOfSearch;
    String lastName;
    int lastNameBufferIndex;
    int lastNameOffset;
    int resumeKey;
    int sid;

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
    class SmbFindFileBothDirectoryInfo implements FileEntry {
        long allocationSize;
        long changeTime;
        long creationTime;
        int eaSize;
        long endOfFile;
        int extFileAttributes;
        int fileIndex;
        int fileNameLength;
        String filename;
        long lastAccessTime;
        long lastWriteTime;
        int nextEntryOffset;
        String shortName;
        int shortNameLength;

        @Override // jcifs.smb.FileEntry
        public int getType() {
            return 1;
        }

        SmbFindFileBothDirectoryInfo() {
        }

        @Override // jcifs.smb.FileEntry
        public String getName() {
            return this.filename;
        }

        @Override // jcifs.smb.FileEntry
        public int getAttributes() {
            return this.extFileAttributes;
        }

        @Override // jcifs.smb.FileEntry
        public long createTime() {
            return this.creationTime;
        }

        @Override // jcifs.smb.FileEntry
        public long lastModified() {
            return this.lastWriteTime;
        }

        @Override // jcifs.smb.FileEntry
        public long length() {
            return this.endOfFile;
        }

        public String toString() {
            return new String("SmbFindFileBothDirectoryInfo[nextEntryOffset=" + this.nextEntryOffset + ",fileIndex=" + this.fileIndex + ",creationTime=" + new Date(this.creationTime) + ",lastAccessTime=" + new Date(this.lastAccessTime) + ",lastWriteTime=" + new Date(this.lastWriteTime) + ",changeTime=" + new Date(this.changeTime) + ",endOfFile=" + this.endOfFile + ",allocationSize=" + this.allocationSize + ",extFileAttributes=" + this.extFileAttributes + ",fileNameLength=" + this.fileNameLength + ",eaSize=" + this.eaSize + ",shortNameLength=" + this.shortNameLength + ",shortName=" + this.shortName + ",filename=" + this.filename + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2FindFirst2Response() {
        this.command = (byte) 50;
        this.subCommand = (byte) 1;
    }

    String readString(byte[] bArr, int i, int i2) {
        try {
            if (this.useUnicode) {
                return new String(bArr, i, i2, SmbConstants.UNI_ENCODING);
            }
            if (i2 > 0 && bArr[(i + i2) - 1] == 0) {
                i2--;
            }
            return new String(bArr, i, i2, ServerMessageBlock.OEM_ENCODING);
        } catch (UnsupportedEncodingException e) {
            LogStream logStream = log;
            if (LogStream.level > 1) {
                e.printStackTrace(log);
            }
            return null;
        }
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        int i3;
        if (this.subCommand == 1) {
            this.sid = readInt2(bArr, i);
            i3 = i + 2;
        } else {
            i3 = i;
        }
        this.numEntries = readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.isEndOfSearch = (bArr[i4] & 1) == 1;
        int i5 = i4 + 2;
        this.eaErrorOffset = readInt2(bArr, i5);
        int i6 = i5 + 2;
        this.lastNameOffset = readInt2(bArr, i6);
        return (i6 + 2) - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        this.lastNameBufferIndex = this.lastNameOffset + i;
        this.results = new SmbFindFileBothDirectoryInfo[this.numEntries];
        for (int i3 = 0; i3 < this.numEntries; i3++) {
            FileEntry[] fileEntryArr = this.results;
            SmbFindFileBothDirectoryInfo smbFindFileBothDirectoryInfo = new SmbFindFileBothDirectoryInfo();
            fileEntryArr[i3] = smbFindFileBothDirectoryInfo;
            smbFindFileBothDirectoryInfo.nextEntryOffset = readInt4(bArr, i);
            smbFindFileBothDirectoryInfo.fileIndex = readInt4(bArr, i + 4);
            smbFindFileBothDirectoryInfo.creationTime = readTime(bArr, i + 8);
            smbFindFileBothDirectoryInfo.lastWriteTime = readTime(bArr, i + 24);
            smbFindFileBothDirectoryInfo.endOfFile = readInt8(bArr, i + 40);
            smbFindFileBothDirectoryInfo.extFileAttributes = readInt4(bArr, i + 56);
            smbFindFileBothDirectoryInfo.fileNameLength = readInt4(bArr, i + 60);
            smbFindFileBothDirectoryInfo.filename = readString(bArr, i + 94, smbFindFileBothDirectoryInfo.fileNameLength);
            if (this.lastNameBufferIndex >= i && (smbFindFileBothDirectoryInfo.nextEntryOffset == 0 || this.lastNameBufferIndex < smbFindFileBothDirectoryInfo.nextEntryOffset + i)) {
                this.lastName = smbFindFileBothDirectoryInfo.filename;
                this.resumeKey = smbFindFileBothDirectoryInfo.fileIndex;
            }
            i += smbFindFileBothDirectoryInfo.nextEntryOffset;
        }
        return this.dataCount;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String((this.subCommand == 1 ? "Trans2FindFirst2Response[" : "Trans2FindNext2Response[") + super.toString() + ",sid=" + this.sid + ",searchCount=" + this.numEntries + ",isEndOfSearch=" + this.isEndOfSearch + ",eaErrorOffset=" + this.eaErrorOffset + ",lastNameOffset=" + this.lastNameOffset + ",lastName=" + this.lastName + "]");
    }
}
