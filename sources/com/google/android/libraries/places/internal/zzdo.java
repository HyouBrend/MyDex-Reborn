package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzdo implements Continuation {
    final /* synthetic */ zzdp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdo(zzdp zzdpVar) {
        this.zza = zzdpVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task task) throws Exception {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (task.isCanceled()) {
            taskCompletionSource.trySetException(new ApiException(new Status(16, "Location request was cancelled. Please try again.")));
        } else if (task.getException() == null && task.getResult() == null) {
            taskCompletionSource.trySetException(new ApiException(new Status(8, "Location unavailable.")));
        }
        return taskCompletionSource.getTask().getException() != null ? taskCompletionSource.getTask() : task;
    }
}
