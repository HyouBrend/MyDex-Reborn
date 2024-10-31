package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zztt extends zzago implements zzahx {
    private static final zztt zzb;
    private int zzd;
    private int zze;
    private float zzf;
    private int zzg;
    private float zzh;
    private int zzi;

    static {
        zztt zzttVar = new zztt();
        zzb = zzttVar;
        zzago.zzI(zztt.class, zzttVar);
    }

    private zztt() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001င\u0000\u0002ခ\u0001\u0003င\u0002\u0004ခ\u0003\u0005င\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zztt();
        }
        zzrv zzrvVar = null;
        if (i2 == 4) {
            return new zzts(zzrvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
