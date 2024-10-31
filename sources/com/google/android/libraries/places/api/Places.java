package com.google.android.libraries.places.api;

import android.content.Context;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zzgx;
import com.google.android.libraries.places.internal.zzgy;
import com.google.android.libraries.places.internal.zzgz;
import com.google.android.libraries.places.internal.zzhb;
import com.google.android.libraries.places.internal.zzhi;
import com.google.android.libraries.places.internal.zzhk;
import com.google.android.libraries.places.internal.zzjp;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class Places {
    private static final zzhb zza = new zzhb();
    private static volatile zzgz zzb;

    private Places() {
    }

    public static synchronized PlacesClient createClient(Context context) {
        PlacesClient zza2;
        synchronized (Places.class) {
            try {
                zzjp.zzc(context, "Context must not be null.");
                zza2 = zza(context, zzhi.zzd(context).zze());
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
        return zza2;
    }

    public static synchronized void deinitialize() {
        synchronized (Places.class) {
            zza.zzc();
        }
    }

    public static void initialize(Context context, String str) {
        try {
            zzb(context, str, null, false);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public static synchronized boolean isInitialized() {
        boolean zzf;
        synchronized (Places.class) {
            try {
                zzf = zza.zzf();
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
        return zzf;
    }

    public static synchronized PlacesClient zza(Context context, zzhi zzhiVar) {
        PlacesClient zza2;
        synchronized (Places.class) {
            try {
                zzjp.zzc(context, "Context must not be null.");
                zzjp.zzk(isInitialized(), "Places must be initialized first.");
                zzgy zza3 = zzgx.zza();
                zza3.zzc(context);
                zza3.zza(zza);
                zza3.zzb(zzhiVar);
                zza2 = zza3.zzd().zza();
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
        return zza2;
    }

    public static synchronized void zzb(Context context, String str, Locale locale, boolean z) {
        synchronized (Places.class) {
            try {
                zzjp.zzc(context, "Application context must not be null.");
                zzjp.zzc(str, "API Key must not be null.");
                zzjp.zze(!str.isEmpty(), "API Key must not be empty.");
                zzhk.zza(context.getApplicationContext(), false);
                zza.zzd(str, locale, false);
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
    }

    public static synchronized zzhb zzc() {
        zzhb zzhbVar;
        synchronized (Places.class) {
            zzhbVar = zza;
        }
        return zzhbVar;
    }

    public static synchronized void initialize(Context context, String str, Locale locale) {
        synchronized (Places.class) {
            try {
                zzb(context, str, locale, false);
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
    }
}
