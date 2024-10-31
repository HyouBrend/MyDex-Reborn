package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzjp;
import com.google.android.libraries.places.internal.zzkh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class OpeningHours implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public OpeningHours build() {
            OpeningHours zza = zza();
            Iterator<String> it = zza.getWeekdayText().iterator();
            while (it.hasNext()) {
                zzjp.zzk(!TextUtils.isEmpty(it.next()), "WeekdayText must not contain null or empty values.");
            }
            setPeriods(zzkh.zzj(zza.getPeriods()));
            setWeekdayText(zzkh.zzj(zza.getWeekdayText()));
            setSpecialDays(zzkh.zzj(zza.getSpecialDays()));
            return zza();
        }

        public abstract HoursType getHoursType();

        public abstract List<Period> getPeriods();

        public abstract List<SpecialDay> getSpecialDays();

        public abstract List<String> getWeekdayText();

        public abstract Builder setHoursType(HoursType hoursType);

        public abstract Builder setPeriods(List<Period> list);

        public abstract Builder setSpecialDays(List<SpecialDay> list);

        public abstract Builder setWeekdayText(List<String> list);

        abstract OpeningHours zza();
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public enum HoursType implements Parcelable {
        ACCESS,
        BREAKFAST,
        BRUNCH,
        DELIVERY,
        DINNER,
        DRIVE_THROUGH,
        HAPPY_HOUR,
        KITCHEN,
        LUNCH,
        ONLINE_SERVICE_HOURS,
        PICKUP,
        SENIOR_HOURS,
        TAKEOUT;

        public static final Parcelable.Creator<HoursType> CREATOR = new zzbo();

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    public static Builder builder() {
        zzm zzmVar = new zzm();
        zzmVar.setPeriods(new ArrayList());
        zzmVar.setSpecialDays(new ArrayList());
        zzmVar.setWeekdayText(new ArrayList());
        return zzmVar;
    }

    public abstract HoursType getHoursType();

    public abstract List<Period> getPeriods();

    public abstract List<SpecialDay> getSpecialDays();

    public abstract List<String> getWeekdayText();
}
