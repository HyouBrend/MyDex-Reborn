package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzfa extends zzft {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfa(FetchPhotoRequest fetchPhotoRequest, String str, boolean z, zzho zzhoVar) {
        super(fetchPhotoRequest, null, str, false, zzhoVar);
    }

    @Override // com.google.android.libraries.places.internal.zzft
    protected final String zze() {
        return "photo";
    }

    @Override // com.google.android.libraries.places.internal.zzft
    public final Map zzf() {
        FetchPhotoRequest fetchPhotoRequest = (FetchPhotoRequest) zzb();
        PhotoMetadata photoMetadata = fetchPhotoRequest.getPhotoMetadata();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "maxheight", fetchPhotoRequest.getMaxHeight(), null);
        zzg(hashMap, "maxwidth", fetchPhotoRequest.getMaxWidth(), null);
        hashMap.put("photoreference", photoMetadata.zza());
        return hashMap;
    }
}
