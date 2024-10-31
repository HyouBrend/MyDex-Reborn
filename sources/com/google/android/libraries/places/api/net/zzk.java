package com.google.android.libraries.places.api.net;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzk extends FindAutocompletePredictionsRequest {
    private final String zza;
    private final LocationBias zzb;
    private final LocationRestriction zzc;
    private final LatLng zzd;
    private final List zze;
    private final AutocompleteSessionToken zzf;
    private final TypeFilter zzg;
    private final List zzh;
    private final CancellationToken zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzk(String str, LocationBias locationBias, LocationRestriction locationRestriction, LatLng latLng, List list, AutocompleteSessionToken autocompleteSessionToken, TypeFilter typeFilter, List list2, CancellationToken cancellationToken, zzj zzjVar) {
        this.zza = str;
        this.zzb = locationBias;
        this.zzc = locationRestriction;
        this.zzd = latLng;
        this.zze = list;
        this.zzf = autocompleteSessionToken;
        this.zzg = typeFilter;
        this.zzh = list2;
        this.zzi = cancellationToken;
    }

    public final boolean equals(Object obj) {
        AutocompleteSessionToken autocompleteSessionToken;
        TypeFilter typeFilter;
        CancellationToken cancellationToken;
        if (obj == this) {
            return true;
        }
        if (obj instanceof FindAutocompletePredictionsRequest) {
            FindAutocompletePredictionsRequest findAutocompletePredictionsRequest = (FindAutocompletePredictionsRequest) obj;
            String str = this.zza;
            if (str != null ? str.equals(findAutocompletePredictionsRequest.getQuery()) : findAutocompletePredictionsRequest.getQuery() == null) {
                LocationBias locationBias = this.zzb;
                if (locationBias != null ? locationBias.equals(findAutocompletePredictionsRequest.getLocationBias()) : findAutocompletePredictionsRequest.getLocationBias() == null) {
                    LocationRestriction locationRestriction = this.zzc;
                    if (locationRestriction != null ? locationRestriction.equals(findAutocompletePredictionsRequest.getLocationRestriction()) : findAutocompletePredictionsRequest.getLocationRestriction() == null) {
                        LatLng latLng = this.zzd;
                        if (latLng != null ? latLng.equals(findAutocompletePredictionsRequest.getOrigin()) : findAutocompletePredictionsRequest.getOrigin() == null) {
                            if (this.zze.equals(findAutocompletePredictionsRequest.getCountries()) && ((autocompleteSessionToken = this.zzf) != null ? autocompleteSessionToken.equals(findAutocompletePredictionsRequest.getSessionToken()) : findAutocompletePredictionsRequest.getSessionToken() == null) && ((typeFilter = this.zzg) != null ? typeFilter.equals(findAutocompletePredictionsRequest.getTypeFilter()) : findAutocompletePredictionsRequest.getTypeFilter() == null) && this.zzh.equals(findAutocompletePredictionsRequest.getTypesFilter()) && ((cancellationToken = this.zzi) != null ? cancellationToken.equals(findAutocompletePredictionsRequest.getCancellationToken()) : findAutocompletePredictionsRequest.getCancellationToken() == null)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest, com.google.android.libraries.places.internal.zzhc
    public final CancellationToken getCancellationToken() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final List<String> getCountries() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final LocationBias getLocationBias() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final LocationRestriction getLocationRestriction() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final LatLng getOrigin() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final String getQuery() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final AutocompleteSessionToken getSessionToken() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    @Deprecated
    public final TypeFilter getTypeFilter() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
    public final List<String> getTypesFilter() {
        return this.zzh;
    }

    public final String toString() {
        return "FindAutocompletePredictionsRequest{query=" + this.zza + ", locationBias=" + String.valueOf(this.zzb) + ", locationRestriction=" + String.valueOf(this.zzc) + ", origin=" + String.valueOf(this.zzd) + ", countries=" + this.zze.toString() + ", sessionToken=" + String.valueOf(this.zzf) + ", typeFilter=" + String.valueOf(this.zzg) + ", typesFilter=" + this.zzh.toString() + ", cancellationToken=" + String.valueOf(this.zzi) + "}";
    }

    public final int hashCode() {
        String str = this.zza;
        int hashCode = str == null ? 0 : str.hashCode();
        LocationBias locationBias = this.zzb;
        int hashCode2 = locationBias == null ? 0 : locationBias.hashCode();
        int i = hashCode ^ 1000003;
        LocationRestriction locationRestriction = this.zzc;
        int hashCode3 = ((((i * 1000003) ^ hashCode2) * 1000003) ^ (locationRestriction == null ? 0 : locationRestriction.hashCode())) * 1000003;
        LatLng latLng = this.zzd;
        int hashCode4 = (((hashCode3 ^ (latLng == null ? 0 : latLng.hashCode())) * 1000003) ^ this.zze.hashCode()) * 1000003;
        AutocompleteSessionToken autocompleteSessionToken = this.zzf;
        int hashCode5 = (hashCode4 ^ (autocompleteSessionToken == null ? 0 : autocompleteSessionToken.hashCode())) * 1000003;
        TypeFilter typeFilter = this.zzg;
        int hashCode6 = (((hashCode5 ^ (typeFilter == null ? 0 : typeFilter.hashCode())) * 1000003) ^ this.zzh.hashCode()) * 1000003;
        CancellationToken cancellationToken = this.zzi;
        return hashCode6 ^ (cancellationToken != null ? cancellationToken.hashCode() : 0);
    }
}
