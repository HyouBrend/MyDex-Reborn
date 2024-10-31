package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabd extends zzago implements zzahx {
    private static final zzabd zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    static {
        zzabd zzabdVar = new zzabd();
        zzb = zzabdVar;
        zzago.zzI(zzabd.class, zzabdVar);
    }

    private zzabd() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0005᠌\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", zzyx.zza});
        }
        if (i2 == 3) {
            return new zzabd();
        }
        if (i2 == 4) {
            return new zzabc(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
