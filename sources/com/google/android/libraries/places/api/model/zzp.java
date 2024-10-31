package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzp extends Period {
    private final TimeOfWeek zza;
    private final TimeOfWeek zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(TimeOfWeek timeOfWeek, TimeOfWeek timeOfWeek2) {
        this.zza = timeOfWeek;
        this.zzb = timeOfWeek2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Period) {
            Period period = (Period) obj;
            TimeOfWeek timeOfWeek = this.zza;
            if (timeOfWeek != null ? timeOfWeek.equals(period.getOpen()) : period.getOpen() == null) {
                TimeOfWeek timeOfWeek2 = this.zzb;
                if (timeOfWeek2 != null ? timeOfWeek2.equals(period.getClose()) : period.getClose() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.Period
    public final TimeOfWeek getClose() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Period
    public final TimeOfWeek getOpen() {
        return this.zza;
    }

    public final String toString() {
        return "Period{open=" + String.valueOf(this.zza) + ", close=" + String.valueOf(this.zzb) + "}";
    }

    public final int hashCode() {
        TimeOfWeek timeOfWeek = this.zza;
        int hashCode = timeOfWeek == null ? 0 : timeOfWeek.hashCode();
        TimeOfWeek timeOfWeek2 = this.zzb;
        return ((hashCode ^ 1000003) * 1000003) ^ (timeOfWeek2 != null ? timeOfWeek2.hashCode() : 0);
    }
}
