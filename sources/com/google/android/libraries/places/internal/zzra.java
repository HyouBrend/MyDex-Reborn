package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzra extends zzago implements zzahx {
    private static final zzra zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        zzra zzraVar = new zzra();
        zzb = zzraVar;
        zzago.zzI(zzra.class, zzraVar);
    }

    private zzra() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzra();
        }
        zzqs zzqsVar = null;
        if (i2 == 4) {
            return new zzqz(zzqsVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}