package com.google.android.libraries.places.internal;

import java.util.Set;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzmq implements zzmk {
    private final String zza;
    private final Level zzb;
    private final Set zzc;
    private final zzma zzd;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzmq() {
        /*
            r8 = this;
            java.util.logging.Level r4 = java.util.logging.Level.ALL
            java.util.Set r6 = com.google.android.libraries.places.internal.zzmt.zzc()
            com.google.android.libraries.places.internal.zzma r7 = com.google.android.libraries.places.internal.zzmt.zzb()
            java.lang.String r1 = ""
            r2 = 1
            r3 = 0
            r5 = 0
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzmq.<init>():void");
    }

    private zzmq(String str, boolean z, boolean z2, Level level, boolean z3, Set set, zzma zzmaVar) {
        this.zza = "";
        this.zzb = level;
        this.zzc = set;
        this.zzd = zzmaVar;
    }

    @Override // com.google.android.libraries.places.internal.zzmk
    public final zzlp zza(String str) {
        return new zzmt(this.zza, str, true, false, this.zzb, this.zzc, this.zzd, null);
    }

    public final zzmq zzb(boolean z) {
        return new zzmq(this.zza, true, false, Level.OFF, false, this.zzc, this.zzd);
    }
}
