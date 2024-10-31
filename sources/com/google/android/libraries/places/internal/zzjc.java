package com.google.android.libraries.places.internal;

import net.lingala.zip4j.crypto.PBKDF2.BinTools;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzjc extends zzjb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjc(char c) {
    }

    public final String toString() {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        int i = 46;
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[5 - i2] = BinTools.hex.charAt(i & 15);
            i >>= 4;
        }
        return "CharMatcher.is('" + String.copyValueOf(cArr) + "')";
    }
}
