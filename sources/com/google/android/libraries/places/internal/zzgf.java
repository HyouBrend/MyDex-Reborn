package com.google.android.libraries.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzgf {
    private final String zza;
    private final String zzb;
    private Locale zzc = null;
    private Map zzd = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgf(String str, String str2) {
        this.zza = str;
        zzjp.zze(!TextUtils.isEmpty(str2), "API key cannot be empty.");
        this.zzb = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgf zza(Locale locale) {
        this.zzc = locale;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgf zzb(Map map) {
        this.zzd = new HashMap(map);
        return this;
    }

    public final String zzc() {
        Uri.Builder buildUpon = Uri.parse("https://maps.googleapis.com/").buildUpon();
        buildUpon.appendEncodedPath("maps/api/place/");
        buildUpon.appendEncodedPath(this.zza);
        buildUpon.appendQueryParameter("key", this.zzb);
        Locale locale = this.zzc;
        if (locale != null) {
            String languageTag = locale.toLanguageTag();
            if (!TextUtils.isEmpty(languageTag)) {
                buildUpon.appendQueryParameter("language", languageTag);
            }
        }
        Map map = this.zzd;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return buildUpon.build().toString();
    }
}
