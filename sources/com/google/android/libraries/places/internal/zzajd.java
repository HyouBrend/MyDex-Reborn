package com.google.android.libraries.places.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzajd extends AbstractList implements RandomAccess, zzahe {
    private final zzahe zza;

    public zzajd(zzahe zzaheVar) {
        this.zza = zzaheVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzahd) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzajc(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzajb(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.libraries.places.internal.zzahe
    public final zzahe zzd() {
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzahe
    public final Object zze(int i) {
        return this.zza.zze(i);
    }

    @Override // com.google.android.libraries.places.internal.zzahe
    public final List zzh() {
        return this.zza.zzh();
    }
}
