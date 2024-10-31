package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzod extends zzago implements zzahx {
    private static final zzod zzb;
    private int zzd;
    private int zzf;
    private int zzg;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private String zze = "";
    private String zzh = "";

    static {
        zzod zzodVar = new zzod();
        zzb = zzodVar;
        zzago.zzI(zzod.class, zzodVar);
    }

    private zzod() {
    }

    public static zzny zza() {
        return (zzny) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzod zzodVar, String str) {
        zzodVar.zzd |= 1;
        zzodVar.zze = str;
    }

    public static /* synthetic */ void zze(zzod zzodVar, int i) {
        zzodVar.zzd |= 2;
        zzodVar.zzf = i;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006᠌\u0005\u0007᠌\u0006\b᠌\u0007\t᠌\b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zznz.zza, "zzk", zzob.zza, "zzl", zzoa.zza, "zzm", zzoc.zza});
        }
        if (i2 == 3) {
            return new zzod();
        }
        if (i2 == 4) {
            return new zzny(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
