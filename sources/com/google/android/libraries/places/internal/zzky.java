package com.google.android.libraries.places.internal;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzky extends zzkl {
    private final transient zzkk zza;
    private final transient zzkh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzky(zzkk zzkkVar, zzkh zzkhVar) {
        this.zza = zzkkVar;
        this.zzb = zzkhVar;
    }

    @Override // com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.libraries.places.internal.zzkl, com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.libraries.places.internal.zzkl, com.google.android.libraries.places.internal.zzke
    public final zzkh zzd() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzkl, com.google.android.libraries.places.internal.zzke
    /* renamed from: zze */
    public final zzle iterator() {
        return this.zzb.listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    public final boolean zzf() {
        throw null;
    }
}
