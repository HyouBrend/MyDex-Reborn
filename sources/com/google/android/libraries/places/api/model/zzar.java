package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.OpeningHours;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzar implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzas((OpeningHours.HoursType) parcel.readParcelable(OpeningHours.class.getClassLoader()), parcel.readArrayList(OpeningHours.class.getClassLoader()), parcel.readArrayList(OpeningHours.class.getClassLoader()), parcel.readArrayList(OpeningHours.class.getClassLoader()));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzas[i];
    }
}
