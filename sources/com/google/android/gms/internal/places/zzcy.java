package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class zzcy<E> extends zzq<E> {
    private static final zzcy<Object> zzlo;
    private final List<E> zzka;

    public static <E> zzcy<E> zzct() {
        return (zzcy<E>) zzlo;
    }

    zzcy() {
        this(new ArrayList(10));
    }

    private zzcy(List<E> list) {
        this.zzka = list;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        zzac();
        this.zzka.add(i, e);
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        return this.zzka.get(i);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        zzac();
        E remove = this.zzka.remove(i);
        this.modCount++;
        return remove;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zzac();
        E e2 = this.zzka.set(i, e);
        this.modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzka.size();
    }

    @Override // com.google.android.gms.internal.places.zzbh
    public final /* synthetic */ zzbh zzh(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzka);
        return new zzcy(arrayList);
    }

    static {
        zzcy<Object> zzcyVar = new zzcy<>(new ArrayList(0));
        zzlo = zzcyVar;
        zzcyVar.zzab();
    }
}
