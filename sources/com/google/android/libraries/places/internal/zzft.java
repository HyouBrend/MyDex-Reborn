package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzft extends zzem {
    private final Locale zza;
    private final String zzb;
    private final zzho zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzft(zzhc zzhcVar, Locale locale, String str, boolean z, zzho zzhoVar) {
        super(zzhcVar);
        this.zza = locale;
        this.zzb = str;
        this.zzc = zzhoVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzg(Map map, String str, Object obj, Object obj2) {
        String obj3 = obj != null ? obj.toString() : null;
        if (TextUtils.isEmpty(obj3)) {
            return;
        }
        map.put(str, obj3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzem
    public final String zzc() {
        zzgf zzgfVar = new zzgf(zze(), this.zzb);
        zzgfVar.zza(this.zza);
        zzgfVar.zzb(zzf());
        return zzgfVar.zzc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzem
    public final Map zzd() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.zzc.zza());
        hashMap.put("X-Places-Android-Sdk", "3.2.0");
        return hashMap;
    }

    protected abstract String zze();

    protected abstract Map zzf();
}
