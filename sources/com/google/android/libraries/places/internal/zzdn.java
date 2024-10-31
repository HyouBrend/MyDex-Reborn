package com.google.android.libraries.places.internal;

import android.os.SystemClock;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzdn {
    private static final zzde zza = new zzdh();
    private static final zzdn zzb = new zzdn(SystemClock.elapsedRealtime());

    private zzdn() {
        SystemClock.elapsedRealtime();
    }

    private zzdn(long j) {
    }

    public static zzdn zza() {
        return new zzdn(SystemClock.elapsedRealtime());
    }
}
