package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class RectangularBounds implements LocationBias, LocationRestriction {
    public static RectangularBounds newInstance(LatLng latLng, LatLng latLng2) {
        return newInstance(new LatLngBounds(latLng, latLng2));
    }

    public abstract LatLng getNortheast();

    public abstract LatLng getSouthwest();

    public static RectangularBounds newInstance(LatLngBounds latLngBounds) {
        zzx zzxVar = new zzx();
        zzxVar.zzb(latLngBounds.southwest);
        zzxVar.zza(latLngBounds.northeast);
        return zzxVar.zzc();
    }
}
