package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzez;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzfm {
    public static final FindAutocompletePredictionsResponse zza(zzfl zzflVar) throws ApiException {
        int zza = zzgk.zza(zzflVar.status);
        if (PlacesStatusCodes.isError(zza)) {
            throw new ApiException(new Status(zza, zzgk.zzb(zzflVar.status, zzflVar.errorMessage)));
        }
        ArrayList arrayList = new ArrayList();
        zzez[] zzezVarArr = zzflVar.predictions;
        if (zzezVarArr != null) {
            for (zzez zzezVar : zzezVarArr) {
                if (zzezVar != null && !TextUtils.isEmpty(zzezVar.zzf())) {
                    AutocompletePrediction.Builder builder = AutocompletePrediction.builder(zzezVar.zzf());
                    builder.setDistanceMeters(zzezVar.zzd());
                    builder.setPlaceTypes(zzgg.zzd(zzgg.zze(zzezVar.zzc())));
                    builder.setFullText(zzju.zzb(zzezVar.zze()));
                    builder.zza(zzb(zzezVar.zzb()));
                    zzez.zza zza2 = zzezVar.zza();
                    if (zza2 != null) {
                        builder.setPrimaryText(zzju.zzb(zza2.zzc()));
                        builder.zzc(zzb(zza2.zza()));
                        builder.setSecondaryText(zzju.zzb(zza2.zzd()));
                        builder.zzd(zzb(zza2.zzb()));
                    }
                    arrayList.add(builder.build());
                } else {
                    throw new ApiException(new Status(8, "Unexpected server error: Place ID not provided for an autocomplete prediction result"));
                }
            }
        }
        return FindAutocompletePredictionsResponse.newInstance(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static List zzb(List list) throws ApiException {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        zzlf listIterator = ((zzkh) list).listIterator(0);
        while (listIterator.hasNext()) {
            zzez.zzb zzbVar = (zzez.zzb) listIterator.next();
            Status status = new Status(8, "Unexpected server error: Place ID not provided for an autocomplete prediction result");
            if (zzbVar != null) {
                Integer num = zzbVar.offset;
                Integer num2 = zzbVar.length;
                if (num == null || num2 == null) {
                    throw new ApiException(status);
                }
                com.google.android.libraries.places.api.model.zzbj zzc = com.google.android.libraries.places.api.model.zzbk.zzc();
                zzc.zzb(num.intValue());
                zzc.zza(num2.intValue());
                arrayList.add(zzc.zzc());
            } else {
                throw new ApiException(status);
            }
        }
        return arrayList;
    }
}
