package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabk extends zzago implements zzahx {
    private static final zzabk zzb;
    private int zzd;
    private zzrd zze;
    private byte zzf = 2;

    static {
        zzabk zzabkVar = new zzabk();
        zzb = zzabkVar;
        zzago.zzI(zzabk.class, zzabkVar);
    }

    private zzabk() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzf);
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzabk();
        }
        if (i2 == 4) {
            return new zzabj(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzf = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
