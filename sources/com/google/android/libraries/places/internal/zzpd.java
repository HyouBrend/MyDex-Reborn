package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzpd extends zzago implements zzahx {
    private static final zzpd zzb;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzpd zzpdVar = new zzpd();
        zzb = zzpdVar;
        zzago.zzI(zzpd.class, zzpdVar);
    }

    private zzpd() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzpc.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzpd();
        }
        if (i2 == 4) {
            return new zzpb(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
