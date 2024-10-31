package jcifs.smb;

import jcifs.util.LogStream;

/* loaded from: classes2.dex */
class TransCallNamedPipe extends SmbComTransaction {
    private byte[] pipeData;
    private int pipeDataLen;
    private int pipeDataOff;

    @Override // jcifs.smb.SmbComTransaction
    int readDataWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int readParametersWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int readSetupWireFormat(byte[] bArr, int i, int i2) {
        return 0;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeParametersWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransCallNamedPipe(String str, byte[] bArr, int i, int i2) {
        this.name = str;
        this.pipeData = bArr;
        this.pipeDataOff = i;
        this.pipeDataLen = i2;
        this.command = (byte) 37;
        this.subCommand = (byte) 84;
        this.timeout = -1;
        this.maxParameterCount = 0;
        this.maxDataCount = 65535;
        this.maxSetupCount = (byte) 0;
        this.setupCount = 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.SmbComTransaction
    public int writeSetupWireFormat(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = this.subCommand;
        int i3 = i2 + 1;
        bArr[i2] = 0;
        bArr[i3] = 0;
        bArr[i3 + 1] = 0;
        return 4;
    }

    @Override // jcifs.smb.SmbComTransaction
    int writeDataWireFormat(byte[] bArr, int i) {
        int length = bArr.length - i;
        int i2 = this.pipeDataLen;
        if (length < i2) {
            LogStream logStream = log;
            if (LogStream.level < 3) {
                return 0;
            }
            log.println("TransCallNamedPipe data too long for buffer");
            return 0;
        }
        System.arraycopy(this.pipeData, this.pipeDataOff, bArr, i, i2);
        return this.pipeDataLen;
    }

    @Override // jcifs.smb.SmbComTransaction, jcifs.smb.ServerMessageBlock
    public String toString() {
        return new String("TransCallNamedPipe[" + super.toString() + ",pipeName=" + this.name + "]");
    }
}
