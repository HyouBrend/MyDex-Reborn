package com.google.android.libraries.places.internal;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzmp extends zzmu {
    private static final zzmp zza = new zzmp(zzmu.zze());
    private final AtomicReference zzb;

    zzmp(zzmu zzmuVar) {
        this.zzb = new AtomicReference(zzmuVar);
    }

    public static final zzmp zzb() {
        return zza;
    }

    @Override // com.google.android.libraries.places.internal.zzmu
    public final zzlr zza() {
        return ((zzmu) this.zzb.get()).zza();
    }

    @Override // com.google.android.libraries.places.internal.zzmu
    public final zznh zzc() {
        return ((zzmu) this.zzb.get()).zzc();
    }

    @Override // com.google.android.libraries.places.internal.zzmu
    public final boolean zzd(String str, Level level, boolean z) {
        ((zzmu) this.zzb.get()).zzd(str, level, z);
        return false;
    }
}
