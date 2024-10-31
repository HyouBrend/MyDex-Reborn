package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzy extends RectangularBounds {
    private final LatLng zza;
    private final LatLng zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(LatLng latLng, LatLng latLng2) {
        if (latLng == null) {
            throw new NullPointerException("Null southwest");
        }
        this.zza = latLng;
        if (latLng2 != null) {
            this.zzb = latLng2;
            return;
        }
        throw new NullPointerException("Null northeast");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RectangularBounds) {
            RectangularBounds rectangularBounds = (RectangularBounds) obj;
            if (this.zza.equals(rectangularBounds.getSouthwest()) && this.zzb.equals(rectangularBounds.getNortheast())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds
    public final LatLng getNortheast() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds
    public final LatLng getSouthwest() {
        return this.zza;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        return "RectangularBounds{southwest=" + this.zza.toString() + ", northeast=" + this.zzb.toString() + "}";
    }
}
