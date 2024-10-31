package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaev extends zzago implements zzahx {
    private static final zzaev zzb;
    private int zzd;
    private int zze;
    private int zzf = 1;
    private int zzg;
    private int zzh;

    static {
        zzaev zzaevVar = new zzaev();
        zzb = zzaevVar;
        zzago.zzI(zzaev.class, zzaevVar);
    }

    private zzaev() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzd", "zze", zzaet.zza, "zzf", zzaeu.zza, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzaev();
        }
        if (i2 == 4) {
            return new zzaes(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
