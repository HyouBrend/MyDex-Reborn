package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzl extends zzago implements zzahx {
    private static final zzl zzb;
    private int zzd;
    private zzcb zze;
    private long zzf;
    private int zzg;
    private boolean zzh;
    private zzar zzi;

    static {
        zzl zzlVar = new zzl();
        zzb = zzlVar;
        zzago.zzI(zzl.class, zzlVar);
    }

    private zzl() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဂ\u0001\u0003᠌\u0002\u0004ဇ\u0003\u0005ဉ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", zzm.zza, "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzl();
        }
        if (i2 == 4) {
            return new zzk(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
