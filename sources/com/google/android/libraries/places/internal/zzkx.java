package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzkx extends zzkl {
    private final transient zzkk zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkx(zzkk zzkkVar, Object[] objArr, int i, int i2) {
        this.zza = zzkkVar;
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zzkl, com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    public final int zza(Object[] objArr, int i) {
        return zzd().zza(objArr, 0);
    }

    @Override // com.google.android.libraries.places.internal.zzkl, com.google.android.libraries.places.internal.zzke
    /* renamed from: zze */
    public final zzle iterator() {
        return zzd().listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    public final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzkl
    final zzkh zzh() {
        return new zzkw(this);
    }
}
