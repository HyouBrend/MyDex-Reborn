package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzek {
    private final RequestQueue zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzek(RequestQueue requestQueue) {
        this.zza = requestQueue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, VolleyError volleyError) {
        ApiException zza;
        try {
            if (volleyError.networkResponse != null) {
                int i = volleyError.networkResponse.statusCode;
                if (i == 400) {
                    zza = new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, "The provided parameters are invalid (did you include a max width or height?)."));
                } else if (i == 403) {
                    zza = new ApiException(new Status(PlacesStatusCodes.REQUEST_DENIED, "The provided API key is invalid."));
                }
                taskCompletionSource.trySetException(zza);
            }
            zza = zzdy.zza(volleyError);
            taskCompletionSource.trySetException(zza);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzc(zzfb zzfbVar, TaskCompletionSource taskCompletionSource, Bitmap bitmap) {
        try {
            zzfbVar.zzb(bitmap);
            taskCompletionSource.trySetResult(zzfbVar.zza());
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final Task zzb(zzem zzemVar, final zzfb zzfbVar) {
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
        final zzej zzejVar = new zzej(this, zzc, new Response.Listener() { // from class: com.google.android.libraries.places.internal.zzeg
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                zzek.zzc(zzfb.this, taskCompletionSource2, (Bitmap) obj);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener() { // from class: com.google.android.libraries.places.internal.zzeh
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                zzek.zza(TaskCompletionSource.this, volleyError);
            }
        }, zzd);
        if (zza != null) {
            zza.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.libraries.places.internal.zzei
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final void onCanceled() {
                    ImageRequest.this.cancel();
                }
            });
        }
        this.zza.add(zzejVar);
        return taskCompletionSource2.getTask();
    }
}
