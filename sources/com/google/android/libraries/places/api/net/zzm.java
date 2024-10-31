package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzm extends FindCurrentPlaceRequest.Builder {
    private List zza;
    private CancellationToken zzb;

    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceRequest.Builder
    public final CancellationToken getCancellationToken() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceRequest.Builder
    public final FindCurrentPlaceRequest.Builder setCancellationToken(CancellationToken cancellationToken) {
        this.zzb = cancellationToken;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceRequest.Builder
    public final FindCurrentPlaceRequest.Builder zza(List list) {
        if (list == null) {
            throw new NullPointerException("Null placeFields");
        }
        this.zza = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceRequest.Builder
    final FindCurrentPlaceRequest zzb() {
        List list = this.zza;
        if (list != null) {
            return new zzo(list, this.zzb, null);
        }
        throw new IllegalStateException("Missing required properties: placeFields");
    }
}
