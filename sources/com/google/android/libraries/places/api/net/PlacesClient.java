package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public interface PlacesClient {
    Task<FetchPhotoResponse> fetchPhoto(FetchPhotoRequest fetchPhotoRequest);

    Task<FetchPlaceResponse> fetchPlace(FetchPlaceRequest fetchPlaceRequest);

    Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest);

    Task<FindCurrentPlaceResponse> findCurrentPlace(FindCurrentPlaceRequest findCurrentPlaceRequest);

    Task<IsOpenResponse> isOpen(IsOpenRequest isOpenRequest);
}
