package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzkp extends AbstractSequentialList implements Serializable {
    final List zza;
    final zzdl zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkp(List list, zzdl zzdlVar) {
        list.getClass();
        this.zza = list;
        this.zzb = zzdlVar;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzko(this, this.zza.listIterator(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}
