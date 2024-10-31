package com.google.android.gms.internal.places;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public final class zzdt extends AbstractList<String> implements zzbr, RandomAccess {
    private final zzbr zzmj;

    public zzdt(zzbr zzbrVar) {
        this.zzmj = zzbrVar;
    }

    @Override // com.google.android.gms.internal.places.zzbr
    public final zzbr zzbz() {
        return this;
    }

    @Override // com.google.android.gms.internal.places.zzbr
    public final Object zzae(int i) {
        return this.zzmj.zzae(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzmj.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzdw(this, i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzdv(this);
    }

    @Override // com.google.android.gms.internal.places.zzbr
    public final List<?> zzby() {
        return this.zzmj.zzby();
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zzmj.get(i);
    }
}
