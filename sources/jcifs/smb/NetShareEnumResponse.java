package jcifs.smb;

import jcifs.util.LogStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NetShareEnumResponse extends SmbComTransactionResponse {
    private int converter;
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
        this.useUnicode = false;
        this.results = new SmbShareInfo[this.numEntries];
        int i3 = i;
        for (int i4 = 0; i4 < this.numEntries; i4++) {
            FileEntry[] fileEntryArr = this.results;
            SmbShareInfo smbShareInfo = new SmbShareInfo();
            fileEntryArr[i4] = smbShareInfo;
            smbShareInfo.netName = readString(bArr, i3, 13, false);
            int i5 = i3 + 14;
            smbShareInfo.type = readInt2(bArr, i5);
            int i6 = i5 + 2;
            int readInt4 = readInt4(bArr, i6);
            i3 = i6 + 4;
            smbShareInfo.remark = readString(bArr, ((readInt4 & 65535) - this.converter) + i, 128, false);
            LogStream logStream = log;
            if (LogStream.level >= 4) {
                log.println(smbShareInfo);
            }
        }
        return i3 - i;
    }

    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("NetShareEnumResponse[" + super.toString() + ",status=" + this.status + ",converter=" + this.converter + ",entriesReturned=" + this.numEntries + ",totalAvailableEntries=" + this.totalAvailableEntries + "]");
    }
}
