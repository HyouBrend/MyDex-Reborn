package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzby extends zzago implements zzahx {
    private static final zzby zzb;
    private int zzd;
    private zzcb zze;
    private long zzf;
    private long zzg;
    private boolean zzh;
    private int zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;

    static {
        zzby zzbyVar = new zzby();
        zzb = zzbyVar;
        zzago.zzI(zzby.class, zzbyVar);
    }

    private zzby() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဇ\u0003\u0005င\u0004\u0006ဇ\u0005\u0007င\u0007\bင\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzl", "zzk"});
        }
        if (i2 == 3) {
            return new zzby();
        }
        if (i2 == 4) {
            return new zzbx(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}