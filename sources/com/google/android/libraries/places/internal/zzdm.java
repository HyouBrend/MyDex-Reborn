package com.google.android.libraries.places.internal;

import java.util.Arrays;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzdm {
    private final String zza;

    private zzdm(String str) {
        this.zza = str;
    }

    public static zzdm zza(zzdm zzdmVar, zzdm... zzdmVarArr) {
        return new zzdm(zzdmVar.zza.concat(zzjk.zzb("").zze(zzkq.zza(Arrays.asList(zzdmVarArr), zzdl.zza))));
    }

    public static zzdm zzb(String str) {
        return new zzdm(str);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzdm) {
            return this.zza.equals(((zzdm) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza;
    }
}
