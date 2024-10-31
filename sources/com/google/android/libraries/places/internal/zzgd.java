package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzgd implements zzdz {
    private final zzho zza;
    private final zzee zzb;
    private final zzek zzc;
    private final zzha zzd;
    private final zzde zze;
    private final zzfe zzf;
    private final zzfi zzg;
    private final zzfm zzh;
    private final zzfq zzi;
    private final zzhb zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgd(zzhb zzhbVar, zzho zzhoVar, zzee zzeeVar, zzek zzekVar, zzha zzhaVar, zzde zzdeVar, zzfe zzfeVar, zzfi zzfiVar, zzfm zzfmVar, zzfq zzfqVar) {
        this.zzj = zzhbVar;
        this.zza = zzhoVar;
        this.zzb = zzeeVar;
        this.zzc = zzekVar;
        this.zzd = zzhaVar;
        this.zze = zzdeVar;
        this.zzf = zzfeVar;
        this.zzg = zzfiVar;
        this.zzh = zzfmVar;
        this.zzi = zzfqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ FetchPlaceResponse zzi(Task task) throws Exception {
        zzfh zzfhVar = (zzfh) task.getResult();
        int zza = zzgk.zza(zzfhVar.status);
        if (PlacesStatusCodes.isError(zza)) {
            throw new ApiException(new Status(zza, zzgk.zzb(zzfhVar.status, zzfhVar.errorMessage)));
        }
        zzgj zzgjVar = zzfhVar.result;
        String[] strArr = zzfhVar.htmlAttributions;
        return FetchPlaceResponse.newInstance(zzgg.zzf(zzgjVar, strArr != null ? zzkh.zzk(strArr) : null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ FindCurrentPlaceResponse zzj(Task task) throws Exception {
        zzfp zzfpVar = (zzfp) task.getResult();
        int zza = zzgk.zza(zzfpVar.status);
        if (PlacesStatusCodes.isError(zza)) {
            throw new ApiException(new Status(zza, zzgk.zzb(zzfpVar.status, zzfpVar.errorMessage)));
        }
        ArrayList arrayList = new ArrayList();
        zzgi[] zzgiVarArr = zzfpVar.predictions;
        if (zzgiVarArr != null) {
            for (zzgi zzgiVar : zzgiVarArr) {
                if (zzgiVar.zza() != null) {
                    Double zzb = zzgiVar.zzb();
                    if (zzb != null) {
                        zzgj zza2 = zzgiVar.zza();
                        String[] strArr = zzfpVar.htmlAttributions;
                        arrayList.add(PlaceLikelihood.newInstance(zzgg.zzf(zza2, strArr != null ? zzkh.zzk(strArr) : null), zzb.doubleValue()));
                    } else {
                        throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a likelihood value"));
                    }
                } else {
                    throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a Place value"));
                }
            }
        }
        return FindCurrentPlaceResponse.newInstance(arrayList);
    }

    @Override // com.google.android.libraries.places.internal.zzdz
    public final Task zza(FetchPhotoRequest fetchPhotoRequest) {
        Integer maxWidth = fetchPhotoRequest.getMaxWidth();
        Integer maxHeight = fetchPhotoRequest.getMaxHeight();
        if (maxWidth != null || maxHeight != null) {
            if (maxWidth == null || maxWidth.intValue() > 0) {
                if (maxHeight == null || maxHeight.intValue() > 0) {
                    String zza = this.zzj.zza();
                    this.zzj.zze();
                    zzfa zzfaVar = new zzfa(fetchPhotoRequest, zza, false, this.zza);
                    final long zza2 = this.zze.zza();
                    return this.zzc.zzb(zzfaVar, new zzfb()).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzgb
                        @Override // com.google.android.gms.tasks.Continuation
                        public final Object then(Task task) {
                            return FetchPhotoResponse.newInstance(((zzfd) task.getResult()).zza);
                        }
                    }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzgc
                        @Override // com.google.android.gms.tasks.Continuation
                        public final Object then(Task task) {
                            return zzgd.this.zze(zza2, task);
                        }
                    });
                }
                return Tasks.forException(new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, String.format("Max Height must not be < 1, but was: %d.", maxHeight))));
            }
            return Tasks.forException(new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, String.format("Max Width must not be < 1, but was: %d.", maxWidth))));
        }
        return Tasks.forException(new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, "Must include max width or max height in request.")));
    }

    @Override // com.google.android.libraries.places.internal.zzdz
    public final Task zzb(FetchPlaceRequest fetchPlaceRequest) {
        if (TextUtils.isEmpty(fetchPlaceRequest.getPlaceId())) {
            return Tasks.forException(new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, "Place ID must not be empty.")));
        }
        if (fetchPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        Locale zzb = this.zzj.zzb();
        String zza = this.zzj.zza();
        this.zzj.zze();
        zzfg zzfgVar = new zzfg(fetchPlaceRequest, zzb, zza, false, this.zza);
        final long zza2 = this.zze.zza();
        return this.zzb.zza(zzfgVar, zzfh.class).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzfx
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzgd.zzi(task);
            }
        }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzfy
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzgd.this.zzf(zza2, task);
            }
        });
    }

    @Override // com.google.android.libraries.places.internal.zzdz
    public final Task zzc(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        String query = findAutocompletePredictionsRequest.getQuery();
        if (query == null || TextUtils.isEmpty(query.trim())) {
            return Tasks.forResult(FindAutocompletePredictionsResponse.newInstance(zzkh.zzl()));
        }
        Locale zzb = this.zzj.zzb();
        String zza = this.zzj.zza();
        this.zzj.zze();
        zzfk zzfkVar = new zzfk(findAutocompletePredictionsRequest, zzb, zza, false, this.zza);
        final long zza2 = this.zze.zza();
        return this.zzb.zza(zzfkVar, zzfl.class).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzfv
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzfm.zza((zzfl) task.getResult());
            }
        }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzfw
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzgd.this.zzg(zza2, task);
            }
        });
    }

    @Override // com.google.android.libraries.places.internal.zzdz
    public final Task zzd(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzkh zzkhVar) {
        if (findCurrentPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        Locale zzb = this.zzj.zzb();
        String zza = this.zzj.zza();
        this.zzj.zze();
        zzfo zzfoVar = new zzfo(findCurrentPlaceRequest, location, zzkhVar, zzb, zza, false, this.zza);
        final long zza2 = this.zze.zza();
        return this.zzb.zza(zzfoVar, zzfp.class).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzfz
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzgd.zzj(task);
            }
        }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzga
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzgd.this.zzh(zza2, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPhotoResponse zze(long j, Task task) throws Exception {
        this.zzd.zzb(task, j, this.zze.zza());
        return (FetchPhotoResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zzf(long j, Task task) throws Exception {
        this.zzd.zzd(task, j, this.zze.zza());
        return (FetchPlaceResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindAutocompletePredictionsResponse zzg(long j, Task task) throws Exception {
        this.zzd.zzf(task, j, this.zze.zza());
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindCurrentPlaceResponse zzh(long j, Task task) throws Exception {
        this.zzd.zzh(task, j, this.zze.zza());
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
