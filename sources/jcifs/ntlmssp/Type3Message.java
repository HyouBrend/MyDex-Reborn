package jcifs.ntlmssp;

import androidx.work.WorkRequest;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Arrays;
import jcifs.Config;
import jcifs.netbios.NbtAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbConstants;
import jcifs.util.HMACT64;
import jcifs.util.Hexdump;
import jcifs.util.MD4;
import jcifs.util.RC4;
import org.apache.commons.net.bsd.RCommandClient;

/* loaded from: classes2.dex */
public class Type3Message extends NtlmMessage {
    private static final String DEFAULT_DOMAIN;
    private static final int DEFAULT_FLAGS;
    private static final String DEFAULT_PASSWORD;
    private static final String DEFAULT_USER;
    private static final String DEFAULT_WORKSTATION;
    private static final int LM_COMPATIBILITY;
    static final long MILLISECONDS_BETWEEN_1970_AND_1601 = 11644473600000L;
    private static final SecureRandom RANDOM = new SecureRandom();
    private String domain;
    private byte[] lmResponse;
    private byte[] masterKey;
    private byte[] ntResponse;
    private byte[] sessionKey;
    private String user;
    private String workstation;

    static {
        DEFAULT_FLAGS = (Config.getBoolean("jcifs.smb.client.useUnicode", true) ? 1 : 2) | 512;
        String str = null;
        DEFAULT_DOMAIN = Config.getProperty("jcifs.smb.client.domain", null);
        DEFAULT_USER = Config.getProperty("jcifs.smb.client.username", null);
        DEFAULT_PASSWORD = Config.getProperty("jcifs.smb.client.password", null);
        try {
            str = NbtAddress.getLocalHost().getHostName();
        } catch (UnknownHostException unused) {
        }
        DEFAULT_WORKSTATION = str;
        LM_COMPATIBILITY = Config.getInt("jcifs.smb.lmCompatibility", 3);
    }

    public Type3Message() {
        this.masterKey = null;
        this.sessionKey = null;
        setFlags(getDefaultFlags());
        setDomain(getDefaultDomain());
        setUser(getDefaultUser());
        setWorkstation(getDefaultWorkstation());
    }

    public Type3Message(Type2Message type2Message) {
        this.masterKey = null;
        this.sessionKey = null;
        setFlags(getDefaultFlags(type2Message));
        setWorkstation(getDefaultWorkstation());
        String defaultDomain = getDefaultDomain();
        setDomain(defaultDomain);
        String defaultUser = getDefaultUser();
        setUser(defaultUser);
        String defaultPassword = getDefaultPassword();
        int i = LM_COMPATIBILITY;
        if (i == 0 || i == 1) {
            setLMResponse(getLMResponse(type2Message, defaultPassword));
            setNTResponse(getNTResponse(type2Message, defaultPassword));
            return;
        }
        if (i == 2) {
            byte[] nTResponse = getNTResponse(type2Message, defaultPassword);
            setLMResponse(nTResponse);
            setNTResponse(nTResponse);
        } else if (i == 3 || i == 4 || i == 5) {
            byte[] bArr = new byte[8];
            RANDOM.nextBytes(bArr);
            setLMResponse(getLMv2Response(type2Message, defaultDomain, defaultUser, defaultPassword, bArr));
        } else {
            setLMResponse(getLMResponse(type2Message, defaultPassword));
            setNTResponse(getNTResponse(type2Message, defaultPassword));
        }
    }

    public Type3Message(Type2Message type2Message, String str, String str2, String str3, String str4, int i) {
        this.masterKey = null;
        this.sessionKey = null;
        setFlags(i | getDefaultFlags(type2Message));
        setWorkstation(str4 == null ? getDefaultWorkstation() : str4);
        setDomain(str2);
        setUser(str3);
        int i2 = LM_COMPATIBILITY;
        if (i2 == 0 || i2 == 1) {
            if ((getFlags() & 524288) == 0) {
                setLMResponse(getLMResponse(type2Message, str));
                setNTResponse(getNTResponse(type2Message, str));
                return;
            }
            byte[] bArr = new byte[24];
            SecureRandom secureRandom = RANDOM;
            secureRandom.nextBytes(bArr);
            Arrays.fill(bArr, 8, 24, (byte) 0);
            byte[] nTOWFv1 = NtlmPasswordAuthentication.nTOWFv1(str);
            byte[] nTLM2Response = NtlmPasswordAuthentication.getNTLM2Response(nTOWFv1, type2Message.getChallenge(), bArr);
            setLMResponse(bArr);
            setNTResponse(nTLM2Response);
            if ((getFlags() & 16) == 16) {
                byte[] bArr2 = new byte[16];
                System.arraycopy(type2Message.getChallenge(), 0, bArr2, 0, 8);
                System.arraycopy(bArr, 0, bArr2, 8, 8);
                MD4 md4 = new MD4();
                md4.update(nTOWFv1);
                HMACT64 hmact64 = new HMACT64(md4.digest());
                hmact64.update(bArr2);
                byte[] digest = hmact64.digest();
                if ((getFlags() & 1073741824) != 0) {
                    byte[] bArr3 = new byte[16];
                    this.masterKey = bArr3;
                    secureRandom.nextBytes(bArr3);
                    byte[] bArr4 = new byte[16];
                    new RC4(digest).update(this.masterKey, 0, 16, bArr4, 0);
                    setSessionKey(bArr4);
                    return;
                }
                this.masterKey = digest;
                setSessionKey(digest);
                return;
            }
            return;
        }
        if (i2 == 2) {
            byte[] nTResponse = getNTResponse(type2Message, str);
            setLMResponse(nTResponse);
            setNTResponse(nTResponse);
            return;
        }
        if (i2 == 3 || i2 == 4 || i2 == 5) {
            byte[] nTOWFv2 = NtlmPasswordAuthentication.nTOWFv2(str2, str3, str);
            byte[] bArr5 = new byte[8];
            SecureRandom secureRandom2 = RANDOM;
            secureRandom2.nextBytes(bArr5);
            setLMResponse(getLMv2Response(type2Message, str2, str3, str, bArr5));
            byte[] bArr6 = new byte[8];
            secureRandom2.nextBytes(bArr6);
            setNTResponse(getNTLMv2Response(type2Message, nTOWFv2, bArr6));
            if ((getFlags() & 16) == 16) {
                HMACT64 hmact642 = new HMACT64(nTOWFv2);
                hmact642.update(this.ntResponse, 0, 16);
                byte[] digest2 = hmact642.digest();
                if ((getFlags() & 1073741824) != 0) {
                    byte[] bArr7 = new byte[16];
                    this.masterKey = bArr7;
                    secureRandom2.nextBytes(bArr7);
                    byte[] bArr8 = new byte[16];
                    new RC4(digest2).update(this.masterKey, 0, 16, bArr8, 0);
                    setSessionKey(bArr8);
                    return;
                }
                this.masterKey = digest2;
                setSessionKey(digest2);
                return;
            }
            return;
        }
        setLMResponse(getLMResponse(type2Message, str));
        setNTResponse(getNTResponse(type2Message, str));
    }

    public Type3Message(int i, byte[] bArr, byte[] bArr2, String str, String str2, String str3) {
        this.masterKey = null;
        this.sessionKey = null;
        setFlags(i);
        setLMResponse(bArr);
        setNTResponse(bArr2);
        setDomain(str);
        setUser(str2);
        setWorkstation(str3);
    }

    public Type3Message(byte[] bArr) throws IOException {
        this.masterKey = null;
        this.sessionKey = null;
        parse(bArr);
    }

    public byte[] getLMResponse() {
        return this.lmResponse;
    }

    public void setLMResponse(byte[] bArr) {
        this.lmResponse = bArr;
    }

    public byte[] getNTResponse() {
        return this.ntResponse;
    }

    public void setNTResponse(byte[] bArr) {
        this.ntResponse = bArr;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public void setWorkstation(String str) {
        this.workstation = str;
    }

    public byte[] getMasterKey() {
        return this.masterKey;
    }

    public byte[] getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(byte[] bArr) {
        this.sessionKey = bArr;
    }

    @Override // jcifs.ntlmssp.NtlmMessage
    public byte[] toByteArray() {
        byte[] bArr;
        byte[] bArr2;
        try {
            int flags = getFlags();
            boolean z = (flags & 1) != 0;
            byte[] bArr3 = null;
            String oEMEncoding = z ? null : getOEMEncoding();
            String domain = getDomain();
            if (domain == null || domain.length() == 0) {
                bArr = null;
            } else {
                bArr = z ? domain.getBytes(SmbConstants.UNI_ENCODING) : domain.getBytes(oEMEncoding);
            }
            int length = bArr != null ? bArr.length : 0;
            String user = getUser();
            if (user == null || user.length() == 0) {
                bArr2 = null;
            } else {
                bArr2 = z ? user.getBytes(SmbConstants.UNI_ENCODING) : user.toUpperCase().getBytes(oEMEncoding);
            }
            int length2 = bArr2 != null ? bArr2.length : 0;
            String workstation = getWorkstation();
            if (workstation != null && workstation.length() != 0) {
                bArr3 = z ? workstation.getBytes(SmbConstants.UNI_ENCODING) : workstation.toUpperCase().getBytes(oEMEncoding);
            }
            int length3 = bArr3 != null ? bArr3.length : 0;
            byte[] lMResponse = getLMResponse();
            int length4 = lMResponse != null ? lMResponse.length : 0;
            byte[] nTResponse = getNTResponse();
            int length5 = nTResponse != null ? nTResponse.length : 0;
            byte[] sessionKey = getSessionKey();
            byte[] bArr4 = new byte[length + 64 + length2 + length3 + length4 + length5 + (sessionKey != null ? sessionKey.length : 0)];
            System.arraycopy(NTLMSSP_SIGNATURE, 0, bArr4, 0, 8);
            writeULong(bArr4, 8, 3);
            writeSecurityBuffer(bArr4, 12, 64, lMResponse);
            int i = length4 + 64;
            writeSecurityBuffer(bArr4, 20, i, nTResponse);
            int i2 = i + length5;
            writeSecurityBuffer(bArr4, 28, i2, bArr);
            int i3 = i2 + length;
            writeSecurityBuffer(bArr4, 36, i3, bArr2);
            int i4 = i3 + length2;
            writeSecurityBuffer(bArr4, 44, i4, bArr3);
            writeSecurityBuffer(bArr4, 52, i4 + length3, sessionKey);
            writeULong(bArr4, 60, flags);
            return bArr4;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public String toString() {
        String str;
        String str2;
        String user = getUser();
        String domain = getDomain();
        String workstation = getWorkstation();
        byte[] lMResponse = getLMResponse();
        byte[] nTResponse = getNTResponse();
        byte[] sessionKey = getSessionKey();
        StringBuilder sb = new StringBuilder();
        sb.append("Type3Message[domain=");
        sb.append(domain);
        sb.append(",user=");
        sb.append(user);
        sb.append(",workstation=");
        sb.append(workstation);
        sb.append(",lmResponse=");
        String str3 = "null";
        if (lMResponse == null) {
            str = "null";
        } else {
            str = SimpleComparison.LESS_THAN_OPERATION + lMResponse.length + " bytes>";
        }
        sb.append(str);
        sb.append(",ntResponse=");
        if (nTResponse == null) {
            str2 = "null";
        } else {
            str2 = SimpleComparison.LESS_THAN_OPERATION + nTResponse.length + " bytes>";
        }
        sb.append(str2);
        sb.append(",sessionKey=");
        if (sessionKey != null) {
            str3 = SimpleComparison.LESS_THAN_OPERATION + sessionKey.length + " bytes>";
        }
        sb.append(str3);
        sb.append(",flags=0x");
        sb.append(Hexdump.toHexString(getFlags(), 8));
        sb.append("]");
        return sb.toString();
    }

    public static int getDefaultFlags() {
        return DEFAULT_FLAGS;
    }

    public static int getDefaultFlags(Type2Message type2Message) {
        if (type2Message == null) {
            return DEFAULT_FLAGS;
        }
        return ((type2Message.getFlags() & 1) == 0 ? 2 : 1) | 512;
    }

    public static byte[] getLMResponse(Type2Message type2Message, String str) {
        if (type2Message == null || str == null) {
            return null;
        }
        return NtlmPasswordAuthentication.getPreNTLMResponse(str, type2Message.getChallenge());
    }

    public static byte[] getLMv2Response(Type2Message type2Message, String str, String str2, String str3, byte[] bArr) {
        if (type2Message == null || str == null || str2 == null || str3 == null || bArr == null) {
            return null;
        }
        return NtlmPasswordAuthentication.getLMv2Response(str, str2, str3, type2Message.getChallenge(), bArr);
    }

    public static byte[] getNTLMv2Response(Type2Message type2Message, byte[] bArr, byte[] bArr2) {
        if (type2Message == null || bArr == null || bArr2 == null) {
            return null;
        }
        return NtlmPasswordAuthentication.getNTLMv2Response(bArr, type2Message.getChallenge(), bArr2, (System.currentTimeMillis() + 11644473600000L) * WorkRequest.MIN_BACKOFF_MILLIS, type2Message.getTargetInformation());
    }

    public static byte[] getNTResponse(Type2Message type2Message, String str) {
        if (type2Message == null || str == null) {
            return null;
        }
        return NtlmPasswordAuthentication.getNTLMResponse(str, type2Message.getChallenge());
    }

    public static String getDefaultDomain() {
        return DEFAULT_DOMAIN;
    }

    public static String getDefaultUser() {
        return DEFAULT_USER;
    }

    public static String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }

    public static String getDefaultWorkstation() {
        return DEFAULT_WORKSTATION;
    }

    private void parse(byte[] bArr) throws IOException {
        int i;
        String oEMEncoding;
        for (int i2 = 0; i2 < 8; i2++) {
            if (bArr[i2] != NTLMSSP_SIGNATURE[i2]) {
                throw new IOException("Not an NTLMSSP message.");
            }
        }
        if (readULong(bArr, 8) != 3) {
            throw new IOException("Not a Type 3 message.");
        }
        byte[] readSecurityBuffer = readSecurityBuffer(bArr, 12);
        int readULong = readULong(bArr, 16);
        byte[] readSecurityBuffer2 = readSecurityBuffer(bArr, 20);
        int readULong2 = readULong(bArr, 24);
        byte[] readSecurityBuffer3 = readSecurityBuffer(bArr, 28);
        int readULong3 = readULong(bArr, 32);
        byte[] readSecurityBuffer4 = readSecurityBuffer(bArr, 36);
        int readULong4 = readULong(bArr, 40);
        byte[] readSecurityBuffer5 = readSecurityBuffer(bArr, 44);
        int readULong5 = readULong(bArr, 48);
        byte[] bArr2 = null;
        if (readULong == 52 || readULong2 == 52 || readULong3 == 52 || readULong4 == 52 || readULong5 == 52) {
            i = RCommandClient.DEFAULT_PORT;
            oEMEncoding = getOEMEncoding();
        } else {
            bArr2 = readSecurityBuffer(bArr, 52);
            i = readULong(bArr, 60);
            oEMEncoding = (i & 1) != 0 ? SmbConstants.UNI_ENCODING : getOEMEncoding();
        }
        setSessionKey(bArr2);
        setFlags(i);
        setLMResponse(readSecurityBuffer);
        setNTResponse(readSecurityBuffer2);
        setDomain(new String(readSecurityBuffer3, oEMEncoding));
        setUser(new String(readSecurityBuffer4, oEMEncoding));
        setWorkstation(new String(readSecurityBuffer5, oEMEncoding));
    }
}
