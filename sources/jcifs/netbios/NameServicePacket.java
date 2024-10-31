package jcifs.netbios;

import androidx.exifinterface.media.ExifInterface;
import java.net.InetAddress;
import jcifs.util.Hexdump;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class NameServicePacket {
    static final int A = 1;
    static final int ACT_ERR = 6;
    static final int ADDITIONAL_OFFSET = 10;
    static final int ANSWER_OFFSET = 6;
    static final int AUTHORITY_OFFSET = 8;
    static final int CFT_ERR = 7;
    static final int FMT_ERR = 1;
    static final int HEADER_LENGTH = 12;
    static final int IMP_ERR = 4;
    static final int IN = 1;
    static final int NB = 32;
    static final int NBSTAT = 33;
    static final int NBSTAT_IN = 2162689;
    static final int NB_IN = 2097153;
    static final int NS = 2;
    static final int NULL = 10;
    static final int OPCODE_OFFSET = 2;
    static final int QUERY = 0;
    static final int QUESTION_OFFSET = 4;
    static final int RFS_ERR = 5;
    static final int SRV_ERR = 2;
    static final int WACK = 7;
    int additionalCount;
    InetAddress addr;
    NbtAddress[] addrEntry;
    int addrIndex;
    int answerCount;
    int authorityCount;
    boolean isAuthAnswer;
    boolean isRecurAvailable;
    boolean isResponse;
    boolean isTruncated;
    int nameTrnId;
    int opCode;
    Name questionName;
    int questionType;
    int rDataLength;
    boolean received;
    int recordClass;
    Name recordName;
    int recordType;
    int resultCode;
    int ttl;
    boolean isRecurDesired = true;
    boolean isBroadcast = true;
    int questionCount = 1;
    int questionClass = 1;

    abstract int readBodyWireFormat(byte[] bArr, int i);

    abstract int readRDataWireFormat(byte[] bArr, int i);

    abstract int writeBodyWireFormat(byte[] bArr, int i);

    abstract int writeRDataWireFormat(byte[] bArr, int i);

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

    static int readInt2(byte[] bArr, int i) {
        return ((bArr[i] & UByte.MAX_VALUE) << 8) + (bArr[i + 1] & UByte.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInt4(byte[] bArr, int i) {
        return ((bArr[i] & UByte.MAX_VALUE) << 24) + ((bArr[i + 1] & UByte.MAX_VALUE) << 16) + ((bArr[i + 2] & UByte.MAX_VALUE) << 8) + (bArr[i + 3] & UByte.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readNameTrnId(byte[] bArr, int i) {
        return readInt2(bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeWireFormat(byte[] bArr, int i) {
        int writeHeaderWireFormat = writeHeaderWireFormat(bArr, i) + i;
        return (writeHeaderWireFormat + writeBodyWireFormat(bArr, writeHeaderWireFormat)) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readWireFormat(byte[] bArr, int i) {
        int readHeaderWireFormat = readHeaderWireFormat(bArr, i) + i;
        return (readHeaderWireFormat + readBodyWireFormat(bArr, readHeaderWireFormat)) - i;
    }

    int writeHeaderWireFormat(byte[] bArr, int i) {
        writeInt2(this.nameTrnId, bArr, i);
        int i2 = i + 2;
        bArr[i2] = (byte) ((this.isResponse ? 128 : 0) + ((this.opCode << 3) & 120) + (this.isAuthAnswer ? 4 : 0) + (this.isTruncated ? 2 : 0) + (this.isRecurDesired ? 1 : 0));
        bArr[i2 + 1] = (byte) ((this.isRecurAvailable ? 128 : 0) + (this.isBroadcast ? 16 : 0) + (this.resultCode & 15));
        writeInt2(this.questionCount, bArr, i + 4);
        writeInt2(this.answerCount, bArr, i + 6);
        writeInt2(this.authorityCount, bArr, i + 8);
        writeInt2(this.additionalCount, bArr, i + 10);
        return 12;
    }

    int readHeaderWireFormat(byte[] bArr, int i) {
        this.nameTrnId = readInt2(bArr, i);
        int i2 = i + 2;
        byte b = bArr[i2];
        this.isResponse = (b & ByteCompanionObject.MIN_VALUE) != 0;
        this.opCode = (b & 120) >> 3;
        this.isAuthAnswer = (b & 4) != 0;
        this.isTruncated = (b & 2) != 0;
        this.isRecurDesired = (b & 1) != 0;
        byte b2 = bArr[i2 + 1];
        this.isRecurAvailable = (b2 & ByteCompanionObject.MIN_VALUE) != 0;
        this.isBroadcast = (b2 & 16) != 0;
        this.resultCode = b2 & 15;
        this.questionCount = readInt2(bArr, i + 4);
        this.answerCount = readInt2(bArr, i + 6);
        this.authorityCount = readInt2(bArr, i + 8);
        this.additionalCount = readInt2(bArr, i + 10);
        return 12;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeQuestionSectionWireFormat(byte[] bArr, int i) {
        int writeWireFormat = this.questionName.writeWireFormat(bArr, i) + i;
        writeInt2(this.questionType, bArr, writeWireFormat);
        int i2 = writeWireFormat + 2;
        writeInt2(this.questionClass, bArr, i2);
        return (i2 + 2) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readQuestionSectionWireFormat(byte[] bArr, int i) {
        int readWireFormat = this.questionName.readWireFormat(bArr, i) + i;
        this.questionType = readInt2(bArr, readWireFormat);
        int i2 = readWireFormat + 2;
        this.questionClass = readInt2(bArr, i2);
        return (i2 + 2) - i;
    }

    int writeResourceRecordWireFormat(byte[] bArr, int i) {
        int writeWireFormat;
        Name name = this.recordName;
        if (name == this.questionName) {
            int i2 = i + 1;
            bArr[i] = -64;
            writeWireFormat = i2 + 1;
            bArr[i2] = 12;
        } else {
            writeWireFormat = i + name.writeWireFormat(bArr, i);
        }
        writeInt2(this.recordType, bArr, writeWireFormat);
        int i3 = writeWireFormat + 2;
        writeInt2(this.recordClass, bArr, i3);
        int i4 = i3 + 2;
        writeInt4(this.ttl, bArr, i4);
        int i5 = i4 + 4;
        int writeRDataWireFormat = writeRDataWireFormat(bArr, i5 + 2);
        this.rDataLength = writeRDataWireFormat;
        writeInt2(writeRDataWireFormat, bArr, i5);
        return (i5 + (this.rDataLength + 2)) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readResourceRecordWireFormat(byte[] bArr, int i) {
        int readWireFormat;
        if ((bArr[i] & 192) == 192) {
            this.recordName = this.questionName;
            readWireFormat = i + 2;
        } else {
            readWireFormat = this.recordName.readWireFormat(bArr, i) + i;
        }
        this.recordType = readInt2(bArr, readWireFormat);
        int i2 = readWireFormat + 2;
        this.recordClass = readInt2(bArr, i2);
        int i3 = i2 + 2;
        this.ttl = readInt4(bArr, i3);
        int i4 = i3 + 4;
        int readInt2 = readInt2(bArr, i4);
        this.rDataLength = readInt2;
        int i5 = i4 + 2;
        this.addrEntry = new NbtAddress[readInt2 / 6];
        int i6 = readInt2 + i5;
        int i7 = 0;
        while (true) {
            this.addrIndex = i7;
            if (i5 >= i6) {
                return i5 - i;
            }
            i5 += readRDataWireFormat(bArr, i5);
            i7 = this.addrIndex + 1;
        }
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        int i = this.opCode;
        if (i != 0) {
            str = i != 7 ? Integer.toString(i) : "WACK";
        } else {
            str = "QUERY";
        }
        int i2 = this.resultCode;
        if (i2 != 1 && i2 != 2 && i2 != 4 && i2 != 5 && i2 != 6 && i2 != 7) {
            Hexdump.toHexString(this.resultCode, 1);
        }
        int i3 = this.questionType;
        String str4 = "NBSTAT";
        if (i3 == 32) {
            str2 = "NB";
        } else if (i3 != 33) {
            str2 = "0x" + Hexdump.toHexString(this.questionType, 4);
        } else {
            str2 = "NBSTAT";
        }
        int i4 = this.recordType;
        if (i4 == 1) {
            str4 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
        } else if (i4 == 2) {
            str4 = "NS";
        } else if (i4 == 10) {
            str4 = "NULL";
        } else if (i4 == 32) {
            str4 = "NB";
        } else if (i4 != 33) {
            str4 = "0x" + Hexdump.toHexString(this.recordType, 4);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("nameTrnId=");
        sb.append(this.nameTrnId);
        sb.append(",isResponse=");
        sb.append(this.isResponse);
        sb.append(",opCode=");
        sb.append(str);
        sb.append(",isAuthAnswer=");
        sb.append(this.isAuthAnswer);
        sb.append(",isTruncated=");
        sb.append(this.isTruncated);
        sb.append(",isRecurAvailable=");
        sb.append(this.isRecurAvailable);
        sb.append(",isRecurDesired=");
        sb.append(this.isRecurDesired);
        sb.append(",isBroadcast=");
        sb.append(this.isBroadcast);
        sb.append(",resultCode=");
        sb.append(this.resultCode);
        sb.append(",questionCount=");
        sb.append(this.questionCount);
        sb.append(",answerCount=");
        sb.append(this.answerCount);
        sb.append(",authorityCount=");
        sb.append(this.authorityCount);
        sb.append(",additionalCount=");
        sb.append(this.additionalCount);
        sb.append(",questionName=");
        sb.append(this.questionName);
        sb.append(",questionType=");
        sb.append(str2);
        sb.append(",questionClass=");
        String str5 = "IN";
        if (this.questionClass == 1) {
            str3 = "IN";
        } else {
            str3 = "0x" + Hexdump.toHexString(this.questionClass, 4);
        }
        sb.append(str3);
        sb.append(",recordName=");
        sb.append(this.recordName);
        sb.append(",recordType=");
        sb.append(str4);
        sb.append(",recordClass=");
        if (this.recordClass != 1) {
            str5 = "0x" + Hexdump.toHexString(this.recordClass, 4);
        }
        sb.append(str5);
        sb.append(",ttl=");
        sb.append(this.ttl);
        sb.append(",rDataLength=");
        sb.append(this.rDataLength);
        return new String(sb.toString());
    }
}
