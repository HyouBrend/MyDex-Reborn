package com.google.android.libraries.places.internal;

import java.io.IOException;
import kotlin.UByte;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zznq extends zznr {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zznq(java.lang.String r2, java.lang.String r3, @javax.annotation.CheckForNull java.lang.Character r4) {
        /*
            r1 = this;
            com.google.android.libraries.places.internal.zzno r0 = new com.google.android.libraries.places.internal.zzno
            char[] r3 = r3.toCharArray()
            r0.<init>(r2, r3)
            r1.<init>(r0, r4)
            char[] r2 = com.google.android.libraries.places.internal.zzno.zzc(r0)
            int r2 = r2.length
            r3 = 64
            if (r2 != r3) goto L17
            r2 = 1
            goto L18
        L17:
            r2 = 0
        L18:
            com.google.android.libraries.places.internal.zzjp.zzd(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zznq.<init>(java.lang.String, java.lang.String, java.lang.Character):void");
    }

    @Override // com.google.android.libraries.places.internal.zznr, com.google.android.libraries.places.internal.zzns
    final void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzjp.zzi(0, i2, bArr.length);
        for (int i4 = i2; i4 >= 3; i4 -= 3) {
            int i5 = i3 + 1;
            int i6 = bArr[i3] & UByte.MAX_VALUE;
            int i7 = bArr[i5] & UByte.MAX_VALUE;
            int i8 = i5 + 1;
            int i9 = (i6 << 16) | (i7 << 8) | (bArr[i8] & UByte.MAX_VALUE);
            appendable.append(this.zzb.zza(i9 >>> 18));
            appendable.append(this.zzb.zza((i9 >>> 12) & 63));
            appendable.append(this.zzb.zza((i9 >>> 6) & 63));
            appendable.append(this.zzb.zza(i9 & 63));
            i3 = i8 + 1;
        }
        if (i3 < i2) {
            zzc(appendable, bArr, i3, i2 - i3);
        }
    }
}
