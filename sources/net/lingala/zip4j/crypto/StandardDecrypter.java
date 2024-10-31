package net.lingala.zip4j.crypto;

import kotlin.UByte;
import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: classes2.dex */
public class StandardDecrypter implements IDecrypter {
    private byte[] crc = new byte[4];
    private FileHeader fileHeader;
    private ZipCryptoEngine zipCryptoEngine;

    public StandardDecrypter(FileHeader fileHeader, byte[] bArr) throws ZipException {
        if (fileHeader == null) {
            throw new ZipException("one of more of the input parameters were null in StandardDecryptor");
        }
        this.fileHeader = fileHeader;
        this.zipCryptoEngine = new ZipCryptoEngine();
        init(bArr);
    }

    @Override // net.lingala.zip4j.crypto.IDecrypter
    public int decryptData(byte[] bArr) throws ZipException {
        return decryptData(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.crypto.IDecrypter
    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (i < 0 || i2 < 0) {
            throw new ZipException("one of the input parameters were null in standard decrpyt data");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                byte decryptByte = (byte) (((bArr[i3] & UByte.MAX_VALUE) ^ this.zipCryptoEngine.decryptByte()) & 255);
                this.zipCryptoEngine.updateKeys(decryptByte);
                bArr[i3] = decryptByte;
            } catch (Exception e) {
                throw new ZipException(e);
            }
        }
        return i2;
    }

    public void init(byte[] bArr) throws ZipException {
        byte[] crcBuff = this.fileHeader.getCrcBuff();
        byte[] bArr2 = this.crc;
        bArr2[3] = (byte) (crcBuff[3] & UByte.MAX_VALUE);
        byte b = crcBuff[3];
        byte b2 = (byte) ((b >> 8) & 255);
        bArr2[2] = b2;
        byte b3 = (byte) ((b >> 16) & 255);
        bArr2[1] = b3;
        byte b4 = (byte) ((b >> 24) & 255);
        int i = 0;
        bArr2[0] = b4;
        if (b2 > 0 || b3 > 0 || b4 > 0) {
            throw new IllegalStateException("Invalid CRC in File Header");
        }
        String password = this.fileHeader.getPassword();
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(password)) {
            throw new ZipException("Wrong password!", 5);
        }
        this.zipCryptoEngine.initKeys(password);
        try {
            byte b5 = bArr[0];
            while (i < 12) {
                ZipCryptoEngine zipCryptoEngine = this.zipCryptoEngine;
                zipCryptoEngine.updateKeys((byte) (zipCryptoEngine.decryptByte() ^ b5));
                i++;
                if (i != 12) {
                    b5 = bArr[i];
                }
            }
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }
}
