package jcifs.smb;

import java.io.Serializable;
import jcifs.UniAddress;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
public final class NtlmChallenge implements Serializable {
    public byte[] challenge;
    public UniAddress dc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NtlmChallenge(byte[] bArr, UniAddress uniAddress) {
        this.challenge = bArr;
        this.dc = uniAddress;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NtlmChallenge[challenge=0x");
        byte[] bArr = this.challenge;
        sb.append(Hexdump.toHexString(bArr, 0, bArr.length * 2));
        sb.append(",dc=");
        sb.append(this.dc.toString());
        sb.append("]");
        return sb.toString();
    }
}
