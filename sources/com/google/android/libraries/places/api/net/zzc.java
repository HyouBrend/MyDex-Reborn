package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.PhotoMetadata;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzc extends FetchPhotoRequest {
    private final Integer zza;
    private final Integer zzb;
    private final PhotoMetadata zzc;
    private final CancellationToken zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzc(Integer num, Integer num2, PhotoMetadata photoMetadata, CancellationToken cancellationToken, zzb zzbVar) {
        this.zza = num;
        this.zzb = num2;
        this.zzc = photoMetadata;
        this.zzd = cancellationToken;
    }

    public final boolean equals(Object obj) {
        CancellationToken cancellationToken;
        if (obj == this) {
            return true;
        }
        if (obj instanceof FetchPhotoRequest) {
            FetchPhotoRequest fetchPhotoRequest = (FetchPhotoRequest) obj;
            Integer num = this.zza;
            if (num != null ? num.equals(fetchPhotoRequest.getMaxWidth()) : fetchPhotoRequest.getMaxWidth() == null) {
                Integer num2 = this.zzb;
                if (num2 != null ? num2.equals(fetchPhotoRequest.getMaxHeight()) : fetchPhotoRequest.getMaxHeight() == null) {
                    if (this.zzc.equals(fetchPhotoRequest.getPhotoMetadata()) && ((cancellationToken = this.zzd) != null ? cancellationToken.equals(fetchPhotoRequest.getCancellationToken()) : fetchPhotoRequest.getCancellationToken() == null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPhotoRequest, com.google.android.libraries.places.internal.zzhc
    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPhotoRequest
    public final Integer getMaxHeight() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPhotoRequest
    public final Integer getMaxWidth() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPhotoRequest
    public final PhotoMetadata getPhotoMetadata() {
        return this.zzc;
    }

    public final String toString() {
        return "FetchPhotoRequest{maxWidth=" + this.zza + ", maxHeight=" + this.zzb + ", photoMetadata=" + this.zzc.toString() + ", cancellationToken=" + String.valueOf(this.zzd) + "}";
    }

    public final int hashCode() {
        Integer num = this.zza;
        int hashCode = num == null ? 0 : num.hashCode();
        Integer num2 = this.zzb;
        int hashCode2 = ((((hashCode ^ 1000003) * 1000003) ^ (num2 == null ? 0 : num2.hashCode())) * 1000003) ^ this.zzc.hashCode();
        CancellationToken cancellationToken = this.zzd;
        return (hashCode2 * 1000003) ^ (cancellationToken != null ? cancellationToken.hashCode() : 0);
    }
}
