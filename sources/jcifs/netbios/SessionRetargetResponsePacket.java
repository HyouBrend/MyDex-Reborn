package jcifs.netbios;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
class SessionRetargetResponsePacket extends SessionServicePacket {
    private NbtAddress retargetAddress;
    private int retargetPort;

    @Override // jcifs.netbios.SessionServicePacket
    int writeTrailerWireFormat(byte[] bArr, int i) {
        return 0;
    }

    SessionRetargetResponsePacket() {
        this.type = 132;
        this.length = 6;
    }

    @Override // jcifs.netbios.SessionServicePacket
    int readTrailerWireFormat(InputStream inputStream, byte[] bArr, int i) throws IOException {
        if (inputStream.read(bArr, i, this.length) != this.length) {
            throw new IOException("unexpected EOF reading netbios retarget session response");
        }
        this.retargetAddress = new NbtAddress(null, readInt4(bArr, i), false, 0);
        this.retargetPort = readInt2(bArr, i + 4);
        return this.length;
    }
}
