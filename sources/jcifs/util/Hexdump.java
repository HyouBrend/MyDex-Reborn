package jcifs.util;

/* loaded from: classes2.dex */
public class Hexdump {
    public static final char[] HEX_DIGITS;
    private static final String NL;
    private static final int NL_LENGTH;
    private static final char[] SPACE_CHARS;

    static {
        String property = System.getProperty("line.separator");
        NL = property;
        NL_LENGTH = property.length();
        SPACE_CHARS = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0088 A[LOOP:1: B:9:0x0026->B:18:0x0088, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void hexdump(java.io.PrintStream r11, byte[] r12, int r13, int r14) {
        /*
            if (r14 != 0) goto L3
            return
        L3:
            int r0 = r14 % 16
            int r1 = r14 / 16
            if (r0 != 0) goto La
            goto Lc
        La:
            int r1 = r1 + 1
        Lc:
            int r2 = jcifs.util.Hexdump.NL_LENGTH
            int r2 = r2 + 74
            int r1 = r1 * r2
            char[] r1 = new char[r1]
            r2 = 16
            char[] r3 = new char[r2]
            r4 = 0
            r5 = 0
            r6 = 0
        L1b:
            r7 = 5
            toHexChars(r5, r1, r6, r7)
            int r6 = r6 + r7
            int r7 = r6 + 1
            r8 = 58
            r1[r6] = r8
        L26:
            r6 = 32
            if (r5 != r14) goto L38
            int r8 = 16 - r0
            char[] r9 = jcifs.util.Hexdump.SPACE_CHARS
            int r10 = r8 * 3
            java.lang.System.arraycopy(r9, r4, r1, r7, r10)
            int r7 = r7 + r10
            java.lang.System.arraycopy(r9, r4, r3, r0, r8)
            goto L63
        L38:
            int r8 = r7 + 1
            r1[r7] = r6
            int r7 = r13 + r5
            r7 = r12[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r9 = 2
            toHexChars(r7, r1, r8, r9)
            int r8 = r8 + r9
            if (r7 < 0) goto L56
            char r7 = (char) r7
            boolean r9 = java.lang.Character.isISOControl(r7)
            if (r9 == 0) goto L51
            goto L56
        L51:
            int r9 = r5 % 16
            r3[r9] = r7
            goto L5c
        L56:
            int r7 = r5 % 16
            r9 = 46
            r3[r7] = r9
        L5c:
            int r5 = r5 + 1
            int r7 = r5 % 16
            if (r7 != 0) goto L88
            r7 = r8
        L63:
            int r8 = r7 + 1
            r1[r7] = r6
            int r7 = r8 + 1
            r1[r8] = r6
            int r6 = r7 + 1
            r8 = 124(0x7c, float:1.74E-43)
            r1[r7] = r8
            java.lang.System.arraycopy(r3, r4, r1, r6, r2)
            int r6 = r6 + r2
            int r7 = r6 + 1
            r1[r6] = r8
            java.lang.String r6 = jcifs.util.Hexdump.NL
            int r8 = jcifs.util.Hexdump.NL_LENGTH
            r6.getChars(r4, r8, r1, r7)
            int r6 = r7 + r8
            if (r5 < r14) goto L1b
            r11.println(r1)
            return
        L88:
            r7 = r8
            goto L26
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.util.Hexdump.hexdump(java.io.PrintStream, byte[], int, int):void");
    }

    public static String toHexString(int i, int i2) {
        char[] cArr = new char[i2];
        toHexChars(i, cArr, 0, i2);
        return new String(cArr);
    }

    public static String toHexString(long j, int i) {
        char[] cArr = new char[i];
        toHexChars(j, cArr, 0, i);
        return new String(cArr);
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2];
        int i3 = i2 % 2 == 0 ? i2 / 2 : (i2 / 2) + 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i4 + 1;
            char[] cArr2 = HEX_DIGITS;
            byte b = bArr[i5];
            cArr[i4] = cArr2[(b >> 4) & 15];
            if (i6 == i2) {
                break;
            }
            i4 = i6 + 1;
            cArr[i6] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static void toHexChars(int i, char[] cArr, int i2, int i3) {
        while (i3 > 0) {
            int i4 = (i2 + i3) - 1;
            if (i4 < cArr.length) {
                cArr[i4] = HEX_DIGITS[i & 15];
            }
            if (i != 0) {
                i >>>= 4;
            }
            i3--;
        }
    }

    public static void toHexChars(long j, char[] cArr, int i, int i2) {
        while (i2 > 0) {
            cArr[(i + i2) - 1] = HEX_DIGITS[(int) (15 & j)];
            if (j != 0) {
                j >>>= 4;
            }
            i2--;
        }
    }
}
