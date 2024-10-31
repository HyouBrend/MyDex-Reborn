package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzww extends zzago implements zzahx {
    private static final zzww zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zzww zzwwVar = new zzww();
        zzb = zzwwVar;
        zzago.zzI(zzww.class, zzwwVar);
    }

    private zzww() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004င\u0003", new Object[]{"zzd", "zze", zzwu.zza, "zzf", zzwv.zza, "zzg", zzwx.zza, "zzh"});
        }
        if (i2 == 3) {
            return new zzww();
        }
        if (i2 == 4) {
            return new zzwt(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
