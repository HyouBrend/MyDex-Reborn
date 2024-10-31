package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.libraries.places.internal.zzjp;
import com.google.android.libraries.places.internal.zzkt;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class PlaceLikelihood implements Parcelable {
    public static final double LIKELIHOOD_MAX_VALUE = 1.0d;
    public static final double LIKELIHOOD_MIN_VALUE = 0.0d;

    public static PlaceLikelihood newInstance(Place place, double d) {
        Double valueOf = Double.valueOf(Utils.DOUBLE_EPSILON);
        Double valueOf2 = Double.valueOf(1.0d);
        zzkt zzb = zzkt.zzb(valueOf, valueOf2);
        Double valueOf3 = Double.valueOf(d);
        zzjp.zzh(zzb.zzd(valueOf3), "Likelihood must not be out-of-range: %s to %s, but was: %s.", valueOf, valueOf2, valueOf3);
        return new zzba(place, d);
    }

    public abstract double getLikelihood();

    public abstract Place getPlace();
}
