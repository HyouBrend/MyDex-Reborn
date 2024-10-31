package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzk extends zzago implements zzahx {
    private static final zzzk zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzzk zzzkVar = new zzzk();
        zzb = zzzkVar;
        zzago.zzI(zzzk.class, zzzkVar);
    }

    private zzzk() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzzk();
        }
        zzyp zzypVar = null;
        if (i2 == 4) {
            return new zzzj(zzypVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
