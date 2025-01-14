package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public class AddPlaceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AddPlaceRequest> CREATOR = new zzc();
    private final LatLng latLng;
    private final String name;
    private final String zzf;
    private final List<Integer> zzg;
    private final String zzh;
    private final Uri zzi;

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, String str3, Uri uri) {
        this.name = Preconditions.checkNotEmpty(str);
        this.latLng = (LatLng) Preconditions.checkNotNull(latLng);
        this.zzf = Preconditions.checkNotEmpty(str2);
        this.zzg = new ArrayList((Collection) Preconditions.checkNotNull(list));
        boolean z = true;
        Preconditions.checkArgument(!r1.isEmpty(), "At least one place type should be provided.");
        if (TextUtils.isEmpty(str3) && uri == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "One of phone number or URI should be provided.");
        this.zzh = str3;
        this.zzi = uri;
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, String str3) {
        this(str, latLng, str2, list, Preconditions.checkNotEmpty(str3), null);
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, Uri uri) {
        this(str, latLng, str2, list, null, (Uri) Preconditions.checkNotNull(uri));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getLatLng(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getAddress(), false);
        SafeParcelWriter.writeIntegerList(parcel, 4, getPlaceTypes(), false);
        SafeParcelWriter.writeString(parcel, 5, getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getWebsiteUri(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String getName() {
        return this.name;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public String getAddress() {
        return this.zzf;
    }

    public List<Integer> getPlaceTypes() {
        return this.zzg;
    }

    public String getPhoneNumber() {
        return this.zzh;
    }

    public Uri getWebsiteUri() {
        return this.zzi;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("latLng", this.latLng).add(com.google.android.libraries.places.api.model.PlaceTypes.ADDRESS, this.zzf).add("placeTypes", this.zzg).add("phoneNumer", this.zzh).add("websiteUri", this.zzi).toString();
    }
}
