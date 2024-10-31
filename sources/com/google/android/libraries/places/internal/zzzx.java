package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzx extends zzago implements zzahx {
    private static final zzzx zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private int zzg;

    static {
        zzzx zzzxVar = new zzzx();
        zzb = zzzxVar;
        zzago.zzI(zzzx.class, zzzxVar);
    }

    private zzzx() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", "zzf", "zzg", zzzw.zza});
        }
        if (i2 == 3) {
            return new zzzx();
        }
        if (i2 == 4) {
            return new zzzv(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
