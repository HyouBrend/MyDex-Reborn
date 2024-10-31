package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

/* loaded from: classes.dex */
public final class zzam extends zzaw implements PlaceLikelihood {
    public zzam(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    @Override // com.google.android.gms.location.places.PlaceLikelihood
    public final float getLikelihood() {
        return zzb("place_likelihood", -1.0f);
    }

    @Override // com.google.android.gms.location.places.PlaceLikelihood
    public final Place getPlace() {
        return new zzar(this.mDataHolder, this.mDataRow);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ PlaceLikelihood freeze() {
        PlaceEntity placeEntity = (PlaceEntity) getPlace().freeze();
        return new zzak((PlaceEntity) Preconditions.checkNotNull(placeEntity), getLikelihood());
    }
}
