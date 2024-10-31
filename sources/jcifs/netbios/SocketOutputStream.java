package jcifs.netbios;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
class SocketOutputStream extends FilterOutputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SocketOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 65535) {
            throw new IOException("write too large: " + i2);
        }
        if (i < 4) {
            throw new IOException("NetBIOS socket output buffer requires 4 bytes available before off");
        }
        int i3 = i - 4;
        bArr[i3 + 0] = 0;
        bArr[i3 + 1] = 0;
        bArr[i3 + 2] = (byte) ((i2 >> 8) & 255);
        bArr[i3 + 3] = (byte) (i2 & 255);
        this.out.write(bArr, i3, i2 + 4);
    }
}
