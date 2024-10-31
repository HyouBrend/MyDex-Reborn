package jcifs.smb;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.Arrays;
import java.util.Random;
import jcifs.Config;
import jcifs.util.DES;
import jcifs.util.Encdec;
import jcifs.util.HMACT64;
import jcifs.util.LogStream;
import jcifs.util.MD4;

/* loaded from: classes2.dex */
public final class NtlmPasswordAuthentication implements Principal, Serializable {
    static final String BLANK = "";
    static String DEFAULT_DOMAIN;
    static String DEFAULT_PASSWORD;
    static String DEFAULT_USERNAME;
    byte[] ansiHash;
    byte[] challenge;
    byte[] clientChallenge;
    String domain;
    boolean hashesExternal;
    String password;
    byte[] unicodeHash;
    String username;
    private static final int LM_COMPATIBILITY = Config.getInt("jcifs.smb.lmCompatibility", 3);
    private static final Random RANDOM = new Random();
    private static LogStream log = LogStream.getInstance();
    private static final byte[] S8 = {75, 71, 83, 33, 64, 35, 36, 37};
    public static final NtlmPasswordAuthentication ANONYMOUS = new NtlmPasswordAuthentication("", "", "");
    static final NtlmPasswordAuthentication NULL = new NtlmPasswordAuthentication("", "", "");
    static final NtlmPasswordAuthentication GUEST = new NtlmPasswordAuthentication("?", "GUEST", "");
    static final NtlmPasswordAuthentication DEFAULT = new NtlmPasswordAuthentication(null);

    private static void E(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[7];
        byte[] bArr5 = new byte[8];
        for (int i = 0; i < bArr.length / 7; i++) {
            System.arraycopy(bArr, i * 7, bArr4, 0, 7);
            new DES(bArr4).encrypt(bArr2, bArr5);
            System.arraycopy(bArr5, 0, bArr3, i * 8, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initDefaults() {
        if (DEFAULT_DOMAIN != null) {
            return;
        }
        DEFAULT_DOMAIN = Config.getProperty("jcifs.smb.client.domain", "?");
        DEFAULT_USERNAME = Config.getProperty("jcifs.smb.client.username", "GUEST");
        DEFAULT_PASSWORD = Config.getProperty("jcifs.smb.client.password", "");
    }

    public static byte[] getPreNTLMResponse(String str, byte[] bArr) {
        byte[] bArr2 = new byte[14];
        byte[] bArr3 = new byte[21];
        byte[] bArr4 = new byte[24];
        try {
            byte[] bytes = str.toUpperCase().getBytes(ServerMessageBlock.OEM_ENCODING);
            int length = bytes.length;
            System.arraycopy(bytes, 0, bArr2, 0, length <= 14 ? length : 14);
            E(bArr2, S8, bArr3);
            E(bArr3, bArr, bArr4);
            return bArr4;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Try setting jcifs.encoding=US-ASCII", e);
        }
    }

    public static byte[] getNTLMResponse(String str, byte[] bArr) {
        byte[] bArr2;
        byte[] bArr3 = new byte[21];
        byte[] bArr4 = new byte[24];
        try {
            bArr2 = str.getBytes(SmbConstants.UNI_ENCODING);
        } catch (UnsupportedEncodingException e) {
            if (LogStream.level > 0) {
                e.printStackTrace(log);
            }
            bArr2 = null;
        }
        MD4 md4 = new MD4();
        md4.update(bArr2);
        try {
            md4.digest(bArr3, 0, 16);
        } catch (Exception e2) {
            if (LogStream.level > 0) {
                e2.printStackTrace(log);
            }
        }
        E(bArr3, bArr, bArr4);
        return bArr4;
    }

    public static byte[] getLMv2Response(String str, String str2, String str3, byte[] bArr, byte[] bArr2) {
        try {
            byte[] bArr3 = new byte[24];
            MD4 md4 = new MD4();
            md4.update(str3.getBytes(SmbConstants.UNI_ENCODING));
            HMACT64 hmact64 = new HMACT64(md4.digest());
            hmact64.update(str2.toUpperCase().getBytes(SmbConstants.UNI_ENCODING));
            hmact64.update(str.toUpperCase().getBytes(SmbConstants.UNI_ENCODING));
            HMACT64 hmact642 = new HMACT64(hmact64.digest());
            hmact642.update(bArr);
            hmact642.update(bArr2);
            hmact642.digest(bArr3, 0, 16);
            System.arraycopy(bArr2, 0, bArr3, 16, 8);
            return bArr3;
        } catch (Exception e) {
            if (LogStream.level <= 0) {
                return null;
            }
            e.printStackTrace(log);
            return null;
        }
    }

    public static byte[] getNTLM2Response(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[8];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr2);
            messageDigest.update(bArr3, 0, 8);
            System.arraycopy(messageDigest.digest(), 0, bArr4, 0, 8);
            byte[] bArr5 = new byte[21];
            System.arraycopy(bArr, 0, bArr5, 0, 16);
            byte[] bArr6 = new byte[24];
            E(bArr5, bArr4, bArr6);
            return bArr6;
        } catch (GeneralSecurityException e) {
            if (LogStream.level > 0) {
                e.printStackTrace(log);
            }
            throw new RuntimeException("MD5", e);
        }
    }

    public static byte[] nTOWFv1(String str) {
        if (str == null) {
            throw new RuntimeException("Password parameter is required");
        }
        try {
            MD4 md4 = new MD4();
            md4.update(str.getBytes(SmbConstants.UNI_ENCODING));
            return md4.digest();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static byte[] nTOWFv2(String str, String str2, String str3) {
        try {
            MD4 md4 = new MD4();
            md4.update(str3.getBytes(SmbConstants.UNI_ENCODING));
            HMACT64 hmact64 = new HMACT64(md4.digest());
            hmact64.update(str2.toUpperCase().getBytes(SmbConstants.UNI_ENCODING));
            hmact64.update(str.getBytes(SmbConstants.UNI_ENCODING));
            return hmact64.digest();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    static byte[] computeResponse(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2) {
        HMACT64 hmact64 = new HMACT64(bArr);
        hmact64.update(bArr2);
        hmact64.update(bArr3, i, i2);
        byte[] digest = hmact64.digest();
        byte[] bArr4 = new byte[digest.length + bArr3.length];
        System.arraycopy(digest, 0, bArr4, 0, digest.length);
        System.arraycopy(bArr3, 0, bArr4, digest.length, bArr3.length);
        return bArr4;
    }

    public static byte[] getLMv2Response(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return computeResponse(bArr, bArr2, bArr3, 0, bArr3.length);
    }

    public static byte[] getNTLMv2Response(byte[] bArr, byte[] bArr2, byte[] bArr3, long j, byte[] bArr4) {
        int length = bArr4 != null ? bArr4.length : 0;
        int i = length + 28;
        int i2 = i + 4;
        byte[] bArr5 = new byte[i2];
        Encdec.enc_uint32le(257, bArr5, 0);
        Encdec.enc_uint32le(0, bArr5, 4);
        Encdec.enc_uint64le(j, bArr5, 8);
        System.arraycopy(bArr3, 0, bArr5, 16, 8);
        Encdec.enc_uint32le(0, bArr5, 24);
        if (bArr4 != null) {
            System.arraycopy(bArr4, 0, bArr5, 28, length);
        }
        Encdec.enc_uint32le(0, bArr5, i);
        return computeResponse(bArr, bArr2, bArr5, 0, i2);
    }

    public NtlmPasswordAuthentication(String str) {
        this.hashesExternal = false;
        this.clientChallenge = null;
        this.challenge = null;
        this.password = null;
        this.username = null;
        this.domain = null;
        if (str != null) {
            try {
                str = unescape(str);
            } catch (UnsupportedEncodingException unused) {
            }
            int length = str.length();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                char charAt = str.charAt(i);
                if (charAt == ';') {
                    this.domain = str.substring(0, i);
                    i2 = i + 1;
                } else if (charAt == ':') {
                    this.password = str.substring(i + 1);
                    break;
                }
                i++;
            }
            this.username = str.substring(i2, i);
        }
        initDefaults();
        if (this.domain == null) {
            this.domain = DEFAULT_DOMAIN;
        }
        if (this.username == null) {
            this.username = DEFAULT_USERNAME;
        }
        if (this.password == null) {
            this.password = DEFAULT_PASSWORD;
        }
    }

    public NtlmPasswordAuthentication(String str, String str2, String str3) {
        this.hashesExternal = false;
        this.clientChallenge = null;
        this.challenge = null;
        if (str2 != null) {
            int indexOf = str2.indexOf(64);
            if (indexOf > 0) {
                str = str2.substring(indexOf + 1);
                str2 = str2.substring(0, indexOf);
            } else {
                int indexOf2 = str2.indexOf(92);
                if (indexOf2 > 0) {
                    str = str2.substring(0, indexOf2);
                    str2 = str2.substring(indexOf2 + 1);
                }
            }
        }
        this.domain = str;
        this.username = str2;
        this.password = str3;
        initDefaults();
        if (str == null) {
            this.domain = DEFAULT_DOMAIN;
        }
        if (str2 == null) {
            this.username = DEFAULT_USERNAME;
        }
        if (str3 == null) {
            this.password = DEFAULT_PASSWORD;
        }
    }

    public NtlmPasswordAuthentication(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.hashesExternal = false;
        this.clientChallenge = null;
        this.challenge = null;
        if (str == null || str2 == null || bArr2 == null || bArr3 == null) {
            throw new IllegalArgumentException("External credentials cannot be null");
        }
        this.domain = str;
        this.username = str2;
        this.password = null;
        this.challenge = bArr;
        this.ansiHash = bArr2;
        this.unicodeHash = bArr3;
        this.hashesExternal = true;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override // java.security.Principal
    public String getName() {
        if (!(this.domain.length() > 0 && !this.domain.equals("?"))) {
            return this.username;
        }
        return this.domain + "\\" + this.username;
    }

    public byte[] getAnsiHash(byte[] bArr) {
        if (this.hashesExternal) {
            return this.ansiHash;
        }
        int i = LM_COMPATIBILITY;
        if (i == 0 || i == 1) {
            return getPreNTLMResponse(this.password, bArr);
        }
        if (i == 2) {
            return getNTLMResponse(this.password, bArr);
        }
        if (i == 3 || i == 4 || i == 5) {
            if (this.clientChallenge == null) {
                byte[] bArr2 = new byte[8];
                this.clientChallenge = bArr2;
                RANDOM.nextBytes(bArr2);
            }
            return getLMv2Response(this.domain, this.username, this.password, bArr, this.clientChallenge);
        }
        return getPreNTLMResponse(this.password, bArr);
    }

    public byte[] getUnicodeHash(byte[] bArr) {
        if (this.hashesExternal) {
            return this.unicodeHash;
        }
        int i = LM_COMPATIBILITY;
        if (i == 0 || i == 1 || i == 2) {
            return getNTLMResponse(this.password, bArr);
        }
        return (i == 3 || i == 4 || i == 5) ? new byte[0] : getNTLMResponse(this.password, bArr);
    }

    public byte[] getSigningKey(byte[] bArr) throws SmbException {
        int i = LM_COMPATIBILITY;
        if (i == 0 || i == 1 || i == 2) {
            byte[] bArr2 = new byte[40];
            getUserSessionKey(bArr, bArr2, 0);
            System.arraycopy(getUnicodeHash(bArr), 0, bArr2, 16, 24);
            return bArr2;
        }
        if (i == 3 || i == 4 || i == 5) {
            throw new SmbException("NTLMv2 requires extended security (jcifs.smb.client.useExtendedSecurity must be true if jcifs.smb.lmCompatibility >= 3)");
        }
        return null;
    }

    public byte[] getUserSessionKey(byte[] bArr) {
        if (this.hashesExternal) {
            return null;
        }
        byte[] bArr2 = new byte[16];
        try {
            getUserSessionKey(bArr, bArr2, 0);
        } catch (Exception e) {
            if (LogStream.level > 0) {
                e.printStackTrace(log);
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getUserSessionKey(byte[] bArr, byte[] bArr2, int i) throws SmbException {
        if (this.hashesExternal) {
            return;
        }
        try {
            MD4 md4 = new MD4();
            md4.update(this.password.getBytes(SmbConstants.UNI_ENCODING));
            int i2 = LM_COMPATIBILITY;
            if (i2 == 0 || i2 == 1 || i2 == 2) {
                md4.update(md4.digest());
                md4.digest(bArr2, i, 16);
                return;
            }
            if (i2 == 3 || i2 == 4 || i2 == 5) {
                if (this.clientChallenge == null) {
                    byte[] bArr3 = new byte[8];
                    this.clientChallenge = bArr3;
                    RANDOM.nextBytes(bArr3);
                }
                HMACT64 hmact64 = new HMACT64(md4.digest());
                hmact64.update(this.username.toUpperCase().getBytes(SmbConstants.UNI_ENCODING));
                hmact64.update(this.domain.toUpperCase().getBytes(SmbConstants.UNI_ENCODING));
                byte[] digest = hmact64.digest();
                HMACT64 hmact642 = new HMACT64(digest);
                hmact642.update(bArr);
                hmact642.update(this.clientChallenge);
                HMACT64 hmact643 = new HMACT64(digest);
                hmact643.update(hmact642.digest());
                hmact643.digest(bArr2, i, 16);
                return;
            }
            md4.update(md4.digest());
            md4.digest(bArr2, i, 16);
        } catch (Exception e) {
            throw new SmbException("", e);
        }
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (obj instanceof NtlmPasswordAuthentication) {
            NtlmPasswordAuthentication ntlmPasswordAuthentication = (NtlmPasswordAuthentication) obj;
            if (ntlmPasswordAuthentication.domain.toUpperCase().equals(this.domain.toUpperCase()) && ntlmPasswordAuthentication.username.toUpperCase().equals(this.username.toUpperCase())) {
                boolean z = this.hashesExternal;
                if (z && ntlmPasswordAuthentication.hashesExternal) {
                    return Arrays.equals(this.ansiHash, ntlmPasswordAuthentication.ansiHash) && Arrays.equals(this.unicodeHash, ntlmPasswordAuthentication.unicodeHash);
                }
                if (!z && this.password.equals(ntlmPasswordAuthentication.password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.security.Principal
    public int hashCode() {
        return getName().toUpperCase().hashCode();
    }

    @Override // java.security.Principal
    public String toString() {
        return getName();
    }

    static String unescape(String str) throws NumberFormatException, UnsupportedEncodingException {
        byte[] bArr = new byte[1];
        if (str == null) {
            return null;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i < length) {
            if (!z) {
                char charAt = str.charAt(i);
                if (charAt == '%') {
                    z = true;
                } else {
                    cArr[i2] = charAt;
                    i2++;
                }
            } else if (z) {
                bArr[0] = (byte) (Integer.parseInt(str.substring(i, i + 2), 16) & 255);
                cArr[i2] = new String(bArr, 0, 1, "ASCII").charAt(0);
                i++;
                i2++;
                z = false;
            }
            i++;
        }
        return new String(cArr, 0, i2);
    }
}
