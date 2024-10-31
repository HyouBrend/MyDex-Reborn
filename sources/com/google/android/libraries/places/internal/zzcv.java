package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzcv extends zzago implements zzahx {
    private static final zzcv zzb;
    private int zzd;
    private int zze;
    private zzagw zzf = zzB();
    private zzagw zzg = zzB();

    static {
        zzcv zzcvVar = new zzcv();
        zzb = zzcvVar;
        zzago.zzI(zzcv.class, zzcvVar);
    }

    private zzcv() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001✐\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b✐᠌\u0000", new Object[]{"zzd", "zzf", zzda.class, "zzg", zzdd.class, "zze", zzcu.zza});
        }
        if (i2 == 3) {
            return new zzcv();
        }
        if (i2 == 4) {
            return new zzct(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
