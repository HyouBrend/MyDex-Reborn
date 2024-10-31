package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.SpecialDay;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzz extends SpecialDay.Builder {
    private LocalDate zza;
    private boolean zzb;
    private byte zzc;

    @Override // com.google.android.libraries.places.api.model.SpecialDay.Builder
    public final SpecialDay build() {
        LocalDate localDate;
        if (this.zzc != 1 || (localDate = this.zza) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" date");
            }
            if (this.zzc == 0) {
                sb.append(" exceptional");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzbg(localDate, this.zzb);
    }

    @Override // com.google.android.libraries.places.api.model.SpecialDay.Builder
    public final LocalDate getDate() {
        LocalDate localDate = this.zza;
        if (localDate != null) {
            return localDate;
        }
        throw new IllegalStateException("Property \"date\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.SpecialDay.Builder
    public final boolean isExceptional() {
        if (this.zzc != 0) {
            return this.zzb;
        }
        throw new IllegalStateException("Property \"exceptional\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.SpecialDay.Builder
    public final SpecialDay.Builder setDate(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("Null date");
        }
        this.zza = localDate;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.SpecialDay.Builder
    public final SpecialDay.Builder setExceptional(boolean z) {
        this.zzb = z;
        this.zzc = (byte) 1;
        return this;
    }
}
