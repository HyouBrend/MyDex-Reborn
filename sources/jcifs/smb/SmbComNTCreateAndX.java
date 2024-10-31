package jcifs.smb;

import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
class SmbComNTCreateAndX extends AndXServerMessageBlock {
    static final int FILE_CREATE = 2;
    static final int FILE_OPEN = 1;
    static final int FILE_OPEN_IF = 3;
    static final int FILE_OVERWRITE = 4;
    static final int FILE_OVERWRITE_IF = 5;
    static final int FILE_SEQUENTIAL_ONLY = 4;
    static final int FILE_SUPERSEDE = 0;
    static final int FILE_SYNCHRONOUS_IO_ALERT = 16;
    static final int FILE_SYNCHRONOUS_IO_NONALERT = 32;
    static final int FILE_WRITE_THROUGH = 2;
    static final int SECURITY_CONTEXT_TRACKING = 1;
    static final int SECURITY_EFFECTIVE_ONLY = 2;
    private long allocationSize;
    private int createDisposition;
    private int createOptions;
    int desiredAccess;
    private int extFileAttributes;
    int flags0;
    private int impersonationLevel;
    private int namelen_index;
    private int rootDirectoryFid;
    private byte securityFlags;
    private int shareAccess;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComNTCreateAndX(String str, int i, int i2, int i3, int i4, int i5, ServerMessageBlock serverMessageBlock) {
        super(serverMessageBlock);
        this.path = str;
        this.command = (byte) -94;
        this.desiredAccess = i2 | 137;
        this.extFileAttributes = i4;
        this.shareAccess = i3;
        if ((i & 64) == 64) {
            if ((i & 16) == 16) {
                this.createDisposition = 5;
            } else {
                this.createDisposition = 4;
            }
        } else if ((i & 16) != 16) {
            this.createDisposition = 1;
        } else if ((i & 32) == 32) {
            this.createDisposition = 2;
        } else {
            this.createDisposition = 3;
        }
        if ((i5 & 1) == 0) {
            this.createOptions = i5 | 64;
        } else {
            this.createOptions = i5;
        }
        this.impersonationLevel = 2;
        this.securityFlags = (byte) 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = 0;
        this.namelen_index = i2;
        int i3 = i2 + 2;
        writeInt4(this.flags0, bArr, i3);
        int i4 = i3 + 4;
        writeInt4(this.rootDirectoryFid, bArr, i4);
        int i5 = i4 + 4;
        writeInt4(this.desiredAccess, bArr, i5);
        int i6 = i5 + 4;
        writeInt8(this.allocationSize, bArr, i6);
        int i7 = i6 + 8;
        writeInt4(this.extFileAttributes, bArr, i7);
        int i8 = i7 + 4;
        writeInt4(this.shareAccess, bArr, i8);
        int i9 = i8 + 4;
        writeInt4(this.createDisposition, bArr, i9);
        int i10 = i9 + 4;
        writeInt4(this.createOptions, bArr, i10);
        int i11 = i10 + 4;
        writeInt4(this.impersonationLevel, bArr, i11);
        int i12 = i11 + 4;
        bArr[i12] = this.securityFlags;
        return (i12 + 1) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int writeString = writeString(this.path, bArr, i);
        writeInt2(this.useUnicode ? this.path.length() * 2 : writeString, bArr, this.namelen_index);
        return writeString;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComNTCreateAndX[" + super.toString() + ",flags=0x" + Hexdump.toHexString(this.flags0, 2) + ",rootDirectoryFid=" + this.rootDirectoryFid + ",desiredAccess=0x" + Hexdump.toHexString(this.desiredAccess, 4) + ",allocationSize=" + this.allocationSize + ",extFileAttributes=0x" + Hexdump.toHexString(this.extFileAttributes, 4) + ",shareAccess=0x" + Hexdump.toHexString(this.shareAccess, 4) + ",createDisposition=0x" + Hexdump.toHexString(this.createDisposition, 4) + ",createOptions=0x" + Hexdump.toHexString(this.createOptions, 8) + ",impersonationLevel=0x" + Hexdump.toHexString(this.impersonationLevel, 4) + ",securityFlags=0x" + Hexdump.toHexString((int) this.securityFlags, 2) + ",name=" + this.path + "]");
    }
}
