package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzeq implements Continuation {
    public static final /* synthetic */ zzeq zza = new zzeq();

    private /* synthetic */ zzeq() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        ApiException apiException;
        Exception exception = task.getException();
        if (exception == null) {
            return task;
        }
        if (exception instanceof ApiException) {
            apiException = (ApiException) exception;
        } else {
            apiException = new ApiException(new Status(13, exception.toString()));
        }
        return Tasks.forException(apiException);
    }
}
