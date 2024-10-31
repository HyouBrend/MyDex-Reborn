package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabp extends zzago implements zzahx {
    private static final zzabp zzb;
    private int zzd;
    private zzzz zze;
    private int zzf;
    private int zzg;
    private zzach zzh;

    static {
        zzabp zzabpVar = new zzabp();
        zzb = zzabpVar;
        zzago.zzI(zzabp.class, zzabpVar);
    }

    private zzabp() {
    }

    public static zzabn zza() {
        return (zzabn) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzabp zzabpVar, int i) {
        zzabpVar.zzd |= 4;
        zzabpVar.zzg = i;
    }

    public static /* synthetic */ void zze(zzabp zzabpVar, zzach zzachVar) {
        zzachVar.getClass();
        zzabpVar.zzh = zzachVar;
        zzabpVar.zzd |= 8;
    }

    public static /* synthetic */ void zzf(zzabp zzabpVar, int i) {
        zzabpVar.zzf = i - 1;
        zzabpVar.zzd |= 2;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003င\u0002\u0004ဉ\u0003", new Object[]{"zzd", "zze", "zzf", zzabo.zza, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzabp();
        }
        if (i2 == 4) {
            return new zzabn(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
