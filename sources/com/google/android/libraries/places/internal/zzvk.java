package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzvk extends zzago implements zzahx {
    private static final zzvk zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private zzvg zzg;
    private zzvg zzh;
    private zzuj zzi;
    private zzuj zzj;
    private long zzk;

    static {
        zzvk zzvkVar = new zzvk();
        zzb = zzvkVar;
        zzago.zzI(zzvk.class, zzvkVar);
    }

    private zzvk() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဂ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzvk();
        }
        if (i2 == 4) {
            return new zzvj(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
