package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzfo extends zzft {
    private final Location zza;
    private final zzkh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfo(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzkh zzkhVar, Locale locale, String str, boolean z, zzho zzhoVar) {
        super(findCurrentPlaceRequest, locale, str, false, zzhoVar);
        this.zza = location;
        this.zzb = zzkhVar;
    }

    @Override // com.google.android.libraries.places.internal.zzft
    protected final String zze() {
        return "findplacefromuserlocation/json";
    }

    @Override // com.google.android.libraries.places.internal.zzft
    public final Map zzf() {
        Integer valueOf;
        FindCurrentPlaceRequest findCurrentPlaceRequest = (FindCurrentPlaceRequest) zzb();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "location", zzgl.zza(this.zza), null);
        zzg(hashMap, "wifiaccesspoints", zzgl.zze(this.zzb, 4000), null);
        Location location = this.zza;
        if (location != null) {
            float accuracy = location.getAccuracy();
            if (location.hasAccuracy() && accuracy > 0.0f) {
                valueOf = Integer.valueOf(Math.round(accuracy * 100.0f));
                zzg(hashMap, "precision", valueOf, null);
                zzg(hashMap, "timestamp", Long.valueOf(this.zza.getTime()), null);
                zzg(hashMap, "fields", zzgm.zza(findCurrentPlaceRequest.getPlaceFields()), null);
                return hashMap;
            }
        }
        valueOf = null;
        zzg(hashMap, "precision", valueOf, null);
        zzg(hashMap, "timestamp", Long.valueOf(this.zza.getTime()), null);
        zzg(hashMap, "fields", zzgm.zza(findCurrentPlaceRequest.getPlaceFields()), null);
        return hashMap;
    }
}
