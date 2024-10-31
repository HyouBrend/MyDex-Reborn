package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzb extends zzago implements zzahx {
    private static final zzagu zzb = new zzyy();
    private static final zzzb zzd;
    private int zze;
    private zzagt zzf = zzz();
    private int zzg;

    static {
        zzzb zzzbVar = new zzzb();
        zzd = zzzbVar;
        zzago.zzI(zzzb.class, zzzbVar);
    }

    private zzzb() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzd, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ࠞ\u0002င\u0000", new Object[]{"zze", "zzf", zzyz.zza, "zzg"});
        }
        if (i2 == 3) {
            return new zzzb();
        }
        if (i2 == 4) {
            return new zzza(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzd;
    }
}
