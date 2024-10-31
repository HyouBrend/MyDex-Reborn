package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.TypeFilter;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzgn {
    private static final zzkk zza;

    static {
        zzkj zzkjVar = new zzkj();
        zzkjVar.zza(TypeFilter.ADDRESS, PlaceTypes.ADDRESS);
        zzkjVar.zza(TypeFilter.CITIES, PlaceTypes.CITIES);
        zzkjVar.zza(TypeFilter.ESTABLISHMENT, PlaceTypes.ESTABLISHMENT);
        zzkjVar.zza(TypeFilter.GEOCODE, PlaceTypes.GEOCODE);
        zzkjVar.zza(TypeFilter.REGIONS, PlaceTypes.REGIONS);
        zza = zzkjVar.zzb();
    }

    public static String zza(TypeFilter typeFilter) {
        String str = (String) zza.get(typeFilter);
        return str == null ? "" : str;
    }
}
