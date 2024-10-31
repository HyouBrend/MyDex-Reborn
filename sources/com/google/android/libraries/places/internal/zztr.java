package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zztr extends zzago implements zzahx {
    private static final zztr zzb;
    private int zzd;
    private int zze;
    private zzagw zzf = zzB();

    static {
        zztr zztrVar = new zztr();
        zzb = zztrVar;
        zzago.zzI(zztr.class, zztrVar);
    }

    private zztr() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zzd", "zze", zztq.zza, "zzf", zzto.class});
        }
        if (i2 == 3) {
            return new zztr();
        }
        if (i2 == 4) {
            return new zztp(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
