package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzfg extends zzft {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfg(FetchPlaceRequest fetchPlaceRequest, Locale locale, String str, boolean z, zzho zzhoVar) {
        super(fetchPlaceRequest, locale, str, false, zzhoVar);
    }

    @Override // com.google.android.libraries.places.internal.zzft
    protected final String zze() {
        return "details/json";
    }

    @Override // com.google.android.libraries.places.internal.zzft
    public final Map zzf() {
        FetchPlaceRequest fetchPlaceRequest = (FetchPlaceRequest) zzb();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "placeid", fetchPlaceRequest.getPlaceId(), null);
        zzg(hashMap, "sessiontoken", fetchPlaceRequest.getSessionToken(), null);
        zzg(hashMap, "fields", zzgm.zza(fetchPlaceRequest.getPlaceFields()), null);
        return hashMap;
    }
}
