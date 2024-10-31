package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzdw implements zzha {
    private final zzhi zza;
    private final zzhm zzb;
    private final zzhb zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdw(zzhm zzhmVar, zzhi zzhiVar, zzhb zzhbVar) {
        this.zzb = zzhmVar;
        this.zza = zzhiVar;
        this.zzc = zzhbVar;
    }

    static final int zzi(Task task) {
        ApiException apiException;
        if (task.isSuccessful()) {
            return 2;
        }
        Exception exception = task.getException();
        exception.getClass();
        if (exception instanceof ApiException) {
            apiException = (ApiException) exception;
        } else {
            apiException = new ApiException(new Status(13, exception.getMessage()));
        }
        int statusCode = apiException.getStatusCode();
        if (statusCode != 7) {
            return statusCode != 15 ? 1 : 3;
        }
        return 4;
    }

    private final zzacl zzj() {
        Locale zzb = this.zzc.zzb();
        Locale locale = Locale.getDefault();
        zzacl zza = zzacn.zza();
        zza.zzd(zzb.toString());
        if (!zzb.equals(locale)) {
            zza.zzb(locale.toString());
        }
        return zza;
    }

    private final void zzk(zzzu zzzuVar) {
        zzaaw zzb = zzhn.zzb(this.zza);
        zzb.zzl(16);
        zzb.zze(zzzuVar);
        zzb.zza(this.zzc.zza());
        zzl((zzabb) zzb.zzq());
    }

    private final void zzl(zzabb zzabbVar) {
        this.zzb.zza(zzhn.zza(zzabbVar));
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zza(FetchPhotoRequest fetchPhotoRequest) {
        zzacd zza = zzacf.zza();
        zza.zza(2);
        zzacf zzacfVar = (zzacf) zza.zzq();
        zzaaw zzb = zzhn.zzb(this.zza);
        zzb.zzl(5);
        zzb.zzg(zzacfVar);
        zzb.zza(this.zzc.zza());
        zzl((zzabb) zzb.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zzb(Task task, long j, long j2) {
        zzzp zza = zzzu.zza();
        zza.zzf(15);
        zza.zze(zzi(task));
        zza.zzd((int) (j2 - j));
        zzk((zzzu) zza.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zzc(FetchPlaceRequest fetchPlaceRequest) {
        zzabh zza = zzabi.zza();
        zza.zza(1);
        zzacg zza2 = zzach.zza();
        zza2.zza(zzgm.zzb(fetchPlaceRequest.getPlaceFields()));
        zza.zzb((zzach) zza2.zzq());
        zzabi zzabiVar = (zzabi) zza.zzq();
        zzacl zzj = zzj();
        zzj.zze(5);
        zzj.zzc(zzabiVar);
        zzacn zzacnVar = (zzacn) zzj.zzq();
        zzaaw zzb = zzhn.zzb(this.zza);
        zzb.zzl(1);
        zzb.zzh(zzacnVar);
        zzb.zza(this.zzc.zza());
        AutocompleteSessionToken sessionToken = fetchPlaceRequest.getSessionToken();
        if (sessionToken != null) {
            zzb.zzj(sessionToken.toString());
        }
        zzl((zzabb) zzb.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zzd(Task task, long j, long j2) {
        boolean isSuccessful = task.isSuccessful();
        zzzh zza = zzzi.zza();
        zza.zza(1);
        zza.zzb(isSuccessful ? 1 : 0);
        zzzi zzziVar = (zzzi) zza.zzq();
        zzzp zza2 = zzzu.zza();
        zza2.zzf(8);
        zza2.zzc(zzziVar);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzzu) zza2.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zze(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        zzzy zza = zzzz.zza();
        TypeFilter typeFilter = findAutocompletePredictionsRequest.getTypeFilter();
        if (typeFilter != null) {
            zza.zza(zzgn.zza(typeFilter));
        }
        zzzz zzzzVar = (zzzz) zza.zzq();
        zzaak zza2 = zzaal.zza();
        if (zzzzVar != null) {
            zza2.zza(zzzzVar);
        }
        zzaal zzaalVar = (zzaal) zza2.zzq();
        zzacl zzj = zzj();
        zzj.zze(6);
        zzj.zza(zzaalVar);
        zzacn zzacnVar = (zzacn) zzj.zzq();
        zzaaw zzb = zzhn.zzb(this.zza);
        zzb.zzl(1);
        zzb.zzh(zzacnVar);
        zzb.zza(this.zzc.zza());
        AutocompleteSessionToken sessionToken = findAutocompletePredictionsRequest.getSessionToken();
        if (sessionToken != null) {
            zzb.zzj(sessionToken.toString());
        }
        zzl((zzabb) zzb.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zzf(Task task, long j, long j2) {
        int size = task.isSuccessful() ? ((FindAutocompletePredictionsResponse) task.getResult()).getAutocompletePredictions().size() : 0;
        zzzc zza = zzzd.zza();
        zza.zza(size);
        zzzd zzzdVar = (zzzd) zza.zzq();
        zzzp zza2 = zzzu.zza();
        zza2.zzf(6);
        zza2.zzb(zzzdVar);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzzu) zza2.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zzg(FindCurrentPlaceRequest findCurrentPlaceRequest, Task task, long j, long j2) {
        boolean isSuccessful = task.isSuccessful();
        zzabn zza = zzabp.zza();
        zzacg zza2 = zzach.zza();
        zza2.zza(zzgm.zzb(findCurrentPlaceRequest.getPlaceFields()));
        zza.zzb((zzach) zza2.zzq());
        zza.zza((int) (j2 - j));
        zza.zzc(true == isSuccessful ? 2 : 1);
        zzabp zzabpVar = (zzabp) zza.zzq();
        zzaaw zzb = zzhn.zzb(this.zza);
        zzb.zzl(6);
        zzb.zzd(zzabpVar);
        zzb.zza(this.zzc.zza());
        zzl((zzabb) zzb.zzq());
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final void zzh(Task task, long j, long j2) {
        int size = task.isSuccessful() ? ((FindCurrentPlaceResponse) task.getResult()).getPlaceLikelihoods().size() : 0;
        zzyv zza = zzyw.zza();
        zza.zza(size);
        zzyw zzywVar = (zzyw) zza.zzq();
        zzzp zza2 = zzzu.zza();
        zza2.zzf(4);
        zza2.zza(zzywVar);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzzu) zza2.zzq());
    }
}
