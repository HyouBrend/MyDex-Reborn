package com.google.android.gms.internal.places;

import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes.dex */
public enum zzbm {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzw.class, zzw.class, zzw.zzeg),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class<?> zzjp;
    private final Class<?> zzjq;
    private final Object zzjr;

    zzbm(Class cls, Class cls2, Object obj) {
        this.zzjp = cls;
        this.zzjq = cls2;
        this.zzjr = obj;
    }

    public final Class<?> zzbw() {
        return this.zzjq;
    }
}
