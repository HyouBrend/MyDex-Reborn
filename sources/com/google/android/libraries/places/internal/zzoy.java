package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzoy extends zzago implements zzahx {
    private static final zzoy zzb;
    private int zzd;
    private int zze;
    private zzpa zzf;
    private zzpi zzg;
    private zzpd zzh;
    private zzpg zzi;

    static {
        zzoy zzoyVar = new zzoy();
        zzb = zzoyVar;
        zzago.zzI(zzoy.class, zzoyVar);
    }

    private zzoy() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004", new Object[]{"zzd", "zze", zzox.zza, "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzoy();
        }
        if (i2 == 4) {
            return new zzow(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
