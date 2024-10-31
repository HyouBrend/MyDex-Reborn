package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzag extends zzc {
    public static final Parcelable.Creator<zzag> CREATOR = new zzaf();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzag(List list) {
        super(list);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(asList());
    }
}
