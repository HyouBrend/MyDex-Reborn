package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaa extends zzago implements zzahx {
    private static final zzaa zzb;
    private int zzd;
    private zzcb zze;

    static {
        zzaa zzaaVar = new zzaa();
        zzb = zzaaVar;
        zzago.zzI(zzaa.class, zzaaVar);
    }

    private zzaa() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzaa();
        }
        if (i2 == 4) {
            return new zzz(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
