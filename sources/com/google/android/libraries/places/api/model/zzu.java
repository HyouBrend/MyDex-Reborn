package com.google.android.libraries.places.api.model;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzu extends PlaceLikelihood {
    private final Place zza;
    private final double zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(Place place, double d) {
        if (place == null) {
            throw new NullPointerException("Null place");
        }
        this.zza = place;
        this.zzb = d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlaceLikelihood) {
            PlaceLikelihood placeLikelihood = (PlaceLikelihood) obj;
            if (this.zza.equals(placeLikelihood.getPlace()) && Double.doubleToLongBits(this.zzb) == Double.doubleToLongBits(placeLikelihood.getLikelihood())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.PlaceLikelihood
    public final double getLikelihood() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.PlaceLikelihood
    public final Place getPlace() {
        return this.zza;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.zzb) >>> 32) ^ Double.doubleToLongBits(this.zzb)));
    }

    public final String toString() {
        return "PlaceLikelihood{place=" + this.zza.toString() + ", likelihood=" + this.zzb + "}";
    }
}
