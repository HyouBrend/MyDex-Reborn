package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzay extends zzago implements zzahx {
    private static final zzay zzb;
    private int zzd;
    private zzcl zze;
    private zzai zzf;
    private zzbc zzg;

    static {
        zzay zzayVar = new zzay();
        zzb = zzayVar;
        zzago.zzI(zzay.class, zzayVar);
    }

    private zzay() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0001\u0002ဉ\u0002\u0003ဉ\u0000", new Object[]{"zzd", "zzf", "zzg", "zze"});
        }
        if (i2 == 3) {
            return new zzay();
        }
        if (i2 == 4) {
            return new zzax(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}