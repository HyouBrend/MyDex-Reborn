package jcifs.netbios;

import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
public abstract class SessionServicePacket {
    static final int HEADER_LENGTH = 4;
    static final int MAX_MESSAGE_SIZE = 131071;
    public static final int NEGATIVE_SESSION_RESPONSE = 131;
    public static final int POSITIVE_SESSION_RESPONSE = 130;
    static final int SESSION_KEEP_ALIVE = 133;
    static final int SESSION_MESSAGE = 0;
    static final int SESSION_REQUEST = 129;
    static final int SESSION_RETARGET_RESPONSE = 132;
    int length;
    int type;

    abstract int readTrailerWireFormat(InputStream inputStream, byte[] bArr, int i) throws IOException;

    abstract int writeTrailerWireFormat(byte[] bArr, int i);

    static void writeInt2(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 1] = (byte) (i & 255);
    }

    static void writeInt4(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >> 16) & 255);
        bArr[i4] = (byte) ((i >> 8) & 255);
        bArr[i4 + 1] = (byte) (i & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInt2(byte[] bArr, int i) {
        return ((bArr[i] & UByte.MAX_VALUE) << 8) + (bArr[i + 1] & UByte.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInt4(byte[] bArr, int i) {
        return ((bArr[i] & UByte.MAX_VALUE) << 24) + ((bArr[i + 1] & UByte.MAX_VALUE) << 16) + ((bArr[i + 2] & UByte.MAX_VALUE) << 8) + (bArr[i + 3] & UByte.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readLength(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((bArr[i2] & 1) << 16) + ((bArr[i3] & UByte.MAX_VALUE) << 8) + (bArr[i3 + 1] & UByte.MAX_VALUE);
    }

    static int readn(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read <= 0) {
                break;
            }
            i3 += read;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readPacketType(InputStream inputStream, byte[] bArr, int i) throws IOException {
        int readn = readn(inputStream, bArr, i, 4);
        if (readn == 4) {
            return bArr[i] & UByte.MAX_VALUE;
        }
        if (readn == -1) {
            return -1;
        }
        throw new IOException("unexpected EOF reading netbios session header");
    }

    public int writeWireFormat(byte[] bArr, int i) {
        this.length = writeTrailerWireFormat(bArr, i + 4);
        writeHeaderWireFormat(bArr, i);
        return this.length + 4;
    }

    int readWireFormat(InputStream inputStream, byte[] bArr, int i) throws IOException {
        readHeaderWireFormat(inputStream, bArr, i);
        return readTrailerWireFormat(inputStream, bArr, i) + 4;
    }

    int writeHeaderWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = (byte) this.type;
        int i3 = this.length;
        if (i3 > 65535) {
            bArr[i2] = 1;
        }
        writeInt2(i3, bArr, i2 + 1);
        return 4;
    }

    int readHeaderWireFormat(InputStream inputStream, byte[] bArr, int i) throws IOException {
        int i2 = i + 1;
        this.type = bArr[i] & UByte.MAX_VALUE;
        this.length = ((bArr[i2] & 1) << 16) + readInt2(bArr, i2 + 1);
        return 4;
    }
}
