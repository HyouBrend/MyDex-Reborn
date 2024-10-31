package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;

/* loaded from: classes.dex */
public final class zzap implements PlacePhotoMetadata {
    private final int index;
    private final int maxHeight;
    private final int maxWidth;
    private final String zzcu;
    private final CharSequence zzcv;

    public zzap(String str, int i, int i2, CharSequence charSequence, int i3) {
        this.zzcu = str;
        this.maxWidth = i;
        this.maxHeight = i2;
        this.zzcv = charSequence;
        this.index = i3;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ PlacePhotoMetadata freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String zzk() {
        return this.zzcu;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final int getMaxWidth() {
        return this.maxWidth;
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final int getMaxHeight() {
        return this.maxHeight;
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final CharSequence getAttributions() {
        return this.zzcv;
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    @Override // com.google.android.gms.location.places.PlacePhotoMetadata
    public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        return ((zzh) Places.GeoDataApi).zzb(googleApiClient, this, i, i2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzap)) {
            return false;
        }
        zzap zzapVar = (zzap) obj;
        return zzapVar.maxWidth == this.maxWidth && zzapVar.maxHeight == this.maxHeight && Objects.equal(zzapVar.zzcu, this.zzcu) && Objects.equal(zzapVar.zzcv, this.zzcv);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxWidth), Integer.valueOf(this.maxHeight), this.zzcu, this.zzcv);
    }
}
