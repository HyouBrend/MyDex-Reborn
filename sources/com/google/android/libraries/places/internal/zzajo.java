package com.google.android.libraries.places.internal;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public enum zzajo {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzaft.zzb),
    ENUM(null),
    MESSAGE(null);

    private final Object zzk;

    zzajo(Object obj) {
        this.zzk = obj;
    }
}
