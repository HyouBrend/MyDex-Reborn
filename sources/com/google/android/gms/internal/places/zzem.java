package com.google.android.gms.internal.places;

import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes.dex */
public enum zzem {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzw.zzeg),
    ENUM(null),
    MESSAGE(null);

    private final Object zzjr;

    zzem(Object obj) {
        this.zzjr = obj;
    }
}
