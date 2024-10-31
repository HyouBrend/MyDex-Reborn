package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzht extends zzhr {
    public static final Parcelable.Creator<zzht> CREATOR = new zzhs();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzht(AutocompleteActivityMode autocompleteActivityMode, zzkh zzkhVar, zzhy zzhyVar, String str, String str2, LocationBias locationBias, LocationRestriction locationRestriction, zzkh zzkhVar2, TypeFilter typeFilter, zzkh zzkhVar3, int i, int i2) {
        super(autocompleteActivityMode, zzkhVar, zzhyVar, str, str2, locationBias, locationRestriction, zzkhVar2, typeFilter, zzkhVar3, i, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(zzh(), i);
        parcel.writeList(zzj());
        parcel.writeParcelable(zzf(), i);
        if (zzm() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzm());
        }
        if (zzl() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzl());
        }
        parcel.writeParcelable(zzc(), i);
        parcel.writeParcelable(zzd(), i);
        parcel.writeList(zzi());
        parcel.writeParcelable(zze(), i);
        parcel.writeList(zzk());
        parcel.writeInt(zza());
        parcel.writeInt(zzb());
    }
}
