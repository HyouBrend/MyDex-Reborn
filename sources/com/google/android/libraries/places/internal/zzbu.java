package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbu extends zzago implements zzahx {
    private static final zzbu zzb;
    private int zzd;
    private int zze = 1;
    private zzbw zzf;
    private zzbp zzg;
    private zzcl zzh;
    private zzbr zzi;
    private zzby zzj;

    static {
        zzbu zzbuVar = new zzbu();
        zzb = zzbuVar;
        zzago.zzI(zzbu.class, zzbuVar);
    }

    private zzbu() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005", new Object[]{"zzd", "zze", zzbt.zza, "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzbu();
        }
        if (i2 == 4) {
            return new zzbs(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
