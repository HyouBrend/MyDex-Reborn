package jcifs.smb;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import jcifs.smb.SmbTransport;
import jcifs.util.Hexdump;
import jcifs.util.LogStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
class SmbComNegotiateResponse extends ServerMessageBlock {
    int dialectIndex;
    SmbTransport.ServerData server;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeBytesWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int writeParameterWordsWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbComNegotiateResponse(SmbTransport.ServerData serverData) {
        this.server = serverData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readParameterWordsWireFormat(byte[] bArr, int i) {
        int readInt2 = readInt2(bArr, i);
        this.dialectIndex = readInt2;
        int i2 = i + 2;
        if (readInt2 > 10) {
            return i2 - i;
        }
        int i3 = i2 + 1;
        this.server.securityMode = bArr[i2] & UByte.MAX_VALUE;
        SmbTransport.ServerData serverData = this.server;
        serverData.security = serverData.securityMode & 1;
        SmbTransport.ServerData serverData2 = this.server;
        serverData2.encryptedPasswords = (serverData2.securityMode & 2) == 2;
        SmbTransport.ServerData serverData3 = this.server;
        serverData3.signaturesEnabled = (serverData3.securityMode & 4) == 4;
        SmbTransport.ServerData serverData4 = this.server;
        serverData4.signaturesRequired = (serverData4.securityMode & 8) == 8;
        this.server.maxMpxCount = readInt2(bArr, i3);
        int i4 = i3 + 2;
        this.server.maxNumberVcs = readInt2(bArr, i4);
        int i5 = i4 + 2;
        this.server.maxBufferSize = readInt4(bArr, i5);
        int i6 = i5 + 4;
        this.server.maxRawSize = readInt4(bArr, i6);
        int i7 = i6 + 4;
        this.server.sessionKey = readInt4(bArr, i7);
        int i8 = i7 + 4;
        this.server.capabilities = readInt4(bArr, i8);
        int i9 = i8 + 4;
        this.server.serverTime = readTime(bArr, i9);
        int i10 = i9 + 8;
        this.server.serverTimeZone = readInt2(bArr, i10);
        int i11 = i10 + 2;
        this.server.encryptionKeyLength = bArr[i11] & UByte.MAX_VALUE;
        return (i11 + 1) - i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jcifs.smb.ServerMessageBlock
    public int readBytesWireFormat(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        if ((this.server.capabilities & Integer.MIN_VALUE) == 0) {
            SmbTransport.ServerData serverData = this.server;
            serverData.encryptionKey = new byte[serverData.encryptionKeyLength];
            System.arraycopy(bArr, i, this.server.encryptionKey, 0, this.server.encryptionKeyLength);
            i2 = this.server.encryptionKeyLength + i;
            if (this.byteCount > this.server.encryptionKeyLength) {
                try {
                    if ((this.flags2 & 32768) == 32768) {
                        do {
                            int i4 = i2 + i3;
                            if (bArr[i4] == 0 && bArr[i4 + 1] == 0) {
                                this.server.oemDomainName = new String(bArr, i2, i3, SmbConstants.UNI_ENCODING);
                            }
                            i3 += 2;
                        } while (i3 <= 256);
                        throw new RuntimeException("zero termination not found");
                    }
                    while (bArr[i2 + i3] != 0) {
                        i3++;
                        if (i3 > 256) {
                            throw new RuntimeException("zero termination not found");
                        }
                    }
                    this.server.oemDomainName = new String(bArr, i2, i3, ServerMessageBlock.OEM_ENCODING);
                } catch (UnsupportedEncodingException e) {
                    LogStream logStream = log;
                    if (LogStream.level > 1) {
                        e.printStackTrace(log);
                    }
                }
                i2 += i3;
            } else {
                this.server.oemDomainName = new String();
            }
        } else {
            this.server.guid = new byte[16];
            System.arraycopy(bArr, i, this.server.guid, 0, 16);
            this.server.oemDomainName = new String();
            i2 = i;
        }
        return i2 - i;
    }

    @Override // jcifs.smb.ServerMessageBlock
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmbComNegotiateResponse[");
        sb.append(super.toString());
        sb.append(",wordCount=");
        sb.append(this.wordCount);
        sb.append(",dialectIndex=");
        sb.append(this.dialectIndex);
        sb.append(",securityMode=0x");
        sb.append(Hexdump.toHexString(this.server.securityMode, 1));
        sb.append(",security=");
        sb.append(this.server.security == 0 ? "share" : "user");
        sb.append(",encryptedPasswords=");
        sb.append(this.server.encryptedPasswords);
        sb.append(",maxMpxCount=");
        sb.append(this.server.maxMpxCount);
        sb.append(",maxNumberVcs=");
        sb.append(this.server.maxNumberVcs);
        sb.append(",maxBufferSize=");
        sb.append(this.server.maxBufferSize);
        sb.append(",maxRawSize=");
        sb.append(this.server.maxRawSize);
        sb.append(",sessionKey=0x");
        sb.append(Hexdump.toHexString(this.server.sessionKey, 8));
        sb.append(",capabilities=0x");
        sb.append(Hexdump.toHexString(this.server.capabilities, 8));
        sb.append(",serverTime=");
        sb.append(new Date(this.server.serverTime));
        sb.append(",serverTimeZone=");
        sb.append(this.server.serverTimeZone);
        sb.append(",encryptionKeyLength=");
        sb.append(this.server.encryptionKeyLength);
        sb.append(",byteCount=");
        sb.append(this.byteCount);
        sb.append(",oemDomainName=");
        sb.append(this.server.oemDomainName);
        sb.append("]");
        return new String(sb.toString());
    }
}
