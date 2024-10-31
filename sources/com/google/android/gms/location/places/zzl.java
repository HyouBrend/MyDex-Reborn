package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzl implements Parcelable.Creator<PlacePhotoResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlacePhotoResult[] newArray(int i) {
        return new PlacePhotoResult[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlacePhotoResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        BitmapTeleporter bitmapTeleporter = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            } else if (fieldId == 2) {
                bitmapTeleporter = (BitmapTeleporter) SafeParcelReader.createParcelable(parcel, readHeader, BitmapTeleporter.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PlacePhotoResult(status, bitmapTeleporter);
    }
}
