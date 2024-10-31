package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzy extends zzago implements zzahx {
    private static final zzy zzb;
    private int zzd;
    private zzcb zze;

    static {
        zzy zzyVar = new zzy();
        zzb = zzyVar;
        zzago.zzI(zzy.class, zzyVar);
    }

    private zzy() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzy();
        }
        if (i2 == 4) {
            return new zzx(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
