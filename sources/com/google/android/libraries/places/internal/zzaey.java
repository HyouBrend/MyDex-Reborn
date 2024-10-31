package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaey extends zzago implements zzahx {
    private static final zzaey zzb;
    private int zzd;
    private int zze;
    private zzaec zzf;
    private zzaec zzg;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private zzaec zzn;
    private zzaef zzo;
    private zzael zzp;
    private int zzq;
    private int zzr;
    private zzaei zzs;
    private byte zzt = 2;
    private zzagw zzh = zzB();

    static {
        zzaey zzaeyVar = new zzaey();
        zzb = zzaeyVar;
        zzago.zzI(zzaey.class, zzaeyVar);
    }

    private zzaey() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzt);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u000f\u0000\u0001\u0002\u0010\u000f\u0000\u0001\u0001\u0002ᔄ\u0000\u0003ဉ\u0001\u0004ဉ\u0002\u0005\u001b\u0006င\u0003\u0007င\u0004\bင\u0005\tင\u0006\nင\u0007\u000bဉ\b\fဉ\t\rဉ\n\u000eင\u000b\u000fင\f\u0010ဉ\r", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzaev.class, "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs"});
        }
        if (i2 == 3) {
            return new zzaey();
        }
        if (i2 == 4) {
            return new zzaex(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzt = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
