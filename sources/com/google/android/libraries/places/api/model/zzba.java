package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzba extends zzu {
    public static final Parcelable.Creator<zzba> CREATOR = new zzaz();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(Place place, double d) {
        super(place, d);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getPlace(), i);
        parcel.writeDouble(getLikelihood());
    }
}
