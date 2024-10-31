package com.google.android.libraries.places.api.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzay extends zzt {
    public static final Parcelable.Creator<zzay> CREATOR = new zzax();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(String str, AddressComponents addressComponents, List list, Place.BusinessStatus businessStatus, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue, OpeningHours openingHours, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue2, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue3, String str2, String str3, Integer num, String str4, String str5, LatLng latLng, String str6, OpeningHours openingHours2, String str7, List list2, PlusCode plusCode, Integer num2, Double d, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue4, List list3, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue5, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue6, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue7, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue8, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue9, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue10, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue11, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue12, List list4, Integer num3, Integer num4, LatLngBounds latLngBounds, Uri uri, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue13) {
        super(str, addressComponents, list, businessStatus, booleanPlaceAttributeValue, openingHours, booleanPlaceAttributeValue2, booleanPlaceAttributeValue3, str2, str3, num, str4, str5, latLng, str6, openingHours2, str7, list2, plusCode, num2, d, booleanPlaceAttributeValue4, list3, booleanPlaceAttributeValue5, booleanPlaceAttributeValue6, booleanPlaceAttributeValue7, booleanPlaceAttributeValue8, booleanPlaceAttributeValue9, booleanPlaceAttributeValue10, booleanPlaceAttributeValue11, booleanPlaceAttributeValue12, list4, num3, num4, latLngBounds, uri, booleanPlaceAttributeValue13);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        if (getAddress() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getAddress());
        }
        parcel.writeParcelable(getAddressComponents(), i);
        parcel.writeList(getAttributions());
        parcel.writeParcelable(getBusinessStatus(), i);
        parcel.writeParcelable(getCurbsidePickup(), i);
        parcel.writeParcelable(getCurrentOpeningHours(), i);
        parcel.writeParcelable(getDelivery(), i);
        parcel.writeParcelable(getDineIn(), i);
        if (getEditorialSummary() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getEditorialSummary());
        }
        if (getEditorialSummaryLanguageCode() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getEditorialSummaryLanguageCode());
        }
        if (getIconBackgroundColor() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(getIconBackgroundColor().intValue());
        }
        if (getIconUrl() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getIconUrl());
        }
        if (getId() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getId());
        }
        parcel.writeParcelable(getLatLng(), i);
        if (getName() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getName());
        }
        parcel.writeParcelable(getOpeningHours(), i);
        if (getPhoneNumber() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getPhoneNumber());
        }
        parcel.writeList(getPhotoMetadatas());
        parcel.writeParcelable(getPlusCode(), i);
        if (getPriceLevel() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(getPriceLevel().intValue());
        }
        if (getRating() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeDouble(getRating().doubleValue());
        }
        parcel.writeParcelable(getReservable(), i);
        parcel.writeList(getSecondaryOpeningHours());
        parcel.writeParcelable(getServesBeer(), i);
        parcel.writeParcelable(getServesBreakfast(), i);
        parcel.writeParcelable(getServesBrunch(), i);
        parcel.writeParcelable(getServesDinner(), i);
        parcel.writeParcelable(getServesLunch(), i);
        parcel.writeParcelable(getServesVegetarianFood(), i);
        parcel.writeParcelable(getServesWine(), i);
        parcel.writeParcelable(getTakeout(), i);
        parcel.writeList(getTypes());
        if (getUserRatingsTotal() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(getUserRatingsTotal().intValue());
        }
        if (getUtcOffsetMinutes() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(getUtcOffsetMinutes().intValue());
        }
        parcel.writeParcelable(getViewport(), i);
        parcel.writeParcelable(getWebsiteUri(), i);
        parcel.writeParcelable(getWheelchairAccessibleEntrance(), i);
    }
}
