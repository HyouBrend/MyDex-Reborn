package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzacy extends zzago implements zzahx {
    private static final zzacy zzb;
    private int zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzacy zzacyVar = new zzacy();
        zzb = zzacyVar;
        zzago.zzI(zzacy.class, zzacyVar);
    }

    private zzacy() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zzd", "zze", zzyr.zza, "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzacy();
        }
        if (i2 == 4) {
            return new zzacx(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
