package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzac extends TimeOfWeek {
    private final LocalDate zza;
    private final DayOfWeek zzb;
    private final LocalTime zzc;
    private final boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(LocalDate localDate, DayOfWeek dayOfWeek, LocalTime localTime, boolean z) {
        this.zza = localDate;
        if (dayOfWeek == null) {
            throw new NullPointerException("Null day");
        }
        this.zzb = dayOfWeek;
        if (localTime != null) {
            this.zzc = localTime;
            this.zzd = z;
            return;
        }
        throw new NullPointerException("Null time");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TimeOfWeek) {
            TimeOfWeek timeOfWeek = (TimeOfWeek) obj;
            LocalDate localDate = this.zza;
            if (localDate != null ? localDate.equals(timeOfWeek.getDate()) : timeOfWeek.getDate() == null) {
                if (this.zzb.equals(timeOfWeek.getDay()) && this.zzc.equals(timeOfWeek.getTime()) && this.zzd == timeOfWeek.isTruncated()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final LocalDate getDate() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final DayOfWeek getDay() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final LocalTime getTime() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final boolean isTruncated() {
        return this.zzd;
    }

    public final String toString() {
        return "TimeOfWeek{date=" + String.valueOf(this.zza) + ", day=" + this.zzb.toString() + ", time=" + this.zzc.toString() + ", truncated=" + this.zzd + "}";
    }

    public final int hashCode() {
        LocalDate localDate = this.zza;
        return (((((((localDate == null ? 0 : localDate.hashCode()) ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ (true != this.zzd ? 1237 : 1231);
    }
}
