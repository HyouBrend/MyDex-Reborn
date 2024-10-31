package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzql extends zzago implements zzahx {
    private static final zzql zzb;
    private int zzd;
    private boolean zze;
    private long zzf;
    private zzod zzg;
    private int zzh;

    static {
        zzql zzqlVar = new zzql();
        zzb = zzqlVar;
        zzago.zzI(zzql.class, zzqlVar);
    }

    private zzql() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဂ\u0001\u0003ဉ\u0002\u0004င\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzql();
        }
        if (i2 == 4) {
            return new zzqk(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
