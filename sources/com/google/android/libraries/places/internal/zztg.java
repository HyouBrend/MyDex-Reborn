package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zztg extends zzago implements zzahx {
    private static final zztg zzb;
    private int zzd;
    private long zze;
    private int zzf;
    private int zzg;
    private float zzh;
    private float zzi;

    static {
        zztg zztgVar = new zztg();
        zzb = zztgVar;
        zzago.zzI(zztg.class, zztgVar);
    }

    private zztg() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001စ\u0000\u0002င\u0001\u0003င\u0002\u0004ခ\u0003\u0005ခ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zztg();
        }
        zzrv zzrvVar = null;
        if (i2 == 4) {
            return new zztf(zzrvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
