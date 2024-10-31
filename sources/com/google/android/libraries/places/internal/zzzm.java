package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzm extends zzago implements zzahx {
    private static final zzzm zzb;
    private int zzd;
    private zzagt zze = zzz();
    private zzagw zzf = zzago.zzB();
    private String zzg = "";
    private boolean zzh;
    private int zzi;

    static {
        zzzm zzzmVar = new zzzm();
        zzb = zzzmVar;
        zzago.zzI(zzzm.class, zzzmVar);
    }

    private zzzm() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001\u0016\u0002\u001a\u0003ဈ\u0000\u0004ဇ\u0001\u0005ဋ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzzm();
        }
        if (i2 == 4) {
            return new zzzl(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
