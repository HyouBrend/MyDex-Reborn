package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
final class zzdd<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzlz;
    private final /* synthetic */ zzdb zzma;

    private zzdd(zzdb zzdbVar) {
        List list;
        this.zzma = zzdbVar;
        list = zzdbVar.zzlq;
        this.pos = list.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        int i = this.pos;
        if (i > 0) {
            list = this.zzma.zzlq;
            if (i <= list.size()) {
                return true;
            }
        }
        return zzde().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzde() {
        Map map;
        if (this.zzlz == null) {
            map = this.zzma.zzlt;
            this.zzlz = map.entrySet().iterator();
        }
        return this.zzlz;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        if (zzde().hasNext()) {
            return zzde().next();
        }
        list = this.zzma.zzlq;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdd(zzdb zzdbVar, zzde zzdeVar) {
        this(zzdbVar);
    }
}
