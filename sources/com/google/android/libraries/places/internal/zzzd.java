package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzd extends zzago implements zzahx {
    private static final zzzd zzb;
    private int zzd;
    private int zze;

    static {
        zzzd zzzdVar = new zzzd();
        zzb = zzzdVar;
        zzago.zzI(zzzd.class, zzzdVar);
    }

    private zzzd() {
    }

    public static zzzc zza() {
        return (zzzc) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzzd zzzdVar, int i) {
        zzzdVar.zzd |= 1;
        zzzdVar.zze = i;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzzd();
        }
        if (i2 == 4) {
            return new zzzc(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
