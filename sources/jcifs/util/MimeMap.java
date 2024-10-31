package jcifs.util;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class MimeMap {
    private static final int IN_SIZE = 7000;
    private static final int ST_COMM = 2;
    private static final int ST_EXT = 5;
    private static final int ST_GAP = 4;
    private static final int ST_START = 1;
    private static final int ST_TYPE = 3;
    private byte[] in = new byte[IN_SIZE];
    private int inLen;

    public MimeMap() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("jcifs/util/mime.map");
        this.inLen = 0;
        while (true) {
            byte[] bArr = this.in;
            int i = this.inLen;
            int read = resourceAsStream.read(bArr, i, 7000 - i);
            if (read == -1) {
                break;
            } else {
                this.inLen += read;
            }
        }
        int i2 = this.inLen;
        if (i2 < 100 || i2 == IN_SIZE) {
            throw new IOException("Error reading jcifs/util/mime.map resource");
        }
        resourceAsStream.close();
    }

    public String getMimeType(String str) throws IOException {
        return getMimeType(str, "application/octet-stream");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getMimeType(java.lang.String r17, java.lang.String r18) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = 128(0x80, float:1.8E-43)
            byte[] r1 = new byte[r1]
            r2 = 16
            byte[] r2 = new byte[r2]
            java.lang.String r3 = r17.toLowerCase()
            java.lang.String r4 = "ASCII"
            byte[] r3 = r3.getBytes(r4)
            r6 = 1
            r7 = 0
            r8 = 1
            r9 = 0
            r10 = 0
        L19:
            int r11 = r0.inLen
            if (r7 >= r11) goto L98
            byte[] r11 = r0.in
            r11 = r11[r7]
            r12 = 4
            r13 = 3
            r15 = 2
            r5 = 9
            r14 = 32
            if (r8 == r6) goto L7b
            r6 = 10
            if (r8 == r15) goto L74
            if (r8 == r13) goto L88
            r13 = 5
            if (r8 == r12) goto L37
            if (r8 == r13) goto L3e
            goto L94
        L37:
            if (r11 == r14) goto L94
            if (r11 != r5) goto L3d
            goto L94
        L3d:
            r8 = 5
        L3e:
            if (r11 == r5) goto L4e
            if (r11 == r6) goto L4e
            if (r11 == r14) goto L4e
            r5 = 35
            if (r11 == r5) goto L4e
            int r5 = r9 + 1
            r2[r9] = r11
            r9 = r5
            goto L94
        L4e:
            r5 = 0
        L4f:
            if (r5 >= r9) goto L5d
            int r12 = r3.length
            if (r9 != r12) goto L5d
            r12 = r2[r5]
            r13 = r3[r5]
            if (r12 != r13) goto L5d
            int r5 = r5 + 1
            goto L4f
        L5d:
            int r9 = r3.length
            if (r5 != r9) goto L67
            java.lang.String r2 = new java.lang.String
            r13 = 0
            r2.<init>(r1, r13, r10, r4)
            return r2
        L67:
            r5 = 35
            r13 = 0
            if (r11 != r5) goto L6e
            r8 = 2
            goto L72
        L6e:
            if (r11 != r6) goto L72
            r8 = 1
            r10 = 0
        L72:
            r9 = 0
            goto L94
        L74:
            r13 = 0
            if (r11 != r6) goto L94
            r8 = 1
            r9 = 0
            r10 = 0
            goto L94
        L7b:
            r6 = 0
            if (r11 == r14) goto L94
            if (r11 != r5) goto L81
            goto L94
        L81:
            r6 = 35
            if (r11 != r6) goto L87
            r8 = 2
            goto L94
        L87:
            r8 = 3
        L88:
            if (r11 == r14) goto L93
            if (r11 != r5) goto L8d
            goto L93
        L8d:
            int r5 = r10 + 1
            r1[r10] = r11
            r10 = r5
            goto L94
        L93:
            r8 = 4
        L94:
            int r7 = r7 + 1
            r6 = 1
            goto L19
        L98:
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.util.MimeMap.getMimeType(java.lang.String, java.lang.String):java.lang.String");
    }
}
