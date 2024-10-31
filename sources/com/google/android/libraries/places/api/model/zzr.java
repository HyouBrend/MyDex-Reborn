package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzr extends PhotoMetadata {
    private final String zza;
    private final int zzb;
    private final int zzc;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(String str, int i, int i2, String str2) {
        if (str == null) {
            throw new NullPointerException("Null attributions");
        }
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
        if (str2 != null) {
            this.zzd = str2;
            return;
        }
        throw new NullPointerException("Null photoReference");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PhotoMetadata) {
            PhotoMetadata photoMetadata = (PhotoMetadata) obj;
            if (this.zza.equals(photoMetadata.getAttributions()) && this.zzb == photoMetadata.getHeight() && this.zzc == photoMetadata.getWidth() && this.zzd.equals(photoMetadata.zza())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    public String getAttributions() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    public int getHeight() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    public int getWidth() {
        return this.zzc;
    }

    public final int hashCode() {
        return ((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc) * 1000003) ^ this.zzd.hashCode();
    }

    public final String toString() {
        return "PhotoMetadata{attributions=" + this.zza + ", height=" + this.zzb + ", width=" + this.zzc + ", photoReference=" + this.zzd + "}";
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata
    public final String zza() {
        return this.zzd;
    }
}
