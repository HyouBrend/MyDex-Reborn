package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzcl extends zzago implements zzahx {
    private static final zzcl zzb;
    private int zzd;
    private double zze = 1.0d;
    private double zzf = 1.0d;

    static {
        zzcl zzclVar = new zzcl();
        zzb = zzclVar;
        zzago.zzI(zzcl.class, zzclVar);
    }

    private zzcl() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001က\u0000\u0002က\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzcl();
        }
        zzcj zzcjVar = null;
        if (i2 == 4) {
            return new zzck(zzcjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
