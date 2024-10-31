package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbf extends zzago implements zzahx {
    private static final zzbf zzb;
    private int zzd;
    private zzcl zze;
    private String zzf = "";
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;

    static {
        zzbf zzbfVar = new zzbf();
        zzb = zzbfVar;
        zzago.zzI(zzbf.class, zzbfVar);
    }

    private zzbf() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0001\u0002ဉ\u0000\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004", new Object[]{"zzd", "zzf", "zze", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzbf();
        }
        if (i2 == 4) {
            return new zzbe(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
