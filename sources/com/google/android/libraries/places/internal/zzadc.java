package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzadc extends zzago implements zzahx {
    private static final zzadc zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzadc zzadcVar = new zzadc();
        zzb = zzadcVar;
        zzago.zzI(zzadc.class, zzadcVar);
    }

    private zzadc() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzadc();
        }
        zzyp zzypVar = null;
        if (i2 == 4) {
            return new zzadb(zzypVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
