package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzuo extends zzago implements zzahx {
    private static final zzuo zzb;
    private int zzd;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzl;
    private String zze = "";
    private zzagw zzk = zzB();

    static {
        zzuo zzuoVar = new zzuo();
        zzb = zzuoVar;
        zzago.zzI(zzuo.class, zzuoVar);
    }

    private zzuo() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007\u001b\bင\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzul.class, "zzl"});
        }
        if (i2 == 3) {
            return new zzuo();
        }
        if (i2 == 4) {
            return new zzun(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
