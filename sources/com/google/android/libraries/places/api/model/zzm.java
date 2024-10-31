package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.OpeningHours;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzm extends OpeningHours.Builder {
    private OpeningHours.HoursType zza;
    private List zzb;
    private List zzc;
    private List zzd;

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.HoursType getHoursType() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final List<Period> getPeriods() {
        List<Period> list = this.zzb;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"periods\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final List<SpecialDay> getSpecialDays() {
        List<SpecialDay> list = this.zzc;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"specialDays\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final List<String> getWeekdayText() {
        List<String> list = this.zzd;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"weekdayText\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.Builder setHoursType(OpeningHours.HoursType hoursType) {
        this.zza = hoursType;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.Builder setPeriods(List<Period> list) {
        if (list == null) {
            throw new NullPointerException("Null periods");
        }
        this.zzb = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.Builder setSpecialDays(List<SpecialDay> list) {
        if (list == null) {
            throw new NullPointerException("Null specialDays");
        }
        this.zzc = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.Builder setWeekdayText(List<String> list) {
        if (list == null) {
            throw new NullPointerException("Null weekdayText");
        }
        this.zzd = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    final OpeningHours zza() {
        List list;
        List list2;
        List list3 = this.zzb;
        if (list3 == null || (list = this.zzc) == null || (list2 = this.zzd) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zzb == null) {
                sb.append(" periods");
            }
            if (this.zzc == null) {
                sb.append(" specialDays");
            }
            if (this.zzd == null) {
                sb.append(" weekdayText");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzas(this.zza, list3, list, list2);
    }
}
