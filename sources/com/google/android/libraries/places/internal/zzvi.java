package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzvi extends zzago implements zzahx {
    private static final zzvi zzb;
    private int zzd;
    private boolean zze;
    private float zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private float zzj;
    private long zzk;
    private double zzl;

    static {
        zzvi zzviVar = new zzvi();
        zzb = zzviVar;
        zzago.zzI(zzvi.class, zzviVar);
    }

    private zzvi() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဇ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ဂ\u0006\bက\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzvi();
        }
        zzub zzubVar = null;
        if (i2 == 4) {
            return new zzvh(zzubVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
