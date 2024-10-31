package com.google.android.libraries.places.widget.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public enum AutocompleteActivityMode implements Parcelable {
    FULLSCREEN,
    OVERLAY;

    public static final Parcelable.Creator<AutocompleteActivityMode> CREATOR = new Parcelable.Creator() { // from class: com.google.android.libraries.places.widget.model.zza
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            readString.getClass();
            return AutocompleteActivityMode.valueOf(readString);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object[] newArray(int i) {
            return new AutocompleteActivityMode[i];
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
