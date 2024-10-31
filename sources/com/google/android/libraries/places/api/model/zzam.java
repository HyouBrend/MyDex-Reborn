package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzam extends zzh {
    public static final Parcelable.Creator<zzam> CREATOR = new zzal();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzam(ParcelUuid parcelUuid) {
        super(parcelUuid);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(zza(), i);
    }
}
