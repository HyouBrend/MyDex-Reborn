package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzadt extends zzago implements zzahx {
    private static final zzadt zzb;
    private int zzd;
    private long zze;
    private int zzf;

    static {
        zzadt zzadtVar = new zzadt();
        zzb = zzadtVar;
        zzago.zzI(zzadt.class, zzadtVar);
    }

    private zzadt() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဏ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzadt();
        }
        zzadr zzadrVar = null;
        if (i2 == 4) {
            return new zzads(zzadrVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
