package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzg extends zzbk {
    private final int zza;
    private final int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbk) {
            zzbk zzbkVar = (zzbk) obj;
            if (this.zza == zzbkVar.zzb() && this.zzb == zzbkVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza ^ 1000003) * 1000003) ^ this.zzb;
    }

    public final String toString() {
        return "SubstringMatch{offset=" + this.zza + ", length=" + this.zzb + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbk
    public final int zza() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbk
    public final int zzb() {
        return this.zza;
    }
}
