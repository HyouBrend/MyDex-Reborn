package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzat implements Parcelable.Creator<zzau> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzau[] newArray(int i) {
        return new zzau[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzau createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 2) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 3) {
                str3 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 4) {
                str4 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 6) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 7) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzau(str, str2, str3, str4, i, i2);
    }
}
