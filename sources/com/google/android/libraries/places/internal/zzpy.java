package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzpy extends zzago implements zzahx {
    private static final zzpy zzb;
    private int zzd;
    private zzqa zzf;
    private zzqc zzg;
    private zzaey zzh;
    private zzqe zzi;
    private zzqi zzj;
    private byte zzk = 2;
    private int zze = 1;

    static {
        zzpy zzpyVar = new zzpy();
        zzb = zzpyVar;
        zzago.zzI(zzpy.class, zzpyVar);
    }

    private zzpy() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzk);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0001\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005", new Object[]{"zzd", "zze", zzpx.zza, "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzpy();
        }
        if (i2 == 4) {
            return new zzpw(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzk = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
