package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzaw extends zzr {
    public static final Parcelable.Creator<zzaw> CREATOR = new zzav();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaw(String str, int i, int i2, String str2) {
        super(str, i, i2, str2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getAttributions());
        parcel.writeInt(getHeight());
        parcel.writeInt(getWidth());
        parcel.writeString(zza());
    }
}
