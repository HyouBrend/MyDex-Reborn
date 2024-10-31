package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzvn extends zzago implements zzahx {
    private static final zzvn zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        zzvn zzvnVar = new zzvn();
        zzb = zzvnVar;
        zzago.zzI(zzvn.class, zzvnVar);
    }

    private zzvn() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzvn();
        }
        zzub zzubVar = null;
        if (i2 == 4) {
            return new zzvm(zzubVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
