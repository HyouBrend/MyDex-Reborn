package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.OpeningHours;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzas extends zzn {
    public static final Parcelable.Creator<zzas> CREATOR = new zzar();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(OpeningHours.HoursType hoursType, List list, List list2, List list3) {
        super(hoursType, list, list2, list3);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getHoursType(), i);
        parcel.writeList(getPeriods());
        parcel.writeList(getSpecialDays());
        parcel.writeList(getWeekdayText());
    }
}
