package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public final class zzh implements GeoDataApi {
    @Override // com.google.android.gms.location.places.GeoDataApi
    public final PendingResult<PlaceBuffer> addPlace(GoogleApiClient googleApiClient, AddPlaceRequest addPlaceRequest) {
        Preconditions.checkNotNull(addPlaceRequest, "userAddedPlace == null");
        return googleApiClient.execute(new zzk(this, Places.GEO_DATA_API, googleApiClient, addPlaceRequest));
    }

    @Override // com.google.android.gms.location.places.GeoDataApi
    public final PendingResult<PlaceBuffer> getPlaceById(GoogleApiClient googleApiClient, String... strArr) {
        Preconditions.checkArgument(strArr != null, "placeIds == null");
        Preconditions.checkArgument(strArr.length > 0, "placeIds is empty");
        for (String str : strArr) {
            Preconditions.checkArgument(str != null, "placeId == null");
            Preconditions.checkArgument(!r4.isEmpty(), "placeId is empty");
        }
        return googleApiClient.enqueue(new zzl(this, Places.GEO_DATA_API, googleApiClient, strArr));
    }

    public final PendingResult<AutocompletePredictionBuffer> zzb(GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) {
        return googleApiClient.enqueue(new zzo(this, Places.GEO_DATA_API, googleApiClient, str, latLngBounds, i, autocompleteFilter));
    }

    @Override // com.google.android.gms.location.places.GeoDataApi
    public final PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        return googleApiClient.enqueue(new zzn(this, Places.GEO_DATA_API, googleApiClient, str, latLngBounds, autocompleteFilter));
    }

    @Override // com.google.android.gms.location.places.GeoDataApi
    public final PendingResult<PlacePhotoMetadataResult> getPlacePhotos(GoogleApiClient googleApiClient, String str) {
        Preconditions.checkNotNull(str, "placeId == null");
        Preconditions.checkArgument(!str.isEmpty(), "placeId is empty");
        return googleApiClient.enqueue(new zzj(this, Places.GEO_DATA_API, googleApiClient, str));
    }

    public final PendingResult<PlacePhotoResult> zzb(GoogleApiClient googleApiClient, PlacePhotoMetadata placePhotoMetadata, int i, int i2) {
        Preconditions.checkNotNull(placePhotoMetadata, "photo == null");
        Preconditions.checkArgument(i > 0, "width <= 0");
        Preconditions.checkArgument(i2 > 0, "height <= 0");
        zzap zzapVar = (zzap) placePhotoMetadata.freeze();
        String zzk = zzapVar.zzk();
        int index = zzapVar.getIndex();
        Preconditions.checkNotNull(zzk, "fifeUrl == null");
        return googleApiClient.enqueue(new zzm(this, Places.GEO_DATA_API, googleApiClient, zzk, i, i2, index));
    }
}
