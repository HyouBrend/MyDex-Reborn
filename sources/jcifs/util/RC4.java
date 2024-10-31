package jcifs.util;

/* loaded from: classes2.dex */
public class RC4 {
    int i;
    int j;
    byte[] s;

    public RC4() {
    }

    public RC4(byte[] bArr) {
        init(bArr, 0, bArr.length);
    }

    public void init(byte[] bArr, int i, int i2) {
        this.s = new byte[256];
        this.i = 0;
        while (true) {
            int i3 = this.i;
            if (i3 >= 256) {
                break;
            }
            this.s[i3] = (byte) i3;
            this.i = i3 + 1;
        }
        this.j = 0;
        this.i = 0;
        while (true) {
            int i4 = this.i;
            if (i4 < 256) {
                int i5 = this.j + bArr[(i4 % i2) + i];
                byte[] bArr2 = this.s;
                byte b = bArr2[i4];
                int i6 = (i5 + b) & 255;
                this.j = i6;
                bArr2[i4] = bArr2[i6];
                bArr2[i6] = b;
                this.i = i4 + 1;
            } else {
                this.j = 0;
                this.i = 0;
                return;
            }
        }
    }

    public void update(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2 + i;
        while (i < i4) {
            int i5 = (this.i + 1) & 255;
            this.i = i5;
            int i6 = this.j;
            byte[] bArr3 = this.s;
            byte b = bArr3[i5];
            int i7 = (i6 + b) & 255;
            this.j = i7;
            bArr3[i5] = bArr3[i7];
            bArr3[i7] = b;
            bArr2[i3] = (byte) (bArr[i] ^ bArr3[(bArr3[i5] + b) & 255]);
            i3++;
            i++;
        }
    }
}
