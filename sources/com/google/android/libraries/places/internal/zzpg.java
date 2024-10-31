package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzpg extends zzago implements zzahx {
    private static final zzpg zzb;
    private int zzd;
    private zzov zze;
    private int zzf;
    private int zzg;

    static {
        zzpg zzpgVar = new zzpg();
        zzb = zzpgVar;
        zzago.zzI(zzpg.class, zzpgVar);
    }

    private zzpg() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003င\u0002", new Object[]{"zzd", "zze", "zzf", zzpf.zza, "zzg"});
        }
        if (i2 == 3) {
            return new zzpg();
        }
        if (i2 == 4) {
            return new zzpe(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
