package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zztm extends zzago implements zzahx {
    private static final zztm zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private float zzg;
    private long zzh;

    static {
        zztm zztmVar = new zztm();
        zzb = zztmVar;
        zzago.zzI(zztm.class, zztmVar);
    }

    private zztm() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ဂ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zztm();
        }
        zzrv zzrvVar = null;
        if (i2 == 4) {
            return new zztl(zzrvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
