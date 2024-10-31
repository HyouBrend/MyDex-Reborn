package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzj extends LocalDate {
    private final int zza;
    private final int zzb;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(int i, int i2, int i3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LocalDate) {
            LocalDate localDate = (LocalDate) obj;
            if (this.zza == localDate.getYear() && this.zzb == localDate.getMonth() && this.zzc == localDate.getDay()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.LocalDate
    public final int getDay() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.LocalDate
    public final int getMonth() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.LocalDate
    public final int getYear() {
        return this.zza;
    }

    public final int hashCode() {
        return ((((this.zza ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
    }
}
