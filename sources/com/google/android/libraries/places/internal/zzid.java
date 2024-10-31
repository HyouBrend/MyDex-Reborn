package com.google.android.libraries.places.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzid {
    public static Status zza(Intent intent) {
        try {
            zzjp.zzc(intent, "Intent must not be null.");
            Status status = (Status) intent.getParcelableExtra("places/status");
            zzjp.zzc(status, "Intent expected to contain a Status, but doesn't.");
            return status;
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public static Place zzb(Intent intent) {
        try {
            zzjp.zzc(intent, "Intent must not be null.");
            Place place = (Place) intent.getParcelableExtra("places/selected_place");
            zzjp.zzc(place, "Intent expected to contain a Place, but doesn't.");
            return place;
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public static String zzc(Context context, int i) {
        Object obj = Build.VERSION.SDK_INT < 24 ? context.getResources().getConfiguration().locale : context.getResources().getConfiguration().getLocales().get(0);
        Locale zzb = Places.isInitialized() ? Places.zzc().zzb() : obj;
        if (zzb.equals(obj)) {
            return context.getResources().getString(i);
        }
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(zzb);
        return context.createConfigurationContext(configuration).getResources().getString(i);
    }
}
