package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzpi extends zzago implements zzahx {
    private static final zzpi zzb;
    private int zzd;
    private long zze;

    static {
        zzpi zzpiVar = new zzpi();
        zzb = zzpiVar;
        zzago.zzI(zzpi.class, zzpiVar);
    }

    private zzpi() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဂ\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzpi();
        }
        zzot zzotVar = null;
        if (i2 == 4) {
            return new zzph(zzotVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
