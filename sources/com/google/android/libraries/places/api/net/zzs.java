package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzs extends IsOpenRequest {
    private final Place zza;
    private final String zzb;
    private final long zzc;
    private final CancellationToken zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzs(Place place, String str, long j, CancellationToken cancellationToken, zzr zzrVar) {
        this.zza = place;
        this.zzb = str;
        this.zzc = j;
        this.zzd = cancellationToken;
    }

    public final boolean equals(Object obj) {
        CancellationToken cancellationToken;
        if (obj == this) {
            return true;
        }
        if (obj instanceof IsOpenRequest) {
            IsOpenRequest isOpenRequest = (IsOpenRequest) obj;
            Place place = this.zza;
            if (place != null ? place.equals(isOpenRequest.getPlace()) : isOpenRequest.getPlace() == null) {
                String str = this.zzb;
                if (str != null ? str.equals(isOpenRequest.getPlaceId()) : isOpenRequest.getPlaceId() == null) {
                    if (this.zzc == isOpenRequest.getUtcTimeMillis() && ((cancellationToken = this.zzd) != null ? cancellationToken.equals(isOpenRequest.getCancellationToken()) : isOpenRequest.getCancellationToken() == null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest, com.google.android.libraries.places.internal.zzhc
    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest
    public final Place getPlace() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest
    public final String getPlaceId() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest
    public final long getUtcTimeMillis() {
        return this.zzc;
    }

    public final String toString() {
        return "IsOpenRequest{place=" + String.valueOf(this.zza) + ", placeId=" + this.zzb + ", utcTimeMillis=" + this.zzc + ", cancellationToken=" + String.valueOf(this.zzd) + "}";
    }

    public final int hashCode() {
        Place place = this.zza;
        int hashCode = place == null ? 0 : place.hashCode();
        String str = this.zzb;
        int hashCode2 = str == null ? 0 : str.hashCode();
        int i = hashCode ^ 1000003;
        long j = this.zzc;
        long j2 = j ^ (j >>> 32);
        CancellationToken cancellationToken = this.zzd;
        return (((((i * 1000003) ^ hashCode2) * 1000003) ^ ((int) j2)) * 1000003) ^ (cancellationToken != null ? cancellationToken.hashCode() : 0);
    }
}
