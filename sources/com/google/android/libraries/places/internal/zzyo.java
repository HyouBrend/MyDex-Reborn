package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzyo extends zzago implements zzahx {
    private static final zzyo zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;

    static {
        zzyo zzyoVar = new zzyo();
        zzb = zzyoVar;
        zzago.zzI(zzyo.class, zzyoVar);
    }

    private zzyo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzyo();
        }
        zzwa zzwaVar = null;
        if (i2 == 4) {
            return new zzyn(zzwaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
