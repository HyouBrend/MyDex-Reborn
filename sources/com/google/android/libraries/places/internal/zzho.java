package com.google.android.libraries.places.internal;

import android.content.Context;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzho {
    private final Context zza;

    public zzho(Context context) {
        zzjp.zzc(context, "Context must not be null.");
        this.zza = context;
    }

    public final zzkk zza() {
        String packageName = this.zza.getPackageName();
        String zza = zzhd.zza(this.zza.getPackageManager(), packageName);
        zzkj zzkjVar = new zzkj();
        if (packageName != null) {
            zzkjVar.zza("X-Android-Package", packageName);
        }
        if (zza != null) {
            zzkjVar.zza("X-Android-Cert", zza);
        }
        return zzkjVar.zzb();
    }
}
