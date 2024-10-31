package jcifs.smb;

import java.util.Date;
import jcifs.Config;
import jcifs.util.Hexdump;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComOpenAndX extends AndXServerMessageBlock {
    private static final int BATCH_LIMIT = Config.getInt("jcifs.smb.client.OpenAndX.ReadAndX", 1);
    private static final int DO_NOT_CACHE = 4096;
    private static final int FLAGS_REQUEST_BATCH_OPLOCK = 4;
    private static final int FLAGS_REQUEST_OPLOCK = 2;
    private static final int FLAGS_RETURN_ADDITIONAL_INFO = 1;
    private static final int OPEN_FN_CREATE = 16;
    private static final int OPEN_FN_FAIL_IF_EXISTS = 0;
    private static final int OPEN_FN_OPEN = 1;
    private static final int OPEN_FN_TRUNC = 2;
    private static final int SHARING_COMPATIBILITY = 0;
    private static final int SHARING_DENY_NONE = 64;
    private static final int SHARING_DENY_READ_EXECUTE = 48;
    private static final int SHARING_DENY_READ_WRITE_EXECUTE = 16;
    private static final int SHARING_DENY_WRITE = 32;
    private static final int WRITE_THROUGH = 16384;
    int allocationSize;
    int creationTime;
    int desiredAccess;
    int fileAttributes;
    int flags;
    int openFunction;
    int searchAttributes;

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
    public SmbComOpenAndX(String str, int i, int i2, ServerMessageBlock serverMessageBlock) {
        super(serverMessageBlock);
        this.path = str;
        this.command = (byte) 45;
        int i3 = i & 3;
        this.desiredAccess = i3;
        if (i3 == 3) {
            this.desiredAccess = 2;
        }
        this.desiredAccess = (this.desiredAccess | 64) & (-2);
        this.searchAttributes = 22;
        this.fileAttributes = 0;
        if ((i2 & 64) == 64) {
            if ((i2 & 16) == 16) {
                this.openFunction = 18;
                return;
            } else {
                this.openFunction = 2;
                return;
            }
        }
        if ((i2 & 16) != 16) {
            this.openFunction = 1;
        } else if ((i2 & 32) == 32) {
            this.openFunction = 16;
        } else {
            this.openFunction = 17;
        }
    }

    @Override // jcifs.smb.AndXServerMessageBlock
    int getBatchLimit(byte b) {
        if (b == 46) {
            return BATCH_LIMIT;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.flags, bArr, i);
        int i2 = i + 2;
        writeInt2(this.desiredAccess, bArr, i2);
        int i3 = i2 + 2;
        writeInt2(this.searchAttributes, bArr, i3);
        int i4 = i3 + 2;
        writeInt2(this.fileAttributes, bArr, i4);
        int i5 = i4 + 2;
        this.creationTime = 0;
        writeInt4(0, bArr, i5);
        int i6 = i5 + 4;
        writeInt2(this.openFunction, bArr, i6);
        int i7 = i6 + 2;
        writeInt4(this.allocationSize, bArr, i7);
        int i8 = i7 + 4;
        int i9 = 0;
        while (i9 < 8) {
            bArr[i8] = 0;
            i9++;
            i8++;
        }
        return i8 - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int i2;
        if (this.useUnicode) {
            i2 = i + 1;
            bArr[i] = 0;
        } else {
            i2 = i;
        }
        return (i2 + writeString(this.path, bArr, i2)) - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComOpenAndX[" + super.toString() + ",flags=0x" + Hexdump.toHexString(this.flags, 2) + ",desiredAccess=0x" + Hexdump.toHexString(this.desiredAccess, 4) + ",searchAttributes=0x" + Hexdump.toHexString(this.searchAttributes, 4) + ",fileAttributes=0x" + Hexdump.toHexString(this.fileAttributes, 4) + ",creationTime=" + new Date(this.creationTime) + ",openFunction=0x" + Hexdump.toHexString(this.openFunction, 2) + ",allocationSize=" + this.allocationSize + ",fileName=" + this.path + "]");
    }
}
