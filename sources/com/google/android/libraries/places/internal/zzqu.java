package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzqu extends zzago implements zzahx {
    private static final zzqu zzb;

    static {
        zzqu zzquVar = new zzqu();
        zzb = zzquVar;
        zzago.zzI(zzqu.class, zzquVar);
    }

    private zzqu() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzqs zzqsVar = null;
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0000", null);
        }
        if (i2 == 3) {
            return new zzqu();
        }
        if (i2 == 4) {
            return new zzqt(zzqsVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
