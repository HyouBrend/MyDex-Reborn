package com.google.android.libraries.places.internal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzmg {
    private static String zza = "com.google.android.libraries.places.internal.zzml";
    private static String zzb = "com.google.common.flogger.backend.google.GooglePlatform";
    private static String zzc = "com.google.common.flogger.backend.system.DefaultPlatform";
    private static final String[] zzd = {"com.google.android.libraries.places.internal.zzml", "com.google.common.flogger.backend.google.GooglePlatform", "com.google.common.flogger.backend.system.DefaultPlatform"};

    public static int zza() {
        return zznl.zza();
    }

    public static long zzb() {
        return zzme.zza().zzc();
    }

    public static zzlp zzd(String str) {
        return zzme.zza().zze(str);
    }

    public static zzlr zzf() {
        return zzi().zza();
    }

    public static zzmf zzg() {
        return zzme.zza().zzh();
    }

    public static zzmu zzi() {
        return zzme.zza().zzj();
    }

    public static zznh zzk() {
        return zzi().zzc();
    }

    public static String zzl() {
        return zzme.zza().zzm();
    }

    public static boolean zzn(String str, Level level, boolean z) {
        zzi().zzd(str, level, z);
        return false;
    }

    protected long zzc() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }

    protected abstract zzlp zze(String str);

    protected abstract zzmf zzh();

    protected zzmu zzj() {
        return zzmu.zze();
    }

    protected abstract String zzm();
}
