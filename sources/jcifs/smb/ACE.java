package jcifs.smb;

import jcifs.util.Hexdump;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class ACE {
    public static final int DELETE = 65536;
    public static final int FILE_APPEND_DATA = 4;
    public static final int FILE_DELETE = 64;
    public static final int FILE_EXECUTE = 32;
    public static final int FILE_READ_ATTRIBUTES = 128;
    public static final int FILE_READ_DATA = 1;
    public static final int FILE_READ_EA = 8;
    public static final int FILE_WRITE_ATTRIBUTES = 256;
    public static final int FILE_WRITE_DATA = 2;
    public static final int FILE_WRITE_EA = 16;
    public static final int FLAGS_CONTAINER_INHERIT = 2;
    public static final int FLAGS_INHERITED = 16;
    public static final int FLAGS_INHERIT_ONLY = 8;
    public static final int FLAGS_NO_PROPAGATE = 4;
    public static final int FLAGS_OBJECT_INHERIT = 1;
    public static final int GENERIC_ALL = 268435456;
    public static final int GENERIC_EXECUTE = 536870912;
    public static final int GENERIC_READ = Integer.MIN_VALUE;
    public static final int GENERIC_WRITE = 1073741824;
    public static final int READ_CONTROL = 131072;
    public static final int SYNCHRONIZE = 1048576;
    public static final int WRITE_DAC = 262144;
    public static final int WRITE_OWNER = 524288;
    int access;
    boolean allow;
    int flags;
    SID sid;

    public boolean isAllow() {
        return this.allow;
    }

    public boolean isInherited() {
        return (this.flags & 16) != 0;
    }

    public int getFlags() {
        return this.flags;
    }

    public String getApplyToText() {
        int i = this.flags & 11;
        if (i == 0) {
            return "This folder only";
        }
        if (i == 1) {
            return "This folder and files";
        }
        if (i == 2) {
            return "This folder and subfolders";
        }
        if (i == 3) {
            return "This folder, subfolders and files";
        }
        switch (i) {
            case 9:
                return "Files only";
            case 10:
                return "Subfolders only";
            case 11:
                return "Subfolders and files only";
            default:
                return "Invalid";
        }
    }

    public int getAccessMask() {
        return this.access;
    }

    public SID getSID() {
        return this.sid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int decode(byte[] bArr, int i) {
        int i2 = i + 1;
        this.allow = bArr[i] == 0;
        int i3 = i2 + 1;
        this.flags = bArr[i2] & UByte.MAX_VALUE;
        int readInt2 = ServerMessageBlock.readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.access = ServerMessageBlock.readInt4(bArr, i4);
        this.sid = new SID(bArr, i4 + 4);
        return readInt2;
    }

    void appendCol(StringBuffer stringBuffer, String str, int i) {
        stringBuffer.append(str);
        int length = i - str.length();
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(' ');
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(isAllow() ? "Allow " : "Deny  ");
        appendCol(stringBuffer, this.sid.toDisplayString(), 25);
        stringBuffer.append(" 0x").append(Hexdump.toHexString(this.access, 8)).append(' ');
        stringBuffer.append(isInherited() ? "Inherited " : "Direct    ");
        appendCol(stringBuffer, getApplyToText(), 34);
        return stringBuffer.toString();
    }
}
