package jcifs.smb;

import jcifs.Config;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SmbComSessionSetupAndX extends AndXServerMessageBlock {
    private static final int BATCH_LIMIT = Config.getInt("jcifs.smb.client.SessionSetupAndX.TreeConnectAndX", 1);
    private static final boolean DISABLE_PLAIN_TEXT_PASSWORDS = Config.getBoolean("jcifs.smb.client.disablePlainTextPasswords", true);
    private String accountName;
    private byte[] blob;
    private int capabilities;
    Object cred;
    private byte[] lmHash;
    private byte[] ntHash;
    private String primaryDomain;
    SmbSession session;
    private int sessionKey;

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
    public SmbComSessionSetupAndX(SmbSession smbSession, ServerMessageBlock serverMessageBlock, Object obj) throws SmbException {
        super(serverMessageBlock);
        this.blob = null;
        this.command = (byte) 115;
        this.session = smbSession;
        this.cred = obj;
        this.sessionKey = smbSession.transport.sessionKey;
        this.capabilities = smbSession.transport.capabilities;
        if (smbSession.transport.server.security == 1) {
            if (obj instanceof NtlmPasswordAuthentication) {
                NtlmPasswordAuthentication ntlmPasswordAuthentication = (NtlmPasswordAuthentication) obj;
                if (ntlmPasswordAuthentication == NtlmPasswordAuthentication.ANONYMOUS) {
                    this.lmHash = new byte[0];
                    this.ntHash = new byte[0];
                    this.capabilities &= Integer.MAX_VALUE;
                } else if (smbSession.transport.server.encryptedPasswords) {
                    this.lmHash = ntlmPasswordAuthentication.getAnsiHash(smbSession.transport.server.encryptionKey);
                    byte[] unicodeHash = ntlmPasswordAuthentication.getUnicodeHash(smbSession.transport.server.encryptionKey);
                    this.ntHash = unicodeHash;
                    if (this.lmHash.length == 0 && unicodeHash.length == 0) {
                        throw new RuntimeException("Null setup prohibited.");
                    }
                } else {
                    if (DISABLE_PLAIN_TEXT_PASSWORDS) {
                        throw new RuntimeException("Plain text passwords are disabled");
                    }
                    if (this.useUnicode) {
                        String password = ntlmPasswordAuthentication.getPassword();
                        this.lmHash = new byte[0];
                        byte[] bArr = new byte[(password.length() + 1) * 2];
                        this.ntHash = bArr;
                        writeString(password, bArr, 0);
                    } else {
                        String password2 = ntlmPasswordAuthentication.getPassword();
                        byte[] bArr2 = new byte[(password2.length() + 1) * 2];
                        this.lmHash = bArr2;
                        this.ntHash = new byte[0];
                        writeString(password2, bArr2, 0);
                    }
                }
                this.accountName = ntlmPasswordAuthentication.username;
                if (this.useUnicode) {
                    this.accountName = this.accountName.toUpperCase();
                }
                this.primaryDomain = ntlmPasswordAuthentication.domain.toUpperCase();
                return;
            }
            if (obj instanceof byte[]) {
                this.blob = (byte[]) obj;
                return;
            }
            throw new SmbException("Unsupported credential type");
        }
        if (smbSession.transport.server.security == 0) {
            if (obj instanceof NtlmPasswordAuthentication) {
                NtlmPasswordAuthentication ntlmPasswordAuthentication2 = (NtlmPasswordAuthentication) obj;
                this.lmHash = new byte[0];
                this.ntHash = new byte[0];
                this.accountName = ntlmPasswordAuthentication2.username;
                if (this.useUnicode) {
                    this.accountName = this.accountName.toUpperCase();
                }
                this.primaryDomain = ntlmPasswordAuthentication2.domain.toUpperCase();
                return;
            }
            throw new SmbException("Unsupported credential type");
        }
        throw new SmbException("Unsupported");
    }

    @Override // jcifs.smb.AndXServerMessageBlock
    int getBatchLimit(byte b) {
        if (b == 117) {
            return BATCH_LIMIT;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        writeInt2(this.session.transport.snd_buf_size, bArr, i);
        int i2 = i + 2;
        writeInt2(this.session.transport.maxMpxCount, bArr, i2);
        int i3 = i2 + 2;
        SmbTransport smbTransport = this.session.transport;
        writeInt2(1L, bArr, i3);
        int i4 = i3 + 2;
        writeInt4(this.sessionKey, bArr, i4);
        int i5 = i4 + 4;
        if (this.blob != null) {
            writeInt2(r1.length, bArr, i5);
        } else {
            writeInt2(this.lmHash.length, bArr, i5);
            i5 += 2;
            writeInt2(this.ntHash.length, bArr, i5);
        }
        int i6 = i5 + 2;
        int i7 = i6 + 1;
        bArr[i6] = 0;
        int i8 = i7 + 1;
        bArr[i7] = 0;
        int i9 = i8 + 1;
        bArr[i8] = 0;
        int i10 = i9 + 1;
        bArr[i9] = 0;
        writeInt4(this.capabilities, bArr, i10);
        return (i10 + 4) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        int writeString;
        byte[] bArr2 = this.blob;
        if (bArr2 != null) {
            System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
            writeString = this.blob.length + i;
        } else {
            byte[] bArr3 = this.lmHash;
            System.arraycopy(bArr3, 0, bArr, i, bArr3.length);
            int length = this.lmHash.length + i;
            byte[] bArr4 = this.ntHash;
            System.arraycopy(bArr4, 0, bArr, length, bArr4.length);
            int length2 = length + this.ntHash.length;
            int writeString2 = length2 + writeString(this.accountName, bArr, length2);
            writeString = writeString2 + writeString(this.primaryDomain, bArr, writeString2);
        }
        SmbTransport smbTransport = this.session.transport;
        int writeString3 = writeString + writeString(SmbTransport.NATIVE_OS, bArr, writeString);
        SmbTransport smbTransport2 = this.session.transport;
        return (writeString3 + writeString(SmbTransport.NATIVE_LANMAN, bArr, writeString3)) - i;
    }

    @Override // jcifs.smb.AndXServerMessageBlock, jcifs.smb.ServerMessageBlock
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmbComSessionSetupAndX[");
        sb.append(super.toString());
        sb.append(",snd_buf_size=");
        sb.append(this.session.transport.snd_buf_size);
        sb.append(",maxMpxCount=");
        sb.append(this.session.transport.maxMpxCount);
        sb.append(",VC_NUMBER=");
        SmbTransport smbTransport = this.session.transport;
        sb.append(1);
        sb.append(",sessionKey=");
        sb.append(this.sessionKey);
        sb.append(",lmHash.length=");
        byte[] bArr = this.lmHash;
        sb.append(bArr == null ? 0 : bArr.length);
        sb.append(",ntHash.length=");
        byte[] bArr2 = this.ntHash;
        sb.append(bArr2 != null ? bArr2.length : 0);
        sb.append(",capabilities=");
        sb.append(this.capabilities);
        sb.append(",accountName=");
        sb.append(this.accountName);
        sb.append(",primaryDomain=");
        sb.append(this.primaryDomain);
        sb.append(",NATIVE_OS=");
        SmbTransport smbTransport2 = this.session.transport;
        sb.append(SmbTransport.NATIVE_OS);
        sb.append(",NATIVE_LANMAN=");
        SmbTransport smbTransport3 = this.session.transport;
        sb.append(SmbTransport.NATIVE_LANMAN);
        sb.append("]");
        return new String(sb.toString());
    }
}
