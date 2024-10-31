package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;

/* loaded from: classes.dex */
public final class zzas extends zzaw implements PlacePhotoMetadata {
    private final String zzcu;

    public zzas(DataHolder dataHolder, int i) {
        super(dataHolder, i);
        this.zzcu = getString("photo_fife_url");
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final int getMaxWidth() {
        return zzc("photo_max_width", 0);
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final int getMaxHeight() {
        return zzc("photo_max_height", 0);
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final CharSequence getAttributions() {
        return zzb("photo_attributions", (String) null);
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        return ((PlacePhotoMetadata) freeze()).getScaledPhoto(googleApiClient, i, i2);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ PlacePhotoMetadata freeze() {
        return new zzap(this.zzcu, getMaxWidth(), getMaxHeight(), getAttributions(), this.mDataRow);
    }
}
