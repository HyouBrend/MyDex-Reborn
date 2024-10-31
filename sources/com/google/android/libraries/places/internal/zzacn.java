package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzacn extends zzago implements zzahx {
    private static final zzacn zzb;
    private int zzd;
    private int zze;
    private zzzz zzh;
    private zzacw zzi;
    private zzabk zzj;
    private zzaaj zzk;
    private zzabi zzl;
    private zzaal zzm;
    private zzabg zzn;
    private zzacy zzo;
    private zzacy zzp;
    private zzabm zzq;
    private zzaav zzr;
    private zzacp zzs;
    private zzacr zzt;
    private zzacc zzu;
    private zzabs zzv;
    private zzact zzw;
    private byte zzx = 2;
    private String zzf = "";
    private String zzg = "";

    static {
        zzacn zzacnVar = new zzacn();
        zzb = zzacnVar;
        zzago.zzI(zzacn.class, zzacnVar);
    }

    private zzacn() {
    }

    public static zzacl zza() {
        return (zzacl) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzacn zzacnVar, String str) {
        str.getClass();
        zzacnVar.zzd |= 2;
        zzacnVar.zzf = str;
    }

    public static /* synthetic */ void zze(zzacn zzacnVar, String str) {
        str.getClass();
        zzacnVar.zzd |= 4;
        zzacnVar.zzg = str;
    }

    public static /* synthetic */ void zzf(zzacn zzacnVar, zzabi zzabiVar) {
        zzabiVar.getClass();
        zzacnVar.zzl = zzabiVar;
        zzacnVar.zzd |= 128;
    }

    public static /* synthetic */ void zzg(zzacn zzacnVar, zzaal zzaalVar) {
        zzaalVar.getClass();
        zzacnVar.zzm = zzaalVar;
        zzacnVar.zzd |= 256;
    }

    public static /* synthetic */ void zzh(zzacn zzacnVar, int i) {
        zzacnVar.zze = i - 1;
        zzacnVar.zzd |= 1;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzx);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0013\u0000\u0001\u0001\u0013\u0013\u0000\u0000\u0004\u0001᠌\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003\u0005ᐉ\u0004\u0006ᐉ\u0005\u0007ᐉ\u0006\bဉ\u0007\tᐉ\b\nဉ\t\u000bဉ\u000b\fဉ\n\rဉ\f\u000eဉ\r\u000fဉ\u000e\u0010ဉ\u000f\u0011ဉ\u0010\u0012ဉ\u0011\u0013ဉ\u0012", new Object[]{"zzd", "zze", zzacm.zza, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzp", "zzo", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw"});
        }
        if (i2 == 3) {
            return new zzacn();
        }
        if (i2 == 4) {
            return new zzacl(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzx = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
