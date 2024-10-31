package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaaq extends zzago implements zzahx {
    private static final zzaaq zzb;
    private int zzd;
    private int zze;
    private int zzf = 1;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;
    private int zzr;
    private int zzs;
    private int zzt;

    static {
        zzaaq zzaaqVar = new zzaaq();
        zzb = zzaaqVar;
        zzago.zzI(zzaaq.class, zzaaqVar);
    }

    private zzaaq() {
    }

    public static zzaan zza() {
        return (zzaan) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzaaq zzaaqVar, boolean z) {
        zzaaqVar.zzd |= 4;
        zzaaqVar.zzg = z;
    }

    public static /* synthetic */ void zze(zzaaq zzaaqVar, boolean z) {
        zzaaqVar.zzd |= 8;
        zzaaqVar.zzh = z;
    }

    public static /* synthetic */ void zzf(zzaaq zzaaqVar, boolean z) {
        zzaaqVar.zzd |= 16;
        zzaaqVar.zzi = z;
    }

    public static /* synthetic */ void zzg(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 32;
        zzaaqVar.zzj = i;
    }

    public static /* synthetic */ void zzh(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 64;
        zzaaqVar.zzk = i;
    }

    public static /* synthetic */ void zzi(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 128;
        zzaaqVar.zzl = i;
    }

    public static /* synthetic */ void zzj(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 256;
        zzaaqVar.zzm = i;
    }

    public static /* synthetic */ void zzk(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 512;
        zzaaqVar.zzn = i;
    }

    public static /* synthetic */ void zzl(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 1024;
        zzaaqVar.zzo = i;
    }

    public static /* synthetic */ void zzm(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 2048;
        zzaaqVar.zzp = i;
    }

    public static /* synthetic */ void zzn(zzaaq zzaaqVar, boolean z) {
        zzaaqVar.zzd |= 4096;
        zzaaqVar.zzq = z;
    }

    public static /* synthetic */ void zzo(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzd |= 8192;
        zzaaqVar.zzr = i;
    }

    public static /* synthetic */ void zzp(zzaaq zzaaqVar, int i) {
        zzaaqVar.zze = i - 1;
        zzaaqVar.zzd |= 1;
    }

    public static /* synthetic */ void zzq(zzaaq zzaaqVar, int i) {
        zzaaqVar.zzf = i;
        zzaaqVar.zzd |= 2;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0010\u0000\u0001\u0001\u0011\u0010\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဋ\u0005\u0007ဋ\u0006\bဋ\u0007\nဋ\t\u000bဋ\n\fဋ\u000b\rဇ\f\u000eဋ\r\u000fဋ\b\u0010ဋ\u000e\u0011᠌\u000f", new Object[]{"zzd", "zze", zzaap.zza, "zzf", zzaam.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzn", "zzo", "zzp", "zzq", "zzr", "zzm", "zzs", "zzt", zzaao.zza});
        }
        if (i2 == 3) {
            return new zzaaq();
        }
        if (i2 == 4) {
            return new zzaan(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
