package jcifs.smb;

import jcifs.util.Hexdump;
import jcifs.util.LogStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
class NetServerEnum2Response extends SmbComTransactionResponse {
    private int converter;
    String lastName;
    private int totalAvailableEntries;

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
    class ServerInfo1 implements FileEntry {
        String commentOrMasterBrowser;
        String name;
        int type;
        int versionMajor;
        int versionMinor;

        @Override // jcifs.smb.FileEntry
        public long createTime() {
            return 0L;
        }

        @Override // jcifs.smb.FileEntry
        public int getAttributes() {
            return 17;
        }

        @Override // jcifs.smb.FileEntry
        public long lastModified() {
            return 0L;
        }

        @Override // jcifs.smb.FileEntry
        public long length() {
            return 0L;
        }

        ServerInfo1() {
        }

        @Override // jcifs.smb.FileEntry
        public String getName() {
            return this.name;
        }

        @Override // jcifs.smb.FileEntry
        public int getType() {
            return (this.type & Integer.MIN_VALUE) != 0 ? 2 : 4;
        }

        public String toString() {
            return new String("ServerInfo1[name=" + this.name + ",versionMajor=" + this.versionMajor + ",versionMinor=" + this.versionMinor + ",type=0x" + Hexdump.toHexString(this.type, 8) + ",commentOrMasterBrowser=" + this.commentOrMasterBrowser + "]");
        }
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        this.status = readInt2(bArr, i);
        int i3 = i + 2;
        this.converter = readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.numEntries = readInt2(bArr, i4);
        int i5 = i4 + 2;
        this.totalAvailableEntries = readInt2(bArr, i5);
        return (i5 + 2) - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        this.results = new ServerInfo1[this.numEntries];
        int i3 = i;
        ServerInfo1 serverInfo1 = null;
        int i4 = 0;
        while (i4 < this.numEntries) {
            FileEntry[] fileEntryArr = this.results;
            ServerInfo1 serverInfo12 = new ServerInfo1();
            fileEntryArr[i4] = serverInfo12;
            serverInfo12.name = readString(bArr, i3, 16, false);
            int i5 = i3 + 16;
            int i6 = i5 + 1;
            serverInfo12.versionMajor = bArr[i5] & UByte.MAX_VALUE;
            int i7 = i6 + 1;
            serverInfo12.versionMinor = bArr[i6] & UByte.MAX_VALUE;
            serverInfo12.type = readInt4(bArr, i7);
            int i8 = i7 + 4;
            int readInt4 = readInt4(bArr, i8);
            i3 = i8 + 4;
            serverInfo12.commentOrMasterBrowser = readString(bArr, ((readInt4 & 65535) - this.converter) + i, 48, false);
            LogStream logStream = log;
            if (LogStream.level >= 4) {
                log.println(serverInfo12);
            }
            i4++;
            serverInfo1 = serverInfo12;
        }
        this.lastName = this.numEntries != 0 ? serverInfo1.name : null;
        return i3 - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("NetServerEnum2Response[" + super.toString() + ",status=" + this.status + ",converter=" + this.converter + ",entriesReturned=" + this.numEntries + ",totalAvailableEntries=" + this.totalAvailableEntries + ",lastName=" + this.lastName + "]");
    }
}
