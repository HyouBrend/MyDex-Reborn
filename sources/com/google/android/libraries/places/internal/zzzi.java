package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzi extends zzago implements zzahx {
    private static final zzzi zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzzi zzziVar = new zzzi();
        zzb = zzziVar;
        zzago.zzI(zzzi.class, zzziVar);
    }

    private zzzi() {
    }

    public static zzzh zza() {
        return (zzzh) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzzi zzziVar, int i) {
        zzziVar.zzd |= 1;
        zzziVar.zze = 1;
    }

    public static /* synthetic */ void zze(zzzi zzziVar, int i) {
        zzziVar.zzd |= 2;
        zzziVar.zzf = i;
    }

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
            return new zzzi();
        }
        if (i2 == 4) {
            return new zzzh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
