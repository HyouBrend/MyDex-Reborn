package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationToken;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzem {
    private final zzhc zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzem(zzhc zzhcVar) {
        this.zza = zzhcVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CancellationToken zza() {
        return this.zza.getCancellationToken();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzhc zzb() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Map zzd();
}
