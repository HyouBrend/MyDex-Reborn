package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabg extends zzago implements zzahx {
    private static final zzagu zzb = new zzabe();
    private static final zzabg zzd;
    private zzagt zze = zzz();

    static {
        zzabg zzabgVar = new zzabg();
        zzd = zzabgVar;
        zzago.zzI(zzabg.class, zzabgVar);
    }

    private zzabg() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001ࠞ", new Object[]{"zze", zzyr.zza});
        }
        if (i2 == 3) {
            return new zzabg();
        }
        if (i2 == 4) {
            return new zzabf(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzd;
    }
}
