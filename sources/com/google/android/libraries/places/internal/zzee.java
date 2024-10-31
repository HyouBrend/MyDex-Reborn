package com.google.android.libraries.places.internal;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzee {
    private final RequestQueue zza;
    private final zzfs zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzee(RequestQueue requestQueue, zzfs zzfsVar) {
        this.zza = requestQueue;
        this.zzb = zzfsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzc(TaskCompletionSource taskCompletionSource, VolleyError volleyError) {
        try {
            taskCompletionSource.trySetException(zzdy.zza(volleyError));
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final Task zza(zzem zzemVar, final Class cls) {
        TaskCompletionSource taskCompletionSource;
        String zzc = zzemVar.zzc();
        Map zzd = zzemVar.zzd();
        CancellationToken zza = zzemVar.zza();
        if (zza != null) {
            taskCompletionSource = new TaskCompletionSource(zza);
        } else {
            taskCompletionSource = new TaskCompletionSource();
        }
        final TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        final zzed zzedVar = new zzed(this, 0, zzc, null, new Response.Listener() { // from class: com.google.android.libraries.places.internal.zzea
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                zzee.this.zzb(cls, taskCompletionSource2, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.google.android.libraries.places.internal.zzeb
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                zzee.zzc(TaskCompletionSource.this, volleyError);
            }
        }, zzd);
        if (zza != null) {
            zza.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.libraries.places.internal.zzec
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final void onCanceled() {
                    JsonObjectRequest.this.cancel();
                }
            });
        }
        this.zza.add(zzedVar);
        return taskCompletionSource2.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Class cls, TaskCompletionSource taskCompletionSource, JSONObject jSONObject) {
        try {
            try {
                taskCompletionSource.trySetResult((zzen) this.zzb.zza(jSONObject.toString(), cls));
            } catch (zzeo e) {
                taskCompletionSource.trySetException(new ApiException(new Status(8, e.getMessage())));
            }
        } catch (Error | RuntimeException e2) {
            zzhk.zzb(e2);
            throw e2;
        }
    }
}
