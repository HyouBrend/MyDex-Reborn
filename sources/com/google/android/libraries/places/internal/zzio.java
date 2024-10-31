package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzio implements zzih {
    public static final /* synthetic */ int zza = 0;
    private static final zzkh zzb = zzkh.zzn(Place.Field.ID, Place.Field.TYPES);
    private final PlacesClient zzc;
    private final zzia zzd;
    private final AutocompleteSessionToken zze;
    private zzik zzf;
    private zzil zzg;

    public zzio(PlacesClient placesClient, zzia zziaVar, AutocompleteSessionToken autocompleteSessionToken) {
        this.zzc = placesClient;
        this.zzd = zziaVar;
        this.zze = autocompleteSessionToken;
    }

    @Override // com.google.android.libraries.places.internal.zzih
    public final Task zza(AutocompletePrediction autocompletePrediction) {
        if (zzb.containsAll(this.zzd.zzj())) {
            Place.Builder builder = Place.builder();
            builder.setId(autocompletePrediction.getPlaceId());
            builder.setTypes(autocompletePrediction.getPlaceTypes().isEmpty() ? null : autocompletePrediction.getPlaceTypes());
            return Tasks.forResult(FetchPlaceResponse.newInstance(builder.build()));
        }
        zzil zzilVar = this.zzg;
        if (zzilVar != null) {
            if (zzilVar.zzb().equals(autocompletePrediction.getPlaceId())) {
                Task zzc = zzilVar.zzc();
                zzc.getClass();
                return zzc;
            }
            zzilVar.zza().cancel();
        }
        final zzig zzigVar = new zzig(new CancellationTokenSource(), autocompletePrediction.getPlaceId());
        this.zzg = zzigVar;
        PlacesClient placesClient = this.zzc;
        FetchPlaceRequest.Builder builder2 = FetchPlaceRequest.builder(autocompletePrediction.getPlaceId(), this.zzd.zzj());
        builder2.setSessionToken(this.zze);
        builder2.setCancellationToken(zzigVar.zza().getToken());
        Task continueWithTask = placesClient.fetchPlace(builder2.build()).continueWithTask(new Continuation() { // from class: com.google.android.libraries.places.internal.zzii
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                zzil zzilVar2 = zzil.this;
                int i = zzio.zza;
                return zzilVar2.zza().getToken().isCancellationRequested() ? Tasks.forCanceled() : task;
            }
        });
        zzigVar.zzd(continueWithTask);
        return continueWithTask;
    }

    @Override // com.google.android.libraries.places.internal.zzih
    public final Task zzb(String str) {
        zzjp.zzd(!TextUtils.isEmpty(str));
        zzik zzikVar = this.zzf;
        if (zzikVar != null) {
            if (zzikVar.zzb().equals(str)) {
                Task zzc = zzikVar.zzc();
                zzc.getClass();
                return zzc;
            }
            zzikVar.zza().cancel();
        }
        final zzif zzifVar = new zzif(new CancellationTokenSource(), str);
        this.zzf = zzifVar;
        PlacesClient placesClient = this.zzc;
        FindAutocompletePredictionsRequest.Builder builder = FindAutocompletePredictionsRequest.builder();
        builder.setQuery(str);
        builder.setLocationBias(this.zzd.zzc());
        builder.setLocationRestriction(this.zzd.zzd());
        builder.setCountries(this.zzd.zzi());
        builder.setTypeFilter(this.zzd.zze());
        builder.setTypesFilter(this.zzd.zzk());
        builder.setSessionToken(this.zze);
        builder.setCancellationToken(zzifVar.zza().getToken());
        Task continueWithTask = placesClient.findAutocompletePredictions(builder.build()).continueWithTask(new Continuation() { // from class: com.google.android.libraries.places.internal.zzij
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                zzik zzikVar2 = zzik.this;
                int i = zzio.zza;
                return zzikVar2.zza().getToken().isCancellationRequested() ? Tasks.forCanceled() : task;
            }
        });
        zzifVar.zzd(continueWithTask);
        return continueWithTask;
    }

    @Override // com.google.android.libraries.places.internal.zzih
    public final void zzc() {
        zzik zzikVar = this.zzf;
        if (zzikVar != null) {
            zzikVar.zza().cancel();
        }
        zzil zzilVar = this.zzg;
        if (zzilVar != null) {
            zzilVar.zza().cancel();
        }
        this.zzf = null;
        this.zzg = null;
    }
}
