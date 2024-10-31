package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzrs extends zzago implements zzahx {
    private static final zzrs zzb;
    private int zzd;
    private int zze;

    static {
        zzrs zzrsVar = new zzrs();
        zzb = zzrsVar;
        zzago.zzI(zzrs.class, zzrsVar);
    }

    private zzrs() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzrr.zza});
        }
        if (i2 == 3) {
            return new zzrs();
        }
        if (i2 == 4) {
            return new zzrq(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
