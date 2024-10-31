package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzrz extends zzago implements zzahx {
    private static final zzrz zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private int zzg;
    private int zzh;

    static {
        zzrz zzrzVar = new zzrz();
        zzb = zzrzVar;
        zzago.zzI(zzrz.class, zzrzVar);
    }

    private zzrz() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzrz();
        }
        zzrv zzrvVar = null;
        if (i2 == 4) {
            return new zzry(zzrvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
