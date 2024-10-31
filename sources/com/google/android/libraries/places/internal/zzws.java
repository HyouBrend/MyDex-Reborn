package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzws extends zzago implements zzahx {
    private static final zzws zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private long zzg;

    static {
        zzws zzwsVar = new zzws();
        zzb = zzwsVar;
        zzago.zzI(zzws.class, zzwsVar);
    }

    private zzws() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဂ\u0002", new Object[]{"zzd", "zze", zzwr.zza, "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzws();
        }
        if (i2 == 4) {
            return new zzwq(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
