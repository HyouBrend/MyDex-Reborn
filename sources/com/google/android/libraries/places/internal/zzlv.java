package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzlv {
    private static final zzlz zza = new zzls();
    private static final zzly zzb = new zzlt();
    private final zzlz zze;
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();
    private zzly zzf = null;

    public final zzlv zza(zzly zzlyVar) {
        this.zzf = zzlyVar;
        return this;
    }

    public final zzma zzd() {
        return new zzlx(this, null);
    }

    public final void zzg(zzlm zzlmVar) {
        zznj.zza(zzlmVar, "key");
        if (zzlmVar.zzb()) {
            zzly zzlyVar = zzb;
            zznj.zza(zzlmVar, "key");
            if (zzlmVar.zzb()) {
                this.zzc.remove(zzlmVar);
                this.zzd.put(zzlmVar, zzlyVar);
                return;
            }
            throw new IllegalArgumentException("key must be repeating");
        }
        zzlz zzlzVar = zza;
        zznj.zza(zzlmVar, "key");
        this.zzd.remove(zzlmVar);
        this.zzc.put(zzlmVar, zzlzVar);
    }
}
