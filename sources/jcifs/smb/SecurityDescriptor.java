package jcifs.smb;

import java.io.IOException;

/* loaded from: classes2.dex */
public class SecurityDescriptor {
    public ACE[] aces;
    public int type;

    public SecurityDescriptor() {
    }

    public SecurityDescriptor(byte[] bArr, int i, int i2) throws IOException {
        decode(bArr, i, i2);
    }

    public int decode(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + 1 + 1;
        this.type = ServerMessageBlock.readInt2(bArr, i3);
        int i4 = i3 + 2;
        ServerMessageBlock.readInt4(bArr, i4);
        int i5 = i4 + 4;
        ServerMessageBlock.readInt4(bArr, i5);
        int i6 = i5 + 4;
        ServerMessageBlock.readInt4(bArr, i6);
        int readInt4 = ServerMessageBlock.readInt4(bArr, i6 + 4);
        int i7 = i + readInt4 + 1 + 1;
        ServerMessageBlock.readInt2(bArr, i7);
        int i8 = i7 + 2;
        int readInt42 = ServerMessageBlock.readInt4(bArr, i8);
        int i9 = i8 + 4;
        if (readInt42 > 4096) {
            throw new IOException("Invalid SecurityDescriptor");
        }
        if (readInt4 != 0) {
            this.aces = new ACE[readInt42];
            for (int i10 = 0; i10 < readInt42; i10++) {
                this.aces[i10] = new ACE();
                i9 += this.aces[i10].decode(bArr, i9);
            }
        } else {
            this.aces = null;
        }
        return i9 - i;
    }

    public String toString() {
        String str = "SecurityDescriptor:\n";
        if (this.aces != null) {
            for (int i = 0; i < this.aces.length; i++) {
                str = str + this.aces[i].toString() + "\n";
            }
            return str;
        }
        return "SecurityDescriptor:\nNULL";
    }
}
