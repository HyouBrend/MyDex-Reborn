package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
abstract class zzbu {
    private static final zzbu zzkb;
    private static final zzbu zzkc;

    private zzbu() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> void zzb(Object obj, Object obj2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbu zzca() {
        return zzkb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbu zzcb() {
        return zzkc;
    }

    static {
        zzbt zzbtVar = null;
        zzkb = new zzbw();
        zzkc = new zzbv();
    }
}
