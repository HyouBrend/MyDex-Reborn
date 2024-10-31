package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
final class zzdj<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzlz;
    private final /* synthetic */ zzdb zzma;
    private boolean zzmd;

    private zzdj(zzdb zzdbVar) {
        this.zzma = zzdbVar;
        this.pos = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int i = this.pos + 1;
        list = this.zzma.zzlq;
        if (i >= list.size()) {
            map = this.zzma.zzlr;
            if (map.isEmpty() || !zzde().hasNext()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.zzmd) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzmd = false;
        this.zzma.zzcx();
        int i = this.pos;
        list = this.zzma.zzlq;
        if (i < list.size()) {
            zzdb zzdbVar = this.zzma;
            int i2 = this.pos;
            this.pos = i2 - 1;
            zzdbVar.zzan(i2);
            return;
        }
        zzde().remove();
    }

    private final Iterator<Map.Entry<K, V>> zzde() {
        Map map;
        if (this.zzlz == null) {
            map = this.zzma.zzlr;
            this.zzlz = map.entrySet().iterator();
        }
        return this.zzlz;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        List list2;
        this.zzmd = true;
        int i = this.pos + 1;
        this.pos = i;
        list = this.zzma.zzlq;
        if (i >= list.size()) {
            return zzde().next();
        }
        list2 = this.zzma.zzlq;
        return (Map.Entry) list2.get(this.pos);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdj(zzdb zzdbVar, zzde zzdeVar) {
        this(zzdbVar);
    }
}
