package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzux extends zzago implements zzahx {
    private static final zzagu zzb = new zzus();
    private static final zzagu zzd = new zzut();
    private static final zzux zze;
    private int zzf;
    private long zzh;
    private zzagw zzg = zzB();
    private zzagt zzi = zzz();
    private zzagt zzj = zzz();

    static {
        zzux zzuxVar = new zzux();
        zze = zzuxVar;
        zzago.zzI(zzux.class, zzuxVar);
    }

    private zzux() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzags zzagsVar = zzpu.zza;
            return zzF(zze, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0003\u0000\u0001\u001b\u0002ဂ\u0000\u0003ࠬ\u0004ࠬ", new Object[]{"zzf", "zzg", zzuw.class, "zzh", "zzi", zzagsVar, "zzj", zzagsVar});
        }
        if (i2 == 3) {
            return new zzux();
        }
        if (i2 == 4) {
            return new zzuu(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zze;
    }
}
