package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzya extends zzago implements zzahx {
    private static final zzya zzb;
    private int zzd;
    private int zze;

    static {
        zzya zzyaVar = new zzya();
        zzb = zzyaVar;
        zzago.zzI(zzya.class, zzyaVar);
    }

    private zzya() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzya();
        }
        zzwa zzwaVar = null;
        if (i2 == 4) {
            return new zzxz(zzwaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}