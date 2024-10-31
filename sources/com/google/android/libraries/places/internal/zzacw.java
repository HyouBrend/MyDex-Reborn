package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzacw extends zzago implements zzahx {
    private static final zzacw zzb;
    private int zzd;
    private zzrf zze;
    private int zzf;
    private int zzg;
    private int zzi;
    private byte zzj = 2;
    private String zzh = "";

    static {
        zzacw zzacwVar = new zzacw();
        zzb = zzacwVar;
        zzago.zzI(zzacw.class, zzacwVar);
    }

    private zzacw() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzj);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ᐉ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005᠌\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", zzacv.zza});
        }
        if (i2 == 3) {
            return new zzacw();
        }
        if (i2 == 4) {
            return new zzacu(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
