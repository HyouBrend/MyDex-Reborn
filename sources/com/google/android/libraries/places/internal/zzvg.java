package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzvg extends zzago implements zzahx {
    private static final zzvg zzb;
    private int zzd;
    private int zze;
    private float zzf;
    private float zzg;

    static {
        zzvg zzvgVar = new zzvg();
        zzb = zzvgVar;
        zzago.zzI(zzvg.class, zzvgVar);
    }

    private zzvg() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ခ\u0001\u0003ခ\u0002", new Object[]{"zzd", "zze", zzva.zza, "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzvg();
        }
        if (i2 == 4) {
            return new zzvf(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
