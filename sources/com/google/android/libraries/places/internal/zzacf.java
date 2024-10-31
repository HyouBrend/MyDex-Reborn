package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzacf extends zzago implements zzahx {
    private static final zzacf zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;

    static {
        zzacf zzacfVar = new zzacf();
        zzb = zzacfVar;
        zzago.zzI(zzacf.class, zzacfVar);
    }

    private zzacf() {
    }

    public static zzacd zza() {
        return (zzacd) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzacf zzacfVar, int i) {
        zzacfVar.zze = 1;
        zzacfVar.zzd = 1 | zzacfVar.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003ဇ\u0002", new Object[]{"zzd", "zze", zzace.zza, "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzacf();
        }
        if (i2 == 4) {
            return new zzacd(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
