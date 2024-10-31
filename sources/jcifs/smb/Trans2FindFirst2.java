package jcifs.smb;

import jcifs.Config;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
class Trans2FindFirst2 extends SmbComTransaction {
    private static final int DEFAULT_LIST_COUNT = 200;
    private static final int DEFAULT_LIST_SIZE = 65535;
    private static final int FLAGS_CLOSE_AFTER_THIS_REQUEST = 1;
    private static final int FLAGS_CLOSE_IF_END_REACHED = 2;
    private static final int FLAGS_FIND_WITH_BACKUP_INTENT = 16;
    private static final int FLAGS_RESUME_FROM_PREVIOUS_END = 8;
    private static final int FLAGS_RETURN_RESUME_KEYS = 4;
    static final int SMB_FILE_BOTH_DIRECTORY_INFO = 260;
    static final int SMB_FILE_NAMES_INFO = 259;
    static final int SMB_FIND_FILE_DIRECTORY_INFO = 257;
    static final int SMB_FIND_FILE_FULL_DIRECTORY_INFO = 258;
    static final int SMB_INFO_QUERY_EAS_FROM_LIST = 3;
    static final int SMB_INFO_QUERY_EA_SIZE = 2;
    static final int SMB_INFO_STANDARD = 1;
    private int flags;
    private int informationLevel;
    private int searchAttributes;
    private int searchStorageType = 0;
    private String wildcard;
    static final int LIST_SIZE = Config.getInt("jcifs.smb.client.listSize", 65535);
    static final int LIST_COUNT = Config.getInt("jcifs.smb.client.listCount", 200);

    @Override // jcifs.smb.SmbComTransaction
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int readSetupWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trans2FindFirst2(String str, String str2, int i) {
        if (str.equals("\\")) {
            this.path = str;
        } else {
            this.path = str + "\\";
        }
        this.wildcard = str2;
        this.searchAttributes = i & 55;
        this.command = (byte) 50;
        this.subCommand = (byte) 1;
        this.flags = 0;
        this.informationLevel = SMB_FILE_BOTH_DIRECTORY_INFO;
        this.totalDataCount = 0;
        this.maxParameterCount = 10;
        this.maxDataCount = LIST_SIZE;
        this.maxSetupCount = (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransaction
    public int writeSetupWireFormat(byte[] bArr, int i) {
        bArr[i] = this.subCommand;
        bArr[i + 1] = 0;
        return 2;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeParametersWireFormat(byte[] bArr, int i) {
        writeInt2(this.searchAttributes, bArr, i);
        int i2 = i + 2;
        writeInt2(LIST_COUNT, bArr, i2);
        int i3 = i2 + 2;
        writeInt2(this.flags, bArr, i3);
        int i4 = i3 + 2;
        writeInt2(this.informationLevel, bArr, i4);
        int i5 = i4 + 2;
        writeInt4(this.searchStorageType, bArr, i5);
        int i6 = i5 + 4;
        return (i6 + writeString(this.path + this.wildcard, bArr, i6)) - i;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("Trans2FindFirst2[" + super.toString() + ",searchAttributes=0x" + Hexdump.toHexString(this.searchAttributes, 2) + ",searchCount=" + LIST_COUNT + ",flags=0x" + Hexdump.toHexString(this.flags, 2) + ",informationLevel=0x" + Hexdump.toHexString(this.informationLevel, 3) + ",searchStorageType=" + this.searchStorageType + ",filename=" + this.path + "]");
    }
}
