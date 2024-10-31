package com.google.android.libraries.places.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaho implements zzaii {
    private static final zzahu zza = new zzahm();
    private final zzahu zzb;

    public zzaho() {
        zzahu zzahuVar;
        zzahu[] zzahuVarArr = new zzahu[2];
        zzahuVarArr[0] = zzagk.zza();
        try {
            zzahuVar = (zzahu) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzahuVar = zza;
        }
        zzahuVarArr[1] = zzahuVar;
        zzahn zzahnVar = new zzahn(zzahuVarArr);
        byte[] bArr = zzagx.zzd;
        this.zzb = zzahnVar;
    }

    private static boolean zzb(zzaht zzahtVar) {
        return zzahtVar.zzc() + (-1) != 1;
    }

    @Override // com.google.android.libraries.places.internal.zzaii
    public final zzaih zza(Class cls) {
        zzaij.zzB(cls);
        zzaht zzb = this.zzb.zzb(cls);
        if (!zzb.zzb()) {
            if (zzago.class.isAssignableFrom(cls)) {
                if (zzb(zzb)) {
                    return zzahz.zzi(cls, zzb, zzaic.zzb(), zzahk.zzd(), zzaij.zzz(), zzagg.zzb(), zzahs.zzb());
                }
                return zzahz.zzi(cls, zzb, zzaic.zzb(), zzahk.zzd(), zzaij.zzz(), null, zzahs.zzb());
            }
            if (zzb(zzb)) {
                return zzahz.zzi(cls, zzb, zzaic.zza(), zzahk.zzc(), zzaij.zzy(), zzagg.zza(), zzahs.zza());
            }
            return zzahz.zzi(cls, zzb, zzaic.zza(), zzahk.zzc(), zzaij.zzy(), null, zzahs.zza());
        }
        if (zzago.class.isAssignableFrom(cls)) {
            return zzaia.zzi(zzaij.zzz(), zzagg.zzb(), zzb.zza());
        }
        return zzaia.zzi(zzaij.zzy(), zzagg.zza(), zzb.zza());
    }
}
