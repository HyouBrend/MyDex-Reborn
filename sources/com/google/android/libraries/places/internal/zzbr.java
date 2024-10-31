package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbr extends zzago implements zzahx {
    private static final zzbr zzb;
    private int zzd;
    private zzcb zze;
    private long zzf;
    private long zzg;
    private boolean zzh;
    private int zzi;
    private int zzj;

    static {
        zzbr zzbrVar = new zzbr();
        zzb = zzbrVar;
        zzago.zzI(zzbr.class, zzbrVar);
    }

    private zzbr() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဇ\u0003\u0005င\u0004\u0006င\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzbr();
        }
        if (i2 == 4) {
            return new zzbq(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
