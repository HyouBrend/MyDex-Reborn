package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzty extends zzago implements zzahx {
    private static final zzty zzb;
    private int zzd;
    private int zze;
    private float zzf;

    static {
        zzty zztyVar = new zzty();
        zzb = zztyVar;
        zzago.zzI(zzty.class, zztyVar);
    }

    private zzty() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0010\u0011\u0002\u0000\u0000\u0000\u0010᠌\u0000\u0011ခ\u0001", new Object[]{"zzd", "zze", zzpu.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzty();
        }
        if (i2 == 4) {
            return new zztx(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
