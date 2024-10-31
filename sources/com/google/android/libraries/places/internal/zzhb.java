package com.google.android.libraries.places.internal;

import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzhb {
    private volatile String zza;
    private volatile Locale zzb;
    private volatile boolean zzc;

    public final synchronized String zza() {
        zzjp.zzk(zzf(), "ApiConfig must be initialized.");
        this.zza.getClass();
        return this.zza;
    }

    public final synchronized Locale zzb() {
        zzjp.zzk(zzf(), "ApiConfig must be initialized.");
        return this.zzb == null ? Locale.getDefault() : this.zzb;
    }

    public final synchronized void zzc() {
        this.zza = null;
        this.zzb = null;
    }

    public final synchronized void zzd(String str, Locale locale, boolean z) {
        zzjp.zzc(str, "API Key must not be null.");
        zzjp.zze(!str.isEmpty(), "API Key must not be empty.");
        this.zza = str;
        this.zzb = locale;
        this.zzc = false;
    }

    public final boolean zze() {
        return false;
    }

    public final synchronized boolean zzf() {
        return this.zza != null;
    }
}
