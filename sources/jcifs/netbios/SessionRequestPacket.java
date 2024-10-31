package jcifs.netbios;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class SessionRequestPacket extends SessionServicePacket {
    private Name calledName;
    private Name callingName;

    SessionRequestPacket() {
        this.calledName = new Name();
        this.callingName = new Name();
    }

    public SessionRequestPacket(Name name, Name name2) {
        this.type = NbtException.NOT_LISTENING_CALLING;
        this.calledName = name;
        this.callingName = name2;
    }

    @Override // jcifs.netbios.SessionServicePacket
    int writeTrailerWireFormat(byte[] bArr, int i) {
        int writeWireFormat = this.calledName.writeWireFormat(bArr, i) + i;
        return (writeWireFormat + this.callingName.writeWireFormat(bArr, writeWireFormat)) - i;
    }

    @Override // jcifs.netbios.SessionServicePacket
    int readTrailerWireFormat(InputStream inputStream, byte[] bArr, int i) throws IOException {
        if (inputStream.read(bArr, i, this.length) != this.length) {
            throw new IOException("invalid session request wire format");
        }
        int readWireFormat = this.calledName.readWireFormat(bArr, i) + i;
        return (readWireFormat + this.callingName.readWireFormat(bArr, readWireFormat)) - i;
    }
}
