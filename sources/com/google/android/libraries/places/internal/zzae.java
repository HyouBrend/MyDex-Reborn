package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzae extends zzago implements zzahx {
    private static final zzae zzb;
    private int zzd;
    private zzcl zze;
    private boolean zzf;
    private zzagw zzg = zzB();
    private zzagw zzh = zzB();
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private long zzm;

    static {
        zzae zzaeVar = new zzae();
        zzb = zzaeVar;
        zzago.zzI(zzae.class, zzaeVar);
    }

    private zzae() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဇ\u0001\u0003\u001b\u0004\u001b\u0005င\u0002\u0006င\u0003\u0007င\u0004\bင\u0005\tဂ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", zzar.class, "zzh", zzar.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
        }
        if (i2 == 3) {
            return new zzae();
        }
        if (i2 == 4) {
            return new zzad(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
