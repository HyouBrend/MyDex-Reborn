package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzpl extends zzago implements zzahx {
    private static final zzpl zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private boolean zzg;

    static {
        zzpl zzplVar = new zzpl();
        zzb = zzplVar;
        zzago.zzI(zzpl.class, zzplVar);
    }

    private zzpl() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzpl();
        }
        zzpj zzpjVar = null;
        if (i2 == 4) {
            return new zzpk(zzpjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
