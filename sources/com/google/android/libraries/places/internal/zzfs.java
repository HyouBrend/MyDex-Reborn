package com.google.android.libraries.places.internal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzfs {
    private final Gson zza = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public final Object zza(String str, Class cls) throws zzeo {
        try {
            return this.zza.fromJson(str, cls);
        } catch (JsonSyntaxException unused) {
            throw new zzeo("Could not convert JSON string to " + cls.getName() + " due to syntax errors.");
        }
    }
}
