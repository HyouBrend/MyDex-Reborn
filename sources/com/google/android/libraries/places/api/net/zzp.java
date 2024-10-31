package com.google.android.libraries.places.api.net;

import com.google.android.libraries.places.api.model.PlaceLikelihood;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzp extends FindCurrentPlaceResponse {
    private final List zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(List list) {
        if (list == null) {
            throw new NullPointerException("Null placeLikelihoods");
        }
        this.zza = list;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FindCurrentPlaceResponse) {
            return this.zza.equals(((FindCurrentPlaceResponse) obj).getPlaceLikelihoods());
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
    public final List<PlaceLikelihood> getPlaceLikelihoods() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "FindCurrentPlaceResponse{placeLikelihoods=" + this.zza.toString() + "}";
    }
}
