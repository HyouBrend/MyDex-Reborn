package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzi implements Parcelable.Creator<PlaceFilter> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlaceFilter[] newArray(int i) {
        return new PlaceFilter[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlaceFilter createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<Integer> arrayList = null;
        ArrayList<String> arrayList2 = null;
        ArrayList arrayList3 = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createIntegerList(parcel, readHeader);
            } else if (fieldId == 6) {
                arrayList2 = SafeParcelReader.createStringList(parcel, readHeader);
            } else if (fieldId == 3) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId == 4) {
                arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, zzp.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PlaceFilter((List<Integer>) arrayList, z, (List<String>) arrayList2, (List<zzp>) arrayList3);
    }
}
