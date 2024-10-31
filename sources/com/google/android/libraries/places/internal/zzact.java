package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzact extends zzago implements zzahx {
    private static final zzact zzb;
    private zzagw zzd = zzago.zzB();
    private zzagw zze = zzB();

    static {
        zzact zzactVar = new zzact();
        zzb = zzactVar;
        zzago.zzI(zzact.class, zzactVar);
    }

    private zzact() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001a\u0002\u001b", new Object[]{"zzd", "zze", zzyu.class});
        }
        if (i2 == 3) {
            return new zzact();
        }
        if (i2 == 4) {
            return new zzacs(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
