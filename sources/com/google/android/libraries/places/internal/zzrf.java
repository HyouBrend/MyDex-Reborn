package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzrf extends zzago implements zzahx {
    private static final zzrf zzb;
    private int zzd;
    private zzrd zze;
    private zzrd zzf;
    private byte zzg = 2;

    static {
        zzrf zzrfVar = new zzrf();
        zzb = zzrfVar;
        zzago.zzI(zzrf.class, zzrfVar);
    }

    private zzrf() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᐉ\u0000\u0002ᐉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzrf();
        }
        if (i2 == 4) {
            return new zzre(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
