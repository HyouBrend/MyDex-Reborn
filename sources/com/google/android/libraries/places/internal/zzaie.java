package com.google.android.libraries.places.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaie {
    private static final zzaie zza = new zzaie();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzaii zzb = new zzaho();

    private zzaie() {
    }

    public static zzaie zza() {
        return zza;
    }

    public final zzaih zzb(Class cls) {
        zzagx.zzc(cls, "messageType");
        zzaih zzaihVar = (zzaih) this.zzc.get(cls);
        if (zzaihVar == null) {
            zzaihVar = this.zzb.zza(cls);
            zzagx.zzc(cls, "messageType");
            zzagx.zzc(zzaihVar, "schema");
            zzaih zzaihVar2 = (zzaih) this.zzc.putIfAbsent(cls, zzaihVar);
            if (zzaihVar2 != null) {
                return zzaihVar2;
            }
        }
        return zzaihVar;
    }
}
