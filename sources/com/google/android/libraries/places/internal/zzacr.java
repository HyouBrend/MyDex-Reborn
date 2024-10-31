package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzacr extends zzago implements zzahx {
    private static final zzacr zzb;
    private zzagw zzd = zzago.zzB();

    static {
        zzacr zzacrVar = new zzacr();
        zzb = zzacrVar;
        zzago.zzI(zzacr.class, zzacrVar);
    }

    private zzacr() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        }
        if (i2 == 3) {
            return new zzacr();
        }
        zzyp zzypVar = null;
        if (i2 == 4) {
            return new zzacq(zzypVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
