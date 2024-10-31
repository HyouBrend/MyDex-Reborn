package jcifs.smb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class SigningDigest implements SmbConstants {
    static LogStream log = LogStream.getInstance();
    private boolean bypass;
    private MessageDigest digest;
    private byte[] macSigningKey;
    private int signSequence;
    private int updates;

    public SigningDigest(byte[] bArr, boolean z) throws SmbException {
        this.bypass = false;
        try {
            this.digest = MessageDigest.getInstance("MD5");
            this.macSigningKey = bArr;
            this.bypass = z;
            this.updates = 0;
            this.signSequence = 0;
            if (LogStream.level >= 5) {
                log.println("macSigningKey:");
                Hexdump.hexdump(log, bArr, 0, bArr.length);
            }
        } catch (NoSuchAlgorithmException e) {
            if (LogStream.level > 0) {
                e.printStackTrace(log);
            }
            throw new SmbException("MD5", e);
        }
    }

    public SigningDigest(SmbTransport smbTransport, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbException {
        this.bypass = false;
        try {
            this.digest = MessageDigest.getInstance("MD5");
            try {
                int i = LM_COMPATIBILITY;
                if (i == 0 || i == 1 || i == 2) {
                    this.macSigningKey = new byte[40];
                    ntlmPasswordAuthentication.getUserSessionKey(smbTransport.server.encryptionKey, this.macSigningKey, 0);
                    System.arraycopy(ntlmPasswordAuthentication.getUnicodeHash(smbTransport.server.encryptionKey), 0, this.macSigningKey, 16, 24);
                } else if (i == 3 || i == 4 || i == 5) {
                    this.macSigningKey = new byte[16];
                    ntlmPasswordAuthentication.getUserSessionKey(smbTransport.server.encryptionKey, this.macSigningKey, 0);
                } else {
                    this.macSigningKey = new byte[40];
                    ntlmPasswordAuthentication.getUserSessionKey(smbTransport.server.encryptionKey, this.macSigningKey, 0);
                    System.arraycopy(ntlmPasswordAuthentication.getUnicodeHash(smbTransport.server.encryptionKey), 0, this.macSigningKey, 16, 24);
                }
                if (LogStream.level >= 5) {
                    log.println("LM_COMPATIBILITY=" + LM_COMPATIBILITY);
                    LogStream logStream = log;
                    byte[] bArr = this.macSigningKey;
                    Hexdump.hexdump(logStream, bArr, 0, bArr.length);
                }
            } catch (Exception e) {
                throw new SmbException("", e);
            }
        } catch (NoSuchAlgorithmException e2) {
            if (LogStream.level > 0) {
                e2.printStackTrace(log);
            }
            throw new SmbException("MD5", e2);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        if (LogStream.level >= 5) {
            log.println("update: " + this.updates + " " + i + ":" + i2);
            Hexdump.hexdump(log, bArr, i, Math.min(i2, 256));
            log.flush();
        }
        if (i2 == 0) {
            return;
        }
        this.digest.update(bArr, i, i2);
        this.updates++;
    }

    public byte[] digest() {
        byte[] digest = this.digest.digest();
        if (LogStream.level >= 5) {
            log.println("digest: ");
            Hexdump.hexdump(log, digest, 0, digest.length);
            log.flush();
        }
        this.updates = 0;
        return digest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sign(byte[] bArr, int i, int i2, ServerMessageBlock serverMessageBlock, ServerMessageBlock serverMessageBlock2) {
        serverMessageBlock.signSeq = this.signSequence;
        if (serverMessageBlock2 != null) {
            serverMessageBlock2.signSeq = this.signSequence + 1;
            serverMessageBlock2.verifyFailed = false;
        }
        try {
            try {
                byte[] bArr2 = this.macSigningKey;
                update(bArr2, 0, bArr2.length);
                int i3 = i + 14;
                for (int i4 = 0; i4 < 8; i4++) {
                    bArr[i3 + i4] = 0;
                }
                ServerMessageBlock.writeInt4(this.signSequence, bArr, i3);
                update(bArr, i, i2);
                System.arraycopy(digest(), 0, bArr, i3, 8);
                if (this.bypass) {
                    this.bypass = false;
                    System.arraycopy("BSRSPYL ".getBytes(), 0, bArr, i3, 8);
                }
            } catch (Exception e) {
                if (LogStream.level > 0) {
                    e.printStackTrace(log);
                }
            }
        } finally {
            this.signSequence += 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean verify(byte[] bArr, int i, ServerMessageBlock serverMessageBlock) {
        byte[] bArr2 = this.macSigningKey;
        update(bArr2, 0, bArr2.length);
        update(bArr, i, 14);
        int i2 = i + 14;
        byte[] bArr3 = new byte[8];
        ServerMessageBlock.writeInt4(serverMessageBlock.signSeq, bArr3, 0);
        update(bArr3, 0, 8);
        int i3 = i2 + 8;
        if (serverMessageBlock.command == 46) {
            SmbComReadAndXResponse smbComReadAndXResponse = (SmbComReadAndXResponse) serverMessageBlock;
            update(bArr, i3, ((serverMessageBlock.length - smbComReadAndXResponse.dataLength) - 14) - 8);
            update(smbComReadAndXResponse.b, smbComReadAndXResponse.off, smbComReadAndXResponse.dataLength);
        } else {
            update(bArr, i3, (serverMessageBlock.length - 14) - 8);
        }
        byte[] digest = digest();
        for (int i4 = 0; i4 < 8; i4++) {
            if (digest[i4] != bArr[i2 + i4]) {
                if (LogStream.level >= 2) {
                    log.println("signature verification failure");
                    Hexdump.hexdump(log, digest, 0, 8);
                    Hexdump.hexdump(log, bArr, i2, 8);
                }
                serverMessageBlock.verifyFailed = true;
                return true;
            }
        }
        serverMessageBlock.verifyFailed = false;
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LM_COMPATIBILITY=");
        sb.append(LM_COMPATIBILITY);
        sb.append(" MacSigningKey=");
        byte[] bArr = this.macSigningKey;
        sb.append(Hexdump.toHexString(bArr, 0, bArr.length));
        return sb.toString();
    }
}
