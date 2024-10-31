package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzuj extends zzago implements zzahx {
    private static final zzuj zzb;
    private int zzd;
    private String zze = "";
    private int zzf;
    private int zzg;
    private float zzh;
    private float zzi;
    private float zzj;
    private long zzk;
    private boolean zzl;

    static {
        zzuj zzujVar = new zzuj();
        zzb = zzujVar;
        zzago.zzI(zzuj.class, zzujVar);
    }

    private zzuj() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003င\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ဂ\u0006\bဇ\u0007", new Object[]{"zzd", "zze", "zzf", zzva.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzuj();
        }
        if (i2 == 4) {
            return new zzui(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
