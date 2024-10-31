package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzx extends zzbv {
    private LatLng zza;
    private LatLng zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbv
    public final zzbv zza(LatLng latLng) {
        if (latLng == null) {
            throw new NullPointerException("Null northeast");
        }
        this.zzb = latLng;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbv zzb(LatLng latLng) {
        if (latLng == null) {
            throw new NullPointerException("Null southwest");
        }
        this.zza = latLng;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbv
    public final RectangularBounds zzc() {
        LatLng latLng;
        LatLng latLng2 = this.zza;
        if (latLng2 == null || (latLng = this.zzb) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" southwest");
            }
            if (this.zzb == null) {
                sb.append(" northeast");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzbe(latLng2, latLng);
    }
}
