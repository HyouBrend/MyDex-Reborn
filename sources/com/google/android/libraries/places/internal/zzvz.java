package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzvz extends zzago implements zzahx {
    private static final zzvz zzb;
    private int zzd = 0;
    private Object zze;

    static {
        zzvz zzvzVar = new zzvz();
        zzb = zzvzVar;
        zzago.zzI(zzvz.class, zzvzVar);
    }

    private zzvz() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001<\u0000", new Object[]{"zze", "zzd", zzqw.class});
        }
        if (i2 == 3) {
            return new zzvz();
        }
        if (i2 == 4) {
            return new zzvy(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
