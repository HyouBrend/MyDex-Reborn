package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public class AutocompleteFilter extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<AutocompleteFilter> CREATOR = new zzd();
    public static final int TYPE_FILTER_ADDRESS = 2;
    public static final int TYPE_FILTER_CITIES = 5;
    public static final int TYPE_FILTER_ESTABLISHMENT = 34;
    public static final int TYPE_FILTER_GEOCODE = 1007;
    public static final int TYPE_FILTER_NONE = 0;
    public static final int TYPE_FILTER_REGIONS = 4;
    private final int versionCode;
    private final boolean zzj;
    private final List<Integer> zzk;
    private final String zzl;
    private final int zzm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutocompleteFilter(int i, boolean z, List<Integer> list, String str) {
        this.versionCode = i;
        this.zzk = list;
        this.zzm = (list == null || list.isEmpty()) ? 0 : list.iterator().next().intValue();
        this.zzl = str;
        if (i <= 0) {
            this.zzj = !z;
        } else {
            this.zzj = z;
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean zzj = false;
        private int zzm = 0;
        private String zzl = "";

        public final Builder setTypeFilter(int i) {
            this.zzm = i;
            return this;
        }

        public final Builder setCountry(String str) {
            this.zzl = str;
            return this;
        }

        public final AutocompleteFilter build() {
            return new AutocompleteFilter(1, false, Arrays.asList(Integer.valueOf(this.zzm)), this.zzl);
        }
    }

    public int getTypeFilter() {
        return this.zzm;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzj), Integer.valueOf(this.zzm), this.zzl);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompleteFilter)) {
            return false;
        }
        AutocompleteFilter autocompleteFilter = (AutocompleteFilter) obj;
        return this.zzm == autocompleteFilter.zzm && this.zzj == autocompleteFilter.zzj && this.zzl == autocompleteFilter.zzl;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("includeQueryPredictions", Boolean.valueOf(this.zzj)).add("typeFilter", Integer.valueOf(this.zzm)).add(com.google.android.libraries.places.api.model.PlaceTypes.COUNTRY, this.zzl).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzj);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzl, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
