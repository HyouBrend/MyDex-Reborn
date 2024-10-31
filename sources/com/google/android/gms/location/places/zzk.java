package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzk implements Parcelable.Creator<PlacePhotoMetadataResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlacePhotoMetadataResult[] newArray(int i) {
        return new PlacePhotoMetadataResult[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlacePhotoMetadataResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            } else if (fieldId == 2) {
                dataHolder = (DataHolder) SafeParcelReader.createParcelable(parcel, readHeader, DataHolder.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PlacePhotoMetadataResult(status, dataHolder);
    }
}
