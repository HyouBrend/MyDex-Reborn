package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzahn implements zzahu {
    private final zzahu[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzahn(zzahu... zzahuVarArr) {
        this.zza = zzahuVarArr;
    }

    @Override // com.google.android.libraries.places.internal.zzahu
    public final zzaht zzb(Class cls) {
        zzahu[] zzahuVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzahu zzahuVar = zzahuVarArr[i];
            if (zzahuVar.zzc(cls)) {
                return zzahuVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.libraries.places.internal.zzahu
    public final boolean zzc(Class cls) {
        zzahu[] zzahuVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzahuVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
