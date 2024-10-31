package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaju implements zzajv {
    private static final Object zza = new Object();
    private volatile zzajv zzb;
    private volatile Object zzc = zza;

    private zzaju(zzajv zzajvVar) {
        this.zzb = zzajvVar;
    }

    public static zzajv zza(zzajv zzajvVar) {
        return new zzaju(zzajvVar);
    }

    @Override // com.google.android.libraries.places.internal.zzajv
    public final Object zzb() {
        Object obj = this.zzc;
        if (obj != zza) {
            return obj;
        }
        if (this.zzb == null) {
            return this.zzc;
        }
        zzdh zzdhVar = new zzdh();
        this.zzc = zzdhVar;
        this.zzb = null;
        return zzdhVar;
    }
}
