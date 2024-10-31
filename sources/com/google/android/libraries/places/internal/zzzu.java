package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzu extends zzago implements zzahx {
    private static final zzzu zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private zzzi zzj;
    private zzzb zzk;
    private zzyw zzl;
    private zzadc zzm;
    private zzzd zzn;
    private zzzg zzo;
    private zzade zzp;
    private zzadm zzq;
    private zzadi zzr;
    private int zzs;

    static {
        zzzu zzzuVar = new zzzu();
        zzb = zzzuVar;
        zzago.zzI(zzzu.class, zzzuVar);
    }

    private zzzu() {
    }

    public static zzzp zza() {
        return (zzzp) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzzu zzzuVar, int i) {
        zzzuVar.zzd |= 4;
        zzzuVar.zzg = i;
    }

    public static /* synthetic */ void zze(zzzu zzzuVar, zzzi zzziVar) {
        zzziVar.getClass();
        zzzuVar.zzj = zzziVar;
        zzzuVar.zzd |= 32;
    }

    public static /* synthetic */ void zzf(zzzu zzzuVar, zzyw zzywVar) {
        zzywVar.getClass();
        zzzuVar.zzl = zzywVar;
        zzzuVar.zzd |= 128;
    }

    public static /* synthetic */ void zzg(zzzu zzzuVar, zzzd zzzdVar) {
        zzzdVar.getClass();
        zzzuVar.zzn = zzzdVar;
        zzzuVar.zzd |= 512;
    }

    public static /* synthetic */ void zzh(zzzu zzzuVar, int i) {
        zzzuVar.zze = i - 1;
        zzzuVar.zzd |= 1;
    }

    public static /* synthetic */ void zzi(zzzu zzzuVar, int i) {
        zzzuVar.zzf = i - 1;
        zzzuVar.zzd |= 2;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003င\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f\u000eဉ\r\u000f᠌\u000e", new Object[]{"zzd", "zze", zzzr.zza, "zzf", zzzt.zza, "zzg", "zzh", zzzq.zza, "zzi", zzzo.zza, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", zzzs.zza});
        }
        if (i2 == 3) {
            return new zzzu();
        }
        if (i2 == 4) {
            return new zzzp(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
