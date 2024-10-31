package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzzz extends zzago implements zzahx {
    private static final zzzz zzb;
    private int zzd;
    private int zze;
    private boolean zzi;
    private zzagw zzf = zzago.zzB();
    private String zzg = "";
    private String zzh = "";
    private zzagw zzj = zzago.zzB();

    static {
        zzzz zzzzVar = new zzzz();
        zzb = zzzzVar;
        zzago.zzI(zzzz.class, zzzzVar);
    }

    private zzzz() {
    }

    public static zzzy zza() {
        return (zzzy) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzzz zzzzVar, String str) {
        zzagw zzagwVar = zzzzVar.zzf;
        if (!zzagwVar.zzc()) {
            zzzzVar.zzf = zzago.zzC(zzagwVar);
        }
        zzzzVar.zzf.add(str);
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001င\u0000\u0002\u001a\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006\u001a", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzzz();
        }
        if (i2 == 4) {
            return new zzzy(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
