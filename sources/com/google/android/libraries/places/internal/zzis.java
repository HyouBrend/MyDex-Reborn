package com.google.android.libraries.places.internal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzis implements ViewModelProvider.Factory {
    private final zzih zza;
    private final zzix zzb;
    private final zziy zzc;

    public zzis(zzih zzihVar, zzix zzixVar, zziy zziyVar) {
        this.zza = zzihVar;
        this.zzb = zzixVar;
        this.zzc = zziyVar;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls) {
        zzjp.zze(cls == zziu.class, "This factory can only be used to instantiate its enclosing class.");
        return new zziu(this.zza, this.zzb, this.zzc, null);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls, CreationExtras creationExtras) {
        return create(cls);
    }
}
