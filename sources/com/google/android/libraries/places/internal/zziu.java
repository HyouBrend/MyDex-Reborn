package com.google.android.libraries.places.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zziu extends ViewModel {
    private final zzih zza;
    private final zzix zzb;
    private final zziy zzc;
    private Runnable zze;
    private final Handler zzd = new Handler(Looper.getMainLooper());
    private final MutableLiveData zzf = new MutableLiveData();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zziu(zzih zzihVar, zzix zzixVar, zziy zziyVar, zzit zzitVar) {
        this.zza = zzihVar;
        this.zzb = zzixVar;
        this.zzc = zziyVar;
    }

    private static Status zzn(Exception exc) {
        if (exc instanceof ApiException) {
            return ((ApiException) exc).getStatus();
        }
        return new Status(13, exc.getMessage());
    }

    private final void zzo(zzic zzicVar) {
        if (zzicVar.equals(this.zzf.getValue())) {
            return;
        }
        this.zzf.setValue(zzicVar);
    }

    private static boolean zzp(Status status) {
        return status.isCanceled() || status.getStatusCode() == 9012 || status.getStatusCode() == 9011;
    }

    public final LiveData zza() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(String str, Task task) {
        if (task.isCanceled()) {
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            this.zzb.zzp();
            List<AutocompletePrediction> autocompletePredictions = ((FindAutocompletePredictionsResponse) task.getResult()).getAutocompletePredictions();
            if (autocompletePredictions.isEmpty()) {
                zzo(zzic.zzh(str));
                return;
            } else {
                zzo(zzic.zzj(autocompletePredictions));
                return;
            }
        }
        this.zzb.zzr();
        Status zzn = zzn(exception);
        if (zzp(zzn)) {
            zzo(zzic.zzq(zzn));
        } else {
            zzo(zzic.zzi(str, zzn));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(AutocompletePrediction autocompletePrediction, Task task) {
        if (task.isCanceled()) {
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            this.zzb.zzq();
            zzo(zzic.zzn(((FetchPlaceResponse) task.getResult()).getPlace()));
            return;
        }
        this.zzb.zzs();
        Status zzn = zzn(exception);
        if (zzp(zzn)) {
            zzo(zzic.zzq(zzn));
        } else {
            zzo(zzic.zzm(autocompletePrediction, zzn));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(final String str) {
        this.zza.zzb(str).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.libraries.places.internal.zziq
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zziu.this.zzb(str, task);
            }
        });
    }

    public final void zze(Bundle bundle) {
        if (bundle == null) {
            this.zzf.setValue(zzic.zzo());
        }
    }

    public final void zzf(final AutocompletePrediction autocompletePrediction, int i) {
        this.zzb.zzu(i);
        Task zza = this.zza.zza(autocompletePrediction);
        if (!zza.isComplete()) {
            zzo(zzic.zzg());
        }
        zza.addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.libraries.places.internal.zzip
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zziu.this.zzc(autocompletePrediction, task);
            }
        });
    }

    public final void zzg() {
        this.zzb.zzv();
    }

    public final void zzh() {
        this.zzb.zzl();
    }

    public final void zzi() {
        this.zzb.zzm();
    }

    public final void zzj() {
        this.zzb.zzn();
        zzo(zzic.zzl());
    }

    public final void zzk() {
        this.zzb.zzw();
        zzm("");
    }

    public final void zzl(String str) {
        this.zza.zzc();
        zzm(str);
        zzo(zzic.zzp());
    }

    public final void zzm(final String str) {
        this.zzb.zzt(str);
        this.zzd.removeCallbacks(this.zze);
        if (str.isEmpty()) {
            this.zza.zzc();
            zzo(zzic.zzk());
        } else {
            Runnable runnable = new Runnable() { // from class: com.google.android.libraries.places.internal.zzir
                @Override // java.lang.Runnable
                public final void run() {
                    zziu.this.zzd(str);
                }
            };
            this.zze = runnable;
            this.zzd.postDelayed(runnable, 100L);
            zzo(zzic.zzg());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        try {
            this.zza.zzc();
            this.zzd.removeCallbacks(this.zze);
            this.zzb.zzo();
            this.zzc.zza(this.zzb);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }
}
