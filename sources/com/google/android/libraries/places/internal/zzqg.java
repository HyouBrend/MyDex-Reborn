package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzqg extends zzago implements zzahx {
    private static final zzqg zzb;
    private int zzd;
    private long zzg;
    private long zzh;
    private int zzi;
    private String zze = "";
    private String zzf = "";
    private String zzj = "";

    static {
        zzqg zzqgVar = new zzqg();
        zzb = zzqgVar;
        zzago.zzI(zzqg.class, zzqgVar);
    }

    private zzqg() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005င\u0004\u0006ဈ\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzqg();
        }
        zzpv zzpvVar = null;
        if (i2 == 4) {
            return new zzqf(zzpvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
