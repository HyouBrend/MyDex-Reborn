package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzom extends zzago implements zzahx {
    private static final zzom zzb;
    private int zzd;
    private zzog zze;
    private zzoq zzf;
    private zzos zzg;
    private zzrl zzh;
    private int zzi;

    static {
        zzom zzomVar = new zzom();
        zzb = zzomVar;
        zzago.zzI(zzom.class, zzomVar);
    }

    private zzom() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဋ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzom();
        }
        if (i2 == 4) {
            return new zzol(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
