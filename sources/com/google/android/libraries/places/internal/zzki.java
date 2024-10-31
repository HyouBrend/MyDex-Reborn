package com.google.android.libraries.places.internal;

import com.j256.ormlite.stmt.query.SimpleComparison;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzki {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzki(Object obj, Object obj2, Object obj3) {
        this.zza = obj;
        this.zzb = obj2;
        this.zzc = obj3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final IllegalArgumentException zza() {
        return new IllegalArgumentException("Multiple entries with same key: " + String.valueOf(this.zza) + SimpleComparison.EQUAL_TO_OPERATION + String.valueOf(this.zzb) + " and " + String.valueOf(this.zza) + SimpleComparison.EQUAL_TO_OPERATION + String.valueOf(this.zzc));
    }
}
