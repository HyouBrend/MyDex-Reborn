package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public enum zzhy implements Parcelable {
    FRAGMENT,
    INTENT;

    public static final Parcelable.Creator<zzhy> CREATOR = new Parcelable.Creator() { // from class: com.google.android.libraries.places.internal.zzhx
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            readString.getClass();
            return (zzhy) Enum.valueOf(zzhy.class, readString);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object[] newArray(int i) {
            return new zzhy[i];
        }
    };

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
