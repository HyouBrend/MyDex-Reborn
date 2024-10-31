package jcifs.smb;

import jcifs.util.LogStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
abstract class SmbComNtTransactionResponse extends SmbComTransactionResponse {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransactionResponse, jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = 0;
        int i3 = i2 + 1;
        bArr[i2] = 0;
        int i4 = i3 + 1;
        bArr[i3] = 0;
        this.totalParameterCount = readInt4(bArr, i4);
        if (this.bufDataStart == 0) {
            this.bufDataStart = this.totalParameterCount;
        }
        int i5 = i4 + 4;
        this.totalDataCount = readInt4(bArr, i5);
        int i6 = i5 + 4;
        this.parameterCount = readInt4(bArr, i6);
        int i7 = i6 + 4;
        this.parameterOffset = readInt4(bArr, i7);
        int i8 = i7 + 4;
        this.parameterDisplacement = readInt4(bArr, i8);
        int i9 = i8 + 4;
        this.dataCount = readInt4(bArr, i9);
        int i10 = i9 + 4;
        this.dataOffset = readInt4(bArr, i10);
        int i11 = i10 + 4;
        this.dataDisplacement = readInt4(bArr, i11);
        int i12 = i11 + 4;
        this.setupCount = bArr[i12] & UByte.MAX_VALUE;
        int i13 = i12 + 2;
        if (this.setupCount != 0) {
            LogStream logStream = log;
            if (LogStream.level >= 3) {
                log.println("setupCount is not zero: " + this.setupCount);
            }
        }
        return i13 - i;
    }
}
