package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbj extends zzago implements zzahx {
    private static final zzbj zzb;
    private int zzd;
    private zzai zze;
    private zzbm zzf;
    private zzcl zzg;

    static {
        zzbj zzbjVar = new zzbj();
        zzb = zzbjVar;
        zzago.zzI(zzbj.class, zzbjVar);
    }

    private zzbj() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzbj();
        }
        if (i2 == 4) {
            return new zzbi(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
