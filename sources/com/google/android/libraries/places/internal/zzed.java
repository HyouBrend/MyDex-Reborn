package com.google.android.libraries.places.internal;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzed extends JsonObjectRequest {
    final /* synthetic */ Map zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzed(zzee zzeeVar, int i, String str, JSONObject jSONObject, Response.Listener listener, Response.ErrorListener errorListener, Map map) {
        super(0, str, null, listener, errorListener);
        this.zza = map;
    }

    @Override // com.android.volley.Request
    public final Map getHeaders() {
        return this.zza;
    }
}
