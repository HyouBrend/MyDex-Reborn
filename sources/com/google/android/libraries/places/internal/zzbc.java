package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbc extends zzago implements zzahx {
    private static final zzagu zzb = new zzaz();
    private static final zzbc zzd;
    private int zze;
    private int zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private int zzj;
    private zzagt zzk = zzz();
    private int zzl;

    static {
        zzbc zzbcVar = new zzbc();
        zzd = zzbcVar;
        zzago.zzI(zzbc.class, zzbcVar);
    }

    private zzbc() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzags zzagsVar = zzbb.zza;
            return zzF(zzd, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004င\u0003\u0005င\u0004\u0006ࠞ\u0007᠌\u0005", new Object[]{"zze", "zzf", zzas.zza, "zzg", "zzh", "zzi", "zzj", "zzk", zzagsVar, "zzl", zzagsVar});
        }
        if (i2 == 3) {
            return new zzbc();
        }
        if (i2 == 4) {
            return new zzba(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzd;
    }
}
