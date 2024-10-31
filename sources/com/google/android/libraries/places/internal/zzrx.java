package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzrx extends zzago implements zzahx {
    private static final zzrx zzb;
    private int zzd;
    private long zze;
    private long zzf;

    static {
        zzrx zzrxVar = new zzrx();
        zzb = zzrxVar;
        zzago.zzI(zzrx.class, zzrxVar);
    }

    private zzrx() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzrx();
        }
        zzrv zzrvVar = null;
        if (i2 == 4) {
            return new zzrw(zzrvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
