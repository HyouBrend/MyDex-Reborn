package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public enum zzajn {
    DOUBLE(zzajo.DOUBLE, 1),
    FLOAT(zzajo.FLOAT, 5),
    INT64(zzajo.LONG, 0),
    UINT64(zzajo.LONG, 0),
    INT32(zzajo.INT, 0),
    FIXED64(zzajo.LONG, 1),
    FIXED32(zzajo.INT, 5),
    BOOL(zzajo.BOOLEAN, 0),
    STRING(zzajo.STRING, 2),
    GROUP(zzajo.MESSAGE, 3),
    MESSAGE(zzajo.MESSAGE, 2),
    BYTES(zzajo.BYTE_STRING, 2),
    UINT32(zzajo.INT, 0),
    ENUM(zzajo.ENUM, 0),
    SFIXED32(zzajo.INT, 5),
    SFIXED64(zzajo.LONG, 1),
    SINT32(zzajo.INT, 0),
    SINT64(zzajo.LONG, 0);

    private final zzajo zzt;

    zzajn(zzajo zzajoVar, int i) {
        this.zzt = zzajoVar;
    }

    public final zzajo zza() {
        return this.zzt;
    }
}
