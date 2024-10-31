package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzyj extends zzago implements zzahx {
    private static final zzyj zzb;
    private int zzd;
    private int zze;
    private long zzf;

    static {
        zzyj zzyjVar = new zzyj();
        zzb = zzyjVar;
        zzago.zzI(zzyj.class, zzyjVar);
    }

    private zzyj() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဃ\u0001", new Object[]{"zzd", "zze", zzyi.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzyj();
        }
        if (i2 == 4) {
            return new zzyh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
