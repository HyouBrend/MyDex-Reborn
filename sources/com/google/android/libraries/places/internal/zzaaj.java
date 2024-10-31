package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaaj extends zzago implements zzahx {
    private static final zzaaj zzb;
    private int zzd;
    private zzrd zzf;
    private byte zzk = 2;
    private String zze = "";
    private String zzg = "";
    private zzagw zzh = zzago.zzB();
    private String zzi = "";
    private String zzj = "";

    static {
        zzaaj zzaajVar = new zzaaj();
        zzb = zzaajVar;
        zzago.zzI(zzaaj.class, zzaajVar);
    }

    private zzaaj() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzk);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဈ\u0002\u0004\u001a\u0005ဈ\u0003\u0006ဈ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzaaj();
        }
        if (i2 == 4) {
            return new zzaai(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzk = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
