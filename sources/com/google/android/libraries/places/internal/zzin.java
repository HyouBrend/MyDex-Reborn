package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzin {
    private Task zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzin(zzim zzimVar) {
    }

    public abstract CancellationTokenSource zza();

    public final Task zzc() {
        return this.zza;
    }

    public final void zzd(Task task) {
        this.zza = task;
    }
}
