package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaat extends zzago implements zzahx {
    private static final zzaat zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzaat zzaatVar = new zzaat();
        zzb = zzaatVar;
        zzago.zzI(zzaat.class, zzaatVar);
    }

    private zzaat() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဋ\u0001", new Object[]{"zzd", "zze", zzaas.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzaat();
        }
        if (i2 == 4) {
            return new zzaar(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
