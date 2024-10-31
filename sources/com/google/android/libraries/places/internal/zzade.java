package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzade extends zzago implements zzahx {
    private static final zzade zzb;
    private int zzd;
    private int zze;
    private zzagw zzf = zzago.zzB();
    private zzagw zzg = zzB();

    static {
        zzade zzadeVar = new zzade();
        zzb = zzadeVar;
        zzago.zzI(zzade.class, zzadeVar);
    }

    private zzade() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001င\u0000\u0002\u001a\u0003\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", zzadg.class});
        }
        if (i2 == 3) {
            return new zzade();
        }
        if (i2 == 4) {
            return new zzadd(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
