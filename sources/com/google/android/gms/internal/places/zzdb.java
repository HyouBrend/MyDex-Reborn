package com.google.android.gms.internal.places;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class zzdb<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzfk;
    private final int zzlp;
    private List<zzdk> zzlq;
    private Map<K, V> zzlr;
    private volatile zzdm zzls;
    private Map<K, V> zzlt;
    private volatile zzdg zzlu;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <FieldDescriptorType extends zzax<FieldDescriptorType>> zzdb<FieldDescriptorType, Object> zzal(int i) {
        return new zzde(i);
    }

    private zzdb(int i) {
        this.zzlp = i;
        this.zzlq = Collections.emptyList();
        this.zzlr = Collections.emptyMap();
        this.zzlt = Collections.emptyMap();
    }

    public void zzab() {
        Map<K, V> unmodifiableMap;
        Map<K, V> unmodifiableMap2;
        if (this.zzfk) {
            return;
        }
        if (this.zzlr.isEmpty()) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(this.zzlr);
        }
        this.zzlr = unmodifiableMap;
        if (this.zzlt.isEmpty()) {
            unmodifiableMap2 = Collections.emptyMap();
        } else {
            unmodifiableMap2 = Collections.unmodifiableMap(this.zzlt);
        }
        this.zzlt = unmodifiableMap2;
        this.zzfk = true;
    }

    public final boolean isImmutable() {
        return this.zzfk;
    }

    public final int zzcu() {
        return this.zzlq.size();
    }

    public final Map.Entry<K, V> zzam(int i) {
        return this.zzlq.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzcv() {
        if (this.zzlr.isEmpty()) {
            return zzdf.zzdf();
        }
        return this.zzlr.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzlq.size() + this.zzlr.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzb((zzdb<K, V>) comparable) >= 0 || this.zzlr.containsKey(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzb = zzb((zzdb<K, V>) comparable);
        if (zzb >= 0) {
            return (V) this.zzlq.get(zzb).getValue();
        }
        return this.zzlr.get(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V zzb(K k, V v) {
        zzcx();
        int zzb = zzb((zzdb<K, V>) k);
        if (zzb >= 0) {
            return (V) this.zzlq.get(zzb).setValue(v);
        }
        zzcx();
        if (this.zzlq.isEmpty() && !(this.zzlq instanceof ArrayList)) {
            this.zzlq = new ArrayList(this.zzlp);
        }
        int i = -(zzb + 1);
        if (i >= this.zzlp) {
            return zzcy().put(k, v);
        }
        int size = this.zzlq.size();
        int i2 = this.zzlp;
        if (size == i2) {
            zzdk remove = this.zzlq.remove(i2 - 1);
            zzcy().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzlq.add(i, new zzdk(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzcx();
        if (!this.zzlq.isEmpty()) {
            this.zzlq.clear();
        }
        if (this.zzlr.isEmpty()) {
            return;
        }
        this.zzlr.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzcx();
        Comparable comparable = (Comparable) obj;
        int zzb = zzb((zzdb<K, V>) comparable);
        if (zzb >= 0) {
            return (V) zzan(zzb);
        }
        if (this.zzlr.isEmpty()) {
            return null;
        }
        return this.zzlr.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzan(int i) {
        zzcx();
        V v = (V) this.zzlq.remove(i).getValue();
        if (!this.zzlr.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzcy().entrySet().iterator();
            this.zzlq.add(new zzdk(this, it.next()));
            it.remove();
        }
        return v;
    }

    private final int zzb(K k) {
        int size = this.zzlq.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzlq.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzlq.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else {
                if (compareTo2 <= 0) {
                    return i2;
                }
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzls == null) {
            this.zzls = new zzdm(this, null);
        }
        return this.zzls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzcw() {
        if (this.zzlu == null) {
            this.zzlu = new zzdg(this, null);
        }
        return this.zzlu;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzcx() {
        if (this.zzfk) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzcy() {
        zzcx();
        if (this.zzlr.isEmpty() && !(this.zzlr instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzlr = treeMap;
            this.zzlt = treeMap.descendingMap();
        }
        return (SortedMap) this.zzlr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return super.equals(obj);
        }
        zzdb zzdbVar = (zzdb) obj;
        int size = size();
        if (size != zzdbVar.size()) {
            return false;
        }
        int zzcu = zzcu();
        if (zzcu != zzdbVar.zzcu()) {
            return entrySet().equals(zzdbVar.entrySet());
        }
        for (int i = 0; i < zzcu; i++) {
            if (!zzam(i).equals(zzdbVar.zzam(i))) {
                return false;
            }
        }
        if (zzcu != size) {
            return this.zzlr.equals(zzdbVar.zzlr);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int zzcu = zzcu();
        int i = 0;
        for (int i2 = 0; i2 < zzcu; i2++) {
            i += this.zzlq.get(i2).hashCode();
        }
        return this.zzlr.size() > 0 ? i + this.zzlr.hashCode() : i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zzb((zzdb<K, V>) obj, (Comparable) obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdb(int i, zzde zzdeVar) {
        this(i);
    }
}
