package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzvw extends zzago implements zzahx {
    private static final zzvw zzb;
    private int zzd;
    private String zze = "";
    private int zzf;
    private long zzg;

    static {
        zzvw zzvwVar = new zzvw();
        zzb = zzvwVar;
        zzago.zzI(zzvw.class, zzvwVar);
    }

    private zzvw() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ဂ\u0002", new Object[]{"zzd", "zze", "zzf", zzvv.zza, "zzg"});
        }
        if (i2 == 3) {
            return new zzvw();
        }
        if (i2 == 4) {
            return new zzvu(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
