package com.google.android.libraries.places.api.net;

import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.internal.zzkh;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class FindCurrentPlaceResponse {
    public static FindCurrentPlaceResponse newInstance(List<PlaceLikelihood> list) {
        return new zzp(zzkh.zzj(list));
    }

    public abstract List<PlaceLikelihood> getPlaceLikelihoods();
}
