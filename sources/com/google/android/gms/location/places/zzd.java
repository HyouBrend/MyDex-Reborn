package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzd implements Parcelable.Creator<AutocompleteFilter> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AutocompleteFilter[] newArray(int i) {
        return new AutocompleteFilter[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AutocompleteFilter createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<Integer> arrayList = null;
        String str = null;
        int i = 0;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId == 2) {
                arrayList = SafeParcelReader.createIntegerList(parcel, readHeader);
            } else if (fieldId == 3) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 1000) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new AutocompleteFilter(i, z, arrayList, str);
    }
}
