package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Response;

@Deprecated
/* loaded from: classes.dex */
public class PlacePhotoMetadataResponse extends Response<PlacePhotoMetadataResult> {
    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return getResult().getPhotoMetadata();
    }
}
