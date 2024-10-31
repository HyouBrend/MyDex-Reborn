package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaef extends zzago implements zzahx {
    private static final zzaef zzb;
    private zzagw zzd = zzB();

    static {
        zzaef zzaefVar = new zzaef();
        zzb = zzaefVar;
        zzago.zzI(zzaef.class, zzaefVar);
    }

    private zzaef() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzaeq.class});
        }
        if (i2 == 3) {
            return new zzaef();
        }
        if (i2 == 4) {
            return new zzaee(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
