package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.IsOpenRequest;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzq extends IsOpenRequest.Builder {
    private Place zza;
    private String zzb;
    private long zzc;
    private CancellationToken zzd;
    private byte zze;

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final Place getPlace() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final String getPlaceId() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final long getUtcTimeMillis() {
        if (this.zze != 0) {
            return this.zzc;
        }
        throw new IllegalStateException("Property \"utcTimeMillis\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final IsOpenRequest.Builder setCancellationToken(CancellationToken cancellationToken) {
        this.zzd = cancellationToken;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final IsOpenRequest.Builder setPlace(Place place) {
        this.zza = place;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final IsOpenRequest.Builder setPlaceId(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    public final IsOpenRequest.Builder setUtcTimeMillis(long j) {
        this.zzc = j;
        this.zze = (byte) 1;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenRequest.Builder
    final IsOpenRequest zza() {
        if (this.zze == 1) {
            return new zzs(this.zza, this.zzb, this.zzc, this.zzd, null);
        }
        throw new IllegalStateException("Missing required properties: utcTimeMillis");
    }
}
