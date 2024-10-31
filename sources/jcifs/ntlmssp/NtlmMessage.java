package jcifs.ntlmssp;

import jcifs.Config;
import kotlin.UByte;

/* loaded from: classes2.dex */
public abstract class NtlmMessage implements NtlmFlags {
    protected static final byte[] NTLMSSP_SIGNATURE = {78, 84, 76, 77, 83, 83, 80, 0};
    private static final String OEM_ENCODING = Config.DEFAULT_OEM_ENCODING;
    protected static final String UNI_ENCODING = "UTF-16LE";
    private int flags;

    public abstract byte[] toByteArray();

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public boolean getFlag(int i) {
        return (i & getFlags()) != 0;
    }

    public void setFlag(int i, boolean z) {
        int flags;
        if (z) {
            flags = i | getFlags();
        } else {
            flags = (i ^ (-1)) & getFlags();
        }
        setFlags(flags);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readULong(byte[] bArr, int i) {
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    static int readUShort(byte[] bArr, int i) {
        return ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | (bArr[i] & UByte.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] readSecurityBuffer(byte[] bArr, int i) {
        int readUShort = readUShort(bArr, i);
        byte[] bArr2 = new byte[readUShort];
        System.arraycopy(bArr, readULong(bArr, i + 4), bArr2, 0, readUShort);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeULong(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >> 8) & 255);
        bArr[i + 2] = (byte) ((i2 >> 16) & 255);
        bArr[i + 3] = (byte) ((i2 >> 24) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeUShort(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >> 8) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeSecurityBuffer(byte[] bArr, int i, int i2, byte[] bArr2) {
        int length = bArr2 != null ? bArr2.length : 0;
        if (length == 0) {
            return;
        }
        writeUShort(bArr, i, length);
        writeUShort(bArr, i + 2, length);
        writeULong(bArr, i + 4, i2);
        System.arraycopy(bArr2, 0, bArr, i2, length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getOEMEncoding() {
        return OEM_ENCODING;
    }
}
