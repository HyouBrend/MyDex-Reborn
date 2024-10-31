package jcifs.smb;

import java.util.Date;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
class SmbComQueryInformationResponse extends ServerMessageBlock implements Info {
    private long serverTimeZoneOffset;
    private int fileAttributes = 0;
    private long lastWriteTime = 0;
    private int fileSize = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComQueryInformationResponse(long j) {
        this.serverTimeZoneOffset = j;
        this.command = (byte) 8;
    }

    @Override // jcifs.smb.Info
    public int getAttributes() {
        return this.fileAttributes;
    }

    @Override // jcifs.smb.Info
    public long getCreateTime() {
        return this.lastWriteTime + this.serverTimeZoneOffset;
    }

    @Override // jcifs.smb.Info
    public long getLastWriteTime() {
        return this.lastWriteTime + this.serverTimeZoneOffset;
    }

    @Override // jcifs.smb.Info
    public long getSize() {
        return this.fileSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        if (this.wordCount == 0) {
            return 0;
        }
        this.fileAttributes = readInt2(bArr, i);
        int i2 = i + 2;
        this.lastWriteTime = readUTime(bArr, i2);
        this.fileSize = readInt4(bArr, i2 + 4);
        return 20;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComQueryInformationResponse[" + super.toString() + ",fileAttributes=0x" + Hexdump.toHexString(this.fileAttributes, 4) + ",lastWriteTime=" + new Date(this.lastWriteTime) + ",fileSize=" + this.fileSize + "]");
    }
}
