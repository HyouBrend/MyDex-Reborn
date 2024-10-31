package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public enum TypeFilter implements Parcelable {
    ADDRESS,
    CITIES,
    ESTABLISHMENT,
    GEOCODE,
    REGIONS;

    public static final Parcelable.Creator<TypeFilter> CREATOR = new Parcelable.Creator() { // from class: com.google.android.libraries.places.api.model.zzbw
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            readString.getClass();
            return TypeFilter.valueOf(readString);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object[] newArray(int i) {
            return new TypeFilter[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
