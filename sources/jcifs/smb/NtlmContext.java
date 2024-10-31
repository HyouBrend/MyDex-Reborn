package jcifs.smb;

import java.io.UnsupportedEncodingException;
import jcifs.ntlmssp.Type1Message;
import jcifs.ntlmssp.Type2Message;
import jcifs.ntlmssp.Type3Message;
import jcifs.util.Encdec;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class NtlmContext {
    NtlmPasswordAuthentication auth;
    LogStream log;
    int ntlmsspFlags;
    String workstation;
    boolean isEstablished = false;
    byte[] serverChallenge = null;
    byte[] signingKey = null;
    String netbiosName = null;
    int state = 1;

    public NtlmContext(NtlmPasswordAuthentication ntlmPasswordAuthentication, boolean z) {
        this.auth = ntlmPasswordAuthentication;
        int i = this.ntlmsspFlags | 4 | 524288 | 536870912;
        this.ntlmsspFlags = i;
        if (z) {
            this.ntlmsspFlags = i | 1073774608;
        }
        this.workstation = Type1Message.getDefaultWorkstation();
        this.log = LogStream.getInstance();
    }

    public String toString() {
        String sb;
        String sb2;
        String str = "NtlmContext[auth=" + this.auth + ",ntlmsspFlags=0x" + Hexdump.toHexString(this.ntlmsspFlags, 8) + ",workstation=" + this.workstation + ",isEstablished=" + this.isEstablished + ",state=" + this.state + ",serverChallenge=";
        if (this.serverChallenge == null) {
            sb = str + "null";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            byte[] bArr = this.serverChallenge;
            sb3.append(Hexdump.toHexString(bArr, 0, bArr.length * 2));
            sb = sb3.toString();
        }
        String str2 = sb + ",signingKey=";
        if (this.signingKey == null) {
            sb2 = str2 + "null";
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            byte[] bArr2 = this.signingKey;
            sb4.append(Hexdump.toHexString(bArr2, 0, bArr2.length * 2));
            sb2 = sb4.toString();
        }
        return sb2 + "]";
    }

    public boolean isEstablished() {
        return this.isEstablished;
    }

    public byte[] getServerChallenge() {
        return this.serverChallenge;
    }

    public byte[] getSigningKey() {
        return this.signingKey;
    }

    public String getNetbiosName() {
        return this.netbiosName;
    }

    private String getNtlmsspListItem(byte[] bArr, int i) {
        int i2;
        int i3 = 58;
        while (true) {
            short dec_uint16le = Encdec.dec_uint16le(bArr, i3);
            short dec_uint16le2 = Encdec.dec_uint16le(bArr, i3 + 2);
            int i4 = i3 + 4;
            if (dec_uint16le == 0 || (i2 = i4 + dec_uint16le2) > bArr.length) {
                return null;
            }
            if (dec_uint16le == i) {
                try {
                    return new String(bArr, i4, dec_uint16le2, SmbConstants.UNI_ENCODING);
                } catch (UnsupportedEncodingException unused) {
                    return null;
                }
            }
            i3 = i2;
        }
    }

    public byte[] initSecContext(byte[] bArr, int i, int i2) throws SmbException {
        byte[] byteArray;
        int i3 = this.state;
        if (i3 == 1) {
            Type1Message type1Message = new Type1Message(this.ntlmsspFlags, this.auth.getDomain(), this.workstation);
            byteArray = type1Message.toByteArray();
            if (LogStream.level >= 4) {
                this.log.println(type1Message);
                if (LogStream.level >= 6) {
                    Hexdump.hexdump(this.log, byteArray, 0, byteArray.length);
                }
            }
            this.state++;
        } else if (i3 == 2) {
            try {
                Type2Message type2Message = new Type2Message(bArr);
                if (LogStream.level >= 4) {
                    this.log.println(type2Message);
                    if (LogStream.level >= 6) {
                        Hexdump.hexdump(this.log, bArr, 0, bArr.length);
                    }
                }
                this.serverChallenge = type2Message.getChallenge();
                this.ntlmsspFlags &= type2Message.getFlags();
                Type3Message type3Message = new Type3Message(type2Message, this.auth.getPassword(), this.auth.getDomain(), this.auth.getUsername(), this.workstation, this.ntlmsspFlags);
                byteArray = type3Message.toByteArray();
                if (LogStream.level >= 4) {
                    this.log.println(type3Message);
                    if (LogStream.level >= 6) {
                        Hexdump.hexdump(this.log, byteArray, 0, byteArray.length);
                    }
                }
                if ((this.ntlmsspFlags & 16) != 0) {
                    this.signingKey = type3Message.getMasterKey();
                }
                this.isEstablished = true;
                this.state++;
            } catch (Exception e) {
                throw new SmbException(e.getMessage(), e);
            }
        } else {
            throw new SmbException("Invalid state");
        }
        return byteArray;
    }
}
