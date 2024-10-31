package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzdp {
    private static final long zza = TimeUnit.SECONDS.toMillis(30);
    private final FusedLocationProviderClient zzb;
    private final zzgs zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdp(FusedLocationProviderClient fusedLocationProviderClient, zzgs zzgsVar) {
        this.zzb = fusedLocationProviderClient;
        this.zzc = zzgsVar;
    }

    public final Task zza(CancellationToken cancellationToken) {
        Task<Location> task;
        final TaskCompletionSource taskCompletionSource;
        CurrentLocationRequest.Builder priority = new CurrentLocationRequest.Builder().setPriority(100);
        long j = zza;
        CurrentLocationRequest build = priority.setDurationMillis(j).build();
        if (FusedLocationProviderClient.class.isInterface()) {
            task = this.zzb.getCurrentLocation(build, cancellationToken);
        } else {
            try {
                task = (Task) FusedLocationProviderClient.class.getMethod("getCurrentLocation", CurrentLocationRequest.class, CancellationToken.class).invoke(this.zzb, build, cancellationToken);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(e);
            }
        }
        final zzgs zzgsVar = this.zzc;
        if (cancellationToken == null) {
            taskCompletionSource = new TaskCompletionSource();
        } else {
            taskCompletionSource = new TaskCompletionSource(cancellationToken);
        }
        zzgsVar.zza(taskCompletionSource, j, "Location timeout.");
        task.continueWithTask(new Continuation() { // from class: com.google.android.libraries.places.internal.zzgq
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                Exception exception = task2.getException();
                if (task2.isSuccessful()) {
                    taskCompletionSource2.setResult(task2.getResult());
                } else if (!task2.isCanceled() && exception != null) {
                    taskCompletionSource2.setException(exception);
                }
                return taskCompletionSource2.getTask();
            }
        });
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.libraries.places.internal.zzgr
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                zzgs.this.zzb(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask().continueWithTask(new zzdo(this));
    }
}
