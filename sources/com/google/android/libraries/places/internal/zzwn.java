package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzwn extends zzago implements zzahx {
    private static final zzwn zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzwn zzwnVar = new zzwn();
        zzb = zzwnVar;
        zzago.zzI(zzwn.class, zzwnVar);
    }

    private zzwn() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", zzwk.zza, "zzf", zzwm.zza, "zzg", zzwl.zza});
        }
        if (i2 == 3) {
            return new zzwn();
        }
        if (i2 == 4) {
            return new zzwj(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
