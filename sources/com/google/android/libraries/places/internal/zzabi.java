package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzabi extends zzago implements zzahx {
    private static final zzabi zzb;
    private int zzd;
    private zzagw zze = zzago.zzB();
    private int zzf;
    private int zzg;
    private zzach zzh;

    static {
        zzabi zzabiVar = new zzabi();
        zzb = zzabiVar;
        zzago.zzI(zzabi.class, zzabiVar);
    }

    private zzabi() {
    }

    public static zzabh zza() {
        return (zzabh) zzb.zzw();
    }

    public static /* synthetic */ void zzd(zzabi zzabiVar, int i) {
        zzabiVar.zzd |= 2;
        zzabiVar.zzg = 1;
    }

    public static /* synthetic */ void zze(zzabi zzabiVar, zzach zzachVar) {
        zzachVar.getClass();
        zzabiVar.zzh = zzachVar;
        zzabiVar.zzd |= 4;
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001a\u0002᠌\u0000\u0003ဋ\u0001\u0004ဉ\u0002", new Object[]{"zzd", "zze", "zzf", zzze.zza, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzabi();
        }
        if (i2 == 4) {
            return new zzabh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
