package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabu extends zzago implements zzahx {
    private static final zzabu zzb;
    private int zzd;
    private int zze;
    private int zzg;
    private int zzh;
    private long zzi;
    private boolean zzk;
    private zzagw zzf = zzago.zzB();
    private String zzj = "";

    static {
        zzabu zzabuVar = new zzabu();
        zzb = zzabuVar;
        zzago.zzI(zzabu.class, zzabuVar);
    }

    private zzabu() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001a\u0003င\u0001\u0004᠌\u0002\u0005ဂ\u0003\u0006ဈ\u0004\u0007ဇ\u0005", new Object[]{"zzd", "zze", zzzn.zza, "zzf", "zzg", "zzh", zzabx.zza, "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzabu();
        }
        if (i2 == 4) {
            return new zzabt(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
