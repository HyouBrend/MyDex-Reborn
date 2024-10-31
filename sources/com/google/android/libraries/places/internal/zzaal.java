package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaal extends zzago implements zzahx {
    private static final zzaal zzb;
    private int zzd;
    private zzrf zzf;
    private zzzz zzg;
    private byte zzh = 2;
    private String zze = "";

    static {
        zzaal zzaalVar = new zzaal();
        zzb = zzaalVar;
        zzago.zzI(zzaal.class, zzaalVar);
    }

    private zzaal() {
    }

    public static zzaak zza() {
        return (zzaak) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzaal zzaalVar, zzzz zzzzVar) {
        zzaalVar.zzg = zzzzVar;
        zzaalVar.zzd |= 4;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzaal();
        }
        if (i2 == 4) {
            return new zzaak(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
