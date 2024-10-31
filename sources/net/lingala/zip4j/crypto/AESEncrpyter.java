package net.lingala.zip4j.crypto;

import java.util.ArrayList;
import java.util.Random;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;
import org.apache.commons.net.ftp.FTP;

/* loaded from: classes2.dex */
public class AESEncrpyter implements IEncrypter {
    private int KEY_LENGTH;
    private int MAC_LENGTH;
    private int SALT_LENGTH;
    private AESEngine aesEngine;
    private byte[] aesKey;
    private byte[] derivedPasswordVerifier;
    private boolean finished;
    private int keyStrength;
    private MacBasedPRF mac;
    private byte[] macKey;
    private String password;
    private byte[] saltBytes;
    private final int PASSWORD_VERIFIER_LENGTH = 2;
    private int nonce = 1;
    private byte[] iv = new byte[16];
    private byte[] counterBlock = new byte[16];
    private byte[] aesSplitBlock = new byte[16];

    public int getPasswordVeriifierLength() {
        return 2;
    }

    public AESEncrpyter(String str, int i) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("input password is empty or null in AES encrypter constructor");
        }
        if (i != 1 && i != 3) {
            throw new ZipException("Invalid key strength in AES encrypter constructor");
        }
        this.password = str;
        this.keyStrength = i;
        this.finished = false;
        init();
    }

    private void init() throws ZipException {
        int i = this.keyStrength;
        if (i == 1) {
            this.KEY_LENGTH = 16;
            this.MAC_LENGTH = 16;
            this.SALT_LENGTH = 8;
        } else if (i == 3) {
            this.KEY_LENGTH = 32;
            this.MAC_LENGTH = 32;
            this.SALT_LENGTH = 16;
        } else {
            throw new ZipException("invalid aes key strength, cannot determine key sizes");
        }
        byte[] generateSalt = generateSalt(this.SALT_LENGTH);
        this.saltBytes = generateSalt;
        byte[] deriveKey = deriveKey(generateSalt, this.password);
        if (deriveKey != null) {
            int length = deriveKey.length;
            int i2 = this.KEY_LENGTH;
            int i3 = this.MAC_LENGTH;
            if (length == i2 + i3 + 2) {
                byte[] bArr = new byte[i2];
                this.aesKey = bArr;
                this.macKey = new byte[i3];
                this.derivedPasswordVerifier = new byte[2];
                System.arraycopy(deriveKey, 0, bArr, 0, i2);
                System.arraycopy(deriveKey, this.KEY_LENGTH, this.macKey, 0, this.MAC_LENGTH);
                System.arraycopy(deriveKey, this.KEY_LENGTH + this.MAC_LENGTH, this.derivedPasswordVerifier, 0, 2);
                this.aesEngine = new AESEngine(this.aesKey);
                MacBasedPRF macBasedPRF = new MacBasedPRF("HmacSHA1");
                this.mac = macBasedPRF;
                macBasedPRF.init(this.macKey);
                return;
            }
        }
        throw new ZipException("invalid key generated, cannot decrypt file");
    }

    private byte[] deriveKey(byte[] bArr, String str) throws ZipException {
        try {
            return new PBKDF2Engine(new PBKDF2Parameters("HmacSHA1", FTP.DEFAULT_CONTROL_ENCODING, bArr, 1000)).deriveKey(str, this.KEY_LENGTH + this.MAC_LENGTH + 2);
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    @Override // net.lingala.zip4j.crypto.IEncrypter
    public int encryptData(byte[] bArr) throws ZipException {
        if (bArr == null) {
            throw new ZipException("input bytes are null, cannot perform AES encrpytion");
        }
        return encryptData(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.crypto.IEncrypter
    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (this.finished) {
            throw new ZipException("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
        if (i2 % 16 != 0) {
            this.finished = true;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 16;
            if (i4 > i2) {
                int i5 = i2 - i3;
                byte[] bArr2 = new byte[i5];
                System.arraycopy(bArr, i3 + i, bArr2, 0, i5);
                arrayList.add(bArr2);
            } else {
                byte[] bArr3 = this.aesSplitBlock;
                System.arraycopy(bArr, i3 + i, bArr3, 0, bArr3.length);
                arrayList.add(this.aesSplitBlock.clone());
            }
            i3 = i4;
        }
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            byte[] bArr4 = (byte[]) arrayList.get(i6);
            byte[] bArr5 = new byte[bArr4.length];
            byte[] byteArray = Raw.toByteArray(this.nonce, 16);
            this.iv = byteArray;
            this.aesEngine.processBlock(byteArray, this.counterBlock);
            for (int i7 = 0; i7 < bArr4.length; i7++) {
                bArr5[i7] = (byte) (bArr4[i7] ^ this.counterBlock[i7]);
            }
            arrayList2.add(bArr5);
            this.mac.update(bArr5);
            this.nonce++;
        }
        for (int i8 = 0; i8 < arrayList2.size(); i8++) {
            System.arraycopy(arrayList2.get(i8), 0, bArr, i, ((byte[]) arrayList2.get(i8)).length);
            i += ((byte[]) arrayList2.get(i8)).length;
        }
        return i2;
    }

    private static byte[] generateSalt(int i) throws ZipException {
        if (i != 8 && i != 16) {
            throw new ZipException("invalid salt size, cannot generate salt");
        }
        int i2 = i == 8 ? 2 : 0;
        if (i == 16) {
            i2 = 4;
        }
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = new Random().nextInt();
            int i4 = i3 * 4;
            bArr[i4 + 0] = (byte) (nextInt >> 24);
            bArr[i4 + 1] = (byte) (nextInt >> 16);
            bArr[i4 + 2] = (byte) (nextInt >> 8);
            bArr[i4 + 3] = (byte) nextInt;
        }
        return bArr;
    }

    public byte[] getFinalMac() {
        byte[] bArr = new byte[10];
        System.arraycopy(this.mac.doFinal(), 0, bArr, 0, 10);
        return bArr;
    }

    public byte[] getDerivedPasswordVerifier() {
        return this.derivedPasswordVerifier;
    }

    public void setDerivedPasswordVerifier(byte[] bArr) {
        this.derivedPasswordVerifier = bArr;
    }

    public byte[] getSaltBytes() {
        return this.saltBytes;
    }

    public void setSaltBytes(byte[] bArr) {
        this.saltBytes = bArr;
    }

    public int getSaltLength() {
        return this.SALT_LENGTH;
    }
}
