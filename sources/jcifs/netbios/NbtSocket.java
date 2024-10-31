package jcifs.netbios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import jcifs.Config;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class NbtSocket extends Socket {
    private static final int BUFFER_SIZE = 512;
    private static final int DEFAULT_SO_TIMEOUT = 5000;
    private static final int SSN_SRVC_PORT = 139;
    private static LogStream log = LogStream.getInstance();
    private NbtAddress address;
    private Name calledName;
    private int soTimeout;

    public NbtSocket() {
    }

    public NbtSocket(NbtAddress nbtAddress, int i) throws IOException {
        this(nbtAddress, i, null, 0);
    }

    public NbtSocket(NbtAddress nbtAddress, int i, InetAddress inetAddress, int i2) throws IOException {
        this(nbtAddress, null, i, inetAddress, i2);
    }

    public NbtSocket(NbtAddress nbtAddress, String str, int i, InetAddress inetAddress, int i2) throws IOException {
        super(nbtAddress.getInetAddress(), i == 0 ? SSN_SRVC_PORT : i, inetAddress, i2);
        this.address = nbtAddress;
        if (str == null) {
            this.calledName = nbtAddress.hostName;
        } else {
            this.calledName = new Name(str, 32, null);
        }
        this.soTimeout = Config.getInt("jcifs.netbios.soTimeout", 5000);
        connect();
    }

    public NbtAddress getNbtAddress() {
        return this.address;
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        return new SocketInputStream(super.getInputStream());
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        return new SocketOutputStream(super.getOutputStream());
    }

    @Override // java.net.Socket
    public int getPort() {
        return super.getPort();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return super.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return super.getLocalPort();
    }

    @Override // java.net.Socket
    public String toString() {
        return "NbtSocket[addr=" + this.address + ",port=" + super.getPort() + ",localport=" + super.getLocalPort() + "]";
    }

    private void connect() throws IOException {
        byte[] bArr = new byte[512];
        try {
            InputStream inputStream = super.getInputStream();
            super.getOutputStream().write(bArr, 0, new SessionRequestPacket(this.calledName, NbtAddress.localhost.hostName).writeWireFormat(bArr, 0));
            setSoTimeout(this.soTimeout);
            int readPacketType = SessionServicePacket.readPacketType(inputStream, bArr, 0);
            if (readPacketType == -1) {
                throw new NbtException(2, -1);
            }
            if (readPacketType != 130) {
                if (readPacketType == 131) {
                    int read = inputStream.read() & 255;
                    close();
                    throw new NbtException(2, read);
                }
                close();
                throw new NbtException(2, 0);
            }
            if (LogStream.level > 2) {
                log.println("session established ok with " + this.address);
            }
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (LogStream.level > 3) {
            log.println("close: " + this);
        }
        super.close();
    }
}
