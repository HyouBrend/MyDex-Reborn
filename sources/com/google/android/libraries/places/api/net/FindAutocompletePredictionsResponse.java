package com.google.android.libraries.places.api.net;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzkh;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class FindAutocompletePredictionsResponse {
    public static FindAutocompletePredictionsResponse newInstance(List<AutocompletePrediction> list) {
        return new zzl(zzkh.zzj(list));
    }

    public abstract List<AutocompletePrediction> getAutocompletePredictions();
}
