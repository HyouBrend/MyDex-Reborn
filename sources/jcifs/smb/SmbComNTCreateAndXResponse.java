package jcifs.smb;

import java.util.Date;
import jcifs.util.Hexdump;
import kotlin.UByte;

/* loaded from: classes2.dex */
class SmbComNTCreateAndXResponse extends AndXServerMessageBlock {
    static final int BATCH_OPLOCK_GRANTED = 2;
    static final int EXCLUSIVE_OPLOCK_GRANTED = 1;
    static final int LEVEL_II_OPLOCK_GRANTED = 3;
    long allocationSize;
    long changeTime;
    int createAction;
    long creationTime;
    int deviceState;
    boolean directory;
    long endOfFile;
    int extFileAttributes;
    int fid;
    int fileType;
    boolean isExtended;
    long lastAccessTime;
    long lastWriteTime;
    byte oplockLevel;

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
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        this.oplockLevel = bArr[i];
        this.fid = readInt2(bArr, i2);
        int i3 = i2 + 2;
        this.createAction = readInt4(bArr, i3);
        int i4 = i3 + 4;
        this.creationTime = readTime(bArr, i4);
        int i5 = i4 + 8;
        this.lastAccessTime = readTime(bArr, i5);
        int i6 = i5 + 8;
        this.lastWriteTime = readTime(bArr, i6);
        int i7 = i6 + 8;
        this.changeTime = readTime(bArr, i7);
        int i8 = i7 + 8;
        this.extFileAttributes = readInt4(bArr, i8);
        int i9 = i8 + 4;
        this.allocationSize = readInt8(bArr, i9);
        int i10 = i9 + 8;
        this.endOfFile = readInt8(bArr, i10);
        int i11 = i10 + 8;
        this.fileType = readInt2(bArr, i11);
        int i12 = i11 + 2;
        this.deviceState = readInt2(bArr, i12);
        int i13 = i12 + 2;
        int i14 = i13 + 1;
        this.directory = (bArr[i13] & UByte.MAX_VALUE) > 0;
        return i14 - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComNTCreateAndXResponse[" + super.toString() + ",oplockLevel=" + ((int) this.oplockLevel) + ",fid=" + this.fid + ",createAction=0x" + Hexdump.toHexString(this.createAction, 4) + ",creationTime=" + new Date(this.creationTime) + ",lastAccessTime=" + new Date(this.lastAccessTime) + ",lastWriteTime=" + new Date(this.lastWriteTime) + ",changeTime=" + new Date(this.changeTime) + ",extFileAttributes=0x" + Hexdump.toHexString(this.extFileAttributes, 4) + ",allocationSize=" + this.allocationSize + ",endOfFile=" + this.endOfFile + ",fileType=" + this.fileType + ",deviceState=" + this.deviceState + ",directory=" + this.directory + "]");
    }
}
