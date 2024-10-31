package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaca extends zzago implements zzahx {
    private static final zzaca zzb;
    private int zzd;
    private int zzf;
    private boolean zzh;
    private zzagw zze = zzago.zzB();
    private String zzg = "";

    static {
        zzaca zzacaVar = new zzaca();
        zzb = zzacaVar;
        zzago.zzI(zzaca.class, zzacaVar);
    }

    private zzaca() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001a\u0002᠌\u0000\u0003ဈ\u0001\u0004ဇ\u0002", new Object[]{"zzd", "zze", "zzf", zzabx.zza, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzaca();
        }
        if (i2 == 4) {
            return new zzabz(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
