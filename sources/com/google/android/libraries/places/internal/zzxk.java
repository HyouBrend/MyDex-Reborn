package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzxk extends zzago implements zzahx {
    private static final zzxk zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;

    static {
        zzxk zzxkVar = new zzxk();
        zzb = zzxkVar;
        zzago.zzI(zzxk.class, zzxkVar);
    }

    private zzxk() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006ဇ\u0005\u0007᠌\u0006\b᠌\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzxj.zza, "zzl", zzxi.zza});
        }
        if (i2 == 3) {
            return new zzxk();
        }
        if (i2 == 4) {
            return new zzxh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
