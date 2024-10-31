package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzg extends zzago implements zzahx {
    private static final zzg zzb;
    private int zzd;
    private zzcb zze;
    private long zzf;
    private long zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;
    private long zzk;
    private boolean zzl;
    private zzar zzm;

    static {
        zzg zzgVar = new zzg();
        zzb = zzgVar;
        zzago.zzI(zzg.class, zzgVar);
    }

    private zzg() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ဇ\u0005\u0007ဂ\u0006\bဇ\u0007\tဉ\b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzas.zza, "zzi", zzm.zza, "zzj", "zzk", "zzl", "zzm"});
        }
        if (i2 == 3) {
            return new zzg();
        }
        if (i2 == 4) {
            return new zzf(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
