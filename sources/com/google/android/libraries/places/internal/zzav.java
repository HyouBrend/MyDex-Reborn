package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzav extends zzago implements zzahx {
    private static final zzav zzb;
    private int zzd;
    private zzcl zze;
    private zzai zzf;

    static {
        zzav zzavVar = new zzav();
        zzb = zzavVar;
        zzago.zzI(zzav.class, zzavVar);
    }

    private zzav() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0001\u0002ဉ\u0000", new Object[]{"zzd", "zzf", "zze"});
        }
        if (i2 == 3) {
            return new zzav();
        }
        if (i2 == 4) {
            return new zzau(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
