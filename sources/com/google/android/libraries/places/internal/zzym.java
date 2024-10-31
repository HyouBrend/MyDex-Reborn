package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzym extends zzago implements zzahx {
    private static final zzym zzb;
    private int zzd;
    private int zze;
    private zzyj zzf;
    private zzyj zzg;
    private int zzh;
    private zzyo zzi;
    private zzxg zzj;

    static {
        zzym zzymVar = new zzym();
        zzb = zzymVar;
        zzago.zzI(zzym.class, zzymVar);
    }

    private zzym() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004᠌\u0003\u0005ဉ\u0004\u0006ဉ\u0005", new Object[]{"zzd", "zze", zzyk.zza, "zzf", "zzg", "zzh", zzyl.zza, "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzym();
        }
        if (i2 == 4) {
            return new zzyg(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
