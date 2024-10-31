package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabm extends zzago implements zzahx {
    private static final zzabm zzb;

    static {
        zzabm zzabmVar = new zzabm();
        zzb = zzabmVar;
        zzago.zzI(zzabm.class, zzabmVar);
    }

    private zzabm() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzyp zzypVar = null;
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0000", null);
        }
        if (i2 == 3) {
            return new zzabm();
        }
        if (i2 == 4) {
            return new zzabl(zzypVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
