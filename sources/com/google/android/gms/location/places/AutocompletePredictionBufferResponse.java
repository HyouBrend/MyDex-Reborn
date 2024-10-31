package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

@Deprecated
/* loaded from: classes.dex */
public class AutocompletePredictionBufferResponse extends DataBufferResponse<AutocompletePrediction, AutocompletePredictionBuffer> {
    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        return ((AutocompletePredictionBuffer) getResult()).toString();
    }
}
