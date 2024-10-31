package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzadi extends zzago implements zzahx {
    private static final zzadi zzb;
    private int zzd;
    private int zze;
    private zzagw zzf = zzago.zzB();
    private zzagw zzg = zzB();
    private zzagw zzh = zzB();

    static {
        zzadi zzadiVar = new zzadi();
        zzb = zzadiVar;
        zzago.zzI(zzadi.class, zzadiVar);
    }

    private zzadi() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0003\u0000\u0001င\u0000\u0002\u001a\u0003\u001b\u0004\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", zzadk.class, "zzh", zzyu.class});
        }
        if (i2 == 3) {
            return new zzadi();
        }
        if (i2 == 4) {
            return new zzadh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
