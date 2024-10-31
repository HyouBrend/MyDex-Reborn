package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhs implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzht((AutocompleteActivityMode) parcel.readParcelable(zzia.class.getClassLoader()), zzkh.zzj(parcel.readArrayList(Place.Field.class.getClassLoader())), (zzhy) parcel.readParcelable(zzia.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, (LocationBias) parcel.readParcelable(zzia.class.getClassLoader()), (LocationRestriction) parcel.readParcelable(zzia.class.getClassLoader()), zzkh.zzj(parcel.readArrayList(String.class.getClassLoader())), (TypeFilter) parcel.readParcelable(zzia.class.getClassLoader()), zzkh.zzj(parcel.readArrayList(String.class.getClassLoader())), parcel.readInt(), parcel.readInt());
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzht[i];
    }
}
