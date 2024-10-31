package jcifs.smb;

import jcifs.util.Hexdump;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AndXServerMessageBlock extends ServerMessageBlock {
    private static final int ANDX_COMMAND_OFFSET = 1;
    private static final int ANDX_OFFSET_OFFSET = 3;
    private static final int ANDX_RESERVED_OFFSET = 2;
    ServerMessageBlock andx;
    private byte andxCommand;
    private int andxOffset;

    int getBatchLimit(byte b) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndXServerMessageBlock() {
        this.andxCommand = (byte) -1;
        this.andxOffset = 0;
        this.andx = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndXServerMessageBlock(ServerMessageBlock serverMessageBlock) {
        this.andxCommand = (byte) -1;
        this.andxOffset = 0;
        this.andx = null;
        if (serverMessageBlock != null) {
            this.andx = serverMessageBlock;
            this.andxCommand = serverMessageBlock.command;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int encode(byte[] bArr, int i) {
        this.headerStart = i;
        int writeHeaderWireFormat = writeHeaderWireFormat(bArr, i) + i;
        this.length = (writeHeaderWireFormat + writeAndXWireFormat(bArr, writeHeaderWireFormat)) - i;
        if (this.digest != null) {
            this.digest.sign(bArr, this.headerStart, this.length, this, this.response);
        }
        return this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int decode(byte[] bArr, int i) {
        this.headerStart = i;
        int readHeaderWireFormat = readHeaderWireFormat(bArr, i) + i;
        this.length = (readHeaderWireFormat + readAndXWireFormat(bArr, readHeaderWireFormat)) - i;
        return this.length;
    }

    int writeAndXWireFormat(byte[] bArr, int i) {
        int i2;
        int i3 = i + 3;
        this.wordCount = writeParameterWordsWireFormat(bArr, i3 + 2);
        this.wordCount += 4;
        int i4 = this.wordCount + 1 + i;
        this.wordCount /= 2;
        bArr[i] = (byte) (this.wordCount & 255);
        this.byteCount = writeBytesWireFormat(bArr, i4 + 2);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (this.byteCount & 255);
        bArr[i5] = (byte) ((this.byteCount >> 8) & 255);
        int i6 = i5 + 1 + this.byteCount;
        if (this.andx == null || !USE_BATCHING || this.batchLevel >= getBatchLimit(this.andx.command)) {
            this.andxCommand = (byte) -1;
            this.andx = null;
            bArr[i + 1] = -1;
            bArr[i + 2] = 0;
            bArr[i3] = -34;
            bArr[i3 + 1] = -34;
            return i6 - i;
        }
        this.andx.batchLevel = this.batchLevel + 1;
        bArr[i + 1] = this.andxCommand;
        bArr[i + 2] = 0;
        int i7 = i6 - this.headerStart;
        this.andxOffset = i7;
        writeInt2(i7, bArr, i3);
        this.andx.useUnicode = this.useUnicode;
        ServerMessageBlock serverMessageBlock = this.andx;
        if (serverMessageBlock instanceof AndXServerMessageBlock) {
            serverMessageBlock.uid = this.uid;
            i2 = i6 + ((AndXServerMessageBlock) this.andx).writeAndXWireFormat(bArr, i6);
        } else {
            serverMessageBlock.wordCount = serverMessageBlock.writeParameterWordsWireFormat(bArr, i6);
            int i8 = this.andx.wordCount + 1 + i6;
            this.andx.wordCount /= 2;
            bArr[i6] = (byte) (this.andx.wordCount & 255);
            ServerMessageBlock serverMessageBlock2 = this.andx;
            serverMessageBlock2.byteCount = serverMessageBlock2.writeBytesWireFormat(bArr, i8 + 2);
            int i9 = i8 + 1;
            bArr[i8] = (byte) (this.andx.byteCount & 255);
            bArr[i9] = (byte) ((this.andx.byteCount >> 8) & 255);
            i2 = i9 + 1 + this.andx.byteCount;
        }
        return i2 - i;
    }

    int readAndXWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        this.wordCount = bArr[i];
        if (this.wordCount != 0) {
            this.andxCommand = bArr[i2];
            int readInt2 = readInt2(bArr, i2 + 2);
            this.andxOffset = readInt2;
            if (readInt2 == 0) {
                this.andxCommand = (byte) -1;
            }
            if (this.wordCount > 2) {
                readParameterWordsWireFormat(bArr, i2 + 4);
                if (this.command == -94 && ((SmbComNTCreateAndXResponse) this).isExtended) {
                    this.wordCount += 8;
                }
            }
            i2 += this.wordCount * 2;
        }
        this.byteCount = readInt2(bArr, i2);
        int i3 = i2 + 2;
        if (this.byteCount != 0) {
            readBytesWireFormat(bArr, i3);
            i3 += this.byteCount;
        }
        if (this.errorCode != 0 || this.andxCommand == -1) {
            this.andxCommand = (byte) -1;
            this.andx = null;
        } else {
            if (this.andx == null) {
                this.andxCommand = (byte) -1;
                throw new RuntimeException("no andx command supplied with response");
            }
            int i4 = this.headerStart + this.andxOffset;
            this.andx.headerStart = this.headerStart;
            this.andx.command = this.andxCommand;
            this.andx.errorCode = this.errorCode;
            this.andx.flags = this.flags;
            this.andx.flags2 = this.flags2;
            this.andx.tid = this.tid;
            this.andx.pid = this.pid;
            this.andx.uid = this.uid;
            this.andx.mid = this.mid;
            this.andx.useUnicode = this.useUnicode;
            ServerMessageBlock serverMessageBlock = this.andx;
            if (serverMessageBlock instanceof AndXServerMessageBlock) {
                i3 = i4 + ((AndXServerMessageBlock) serverMessageBlock).readAndXWireFormat(bArr, i4);
            } else {
                int i5 = i4 + 1;
                bArr[i4] = (byte) (serverMessageBlock.wordCount & 255);
                if (this.andx.wordCount != 0 && this.andx.wordCount > 2) {
                    i5 += this.andx.readParameterWordsWireFormat(bArr, i5);
                }
                this.andx.byteCount = readInt2(bArr, i5);
                int i6 = i5 + 2;
                if (this.andx.byteCount != 0) {
                    this.andx.readBytesWireFormat(bArr, i6);
                    i6 += this.andx.byteCount;
                }
                i3 = i6;
            }
            this.andx.received = true;
        }
        return i3 - i;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String(super.toString() + ",andxCommand=0x" + Hexdump.toHexString((int) this.andxCommand, 2) + ",andxOffset=" + this.andxOffset);
    }
}
