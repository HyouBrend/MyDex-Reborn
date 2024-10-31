package jcifs.util;

import java.security.MessageDigest;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MD4 extends MessageDigest implements Cloneable {
    private static final int BLOCK_LENGTH = 64;
    private int[] X;
    private byte[] buffer;
    private int[] context;
    private long count;

    private int FF(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i + (((~i2) & i4) | (i3 & i2)) + i5;
        return (i7 >>> (32 - i6)) | (i7 << i6);
    }

    private int GG(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i + ((i2 & (i3 | i4)) | (i3 & i4)) + i5 + 1518500249;
        return (i7 >>> (32 - i6)) | (i7 << i6);
    }

    private int HH(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i + ((i2 ^ i3) ^ i4) + i5 + 1859775393;
        return (i7 >>> (32 - i6)) | (i7 << i6);
    }

    public MD4() {
        super("MD4");
        this.context = new int[4];
        this.buffer = new byte[64];
        this.X = new int[16];
        engineReset();
    }

    private MD4(MD4 md4) {
        this();
        this.context = (int[]) md4.context.clone();
        this.buffer = (byte[]) md4.buffer.clone();
        this.count = md4.count;
    }

    @Override // java.security.MessageDigest, java.security.MessageDigestSpi
    public Object clone() {
        return new MD4(this);
    }

    @Override // java.security.MessageDigestSpi
    public void engineReset() {
        int[] iArr = this.context;
        iArr[0] = 1732584193;
        iArr[1] = -271733879;
        iArr[2] = -1732584194;
        iArr[3] = 271733878;
        this.count = 0L;
        for (int i = 0; i < 64; i++) {
            this.buffer[i] = 0;
        }
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte b) {
        long j = this.count;
        int i = (int) (j % 64);
        this.count = j + 1;
        byte[] bArr = this.buffer;
        bArr[i] = b;
        if (i == 63) {
            transform(bArr, 0);
        }
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte[] bArr, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            long j = i2;
            if (i + j <= bArr.length) {
                long j2 = this.count;
                int i3 = (int) (j2 % 64);
                this.count = j2 + j;
                int i4 = 64 - i3;
                int i5 = 0;
                if (i2 >= i4) {
                    System.arraycopy(bArr, i, this.buffer, i3, i4);
                    transform(this.buffer, 0);
                    while (true) {
                        int i6 = i4 + 64;
                        if (i6 - 1 >= i2) {
                            break;
                        }
                        transform(bArr, i4 + i);
                        i4 = i6;
                    }
                    i5 = i4;
                    i3 = 0;
                }
                if (i5 < i2) {
                    System.arraycopy(bArr, i + i5, this.buffer, i3, i2 - i5);
                    return;
                }
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override // java.security.MessageDigestSpi
    public byte[] engineDigest() {
        int i = (int) (this.count % 64);
        int i2 = i < 56 ? 56 - i : 120 - i;
        int i3 = i2 + 8;
        byte[] bArr = new byte[i3];
        bArr[0] = ByteCompanionObject.MIN_VALUE;
        for (int i4 = 0; i4 < 8; i4++) {
            bArr[i2 + i4] = (byte) ((this.count * 8) >>> (i4 * 8));
        }
        engineUpdate(bArr, 0, i3);
        byte[] bArr2 = new byte[16];
        for (int i5 = 0; i5 < 4; i5++) {
            for (int i6 = 0; i6 < 4; i6++) {
                bArr2[(i5 * 4) + i6] = (byte) (this.context[i5] >>> (i6 * 8));
            }
        }
        engineReset();
        return bArr2;
    }

    private void transform(byte[] bArr, int i) {
        int i2 = i;
        int i3 = 0;
        while (i3 < 16) {
            int[] iArr = this.X;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = (bArr[i2] & UByte.MAX_VALUE) | ((bArr[i4] & UByte.MAX_VALUE) << 8);
            int i7 = i5 + 1;
            iArr[i3] = i6 | ((bArr[i5] & UByte.MAX_VALUE) << 16) | ((bArr[i7] & UByte.MAX_VALUE) << 24);
            i3++;
            i2 = i7 + 1;
        }
        int[] iArr2 = this.context;
        int i8 = iArr2[0];
        int i9 = iArr2[1];
        int i10 = iArr2[2];
        int i11 = iArr2[3];
        int FF = FF(i8, i9, i10, i11, this.X[0], 3);
        int FF2 = FF(i11, FF, i9, i10, this.X[1], 7);
        int FF3 = FF(i10, FF2, FF, i9, this.X[2], 11);
        int FF4 = FF(i9, FF3, FF2, FF, this.X[3], 19);
        int FF5 = FF(FF, FF4, FF3, FF2, this.X[4], 3);
        int FF6 = FF(FF2, FF5, FF4, FF3, this.X[5], 7);
        int FF7 = FF(FF3, FF6, FF5, FF4, this.X[6], 11);
        int FF8 = FF(FF4, FF7, FF6, FF5, this.X[7], 19);
        int FF9 = FF(FF5, FF8, FF7, FF6, this.X[8], 3);
        int FF10 = FF(FF6, FF9, FF8, FF7, this.X[9], 7);
        int FF11 = FF(FF7, FF10, FF9, FF8, this.X[10], 11);
        int FF12 = FF(FF8, FF11, FF10, FF9, this.X[11], 19);
        int FF13 = FF(FF9, FF12, FF11, FF10, this.X[12], 3);
        int FF14 = FF(FF10, FF13, FF12, FF11, this.X[13], 7);
        int FF15 = FF(FF11, FF14, FF13, FF12, this.X[14], 11);
        int FF16 = FF(FF12, FF15, FF14, FF13, this.X[15], 19);
        int GG = GG(FF13, FF16, FF15, FF14, this.X[0], 3);
        int GG2 = GG(FF14, GG, FF16, FF15, this.X[4], 5);
        int GG3 = GG(FF15, GG2, GG, FF16, this.X[8], 9);
        int GG4 = GG(FF16, GG3, GG2, GG, this.X[12], 13);
        int GG5 = GG(GG, GG4, GG3, GG2, this.X[1], 3);
        int GG6 = GG(GG2, GG5, GG4, GG3, this.X[5], 5);
        int GG7 = GG(GG3, GG6, GG5, GG4, this.X[9], 9);
        int GG8 = GG(GG4, GG7, GG6, GG5, this.X[13], 13);
        int GG9 = GG(GG5, GG8, GG7, GG6, this.X[2], 3);
        int GG10 = GG(GG6, GG9, GG8, GG7, this.X[6], 5);
        int GG11 = GG(GG7, GG10, GG9, GG8, this.X[10], 9);
        int GG12 = GG(GG8, GG11, GG10, GG9, this.X[14], 13);
        int GG13 = GG(GG9, GG12, GG11, GG10, this.X[3], 3);
        int GG14 = GG(GG10, GG13, GG12, GG11, this.X[7], 5);
        int GG15 = GG(GG11, GG14, GG13, GG12, this.X[11], 9);
        int GG16 = GG(GG12, GG15, GG14, GG13, this.X[15], 13);
        int HH = HH(GG13, GG16, GG15, GG14, this.X[0], 3);
        int HH2 = HH(GG14, HH, GG16, GG15, this.X[8], 9);
        int HH3 = HH(GG15, HH2, HH, GG16, this.X[4], 11);
        int HH4 = HH(GG16, HH3, HH2, HH, this.X[12], 15);
        int HH5 = HH(HH, HH4, HH3, HH2, this.X[2], 3);
        int HH6 = HH(HH2, HH5, HH4, HH3, this.X[10], 9);
        int HH7 = HH(HH3, HH6, HH5, HH4, this.X[6], 11);
        int HH8 = HH(HH4, HH7, HH6, HH5, this.X[14], 15);
        int HH9 = HH(HH5, HH8, HH7, HH6, this.X[1], 3);
        int HH10 = HH(HH6, HH9, HH8, HH7, this.X[9], 9);
        int HH11 = HH(HH7, HH10, HH9, HH8, this.X[5], 11);
        int HH12 = HH(HH8, HH11, HH10, HH9, this.X[13], 15);
        int HH13 = HH(HH9, HH12, HH11, HH10, this.X[3], 3);
        int HH14 = HH(HH10, HH13, HH12, HH11, this.X[11], 9);
        int HH15 = HH(HH11, HH14, HH13, HH12, this.X[7], 11);
        int HH16 = HH(HH12, HH15, HH14, HH13, this.X[15], 15);
        int[] iArr3 = this.context;
        iArr3[0] = iArr3[0] + HH13;
        iArr3[1] = iArr3[1] + HH16;
        iArr3[2] = iArr3[2] + HH15;
        iArr3[3] = iArr3[3] + HH14;
    }
}
