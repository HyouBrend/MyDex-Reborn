package jcifs.smb;

import java.util.Enumeration;
import jcifs.util.LogStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
abstract class SmbComTransactionResponse extends ServerMessageBlock implements Enumeration {
    private static final int DISCONNECT_TID = 1;
    private static final int ONE_WAY_TRANSACTION = 2;
    private static final int SETUP_OFFSET = 61;
    protected int bufDataStart;
    protected int bufParameterStart;
    int dataCount;
    protected int dataDisplacement;
    private boolean dataDone;
    protected int dataOffset;
    int numEntries;
    private int pad;
    private int pad1;
    protected int parameterCount;
    protected int parameterDisplacement;
    protected int parameterOffset;
    private boolean parametersDone;
    FileEntry[] results;
    protected int setupCount;
    int status;
    byte subCommand;
    protected int totalDataCount;
    protected int totalParameterCount;
    boolean hasMore = true;
    boolean isPrimary = true;
    byte[] txn_buf = null;

    abstract int readDataWireFormat(byte[] bArr, int i, int i2);

    abstract int readParametersWireFormat(byte[] bArr, int i, int i2);

    abstract int readSetupWireFormat(byte[] bArr, int i, int i2);

    @Override // jcifs.smb.ServerMessageBlock
    int writeBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    abstract int writeDataWireFormat(byte[] bArr, int i);

    @Override // jcifs.smb.ServerMessageBlock
    int writeParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    abstract int writeParametersWireFormat(byte[] bArr, int i);

    abstract int writeSetupWireFormat(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public void reset() {
        super.reset();
        this.bufDataStart = 0;
        this.hasMore = true;
        this.isPrimary = true;
        this.dataDone = false;
        this.parametersDone = false;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.errorCode == 0 && this.hasMore;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        if (this.isPrimary) {
            this.isPrimary = false;
        }
        return this;
    }

    @Override // jcifs.smb.ServerMessageBlock
    int readParameterWordsWireFormat(byte[] bArr, int i) {
        int readInt2 = readInt2(bArr, i);
        this.totalParameterCount = readInt2;
        if (this.bufDataStart == 0) {
            this.bufDataStart = readInt2;
        }
        int i2 = i + 2;
        this.totalDataCount = readInt2(bArr, i2);
        int i3 = i2 + 4;
        this.parameterCount = readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.parameterOffset = readInt2(bArr, i4);
        int i5 = i4 + 2;
        this.parameterDisplacement = readInt2(bArr, i5);
        int i6 = i5 + 2;
        this.dataCount = readInt2(bArr, i6);
        int i7 = i6 + 2;
        this.dataOffset = readInt2(bArr, i7);
        int i8 = i7 + 2;
        this.dataDisplacement = readInt2(bArr, i8);
        int i9 = i8 + 2;
        int i10 = bArr[i9] & UByte.MAX_VALUE;
        this.setupCount = i10;
        int i11 = i9 + 2;
        if (i10 != 0) {
            LogStream logStream = log;
            if (LogStream.level > 2) {
                log.println("setupCount is not zero: " + this.setupCount);
            }
        }
        return i11 - i;
    }

    @Override // jcifs.smb.ServerMessageBlock
    int readBytesWireFormat(byte[] bArr, int i) {
        this.pad1 = 0;
        this.pad = 0;
        if (this.parameterCount > 0) {
            int i2 = this.parameterOffset - (i - this.headerStart);
            this.pad = i2;
            int i3 = i + i2;
            System.arraycopy(bArr, i3, this.txn_buf, this.bufParameterStart + this.parameterDisplacement, this.parameterCount);
            i = i3 + this.parameterCount;
        }
        if (this.dataCount > 0) {
            int i4 = this.dataOffset - (i - this.headerStart);
            this.pad1 = i4;
            System.arraycopy(bArr, i + i4, this.txn_buf, this.bufDataStart + this.dataDisplacement, this.dataCount);
        }
        if (!this.parametersDone && this.parameterDisplacement + this.parameterCount == this.totalParameterCount) {
            this.parametersDone = true;
        }
        if (!this.dataDone && this.dataDisplacement + this.dataCount == this.totalDataCount) {
            this.dataDone = true;
        }
        if (this.parametersDone && this.dataDone) {
            this.hasMore = false;
            readParametersWireFormat(this.txn_buf, this.bufParameterStart, this.totalParameterCount);
            readDataWireFormat(this.txn_buf, this.bufDataStart, this.totalDataCount);
        }
        return this.pad + this.parameterCount + this.pad1 + this.dataCount;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String(super.toString() + ",totalParameterCount=" + this.totalParameterCount + ",totalDataCount=" + this.totalDataCount + ",parameterCount=" + this.parameterCount + ",parameterOffset=" + this.parameterOffset + ",parameterDisplacement=" + this.parameterDisplacement + ",dataCount=" + this.dataCount + ",dataOffset=" + this.dataOffset + ",dataDisplacement=" + this.dataDisplacement + ",setupCount=" + this.setupCount + ",pad=" + this.pad + ",pad1=" + this.pad1);
    }
}
