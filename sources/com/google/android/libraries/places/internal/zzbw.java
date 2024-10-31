package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzbw extends zzago implements zzahx {
    private static final zzbw zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    static {
        zzbw zzbwVar = new zzbw();
        zzb = zzbwVar;
        zzago.zzI(zzbw.class, zzbwVar);
    }

    private zzbw() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzbw();
        }
        zzbn zzbnVar = null;
        if (i2 == 4) {
            return new zzbv(zzbnVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
