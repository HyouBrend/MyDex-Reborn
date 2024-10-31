package jcifs.netbios;

import java.io.UnsupportedEncodingException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NodeStatusResponse extends NameServicePacket {
    NbtAddress[] addressArray;
    private byte[] macAddress;
    private int numberOfNames;
    private NbtAddress queryAddress;
    private byte[] stats;

    @Override // jcifs.netbios.NameServicePacket
    int writeBodyWireFormat(byte[] bArr, int i) {
        return 0;
    }

    @Override // jcifs.netbios.NameServicePacket
    int writeRDataWireFormat(byte[] bArr, int i) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NodeStatusResponse(NbtAddress nbtAddress) {
        this.queryAddress = nbtAddress;
        this.recordName = new Name();
        this.macAddress = new byte[6];
    }

    @Override // jcifs.netbios.NameServicePacket
    int readBodyWireFormat(byte[] bArr, int i) {
        return readResourceRecordWireFormat(bArr, i);
    }

    @Override // jcifs.netbios.NameServicePacket
    int readRDataWireFormat(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        this.numberOfNames = i2;
        int i3 = i2 * 18;
        int i4 = (this.rDataLength - i3) - 1;
        int i5 = i + 1;
        this.numberOfNames = bArr[i] & UByte.MAX_VALUE;
        System.arraycopy(bArr, i3 + i5, this.macAddress, 0, 6);
        int readNodeNameArray = i5 + readNodeNameArray(bArr, i5);
        byte[] bArr2 = new byte[i4];
        this.stats = bArr2;
        System.arraycopy(bArr, readNodeNameArray, bArr2, 0, i4);
        return (readNodeNameArray + i4) - i;
    }

    private int readNodeNameArray(byte[] bArr, int i) {
        this.addressArray = new NbtAddress[this.numberOfNames];
        String str = this.queryAddress.hostName.scope;
        int i2 = i;
        boolean z = false;
        for (int i3 = 0; i3 < this.numberOfNames; i3++) {
            try {
                int i4 = i2 + 14;
                while (bArr[i4] == 32) {
                    i4--;
                }
                String str2 = new String(bArr, i2, (i4 - i2) + 1, Name.OEM_ENCODING);
                int i5 = bArr[i2 + 15] & UByte.MAX_VALUE;
                byte b = bArr[i2 + 16];
                boolean z2 = (b & ByteCompanionObject.MIN_VALUE) == 128;
                int i6 = (b & 96) >> 5;
                boolean z3 = (b & 16) == 16;
                boolean z4 = (b & 8) == 8;
                boolean z5 = (b & 4) == 4;
                boolean z6 = (b & 2) == 2;
                if (!z && this.queryAddress.hostName.hexCode == i5 && (this.queryAddress.hostName == NbtAddress.UNKNOWN_NAME || this.queryAddress.hostName.name.equals(str2))) {
                    if (this.queryAddress.hostName == NbtAddress.UNKNOWN_NAME) {
                        this.queryAddress.hostName = new Name(str2, i5, str);
                    }
                    this.queryAddress.groupName = z2;
                    this.queryAddress.nodeType = i6;
                    this.queryAddress.isBeingDeleted = z3;
                    this.queryAddress.isInConflict = z4;
                    this.queryAddress.isActive = z5;
                    this.queryAddress.isPermanent = z6;
                    this.queryAddress.macAddress = this.macAddress;
                    z = true;
                    this.queryAddress.isDataFromNodeStatus = true;
                    this.addressArray[i3] = this.queryAddress;
                } else {
                    this.addressArray[i3] = new NbtAddress(new Name(str2, i5, str), this.queryAddress.address, z2, i6, z3, z4, z5, z6, this.macAddress);
                }
                i2 += 18;
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return i2 - i;
    }

    @Override // jcifs.netbios.NameServicePacket
    public String toString() {
        return new String("NodeStatusResponse[" + super.toString() + "]");
    }
}
