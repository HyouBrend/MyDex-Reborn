package net.lingala.zip4j.crypto;

import java.util.Random;
import kotlin.UByte;
import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: classes2.dex */
public class StandardEncrypter implements IEncrypter {
    private byte[] headerBytes;
    private ZipCryptoEngine zipCryptoEngine;

    public StandardEncrypter(String str, int i) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("input password is null or empty in standard encrpyter constructor");
        }
        this.zipCryptoEngine = new ZipCryptoEngine();
        this.headerBytes = new byte[12];
        init(str, i);
    }

    private void init(String str, int i) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("input password is null or empty, cannot initialize standard encrypter");
        }
        this.zipCryptoEngine.initKeys(str);
        byte[] generateRandomBytes = generateRandomBytes(12);
        this.headerBytes = generateRandomBytes;
        generateRandomBytes[11] = (byte) (i >> 24);
        if (generateRandomBytes.length < 12) {
            throw new ZipException("invalid header bytes generated, cannot perform standard encryption");
        }
        int i2 = 0;
        while (true) {
            byte[] bArr = this.headerBytes;
            if (i2 >= bArr.length) {
                return;
            }
            int i3 = bArr[i2] & UByte.MAX_VALUE;
            bArr[i2] = (byte) (this.zipCryptoEngine.decryptByte() ^ i3);
            this.zipCryptoEngine.updateKeys((byte) i3);
            i2++;
        }
    }

    @Override // net.lingala.zip4j.crypto.IEncrypter
    public int encryptData(byte[] bArr) throws ZipException {
        bArr.getClass();
        return encryptData(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.crypto.IEncrypter
    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 < 0) {
            throw new ZipException("invalid length specified to decrpyt data");
        }
        while (i < i2) {
            try {
                int i3 = bArr[i] & UByte.MAX_VALUE;
                bArr[i] = (byte) ((this.zipCryptoEngine.decryptByte() & UByte.MAX_VALUE) ^ i3);
                this.zipCryptoEngine.updateKeys((byte) i3);
                i++;
            } catch (Exception e) {
                throw new ZipException(e);
            }
        }
        return i2;
    }

    protected static byte[] generateRandomBytes(int i) throws ZipException {
        if (i <= 0) {
            throw new ZipException("size is either 0 or less than 0, cannot generate header for standard encryptor");
        }
        byte[] bArr = new byte[i];
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) random.nextInt(256);
        }
        return bArr;
    }

    public byte[] getHeaderBytes() {
        return this.headerBytes;
    }
}
