package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzadp extends zzago implements zzahx {
    private static final zzadp zzb;
    private int zzd;
    private int zze;
    private zzada zzf;

    static {
        zzadp zzadpVar = new zzadp();
        zzb = zzadpVar;
        zzago.zzI(zzadp.class, zzadpVar);
    }

    private zzadp() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", zzado.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzadp();
        }
        if (i2 == 4) {
            return new zzadn(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
