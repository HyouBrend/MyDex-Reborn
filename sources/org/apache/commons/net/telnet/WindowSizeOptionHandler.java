package org.apache.commons.net.telnet;

/* loaded from: classes2.dex */
public class WindowSizeOptionHandler extends TelnetOptionHandler {
    protected static final int WINDOW_SIZE = 31;
    private int m_nHeight;
    private int m_nWidth;

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] answerSubnegotiation(int[] iArr, int i) {
        return null;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationRemote() {
        return null;
    }

    public WindowSizeOptionHandler(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4) {
        super(31, z, z2, z3, z4);
        this.m_nWidth = i;
        this.m_nHeight = i2;
    }

    public WindowSizeOptionHandler(int i, int i2) {
        super(31, false, false, false, false);
        this.m_nWidth = i;
        this.m_nHeight = i2;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationLocal() {
        int i = this.m_nWidth;
        int i2 = this.m_nHeight;
        int i3 = (65536 * i) + i2;
        int i4 = i % 256 == 255 ? 6 : 5;
        if (i / 256 == 255) {
            i4++;
        }
        if (i2 % 256 == 255) {
            i4++;
        }
        if (i2 / 256 == 255) {
            i4++;
        }
        int[] iArr = new int[i4];
        iArr[0] = 31;
        int i5 = 24;
        int i6 = 1;
        while (i6 < i4) {
            int i7 = ((255 << i5) & i3) >>> i5;
            iArr[i6] = i7;
            if (i7 == 255) {
                i6++;
                iArr[i6] = 255;
            }
            i6++;
            i5 -= 8;
        }
        return iArr;
    }
}
