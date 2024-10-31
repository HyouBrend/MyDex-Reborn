package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzafe extends zzago implements zzahx {
    private static final zzafe zzb;
    private int zzd;
    private boolean zze;

    static {
        zzafe zzafeVar = new zzafe();
        zzb = zzafeVar;
        zzago.zzI(zzafe.class, zzafeVar);
    }

    private zzafe() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzafe();
        }
        zzafc zzafcVar = null;
        if (i2 == 4) {
            return new zzafd(zzafcVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
