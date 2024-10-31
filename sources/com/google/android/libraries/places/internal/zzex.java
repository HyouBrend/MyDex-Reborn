package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.IsOpenRequest;
import com.google.android.libraries.places.api.net.IsOpenResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzex implements PlacesClient {
    private final zzdz zza;
    private final zzdp zzb;
    private final zzdu zzc;
    private final zzha zzd;
    private final zzde zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzex(zzdz zzdzVar, zzdp zzdpVar, zzdu zzduVar, zzha zzhaVar, zzde zzdeVar) {
        this.zza = zzdzVar;
        this.zzb = zzdpVar;
        this.zzc = zzduVar;
        this.zzd = zzhaVar;
        this.zze = zzdeVar;
    }

    private static void zzh(zzdm zzdmVar, zzdn zzdnVar) {
        zzdm.zza(zzdmVar, zzdm.zzb("Duration"));
        zzdj.zza();
        zzdj.zza();
        zzdm.zza(zzdmVar, zzdm.zzb("Battery"));
        zzdj.zza();
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FetchPhotoResponse> fetchPhoto(final FetchPhotoRequest fetchPhotoRequest) {
        try {
            zzjp.zzc(fetchPhotoRequest, "Request must not be null.");
            zzdj.zza();
            final zzdn zza = zzdn.zza();
            return this.zza.zza(fetchPhotoRequest).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzep
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzex.this.zzc(fetchPhotoRequest, zza, task);
                }
            }).continueWithTask(zzeq.zza);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FetchPlaceResponse> fetchPlace(final FetchPlaceRequest fetchPlaceRequest) {
        try {
            zzjp.zzc(fetchPlaceRequest, "Request must not be null.");
            zzdj.zza();
            final zzdn zza = zzdn.zza();
            return this.zza.zzb(fetchPlaceRequest).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzev
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzex.this.zzd(fetchPlaceRequest, zza, task);
                }
            }).continueWithTask(zzeq.zza);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(final FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        try {
            zzjp.zzc(findAutocompletePredictionsRequest, "Request must not be null.");
            zzdj.zza();
            final zzdn zza = zzdn.zza();
            return this.zza.zzc(findAutocompletePredictionsRequest).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzew
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzex.this.zzf(findAutocompletePredictionsRequest, zza, task);
                }
            }).continueWithTask(zzeq.zza);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FindCurrentPlaceResponse> findCurrentPlace(FindCurrentPlaceRequest findCurrentPlaceRequest) {
        return zza(findCurrentPlaceRequest, null);
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<IsOpenResponse> isOpen(IsOpenRequest isOpenRequest) {
        List arrayList;
        try {
            zzjp.zzc(isOpenRequest, "Request must not be null.");
            final Place place = isOpenRequest.getPlace();
            String placeId = isOpenRequest.getPlaceId();
            final long utcTimeMillis = isOpenRequest.getUtcTimeMillis();
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            if (place != null) {
                arrayList = new ArrayList();
                Place.BusinessStatus businessStatus = place.getBusinessStatus();
                if (businessStatus == null || businessStatus == Place.BusinessStatus.OPERATIONAL) {
                    if (businessStatus == null) {
                        arrayList.add(Place.Field.BUSINESS_STATUS);
                    }
                    if (place.getCurrentOpeningHours() == null) {
                        arrayList.add(Place.Field.CURRENT_OPENING_HOURS);
                    }
                    if (place.getOpeningHours() == null) {
                        arrayList.add(Place.Field.OPENING_HOURS);
                    }
                    if (place.getUtcOffsetMinutes() == null) {
                        arrayList.add(Place.Field.UTC_OFFSET);
                    }
                }
            } else {
                int i = com.google.android.libraries.places.api.model.zzbq.zza;
                arrayList = Arrays.asList(Place.Field.BUSINESS_STATUS, Place.Field.CURRENT_OPENING_HOURS, Place.Field.OPENING_HOURS, Place.Field.UTC_OFFSET);
            }
            if (arrayList.isEmpty()) {
                place.getClass();
                taskCompletionSource.setResult(IsOpenResponse.newInstance(com.google.android.libraries.places.api.model.zzbq.zza(place, utcTimeMillis)));
                return taskCompletionSource.getTask();
            }
            if (place != null) {
                placeId = place.getId();
            }
            placeId.getClass();
            FetchPlaceRequest.Builder builder = FetchPlaceRequest.builder(placeId, arrayList);
            builder.setCancellationToken(isOpenRequest.getCancellationToken());
            final FetchPlaceRequest build = builder.build();
            zzdj.zza();
            final zzdn zza = zzdn.zza();
            return this.zza.zzb(build).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzer
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzex.this.zze(build, zza, task);
                }
            }).onSuccessTask(new SuccessContinuation() { // from class: com.google.android.libraries.places.internal.zzes
                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    Place place2 = Place.this;
                    long j = utcTimeMillis;
                    TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                    Place place3 = ((FetchPlaceResponse) obj).getPlace();
                    Place.BusinessStatus businessStatus2 = place3.getBusinessStatus();
                    OpeningHours currentOpeningHours = place3.getCurrentOpeningHours();
                    OpeningHours openingHours = place3.getOpeningHours();
                    Integer utcOffsetMinutes = place3.getUtcOffsetMinutes();
                    if (place2 != null) {
                        if (utcOffsetMinutes == null) {
                            utcOffsetMinutes = place2.getUtcOffsetMinutes();
                        }
                        if (businessStatus2 == null) {
                            businessStatus2 = place2.getBusinessStatus();
                        }
                        if (currentOpeningHours == null) {
                            currentOpeningHours = place2.getCurrentOpeningHours();
                        }
                        if (openingHours == null) {
                            openingHours = place2.getOpeningHours();
                        }
                    }
                    Place.Builder builder2 = Place.builder();
                    builder2.setBusinessStatus(businessStatus2);
                    builder2.setCurrentOpeningHours(currentOpeningHours);
                    builder2.setOpeningHours(openingHours);
                    builder2.setUtcOffsetMinutes(utcOffsetMinutes);
                    taskCompletionSource2.setResult(IsOpenResponse.newInstance(com.google.android.libraries.places.api.model.zzbq.zza(builder2.build(), j)));
                    return taskCompletionSource2.getTask();
                }
            }).continueWithTask(zzeq.zza);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final Task zza(final FindCurrentPlaceRequest findCurrentPlaceRequest, String str) {
        try {
            zzjp.zzc(findCurrentPlaceRequest, "Request must not be null.");
            final long zza = this.zze.zza();
            zzdj.zza();
            final zzdn zza2 = zzdn.zza();
            final String str2 = null;
            return this.zzb.zza(findCurrentPlaceRequest.getCancellationToken()).onSuccessTask(new SuccessContinuation(findCurrentPlaceRequest, str2) { // from class: com.google.android.libraries.places.internal.zzet
                public final /* synthetic */ FindCurrentPlaceRequest zzb;

                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    return zzex.this.zzb(this.zzb, null, (Location) obj);
                }
            }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzeu
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzex.this.zzg(findCurrentPlaceRequest, zza, zza2, task);
                }
            }).continueWithTask(zzeq.zza);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zzb(FindCurrentPlaceRequest findCurrentPlaceRequest, String str, Location location) throws Exception {
        zzjp.zzc(location, "Location must not be null.");
        return this.zza.zzd(findCurrentPlaceRequest, location, this.zzc.zza(null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPhotoResponse zzc(FetchPhotoRequest fetchPhotoRequest, zzdn zzdnVar, Task task) throws Exception {
        this.zzd.zza(fetchPhotoRequest);
        zzh(zzdm.zzb("FetchPhoto"), zzdnVar);
        return (FetchPhotoResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zzd(FetchPlaceRequest fetchPlaceRequest, zzdn zzdnVar, Task task) throws Exception {
        this.zzd.zzc(fetchPlaceRequest);
        zzh(zzdm.zzb("FetchPlace"), zzdnVar);
        return (FetchPlaceResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zze(FetchPlaceRequest fetchPlaceRequest, zzdn zzdnVar, Task task) throws Exception {
        this.zzd.zzc(fetchPlaceRequest);
        zzh(zzdm.zzb("IsOpenFetchPlace"), zzdnVar);
        return (FetchPlaceResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindAutocompletePredictionsResponse zzf(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, zzdn zzdnVar, Task task) throws Exception {
        this.zzd.zze(findAutocompletePredictionsRequest);
        zzh(zzdm.zzb("FindAutocompletePredictions"), zzdnVar);
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindCurrentPlaceResponse zzg(FindCurrentPlaceRequest findCurrentPlaceRequest, long j, zzdn zzdnVar, Task task) throws Exception {
        this.zzd.zzg(findCurrentPlaceRequest, task, j, this.zze.zza());
        zzh(zzdm.zzb("FindCurrentPlace"), zzdnVar);
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
