package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zznw extends zzago implements zzahx {
    private static final zznw zzb;
    private zzce zzA;
    private zzbj zzB;
    private int zzd;
    private zzabb zzf;
    private zzpy zzg;
    private zzadw zzh;
    private zzxy zzi;
    private zzte zzj;
    private zzbu zzk;
    private zzps zzl;
    private zzoy zzm;
    private zzqr zzn;
    private zzve zzo;
    private zzvw zzp;
    private zzvz zzq;
    private zzoi zzr;
    private zzrl zzs;
    private zzc zzt;
    private zzan zzu;
    private zzav zzv;
    private zzcv zzw;
    private zzay zzx;
    private zzbf zzy;
    private zzae zzz;
    private byte zzC = 2;
    private int zze = 1;

    static {
        zznw zznwVar = new zznw();
        zzb = zznwVar;
        zzago.zzI(zznw.class, zznwVar);
    }

    private zznw() {
    }

    public static zznu zza() {
        return (zznu) zzb.zzw();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zznw zznwVar, zzabb zzabbVar) {
        zzabbVar.getClass();
        zznwVar.zzf = zzabbVar;
        zznwVar.zzd |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zznw zznwVar, int i) {
        zznwVar.zze = 1;
        zznwVar.zzd = 1 | zznwVar.zzd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzC);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0018\u0000\u0001\u0001\u0019\u0018\u0000\u0000\u0002\u0001᠌\u0000\u0002ᐉ\u0001\u0003ᐉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f\u000fဉ\r\u0010ဉ\u000e\u0011ဉ\u000f\u0012ဉ\u0010\u0013ဉ\u0011\u0014ဉ\u0012\u0015ဉ\u0013\u0016ဉ\u0014\u0017ဉ\u0015\u0018ဉ\u0016\u0019ဉ\u0017", new Object[]{"zzd", "zze", zznv.zza, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB"});
        }
        if (i2 == 3) {
            return new zznw();
        }
        zznt zzntVar = null;
        if (i2 == 4) {
            return new zznu(zzntVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzC = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
