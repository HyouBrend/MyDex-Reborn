package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaae extends zzago implements zzahx {
    private static final zzaae zzb;
    private int zzd;
    private zzzz zzg;
    private zzrf zzh;
    private int zzk;
    private int zzl;
    private int zzn;
    private byte zzo = 2;
    private String zze = "";
    private String zzf = "";
    private int zzi = 1;
    private String zzj = "";
    private String zzm = "";

    static {
        zzaae zzaaeVar = new zzaae();
        zzb = zzaaeVar;
        zzago.zzI(zzaae.class, zzaaeVar);
    }

    private zzaae() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzo);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003\u0005᠌\u0004\u0006ဈ\u0005\u0007᠌\u0006\bင\u0007\tဈ\b\n᠌\t", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", zzaab.zza, "zzj", "zzk", zzaad.zza, "zzl", "zzm", "zzn", zzaac.zza});
        }
        if (i2 == 3) {
            return new zzaae();
        }
        if (i2 == 4) {
            return new zzaaa(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzo = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
