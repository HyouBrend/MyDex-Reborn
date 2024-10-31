package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.OpeningHours;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzn extends OpeningHours {
    private final OpeningHours.HoursType zza;
    private final List zzb;
    private final List zzc;
    private final List zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(OpeningHours.HoursType hoursType, List list, List list2, List list3) {
        this.zza = hoursType;
        if (list == null) {
            throw new NullPointerException("Null periods");
        }
        this.zzb = list;
        if (list2 != null) {
            this.zzc = list2;
            if (list3 != null) {
                this.zzd = list3;
                return;
            }
            throw new NullPointerException("Null weekdayText");
        }
        throw new NullPointerException("Null specialDays");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OpeningHours) {
            OpeningHours openingHours = (OpeningHours) obj;
            OpeningHours.HoursType hoursType = this.zza;
            if (hoursType != null ? hoursType.equals(openingHours.getHoursType()) : openingHours.getHoursType() == null) {
                if (this.zzb.equals(openingHours.getPeriods()) && this.zzc.equals(openingHours.getSpecialDays()) && this.zzd.equals(openingHours.getWeekdayText())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours
    public final OpeningHours.HoursType getHoursType() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours
    public final List<Period> getPeriods() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours
    public final List<SpecialDay> getSpecialDays() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours
    public final List<String> getWeekdayText() {
        return this.zzd;
    }

    public final String toString() {
        return "OpeningHours{hoursType=" + String.valueOf(this.zza) + ", periods=" + this.zzb.toString() + ", specialDays=" + this.zzc.toString() + ", weekdayText=" + this.zzd.toString() + "}";
    }

    public final int hashCode() {
        OpeningHours.HoursType hoursType = this.zza;
        return (((((((hoursType == null ? 0 : hoursType.hashCode()) ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode();
    }
}
