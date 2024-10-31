package jcifs.smb;

import androidx.work.WorkRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;
import jcifs.util.transport.Request;
import jcifs.util.transport.Response;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class ServerMessageBlock extends Response implements Request, SmbConstants {
    static final byte SMB_COM_CHECK_DIRECTORY = 16;
    static final byte SMB_COM_CLOSE = 4;
    static final byte SMB_COM_CREATE_DIRECTORY = 0;
    static final byte SMB_COM_DELETE = 6;
    static final byte SMB_COM_DELETE_DIRECTORY = 1;
    static final byte SMB_COM_ECHO = 43;
    static final byte SMB_COM_FIND_CLOSE2 = 52;
    static final byte SMB_COM_LOGOFF_ANDX = 116;
    static final byte SMB_COM_MOVE = 42;
    static final byte SMB_COM_NEGOTIATE = 114;
    static final byte SMB_COM_NT_CREATE_ANDX = -94;
    static final byte SMB_COM_NT_TRANSACT = -96;
    static final byte SMB_COM_NT_TRANSACT_SECONDARY = -95;
    static final byte SMB_COM_OPEN_ANDX = 45;
    static final byte SMB_COM_QUERY_INFORMATION = 8;
    static final byte SMB_COM_READ_ANDX = 46;
    static final byte SMB_COM_RENAME = 7;
    static final byte SMB_COM_SESSION_SETUP_ANDX = 115;
    static final byte SMB_COM_TRANSACTION = 37;
    static final byte SMB_COM_TRANSACTION2 = 50;
    static final byte SMB_COM_TRANSACTION_SECONDARY = 38;
    static final byte SMB_COM_TREE_CONNECT_ANDX = 117;
    static final byte SMB_COM_TREE_DISCONNECT = 113;
    static final byte SMB_COM_WRITE = 11;
    static final byte SMB_COM_WRITE_ANDX = 47;
    int byteCount;
    byte command;
    int errorCode;
    boolean extendedSecurity;
    int flags2;
    int headerStart;
    int length;
    int mid;
    String path;
    boolean received;
    ServerMessageBlock response;
    int signSeq;
    int tid;
    int uid;
    boolean useUnicode;
    boolean verifyFailed;
    int wordCount;
    static LogStream log = LogStream.getInstance();
    static final byte[] header = {-1, 83, 77, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    long responseTimeout = 1;
    NtlmPasswordAuthentication auth = null;
    SigningDigest digest = null;
    byte flags = 24;
    int pid = PID;
    int batchLevel = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int readBytesWireFormat(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int readParameterWordsWireFormat(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int writeBytesWireFormat(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int writeParameterWordsWireFormat(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeInt2(long j, byte[] bArr, int i) {
        bArr[i] = (byte) j;
        bArr[i + 1] = (byte) (j >> 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeInt4(long j, byte[] bArr, int i) {
        bArr[i] = (byte) j;
        int i2 = i + 1;
        long j2 = j >> 8;
        bArr[i2] = (byte) j2;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (j2 >> 8);
        bArr[i3 + 1] = (byte) (r2 >> 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInt2(byte[] bArr, int i) {
        return (bArr[i] & UByte.MAX_VALUE) + ((bArr[i + 1] & UByte.MAX_VALUE) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInt4(byte[] bArr, int i) {
        return (bArr[i] & UByte.MAX_VALUE) + ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + ((bArr[i + 3] & UByte.MAX_VALUE) << 24);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long readInt8(byte[] bArr, int i) {
        return (readInt4(bArr, i) & InternalZipConstants.ZIP_64_LIMIT) + (readInt4(bArr, i + 4) << 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeInt8(long j, byte[] bArr, int i) {
        bArr[i] = (byte) j;
        int i2 = i + 1;
        long j2 = j >> 8;
        bArr[i2] = (byte) j2;
        int i3 = i2 + 1;
        long j3 = j2 >> 8;
        bArr[i3] = (byte) j3;
        int i4 = i3 + 1;
        long j4 = j3 >> 8;
        bArr[i4] = (byte) j4;
        int i5 = i4 + 1;
        long j5 = j4 >> 8;
        bArr[i5] = (byte) j5;
        int i6 = i5 + 1;
        long j6 = j5 >> 8;
        bArr[i6] = (byte) j6;
        int i7 = i6 + 1;
        bArr[i7] = (byte) (j6 >> 8);
        bArr[i7 + 1] = (byte) (r2 >> 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long readTime(byte[] bArr, int i) {
        return (((readInt4(bArr, i + 4) << 32) | (readInt4(bArr, i) & InternalZipConstants.ZIP_64_LIMIT)) / WorkRequest.MIN_BACKOFF_MILLIS) - 11644473600000L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeTime(long j, byte[] bArr, int i) {
        if (j != 0) {
            j = (j + 11644473600000L) * WorkRequest.MIN_BACKOFF_MILLIS;
        }
        writeInt8(j, bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long readUTime(byte[] bArr, int i) {
        return readInt4(bArr, i) * 1000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeUTime(long j, byte[] bArr, int i) {
        if (j == 0 || j == -1) {
            writeInt4(-1L, bArr, i);
            return;
        }
        synchronized (TZ) {
            if (TZ.inDaylightTime(new Date())) {
                if (!TZ.inDaylightTime(new Date(j))) {
                    j -= 3600000;
                }
            } else if (TZ.inDaylightTime(new Date(j))) {
                j += 3600000;
            }
        }
        writeInt4((int) (j / 1000), bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.flags = (byte) 24;
        this.flags2 = 0;
        this.errorCode = 0;
        this.received = false;
        this.digest = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeString(String str, byte[] bArr, int i) {
        return writeString(str, bArr, i, this.useUnicode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v15 */
    public int writeString(String str, byte[] bArr, int i, boolean z) {
        int i2;
        try {
            try {
                if (z != 0) {
                    if ((i - this.headerStart) % 2 != 0) {
                        i2 = i + 1;
                        bArr[i] = 0;
                    } else {
                        i2 = i;
                    }
                    System.arraycopy(str.getBytes(SmbConstants.UNI_ENCODING), 0, bArr, i2, str.length() * 2);
                    int length = i2 + (str.length() * 2);
                    int i3 = length + 1;
                    try {
                        bArr[length] = 0;
                        int i4 = i3 + 1;
                        bArr[i3] = 0;
                        z = i4;
                    } catch (UnsupportedEncodingException e) {
                        z = i3;
                        e = e;
                        if (LogStream.level > 1) {
                            e.printStackTrace(log);
                        }
                        return z - i;
                    }
                } else {
                    byte[] bytes = str.getBytes(OEM_ENCODING);
                    System.arraycopy(bytes, 0, bArr, i, bytes.length);
                    int length2 = bytes.length + i;
                    int i5 = length2 + 1;
                    bArr[length2] = 0;
                    z = i5;
                }
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                z = i;
            }
        } catch (UnsupportedEncodingException e3) {
            e = e3;
        }
        return z - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readString(byte[] bArr, int i) {
        return readString(bArr, i, 256, this.useUnicode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readString(byte[] bArr, int i, int i2, boolean z) {
        int i3 = 0;
        try {
            if (z) {
                if ((i - this.headerStart) % 2 != 0) {
                    i++;
                }
                do {
                    int i4 = i + i3;
                    if (bArr[i4] == 0 && bArr[i4 + 1] == 0) {
                        return new String(bArr, i, i3, SmbConstants.UNI_ENCODING);
                    }
                    i3 += 2;
                } while (i3 <= i2);
                if (LogStream.level > 0) {
                    Hexdump.hexdump(System.err, bArr, i, i2 < 128 ? i2 + 8 : 128);
                }
                throw new RuntimeException("zero termination not found");
            }
            while (bArr[i + i3] != 0) {
                i3++;
                if (i3 > i2) {
                    if (LogStream.level > 0) {
                        Hexdump.hexdump(System.err, bArr, i, i2 < 128 ? i2 + 8 : 128);
                    }
                    throw new RuntimeException("zero termination not found");
                }
            }
            return new String(bArr, i, i3, OEM_ENCODING);
        } catch (UnsupportedEncodingException e) {
            if (LogStream.level > 1) {
                e.printStackTrace(log);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readString(byte[] bArr, int i, int i2, int i3, boolean z) {
        int i4 = 0;
        try {
            if (z) {
                if ((i - this.headerStart) % 2 != 0) {
                    i++;
                }
                while (true) {
                    int i5 = i + i4;
                    int i6 = i5 + 1;
                    if (i6 >= i2 || (bArr[i5] == 0 && bArr[i6] == 0)) {
                        break;
                    }
                    if (i4 > i3) {
                        if (LogStream.level > 0) {
                            Hexdump.hexdump(System.err, bArr, i, i3 < 128 ? i3 + 8 : 128);
                        }
                        throw new RuntimeException("zero termination not found");
                    }
                    i4 += 2;
                }
                return new String(bArr, i, i4, SmbConstants.UNI_ENCODING);
            }
            while (i < i2 && bArr[i + i4] != 0) {
                if (i4 > i3) {
                    if (LogStream.level > 0) {
                        Hexdump.hexdump(System.err, bArr, i, i3 < 128 ? i3 + 8 : 128);
                    }
                    throw new RuntimeException("zero termination not found");
                }
                i4++;
            }
            return new String(bArr, i, i4, OEM_ENCODING);
        } catch (UnsupportedEncodingException e) {
            if (LogStream.level > 1) {
                e.printStackTrace(log);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int stringWireLength(String str, int i) {
        int length = str.length() + 1;
        if (!this.useUnicode) {
            return length;
        }
        int length2 = (str.length() * 2) + 2;
        if (i % 2 != 0) {
            length2++;
        }
        return length2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readStringLength(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (bArr[i + i3] != 0) {
            int i4 = i3 + 1;
            if (i3 > i2) {
                throw new RuntimeException("zero termination not found: " + this);
            }
            i3 = i4;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int encode(byte[] bArr, int i) {
        this.headerStart = i;
        int writeHeaderWireFormat = writeHeaderWireFormat(bArr, i) + i;
        int i2 = writeHeaderWireFormat + 1;
        int writeParameterWordsWireFormat = writeParameterWordsWireFormat(bArr, i2);
        this.wordCount = writeParameterWordsWireFormat;
        bArr[writeHeaderWireFormat] = (byte) ((writeParameterWordsWireFormat / 2) & 255);
        int i3 = i2 + writeParameterWordsWireFormat;
        this.wordCount = writeParameterWordsWireFormat / 2;
        int writeBytesWireFormat = writeBytesWireFormat(bArr, i3 + 2);
        this.byteCount = writeBytesWireFormat;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (writeBytesWireFormat & 255);
        bArr[i4] = (byte) ((writeBytesWireFormat >> 8) & 255);
        int i5 = ((i4 + 1) + writeBytesWireFormat) - i;
        this.length = i5;
        SigningDigest signingDigest = this.digest;
        if (signingDigest != null) {
            signingDigest.sign(bArr, this.headerStart, i5, this, this.response);
        }
        return this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int decode(byte[] bArr, int i) {
        this.headerStart = i;
        int readHeaderWireFormat = readHeaderWireFormat(bArr, i) + i;
        int i2 = readHeaderWireFormat + 1;
        byte b = bArr[readHeaderWireFormat];
        this.wordCount = b;
        if (b != 0) {
            int readParameterWordsWireFormat = readParameterWordsWireFormat(bArr, i2);
            if (readParameterWordsWireFormat != this.wordCount * 2 && LogStream.level >= 5) {
                log.println("wordCount * 2=" + (this.wordCount * 2) + " but readParameterWordsWireFormat returned " + readParameterWordsWireFormat);
            }
            i2 += this.wordCount * 2;
        }
        int readInt2 = readInt2(bArr, i2);
        this.byteCount = readInt2;
        int i3 = i2 + 2;
        if (readInt2 != 0) {
            int readBytesWireFormat = readBytesWireFormat(bArr, i3);
            if (readBytesWireFormat != this.byteCount && LogStream.level >= 5) {
                log.println("byteCount=" + this.byteCount + " but readBytesWireFormat returned " + readBytesWireFormat);
            }
            i3 += this.byteCount;
        }
        int i4 = i3 - i;
        this.length = i4;
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeHeaderWireFormat(byte[] bArr, int i) {
        byte[] bArr2 = header;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        bArr[i + 4] = this.command;
        int i2 = i + 9;
        bArr[i2] = this.flags;
        writeInt2(this.flags2, bArr, i2 + 1);
        int i3 = i + 24;
        writeInt2(this.tid, bArr, i3);
        writeInt2(this.pid, bArr, i3 + 2);
        writeInt2(this.uid, bArr, i3 + 4);
        writeInt2(this.mid, bArr, i3 + 6);
        return 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readHeaderWireFormat(byte[] bArr, int i) {
        this.command = bArr[i + 4];
        this.errorCode = readInt4(bArr, i + 5);
        int i2 = i + 9;
        this.flags = bArr[i2];
        this.flags2 = readInt2(bArr, i2 + 1);
        int i3 = i + 24;
        this.tid = readInt2(bArr, i3);
        this.pid = readInt2(bArr, i3 + 2);
        this.uid = readInt2(bArr, i3 + 4);
        this.mid = readInt2(bArr, i3 + 6);
        return 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResponse() {
        return (this.flags & ByteCompanionObject.MIN_VALUE) == 128;
    }

    public int hashCode() {
        return this.mid;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServerMessageBlock) && ((ServerMessageBlock) obj).mid == this.mid;
    }

    public String toString() {
        String str;
        byte b = this.command;
        if (b == 0) {
            str = "SMB_COM_CREATE_DIRECTORY";
        } else if (b == 1) {
            str = "SMB_COM_DELETE_DIRECTORY";
        } else if (b == 4) {
            str = "SMB_COM_CLOSE";
        } else if (b == 16) {
            str = "SMB_COM_CHECK_DIRECTORY";
        } else if (b == 50) {
            str = "SMB_COM_TRANSACTION2";
        } else if (b == 52) {
            str = "SMB_COM_FIND_CLOSE2";
        } else if (b == 6) {
            str = "SMB_COM_DELETE";
        } else if (b == 7) {
            str = "SMB_COM_RENAME";
        } else if (b == 8) {
            str = "SMB_COM_QUERY_INFORMATION";
        } else if (b == 37) {
            str = "SMB_COM_TRANSACTION";
        } else if (b == 38) {
            str = "SMB_COM_TRANSACTION_SECONDARY";
        } else if (b == 42) {
            str = "SMB_COM_MOVE";
        } else if (b != 43) {
            switch (b) {
                case -96:
                    str = "SMB_COM_NT_TRANSACT";
                    break;
                case -95:
                    str = "SMB_COM_NT_TRANSACT_SECONDARY";
                    break;
                case -94:
                    str = "SMB_COM_NT_CREATE_ANDX";
                    break;
                default:
                    switch (b) {
                        case 45:
                            str = "SMB_COM_OPEN_ANDX";
                            break;
                        case 46:
                            str = "SMB_COM_READ_ANDX";
                            break;
                        case 47:
                            str = "SMB_COM_WRITE_ANDX";
                            break;
                        default:
                            switch (b) {
                                case 113:
                                    str = "SMB_COM_TREE_DISCONNECT";
                                    break;
                                case 114:
                                    str = "SMB_COM_NEGOTIATE";
                                    break;
                                case 115:
                                    str = "SMB_COM_SESSION_SETUP_ANDX";
                                    break;
                                case 116:
                                    str = "SMB_COM_LOGOFF_ANDX";
                                    break;
                                case 117:
                                    str = "SMB_COM_TREE_CONNECT_ANDX";
                                    break;
                                default:
                                    str = "UNKNOWN";
                                    break;
                            }
                    }
            }
        } else {
            str = "SMB_COM_ECHO";
        }
        int i = this.errorCode;
        return new String("command=" + str + ",received=" + this.received + ",errorCode=" + (i == 0 ? "0" : SmbException.getMessageByCode(i)) + ",flags=0x" + Hexdump.toHexString(this.flags & UByte.MAX_VALUE, 4) + ",flags2=0x" + Hexdump.toHexString(this.flags2, 4) + ",signSeq=" + this.signSeq + ",tid=" + this.tid + ",pid=" + this.pid + ",uid=" + this.uid + ",mid=" + this.mid + ",wordCount=" + this.wordCount + ",byteCount=" + this.byteCount);
    }
}
