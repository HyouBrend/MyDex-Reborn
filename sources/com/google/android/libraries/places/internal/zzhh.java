package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzhh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzhh zzb(int i);

    abstract zzhi zzc();

    public abstract zzhh zzd(int i);

    public final zzhi zze() {
        zzhi zzc = zzc();
        zzjp.zzk(!zzc.zzb().isEmpty(), "Package name must not be empty.");
        return zzc;
    }
}
