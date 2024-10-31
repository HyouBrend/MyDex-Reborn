package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

@Deprecated
/* loaded from: classes.dex */
public class PlaceBufferResponse extends DataBufferResponse<Place, PlaceBuffer> {
    /* JADX WARN: Multi-variable type inference failed */
    public CharSequence getAttributions() {
        return ((PlaceBuffer) getResult()).getAttributions();
    }
}
