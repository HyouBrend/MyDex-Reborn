package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzuh extends zzago implements zzahx {
    private static final zzuh zzb;
    private int zzd;
    private int zzf;
    private String zze = "";
    private zzagw zzg = zzB();

    static {
        zzuh zzuhVar = new zzuh();
        zzb = zzuhVar;
        zzago.zzI(zzuh.class, zzuhVar);
    }

    private zzuh() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002င\u0001\u0003\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", zzug.class});
        }
        if (i2 == 3) {
            return new zzuh();
        }
        if (i2 == 4) {
            return new zzue(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
