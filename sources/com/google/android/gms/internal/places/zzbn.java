package com.google.android.gms.internal.places;

import java.util.Map;

/* loaded from: classes.dex */
final class zzbn<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzbl> zzjt;

    private zzbn(Map.Entry<K, zzbl> entry) {
        this.zzjt = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzjt.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzjt.getValue() == null) {
            return null;
        }
        return zzbl.zzbv();
    }

    public final zzbl zzbx() {
        return this.zzjt.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (!(obj instanceof zzck)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return this.zzjt.getValue().zzj((zzck) obj);
    }
}
