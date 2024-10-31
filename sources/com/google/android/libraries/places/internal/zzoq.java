package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzoq extends zzago implements zzahx {
    private static final zzoq zzb;
    private int zzd;
    private zzoo zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    static {
        zzoq zzoqVar = new zzoq();
        zzb = zzoqVar;
        zzago.zzI(zzoq.class, zzoqVar);
    }

    private zzoq() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0005ဋ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzoq();
        }
        if (i2 == 4) {
            return new zzop(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
