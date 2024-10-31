package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzfk extends zzft {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfk(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, Locale locale, String str, boolean z, zzho zzhoVar) {
        super(findAutocompletePredictionsRequest, locale, str, false, zzhoVar);
    }

    @Override // com.google.android.libraries.places.internal.zzft
    protected final String zze() {
        return "autocomplete/json";
    }

    @Override // com.google.android.libraries.places.internal.zzft
    public final Map zzf() {
        HashMap hashMap = new HashMap();
        FindAutocompletePredictionsRequest findAutocompletePredictionsRequest = (FindAutocompletePredictionsRequest) zzb();
        TypeFilter typeFilter = findAutocompletePredictionsRequest.getTypeFilter();
        List<String> typesFilter = findAutocompletePredictionsRequest.getTypesFilter();
        String query = findAutocompletePredictionsRequest.getQuery();
        zzg(hashMap, "input", query == null ? null : query.replaceFirst("^\\s+", "").replaceFirst("\\s+$", " "), null);
        if (!typesFilter.isEmpty()) {
            zzg(hashMap, "types", TextUtils.join("|", typesFilter), null);
        } else {
            zzg(hashMap, "types", typeFilter != null ? zzgn.zza(typeFilter) : null, null);
        }
        zzg(hashMap, "sessiontoken", findAutocompletePredictionsRequest.getSessionToken(), null);
        zzg(hashMap, "origin", zzgl.zzb(findAutocompletePredictionsRequest.getOrigin()), null);
        zzg(hashMap, "locationbias", zzgl.zzc(findAutocompletePredictionsRequest.getLocationBias()), null);
        zzg(hashMap, "locationrestriction", zzgl.zzd(findAutocompletePredictionsRequest.getLocationRestriction()), null);
        List<String> countries = findAutocompletePredictionsRequest.getCountries();
        StringBuilder sb = new StringBuilder();
        for (String str : countries) {
            String concat = TextUtils.isEmpty(str) ? null : "country:".concat(String.valueOf(str.toLowerCase(Locale.US)));
            if (concat != null) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(concat);
            }
        }
        zzg(hashMap, "components", sb.length() == 0 ? null : sb.toString(), null);
        return hashMap;
    }
}
