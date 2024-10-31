package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzqw extends zzago implements zzahx {
    private static final zzqw zzb;
    private int zzd = 0;
    private Object zze;

    static {
        zzqw zzqwVar = new zzqw();
        zzb = zzqwVar;
        zzago.zzI(zzqw.class, zzqwVar);
    }

    private zzqw() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"zze", "zzd", zzra.class, zzqu.class, zzqy.class});
        }
        if (i2 == 3) {
            return new zzqw();
        }
        if (i2 == 4) {
            return new zzqv(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
