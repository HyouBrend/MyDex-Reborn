package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaeq extends zzago implements zzahx {
    private static final zzaeq zzb;
    private int zzd;
    private int zze = 1;
    private int zzf = 1;
    private int zzg;

    static {
        zzaeq zzaeqVar = new zzaeq();
        zzb = zzaeqVar;
        zzago.zzI(zzaeq.class, zzaeqVar);
    }

    private zzaeq() {
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003င\u0002", new Object[]{"zzd", "zze", zzaep.zza, "zzf", zzaeo.zza, "zzg"});
        }
        if (i2 == 3) {
            return new zzaeq();
        }
        if (i2 == 4) {
            return new zzaen(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
