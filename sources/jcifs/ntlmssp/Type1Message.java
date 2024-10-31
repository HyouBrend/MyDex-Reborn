package jcifs.ntlmssp;

import java.io.IOException;
import java.net.UnknownHostException;
import jcifs.Config;
import jcifs.netbios.NbtAddress;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
public class Type1Message extends NtlmMessage {
    private static final String DEFAULT_DOMAIN;
    private static final int DEFAULT_FLAGS;
    private static final String DEFAULT_WORKSTATION;
    private String suppliedDomain;
    private String suppliedWorkstation;

    static {
        DEFAULT_FLAGS = (Config.getBoolean("jcifs.smb.client.useUnicode", true) ? 1 : 2) | 512;
        String str = null;
        DEFAULT_DOMAIN = Config.getProperty("jcifs.smb.client.domain", null);
        try {
            str = NbtAddress.getLocalHost().getHostName();
        } catch (UnknownHostException unused) {
        }
        DEFAULT_WORKSTATION = str;
    }

    public Type1Message() {
        this(getDefaultFlags(), getDefaultDomain(), getDefaultWorkstation());
    }

    public Type1Message(int i, String str, String str2) {
        setFlags(i | getDefaultFlags());
        setSuppliedDomain(str);
        setSuppliedWorkstation(str2 == null ? getDefaultWorkstation() : str2);
    }

    public Type1Message(byte[] bArr) throws IOException {
        parse(bArr);
    }

    public String getSuppliedDomain() {
        return this.suppliedDomain;
    }

    public void setSuppliedDomain(String str) {
        this.suppliedDomain = str;
    }

    public String getSuppliedWorkstation() {
        return this.suppliedWorkstation;
    }

    public void setSuppliedWorkstation(String str) {
        this.suppliedWorkstation = str;
    }

    @Override // jcifs.ntlmssp.NtlmMessage
    public byte[] toByteArray() {
        int i;
        boolean z;
        int i2;
        try {
            String suppliedDomain = getSuppliedDomain();
            String suppliedWorkstation = getSuppliedWorkstation();
            int flags = getFlags();
            byte[] bArr = new byte[0];
            if (suppliedDomain == null || suppliedDomain.length() == 0) {
                i = flags & (-4097);
                z = false;
            } else {
                i = flags | 4096;
                bArr = suppliedDomain.toUpperCase().getBytes(getOEMEncoding());
                z = true;
            }
            byte[] bArr2 = new byte[0];
            if (suppliedWorkstation == null || suppliedWorkstation.length() == 0) {
                i2 = i & (-8193);
            } else {
                bArr2 = suppliedWorkstation.toUpperCase().getBytes(getOEMEncoding());
                i2 = i | 8192;
                z = true;
            }
            byte[] bArr3 = new byte[z ? bArr.length + 32 + bArr2.length : 16];
            System.arraycopy(NTLMSSP_SIGNATURE, 0, bArr3, 0, 8);
            writeULong(bArr3, 8, 1);
            writeULong(bArr3, 12, i2);
            if (z) {
                writeSecurityBuffer(bArr3, 16, 32, bArr);
                writeSecurityBuffer(bArr3, 24, bArr.length + 32, bArr2);
            }
            return bArr3;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public String toString() {
        String suppliedDomain = getSuppliedDomain();
        String suppliedWorkstation = getSuppliedWorkstation();
        StringBuilder sb = new StringBuilder();
        sb.append("Type1Message[suppliedDomain=");
        if (suppliedDomain == null) {
            suppliedDomain = "null";
        }
        sb.append(suppliedDomain);
        sb.append(",suppliedWorkstation=");
        if (suppliedWorkstation == null) {
            suppliedWorkstation = "null";
        }
        sb.append(suppliedWorkstation);
        sb.append(",flags=0x");
        sb.append(Hexdump.toHexString(getFlags(), 8));
        sb.append("]");
        return sb.toString();
    }

    public static int getDefaultFlags() {
        return DEFAULT_FLAGS;
    }

    public static String getDefaultDomain() {
        return DEFAULT_DOMAIN;
    }

    public static String getDefaultWorkstation() {
        return DEFAULT_WORKSTATION;
    }

    private void parse(byte[] bArr) throws IOException {
        for (int i = 0; i < 8; i++) {
            if (bArr[i] != NTLMSSP_SIGNATURE[i]) {
                throw new IOException("Not an NTLMSSP message.");
            }
        }
        if (readULong(bArr, 8) != 1) {
            throw new IOException("Not a Type 1 message.");
        }
        int readULong = readULong(bArr, 12);
        String str = (readULong & 4096) != 0 ? new String(readSecurityBuffer(bArr, 16), getOEMEncoding()) : null;
        String str2 = (readULong & 8192) != 0 ? new String(readSecurityBuffer(bArr, 24), getOEMEncoding()) : null;
        setFlags(readULong);
        setSuppliedDomain(str);
        setSuppliedWorkstation(str2);
    }
}
