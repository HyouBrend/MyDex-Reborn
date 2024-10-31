package jcifs.ntlmssp;

import androidx.core.view.InputDeviceCompat;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.IOException;
import java.net.UnknownHostException;
import jcifs.Config;
import jcifs.netbios.NbtAddress;
import jcifs.smb.SmbConstants;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
public class Type2Message extends NtlmMessage {
    private static final String DEFAULT_DOMAIN;
    private static final int DEFAULT_FLAGS;
    private static final byte[] DEFAULT_TARGET_INFORMATION;
    private byte[] challenge;
    private byte[] context;
    private String target;
    private byte[] targetInformation;

    static {
        int i;
        DEFAULT_FLAGS = (Config.getBoolean("jcifs.smb.client.useUnicode", true) ? 1 : 2) | 512;
        String property = Config.getProperty("jcifs.smb.client.domain", null);
        DEFAULT_DOMAIN = property;
        byte[] bArr = new byte[0];
        if (property != null) {
            try {
                bArr = property.getBytes(SmbConstants.UNI_ENCODING);
            } catch (IOException unused) {
            }
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[0];
        try {
            String hostName = NbtAddress.getLocalHost().getHostName();
            if (hostName != null) {
                bArr2 = hostName.getBytes(SmbConstants.UNI_ENCODING);
            }
        } catch (UnknownHostException | IOException unused2) {
        }
        int length2 = bArr2.length;
        byte[] bArr3 = new byte[(length > 0 ? length + 4 : 0) + (length2 > 0 ? length2 + 4 : 0) + 4];
        if (length > 0) {
            writeUShort(bArr3, 0, 2);
            writeUShort(bArr3, 2, length);
            System.arraycopy(bArr, 0, bArr3, 4, length);
            i = 4 + length;
        } else {
            i = 0;
        }
        if (length2 > 0) {
            writeUShort(bArr3, i, 1);
            int i2 = i + 2;
            writeUShort(bArr3, i2, length2);
            System.arraycopy(bArr2, 0, bArr3, i2 + 2, length2);
        }
        DEFAULT_TARGET_INFORMATION = bArr3;
    }

    public Type2Message() {
        this(getDefaultFlags(), (byte[]) null, (String) null);
    }

    public Type2Message(Type1Message type1Message) {
        this(type1Message, (byte[]) null, (String) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Type2Message(jcifs.ntlmssp.Type1Message r3, byte[] r4, java.lang.String r5) {
        /*
            r2 = this;
            int r0 = getDefaultFlags(r3)
            if (r3 == 0) goto L13
            if (r5 != 0) goto L13
            r1 = 4
            boolean r3 = r3.getFlag(r1)
            if (r3 == 0) goto L13
            java.lang.String r5 = getDefaultDomain()
        L13:
            r2.<init>(r0, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.ntlmssp.Type2Message.<init>(jcifs.ntlmssp.Type1Message, byte[], java.lang.String):void");
    }

    public Type2Message(int i, byte[] bArr, String str) {
        setFlags(i);
        setChallenge(bArr);
        setTarget(str);
        if (str != null) {
            setTargetInformation(getDefaultTargetInformation());
        }
    }

    public Type2Message(byte[] bArr) throws IOException {
        parse(bArr);
    }

    public byte[] getChallenge() {
        return this.challenge;
    }

    public void setChallenge(byte[] bArr) {
        this.challenge = bArr;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public byte[] getTargetInformation() {
        return this.targetInformation;
    }

    public void setTargetInformation(byte[] bArr) {
        this.targetInformation = bArr;
    }

    public byte[] getContext() {
        return this.context;
    }

    public void setContext(byte[] bArr) {
        this.context = bArr;
    }

    @Override // jcifs.ntlmssp.NtlmMessage
    public byte[] toByteArray() {
        String oEMEncoding;
        try {
            String target = getTarget();
            byte[] challenge = getChallenge();
            byte[] context = getContext();
            byte[] targetInformation = getTargetInformation();
            int flags = getFlags();
            byte[] bArr = new byte[0];
            if ((flags & 4) != 0) {
                if (target == null || target.length() == 0) {
                    flags &= -5;
                } else {
                    if ((flags & 1) != 0) {
                        oEMEncoding = SmbConstants.UNI_ENCODING;
                    } else {
                        target = target.toUpperCase();
                        oEMEncoding = getOEMEncoding();
                    }
                    bArr = target.getBytes(oEMEncoding);
                }
            }
            if (targetInformation != null) {
                flags |= 8388608;
                if (context == null) {
                    context = new byte[8];
                }
            }
            int i = context != null ? 40 : 32;
            if (targetInformation != null) {
                i += 8;
            }
            byte[] bArr2 = new byte[bArr.length + i + (targetInformation != null ? targetInformation.length : 0)];
            System.arraycopy(NTLMSSP_SIGNATURE, 0, bArr2, 0, 8);
            writeULong(bArr2, 8, 2);
            writeSecurityBuffer(bArr2, 12, i, bArr);
            writeULong(bArr2, 20, flags);
            if (challenge == null) {
                challenge = new byte[8];
            }
            System.arraycopy(challenge, 0, bArr2, 24, 8);
            if (context != null) {
                System.arraycopy(context, 0, bArr2, 32, 8);
            }
            if (targetInformation != null) {
                writeSecurityBuffer(bArr2, 40, i + bArr.length, targetInformation);
            }
            return bArr2;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public String toString() {
        String str;
        String str2;
        String target = getTarget();
        byte[] challenge = getChallenge();
        byte[] context = getContext();
        byte[] targetInformation = getTargetInformation();
        StringBuilder sb = new StringBuilder();
        sb.append("Type2Message[target=");
        sb.append(target);
        sb.append(",challenge=");
        String str3 = "null";
        if (challenge == null) {
            str = "null";
        } else {
            str = SimpleComparison.LESS_THAN_OPERATION + challenge.length + " bytes>";
        }
        sb.append(str);
        sb.append(",context=");
        if (context == null) {
            str2 = "null";
        } else {
            str2 = SimpleComparison.LESS_THAN_OPERATION + context.length + " bytes>";
        }
        sb.append(str2);
        sb.append(",targetInformation=");
        if (targetInformation != null) {
            str3 = SimpleComparison.LESS_THAN_OPERATION + targetInformation.length + " bytes>";
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

    public static int getDefaultFlags(Type1Message type1Message) {
        if (type1Message == null) {
            return DEFAULT_FLAGS;
        }
        int flags = type1Message.getFlags();
        int i = ((flags & 1) != 0 ? 1 : 2) | 512;
        return ((flags & 4) == 0 || getDefaultDomain() == null) ? i : i | InputDeviceCompat.SOURCE_TRACKBALL;
    }

    public static String getDefaultDomain() {
        return DEFAULT_DOMAIN;
    }

    public static byte[] getDefaultTargetInformation() {
        return DEFAULT_TARGET_INFORMATION;
    }

    private void parse(byte[] bArr) throws IOException {
        for (int i = 0; i < 8; i++) {
            if (bArr[i] != NTLMSSP_SIGNATURE[i]) {
                throw new IOException("Not an NTLMSSP message.");
            }
        }
        if (readULong(bArr, 8) != 2) {
            throw new IOException("Not a Type 2 message.");
        }
        int readULong = readULong(bArr, 20);
        setFlags(readULong);
        String str = null;
        byte[] readSecurityBuffer = readSecurityBuffer(bArr, 12);
        if (readSecurityBuffer.length != 0) {
            str = new String(readSecurityBuffer, (readULong & 1) != 0 ? SmbConstants.UNI_ENCODING : getOEMEncoding());
        }
        setTarget(str);
        int i2 = 24;
        while (true) {
            if (i2 >= 32) {
                break;
            }
            if (bArr[i2] != 0) {
                byte[] bArr2 = new byte[8];
                System.arraycopy(bArr, 24, bArr2, 0, 8);
                setChallenge(bArr2);
                break;
            }
            i2++;
        }
        int readULong2 = readULong(bArr, 16);
        if (readULong2 == 32 || bArr.length == 32) {
            return;
        }
        int i3 = 32;
        while (true) {
            if (i3 >= 40) {
                break;
            }
            if (bArr[i3] != 0) {
                byte[] bArr3 = new byte[8];
                System.arraycopy(bArr, 32, bArr3, 0, 8);
                setContext(bArr3);
                break;
            }
            i3++;
        }
        if (readULong2 == 40 || bArr.length == 40) {
            return;
        }
        byte[] readSecurityBuffer2 = readSecurityBuffer(bArr, 40);
        if (readSecurityBuffer2.length != 0) {
            setTargetInformation(readSecurityBuffer2);
        }
    }
}
