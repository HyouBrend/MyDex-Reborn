package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzsi extends zzago implements zzahx {
    private static final zzsi zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private long zzg;
    private int zzh;
    private boolean zzi;
    private long zzj;
    private zzod zzk;
    private zzagw zzl = zzB();

    static {
        zzsi zzsiVar = new zzsi();
        zzb = zzsiVar;
        zzago.zzI(zzsi.class, zzsiVar);
    }

    private zzsi() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003ဂ\u0002\u0004င\u0003\u0005ဇ\u0004\u0006ဂ\u0005\u0007ဉ\u0006\b\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzod.class});
        }
        if (i2 == 3) {
            return new zzsi();
        }
        if (i2 == 4) {
            return new zzsh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
