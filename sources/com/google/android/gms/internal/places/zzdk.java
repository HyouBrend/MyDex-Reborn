package com.google.android.gms.internal.places;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX WARN: Incorrect field signature: TK; */
/* loaded from: classes.dex */
public final class zzdk<K, V> implements Comparable<zzdk>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzdb zzma;
    private final Comparable zzme;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdk(zzdb zzdbVar, Map.Entry<K, V> entry) {
        this(zzdbVar, (Comparable) entry.getKey(), entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public zzdk(zzdb zzdbVar, K k, V v) {
        this.zzma = zzdbVar;
        this.zzme = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzma.zzcx();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzme, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.zzme;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        V v = this.value;
        return hashCode ^ (v != null ? v.hashCode() : 0);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzme);
        String valueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append(SimpleComparison.EQUAL_TO_OPERATION);
        sb.append(valueOf2);
        return sb.toString();
    }

    private static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzme;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzdk zzdkVar) {
        return ((Comparable) getKey()).compareTo((Comparable) zzdkVar.getKey());
    }
}
