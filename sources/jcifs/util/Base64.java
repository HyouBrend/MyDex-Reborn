package jcifs.util;

import com.j256.ormlite.stmt.query.SimpleComparison;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class Base64 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        if (length == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(((int) Math.ceil(length / 3.0d)) * 4);
        int i = length % 3;
        int i2 = length - i;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i3] & UByte.MAX_VALUE) << 16) | ((bArr[i4] & UByte.MAX_VALUE) << 8) | (bArr[i5] & UByte.MAX_VALUE);
            stringBuffer.append(ALPHABET.charAt(i6 >>> 18));
            stringBuffer.append(ALPHABET.charAt((i6 >>> 12) & 63));
            stringBuffer.append(ALPHABET.charAt((i6 >>> 6) & 63));
            stringBuffer.append(ALPHABET.charAt(i6 & 63));
            i3 = i5 + 1;
        }
        if (i == 0) {
            return stringBuffer.toString();
        }
        if (i == 1) {
            int i7 = (bArr[i3] & UByte.MAX_VALUE) << 4;
            stringBuffer.append(ALPHABET.charAt(i7 >>> 6));
            stringBuffer.append(ALPHABET.charAt(i7 & 63));
            stringBuffer.append("==");
            return stringBuffer.toString();
        }
        int i8 = ((bArr[i3 + 1] & UByte.MAX_VALUE) | ((bArr[i3] & UByte.MAX_VALUE) << 8)) << 2;
        stringBuffer.append(ALPHABET.charAt(i8 >>> 12));
        stringBuffer.append(ALPHABET.charAt((i8 >>> 6) & 63));
        stringBuffer.append(ALPHABET.charAt(i8 & 63));
        stringBuffer.append(SimpleComparison.EQUAL_TO_OPERATION);
        return stringBuffer.toString();
    }

    public static byte[] decode(String str) {
        int length = str.length();
        int i = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i2 = ((length * 3) / 4) - (str.charAt(length + (-2)) == '=' ? 2 : str.charAt(length + (-1)) == '=' ? 1 : 0);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i < length) {
            int i4 = i + 1;
            int i5 = i4 + 1;
            int indexOf = ((ALPHABET.indexOf(str.charAt(i)) & 255) << 18) | ((ALPHABET.indexOf(str.charAt(i4)) & 255) << 12);
            int i6 = i5 + 1;
            int indexOf2 = indexOf | ((ALPHABET.indexOf(str.charAt(i5)) & 255) << 6);
            int i7 = i6 + 1;
            int indexOf3 = indexOf2 | (ALPHABET.indexOf(str.charAt(i6)) & 255);
            int i8 = i3 + 1;
            bArr[i3] = (byte) (indexOf3 >>> 16);
            if (i8 < i2) {
                i3 = i8 + 1;
                bArr[i8] = (byte) ((indexOf3 >>> 8) & 255);
            } else {
                i3 = i8;
            }
            if (i3 < i2) {
                bArr[i3] = (byte) (indexOf3 & 255);
                i3++;
            }
            i = i7;
        }
        return bArr;
    }
}
