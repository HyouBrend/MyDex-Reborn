package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzpa extends zzago implements zzahx {
    private static final zzpa zzb;
    private int zzd;
    private zzagw zze = zzB();
    private int zzf;

    static {
        zzpa zzpaVar = new zzpa();
        zzb = zzpaVar;
        zzago.zzI(zzpa.class, zzpaVar);
    }

    private zzpa() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002င\u0000", new Object[]{"zzd", "zze", zzov.class, "zzf"});
        }
        if (i2 == 3) {
            return new zzpa();
        }
        if (i2 == 4) {
            return new zzoz(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
