package jcifs.dcerpc;

import jcifs.dcerpc.rpc;

/* loaded from: classes2.dex */
public class UnicodeString extends rpc.unicode_string {
    boolean zterm;

    public UnicodeString(boolean z) {
        this.zterm = z;
    }

    public UnicodeString(rpc.unicode_string unicode_stringVar, boolean z) {
        this.length = unicode_stringVar.length;
        this.maximum_length = unicode_stringVar.maximum_length;
        this.buffer = unicode_stringVar.buffer;
        this.zterm = z;
    }

    public UnicodeString(String str, boolean z) {
        this.zterm = z;
        int length = str.length();
        int i = length + (z ? 1 : 0);
        short s = (short) (i * 2);
        this.maximum_length = s;
        this.length = s;
        this.buffer = new short[i];
        int i2 = 0;
        while (i2 < length) {
            this.buffer[i2] = (short) str.charAt(i2);
            i2++;
        }
        if (z) {
            this.buffer[i2] = 0;
        }
    }

    public String toString() {
        int i = (this.length / 2) - (this.zterm ? 1 : 0);
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = (char) this.buffer[i2];
        }
        return new String(cArr, 0, i);
    }
}
