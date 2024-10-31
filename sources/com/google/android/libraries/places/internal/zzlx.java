package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzlx extends zzma {
    private final Map zza;
    private final Map zzb;
    private final zzlz zzc;
    private final zzly zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzlx(zzlv zzlvVar, zzlw zzlwVar) {
        HashMap hashMap = new HashMap();
        this.zza = hashMap;
        HashMap hashMap2 = new HashMap();
        this.zzb = hashMap2;
        hashMap.putAll(zzlv.zzf(zzlvVar));
        hashMap2.putAll(zzlv.zze(zzlvVar));
        this.zzc = zzlv.zzc(zzlvVar);
        this.zzd = zzlv.zzb(zzlvVar);
    }
}
