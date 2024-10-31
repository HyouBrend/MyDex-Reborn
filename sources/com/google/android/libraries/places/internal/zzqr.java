package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzqr extends zzago implements zzahx {
    private static final zzqr zzb;
    private int zzd;
    private zzqn zze;
    private zzagw zzf = zzB();
    private int zzg;
    private int zzh;

    static {
        zzqr zzqrVar = new zzqr();
        zzb = zzqrVar;
        zzago.zzI(zzqr.class, zzqrVar);
    }

    private zzqr() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b\u0003᠌\u0001\u0004᠌\u0002", new Object[]{"zzd", "zze", "zzf", zzql.class, "zzg", zzqq.zza, "zzh", zzqp.zza});
        }
        if (i2 == 3) {
            return new zzqr();
        }
        if (i2 == 4) {
            return new zzqo(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
