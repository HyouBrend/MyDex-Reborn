package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzrn extends zzago implements zzahx {
    private static final zzrn zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzrn zzrnVar = new zzrn();
        zzb = zzrnVar;
        zzago.zzI(zzrn.class, zzrnVar);
    }

    private zzrn() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဋ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzrn();
        }
        zzrg zzrgVar = null;
        if (i2 == 4) {
            return new zzrm(zzrgVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
