package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzdg extends zzdm {
    private final /* synthetic */ zzdb zzma;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzdg(zzdb zzdbVar) {
        super(zzdbVar, null);
        this.zzma = zzdbVar;
    }

    @Override // com.google.android.gms.internal.places.zzdm, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzdd(this.zzma, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdg(zzdb zzdbVar, zzde zzdeVar) {
        this(zzdbVar);
    }
}
