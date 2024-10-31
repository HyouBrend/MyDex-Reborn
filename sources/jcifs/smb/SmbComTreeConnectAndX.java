package jcifs.smb;

import java.io.UnsupportedEncodingException;
import jcifs.Config;
import jcifs.util.Hexdump;
import kotlin.UByte;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComTreeConnectAndX extends AndXServerMessageBlock {
    private static final boolean DISABLE_PLAIN_TEXT_PASSWORDS = Config.getBoolean("jcifs.smb.client.disablePlainTextPasswords", true);
    private static byte[] batchLimits = {1, 1, 1, 1, 1, 1, 1, 1, 0};
    private boolean disconnectTid;
    private byte[] password;
    private int passwordLength;
    String path;
    private String service;
    private SmbSession session;

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

    static {
        String property = Config.getProperty("jcifs.smb.client.TreeConnectAndX.CheckDirectory");
        if (property != null) {
            batchLimits[0] = Byte.parseByte(property);
        }
        String property2 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.CreateDirectory");
        if (property2 != null) {
            batchLimits[2] = Byte.parseByte(property2);
        }
        String property3 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.Delete");
        if (property3 != null) {
            batchLimits[3] = Byte.parseByte(property3);
        }
        String property4 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.DeleteDirectory");
        if (property4 != null) {
            batchLimits[4] = Byte.parseByte(property4);
        }
        String property5 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.OpenAndX");
        if (property5 != null) {
            batchLimits[5] = Byte.parseByte(property5);
        }
        String property6 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.Rename");
        if (property6 != null) {
            batchLimits[6] = Byte.parseByte(property6);
        }
        String property7 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.Transaction");
        if (property7 != null) {
            batchLimits[7] = Byte.parseByte(property7);
        }
        String property8 = Config.getProperty("jcifs.smb.client.TreeConnectAndX.QueryInformation");
        if (property8 != null) {
            batchLimits[8] = Byte.parseByte(property8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComTreeConnectAndX(SmbSession smbSession, String str, String str2, ServerMessageBlock serverMessageBlock) {
        super(serverMessageBlock);
        this.disconnectTid = false;
        this.session = smbSession;
        this.path = str;
        this.service = str2;
        this.command = (byte) 117;
    }

    @Override // jcifs.smb.AndXServerMessageBlock
    int getBatchLimit(byte b) {
        int i = b & UByte.MAX_VALUE;
        if (i == 0) {
            return batchLimits[2];
        }
        if (i == 1) {
            return batchLimits[4];
        }
        if (i == 6) {
            return batchLimits[3];
        }
        if (i == 7) {
            return batchLimits[6];
        }
        if (i == 8) {
            return batchLimits[8];
        }
        if (i == 16) {
            return batchLimits[0];
        }
        if (i == 37) {
            return batchLimits[7];
        }
        if (i != 45) {
            return 0;
        }
        return batchLimits[5];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        if (this.session.transport.server.security == 0 && (this.session.auth.hashesExternal || this.session.auth.password.length() > 0)) {
            if (this.session.transport.server.encryptedPasswords) {
                byte[] ansiHash = this.session.auth.getAnsiHash(this.session.transport.server.encryptionKey);
                this.password = ansiHash;
                this.passwordLength = ansiHash.length;
            } else {
                if (DISABLE_PLAIN_TEXT_PASSWORDS) {
                    throw new RuntimeException("Plain text passwords are disabled");
                }
                this.password = new byte[(this.session.auth.password.length() + 1) * 2];
                this.passwordLength = writeString(this.session.auth.password, this.password, 0);
            }
        } else {
            this.passwordLength = 1;
        }
        int i2 = i + 1;
        bArr[i] = this.disconnectTid;
        bArr[i2] = 0;
        writeInt2(this.passwordLength, bArr, i2 + 1);
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int i2;
        if (this.session.transport.server.security == 0 && (this.session.auth.hashesExternal || this.session.auth.password.length() > 0)) {
            System.arraycopy(this.password, 0, bArr, i, this.passwordLength);
            i2 = this.passwordLength + i;
        } else {
            i2 = i + 1;
            bArr[i] = 0;
        }
        int writeString = i2 + writeString(this.path, bArr, i2);
        try {
            System.arraycopy(this.service.getBytes("ASCII"), 0, bArr, writeString, this.service.length());
            int length = writeString + this.service.length();
            bArr[length] = 0;
            return (length + 1) - i;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("SmbComTreeConnectAndX[" + super.toString() + ",disconnectTid=" + this.disconnectTid + ",passwordLength=" + this.passwordLength + ",password=" + Hexdump.toHexString(this.password, this.passwordLength, 0) + ",path=" + this.path + ",service=" + this.service + "]");
    }
}
