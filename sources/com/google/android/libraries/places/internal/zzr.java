package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzr extends zzago implements zzahx {
    private static final zzr zzb;
    private int zzd;
    private zzcb zze;
    private boolean zzf;

    static {
        zzr zzrVar = new zzr();
        zzb = zzrVar;
        zzago.zzI(zzr.class, zzrVar);
    }

    private zzr() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzr();
        }
        if (i2 == 4) {
            return new zzq(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
