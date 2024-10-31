package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbm extends zzago implements zzahx {
    private static final zzagu zzb = new zzbk();
    private static final zzbm zzd;
    private int zze;
    private zzagt zzf = zzz();
    private int zzg;
    private int zzh;
    private int zzi;
    private long zzj;
    private float zzk;
    private float zzl;
    private int zzm;

    static {
        zzbm zzbmVar = new zzbm();
        zzd = zzbmVar;
        zzago.zzI(zzbm.class, zzbmVar);
    }

    private zzbm() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzags zzagsVar = zzas.zza;
            zzags zzagsVar2 = zzbh.zza;
            return zzF(zzd, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ࠬ\u0002᠌\u0000\u0003᠌\u0001\u0004င\u0002\u0005ဂ\u0003\u0006ခ\u0004\u0007ခ\u0005\b᠌\u0006", new Object[]{"zze", "zzf", zzagsVar, "zzg", zzagsVar2, "zzh", zzagsVar2, "zzi", "zzj", "zzk", "zzl", "zzm", zzagsVar});
        }
        if (i2 == 3) {
            return new zzbm();
        }
        if (i2 == 4) {
            return new zzbl(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzd;
    }
}
