package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.PlaceTypes;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class PlaceEntity extends AbstractSafeParcelable implements ReflectedParcelable, Place {
    public static final Parcelable.Creator<PlaceEntity> CREATOR = new zzag();
    private final LatLng latLng;
    private Locale locale;
    private final String name;
    private final String zzbw;
    private final float zzbx;
    private final LatLngBounds zzby;
    private final String zzbz;
    private final boolean zzca;
    private final float zzcb;
    private final int zzcc;
    private final List<String> zzcd;
    private final zzal zzce;
    private final zzai zzcf;
    private final String zzcg;
    private final String zzf;
    private final List<Integer> zzg;
    private final String zzh;
    private final Uri zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlaceEntity(String str, List<Integer> list, String str2, String str3, String str4, List<String> list2, LatLng latLng, float f, LatLngBounds latLngBounds, String str5, Uri uri, boolean z, float f2, int i, zzal zzalVar, zzai zzaiVar, String str6) {
        this.zzbw = str;
        this.zzg = Collections.unmodifiableList(list);
        this.name = str2;
        this.zzf = str3;
        this.zzh = str4;
        this.zzcd = list2 != null ? list2 : Collections.emptyList();
        this.latLng = latLng;
        this.zzbx = f;
        this.zzby = latLngBounds;
        this.zzbz = str5 != null ? str5 : "UTC";
        this.zzi = uri;
        this.zzca = z;
        this.zzcb = f2;
        this.zzcc = i;
        this.locale = null;
        this.zzce = zzalVar;
        this.zzcf = zzaiVar;
        this.zzcg = str6;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ Place freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    /* loaded from: classes.dex */
    public static class zzb {
        private LatLng latLng;
        private String name;
        private String zzbw;
        private float zzbx;
        private LatLngBounds zzby;
        private boolean zzca;
        private List<String> zzcd;
        private zzal zzce;
        private String zzcg;
        private List<Integer> zzch;
        private zzai zzci;
        private String zzf;
        private String zzh;
        private Uri zzi;
        private int zzcc = -1;
        private float zzcb = -1.0f;

        public final zzb zzb(String str) {
            this.zzbw = str;
            return this;
        }

        public final zzb zzc(String str) {
            this.name = str;
            return this;
        }

        public final zzb zzb(LatLng latLng) {
            this.latLng = latLng;
            return this;
        }

        public final zzb zzb(float f) {
            this.zzbx = f;
            return this;
        }

        public final zzb zzb(LatLngBounds latLngBounds) {
            this.zzby = latLngBounds;
            return this;
        }

        public final zzb zzb(Uri uri) {
            this.zzi = uri;
            return this;
        }

        public final zzb zzb(boolean z) {
            this.zzca = z;
            return this;
        }

        public final zzb zzc(float f) {
            this.zzcb = f;
            return this;
        }

        public final zzb zzc(int i) {
            this.zzcc = i;
            return this;
        }

        public final zzb zzc(List<Integer> list) {
            this.zzch = list;
            return this;
        }

        public final zzb zzd(String str) {
            this.zzf = str;
            return this;
        }

        public final zzb zze(String str) {
            this.zzh = str;
            return this;
        }

        public final zzb zzd(List<String> list) {
            this.zzcd = list;
            return this;
        }

        public final zzb zzb(zzal zzalVar) {
            this.zzce = zzalVar;
            return this;
        }

        public final zzb zzb(zzai zzaiVar) {
            this.zzci = zzaiVar;
            return this;
        }

        public final zzb zzf(String str) {
            this.zzcg = str;
            return this;
        }

        public final PlaceEntity zzj() {
            return new PlaceEntity(this.zzbw, this.zzch, this.name, this.zzf, this.zzh, this.zzcd, this.latLng, this.zzbx, this.zzby, null, this.zzi, this.zzca, this.zzcb, this.zzcc, this.zzce, this.zzci, this.zzcg);
        }
    }

    @Override // com.google.android.gms.location.places.Place
    public final String getId() {
        return this.zzbw;
    }

    @Override // com.google.android.gms.location.places.Place
    public final List<Integer> getPlaceTypes() {
        return this.zzg;
    }

    @Override // com.google.android.gms.location.places.Place
    public final Locale getLocale() {
        return this.locale;
    }

    public final void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override // com.google.android.gms.location.places.Place
    public final LatLng getLatLng() {
        return this.latLng;
    }

    @Override // com.google.android.gms.location.places.Place
    public final LatLngBounds getViewport() {
        return this.zzby;
    }

    @Override // com.google.android.gms.location.places.Place
    public final Uri getWebsiteUri() {
        return this.zzi;
    }

    @Override // com.google.android.gms.location.places.Place
    @Nullable
    public final CharSequence getAttributions() {
        return zzi.zzc(this.zzcd);
    }

    @Override // com.google.android.gms.location.places.Place
    public final float getRating() {
        return this.zzcb;
    }

    @Override // com.google.android.gms.location.places.Place
    public final int getPriceLevel() {
        return this.zzcc;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("id", this.zzbw).add("placeTypes", this.zzg).add("locale", this.locale).add("name", this.name).add(PlaceTypes.ADDRESS, this.zzf).add("phoneNumber", this.zzh).add("latlng", this.latLng).add("viewport", this.zzby).add("websiteUri", this.zzi).add("isPermanentlyClosed", Boolean.valueOf(this.zzca)).add("priceLevel", Integer.valueOf(this.zzcc)).toString();
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbw, this.locale);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceEntity)) {
            return false;
        }
        PlaceEntity placeEntity = (PlaceEntity) obj;
        return this.zzbw.equals(placeEntity.zzbw) && Objects.equal(this.locale, placeEntity.locale);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getLatLng(), i, false);
        SafeParcelWriter.writeFloat(parcel, 5, this.zzbx);
        SafeParcelWriter.writeParcelable(parcel, 6, getViewport(), i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbz, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getWebsiteUri(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzca);
        SafeParcelWriter.writeFloat(parcel, 10, getRating());
        SafeParcelWriter.writeInt(parcel, 11, getPriceLevel());
        SafeParcelWriter.writeString(parcel, 14, (String) getAddress(), false);
        SafeParcelWriter.writeString(parcel, 15, (String) getPhoneNumber(), false);
        SafeParcelWriter.writeStringList(parcel, 17, this.zzcd, false);
        SafeParcelWriter.writeString(parcel, 19, (String) getName(), false);
        SafeParcelWriter.writeIntegerList(parcel, 20, getPlaceTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 21, this.zzce, i, false);
        SafeParcelWriter.writeParcelable(parcel, 22, this.zzcf, i, false);
        SafeParcelWriter.writeString(parcel, 23, this.zzcg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.location.places.Place
    public final /* synthetic */ CharSequence getPhoneNumber() {
        return this.zzh;
    }

    @Override // com.google.android.gms.location.places.Place
    public final /* synthetic */ CharSequence getName() {
        return this.name;
    }

    @Override // com.google.android.gms.location.places.Place
    public final /* synthetic */ CharSequence getAddress() {
        return this.zzf;
    }
}
