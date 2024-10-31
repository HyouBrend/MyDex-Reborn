package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Deprecated
/* loaded from: classes.dex */
public final class PlaceFilter extends com.google.android.gms.location.places.zzb {
    public static final Parcelable.Creator<PlaceFilter> CREATOR = new zzi();
    private static final PlaceFilter zzr = new PlaceFilter();
    private final List<Integer> zzs;
    private final boolean zzt;
    private final List<zzp> zzu;
    private final List<String> zzv;
    private final Set<Integer> zzw;
    private final Set<zzp> zzx;
    private final Set<String> zzy;

    @Deprecated
    /* loaded from: classes.dex */
    public static final class zzb {
        private Collection<zzp> zzaa;
        private String[] zzab;
        private boolean zzt;
        private Collection<Integer> zzz;

        private zzb() {
            this.zzz = null;
            this.zzt = false;
            this.zzaa = null;
            this.zzab = null;
        }
    }

    public PlaceFilter() {
        this(false, null);
    }

    public PlaceFilter(boolean z, Collection<String> collection) {
        this((Collection<Integer>) null, z, collection, (Collection<zzp>) null);
    }

    private PlaceFilter(Collection<Integer> collection, boolean z, Collection<String> collection2, Collection<zzp> collection3) {
        this((List<Integer>) zzb((Collection) null), z, (List<String>) zzb(collection2), (List<zzp>) zzb((Collection) null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlaceFilter(List<Integer> list, boolean z, List<String> list2, List<zzp> list3) {
        this.zzs = list;
        this.zzt = z;
        this.zzu = list3;
        this.zzv = list2;
        this.zzw = zzb((List) list);
        this.zzx = zzb((List) list3);
        this.zzy = zzb((List) list2);
    }

    @Override // com.google.android.gms.location.places.zzb
    public final Set<String> getPlaceIds() {
        return this.zzy;
    }

    @Override // com.google.android.gms.location.places.zzb
    public final boolean isRestrictedToPlacesOpenNow() {
        return this.zzt;
    }

    public final String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        if (!this.zzw.isEmpty()) {
            stringHelper.add("types", this.zzw);
        }
        stringHelper.add("requireOpenNow", Boolean.valueOf(this.zzt));
        if (!this.zzy.isEmpty()) {
            stringHelper.add("placeIds", this.zzy);
        }
        if (!this.zzx.isEmpty()) {
            stringHelper.add("requestedUserDataTypes", this.zzx);
        }
        return stringHelper.toString();
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzw, Boolean.valueOf(this.zzt), this.zzx, this.zzy);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        return this.zzw.equals(placeFilter.zzw) && this.zzt == placeFilter.zzt && this.zzx.equals(placeFilter.zzx) && this.zzy.equals(placeFilter.zzy);
    }

    @Deprecated
    public static PlaceFilter zzc() {
        new zzb();
        return new PlaceFilter((List<Integer>) null, false, (List<String>) null, (List<zzp>) null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 1, this.zzs, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzu, false);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzv, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
