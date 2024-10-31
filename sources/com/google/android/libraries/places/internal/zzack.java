package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzack extends zzago implements zzahx {
    private static final zzack zzb;
    private int zzd;
    private int zze;
    private long zzf;
    private int zzg;

    static {
        zzack zzackVar = new zzack();
        zzb = zzackVar;
        zzago.zzI(zzack.class, zzackVar);
    }

    private zzack() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\b\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\b᠌\u0002", new Object[]{"zzd", "zze", zzacj.zza, "zzf", "zzg", zzyq.zza});
        }
        if (i2 == 3) {
            return new zzack();
        }
        if (i2 == 4) {
            return new zzaci(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
