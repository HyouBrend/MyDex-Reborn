package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

@Deprecated
/* loaded from: classes.dex */
public class PlaceLikelihoodBufferResponse extends DataBufferResponse<PlaceLikelihood, PlaceLikelihoodBuffer> {
    /* JADX WARN: Multi-variable type inference failed */
    public CharSequence getAttributions() {
        return ((PlaceLikelihoodBuffer) getResult()).getAttributions();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        return ((PlaceLikelihoodBuffer) getResult()).toString();
    }
}
