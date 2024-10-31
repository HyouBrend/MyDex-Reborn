package com.google.android.libraries.places.api.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzax implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzay(parcel.readInt() == 0 ? parcel.readString() : null, (AddressComponents) parcel.readParcelable(Place.class.getClassLoader()), parcel.readArrayList(Place.class.getClassLoader()), (Place.BusinessStatus) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (OpeningHours) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, (LatLng) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, (OpeningHours) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readArrayList(Place.class.getClassLoader()), (PlusCode) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() == 0 ? Double.valueOf(parcel.readDouble()) : null, (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), parcel.readArrayList(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()), parcel.readArrayList(Place.class.getClassLoader()), parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, (LatLngBounds) parcel.readParcelable(Place.class.getClassLoader()), (Uri) parcel.readParcelable(Place.class.getClassLoader()), (Place.BooleanPlaceAttributeValue) parcel.readParcelable(Place.class.getClassLoader()));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzay[i];
    }
}
