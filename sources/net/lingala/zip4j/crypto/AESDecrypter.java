package net.lingala.zip4j.crypto;

import java.util.ArrayList;
import java.util.Arrays;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;
import org.apache.commons.net.ftp.FTP;

/* loaded from: classes2.dex */
public class AESDecrypter implements IDecrypter {
    private int KEY_LENGTH;
    private int MAC_LENGTH;
    private int SALT_LENGTH;
    private AESEngine aesEngine;
    private byte[] aesKey;
    private byte[] derivedPasswordVerifier;
    private LocalFileHeader localFileHeader;
    private MacBasedPRF mac;
    private byte[] macKey;
    private byte[] storedMac;
    private final int PASSWORD_VERIFIER_LENGTH = 2;
    private int nonce = 1;

    public int getPasswordVerifierLength() {
        return 2;
    }

    public AESDecrypter(LocalFileHeader localFileHeader, byte[] bArr, byte[] bArr2) throws ZipException {
        if (localFileHeader == null) {
            throw new ZipException("one of the input parameters is null in AESDecryptor Constructor");
        }
        this.localFileHeader = localFileHeader;
        this.storedMac = null;
        init(bArr, bArr2);
    }

    private void init(byte[] bArr, byte[] bArr2) throws ZipException {
        LocalFileHeader localFileHeader = this.localFileHeader;
        if (localFileHeader == null) {
            throw new ZipException("invalid file header in init method of AESDecryptor");
        }
        AESExtraDataRecord aesExtraDataRecord = localFileHeader.getAesExtraDataRecord();
        if (aesExtraDataRecord == null) {
            throw new ZipException("invalid aes extra data record - in init method of AESDecryptor");
        }
        int aesStrength = aesExtraDataRecord.getAesStrength();
        if (aesStrength == 1) {
            this.KEY_LENGTH = 16;
            this.MAC_LENGTH = 16;
            this.SALT_LENGTH = 8;
        } else if (aesStrength == 2) {
            this.KEY_LENGTH = 24;
            this.MAC_LENGTH = 24;
            this.SALT_LENGTH = 12;
        } else if (aesStrength == 3) {
            this.KEY_LENGTH = 32;
            this.MAC_LENGTH = 32;
            this.SALT_LENGTH = 16;
        } else {
            throw new ZipException("invalid aes key strength for file: " + this.localFileHeader.getFileName());
        }
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(this.localFileHeader.getPassword())) {
            throw new ZipException("empty or null password provided for AES Decryptor");
        }
        byte[] deriveKey = deriveKey(bArr, this.localFileHeader.getPassword());
        if (deriveKey != null) {
            int length = deriveKey.length;
            int i = this.KEY_LENGTH;
            int i2 = this.MAC_LENGTH;
            if (length == i + i2 + 2) {
                byte[] bArr3 = new byte[i];
                this.aesKey = bArr3;
                this.macKey = new byte[i2];
                this.derivedPasswordVerifier = new byte[2];
                System.arraycopy(deriveKey, 0, bArr3, 0, i);
                System.arraycopy(deriveKey, this.KEY_LENGTH, this.macKey, 0, this.MAC_LENGTH);
                System.arraycopy(deriveKey, this.KEY_LENGTH + this.MAC_LENGTH, this.derivedPasswordVerifier, 0, 2);
                byte[] bArr4 = this.derivedPasswordVerifier;
                if (bArr4 == null) {
                    throw new ZipException("invalid derived password verifier for AES");
                }
                if (!Arrays.equals(bArr2, bArr4)) {
                    throw new ZipException("Wrong Password for file: " + this.localFileHeader.getFileName());
                }
                this.aesEngine = new AESEngine(this.aesKey);
                MacBasedPRF macBasedPRF = new MacBasedPRF("HmacSHA1");
                this.mac = macBasedPRF;
                macBasedPRF.init(this.macKey);
                return;
            }
        }
        throw new ZipException("invalid derived key");
    }

    @Override // net.lingala.zip4j.crypto.IDecrypter
    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (this.aesEngine == null) {
            throw new ZipException("AES not initialized properly");
        }
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i3 = 0;
            while (i3 < i2) {
                int i4 = i3 + 16;
                byte[] bArr2 = i4 > i2 ? new byte[i2 - i3] : new byte[16];
                System.arraycopy(bArr, i3, bArr2, 0, bArr2.length);
                arrayList.add(bArr2);
                i3 = i4;
            }
            byte[] bArr3 = new byte[16];
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                byte[] bArr4 = (byte[]) arrayList.get(i5);
                byte[] bArr5 = new byte[bArr4.length];
                this.mac.update(bArr4);
                this.aesEngine.processBlock(Raw.toByteArray(this.nonce, 16), bArr3);
                for (int i6 = 0; i6 < bArr4.length; i6++) {
                    bArr5[i6] = (byte) (bArr4[i6] ^ bArr3[i6]);
                }
                arrayList2.add(bArr5);
                this.nonce++;
            }
            int i7 = 0;
            for (int i8 = 0; i8 < arrayList2.size(); i8++) {
                System.arraycopy(arrayList2.get(i8), 0, bArr, i7, ((byte[]) arrayList2.get(i8)).length);
                i7 += ((byte[]) arrayList2.get(i8)).length;
            }
            return i2;
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException(e2);
        }
    }

    @Override // net.lingala.zip4j.crypto.IDecrypter
    public int decryptData(byte[] bArr) throws ZipException {
        return decryptData(bArr, 0, bArr.length);
    }

    private byte[] deriveKey(byte[] bArr, String str) throws ZipException {
        try {
            return new PBKDF2Engine(new PBKDF2Parameters("HmacSHA1", FTP.DEFAULT_CONTROL_ENCODING, bArr, 1000)).deriveKey(str, this.KEY_LENGTH + this.MAC_LENGTH + 2);
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    public int getSaltLength() {
        return this.SALT_LENGTH;
    }

    public byte[] getCalculatedAuthenticationBytes() {
        return this.mac.doFinal();
    }

    public void setStoredMac(byte[] bArr) {
        this.storedMac = bArr;
    }

    public byte[] getStoredMac() {
        return this.storedMac;
    }
}
