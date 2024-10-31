package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public enum zzagj {
    DOUBLE(0, 1, zzaha.DOUBLE),
    FLOAT(1, 1, zzaha.FLOAT),
    INT64(2, 1, zzaha.LONG),
    UINT64(3, 1, zzaha.LONG),
    INT32(4, 1, zzaha.INT),
    FIXED64(5, 1, zzaha.LONG),
    FIXED32(6, 1, zzaha.INT),
    BOOL(7, 1, zzaha.BOOLEAN),
    STRING(8, 1, zzaha.STRING),
    MESSAGE(9, 1, zzaha.MESSAGE),
    BYTES(10, 1, zzaha.BYTE_STRING),
    UINT32(11, 1, zzaha.INT),
    ENUM(12, 1, zzaha.ENUM),
    SFIXED32(13, 1, zzaha.INT),
    SFIXED64(14, 1, zzaha.LONG),
    SINT32(15, 1, zzaha.INT),
    SINT64(16, 1, zzaha.LONG),
    GROUP(17, 1, zzaha.MESSAGE),
    DOUBLE_LIST(18, 2, zzaha.DOUBLE),
    FLOAT_LIST(19, 2, zzaha.FLOAT),
    INT64_LIST(20, 2, zzaha.LONG),
    UINT64_LIST(21, 2, zzaha.LONG),
    INT32_LIST(22, 2, zzaha.INT),
    FIXED64_LIST(23, 2, zzaha.LONG),
    FIXED32_LIST(24, 2, zzaha.INT),
    BOOL_LIST(25, 2, zzaha.BOOLEAN),
    STRING_LIST(26, 2, zzaha.STRING),
    MESSAGE_LIST(27, 2, zzaha.MESSAGE),
    BYTES_LIST(28, 2, zzaha.BYTE_STRING),
    UINT32_LIST(29, 2, zzaha.INT),
    ENUM_LIST(30, 2, zzaha.ENUM),
    SFIXED32_LIST(31, 2, zzaha.INT),
    SFIXED64_LIST(32, 2, zzaha.LONG),
    SINT32_LIST(33, 2, zzaha.INT),
    SINT64_LIST(34, 2, zzaha.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzaha.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzaha.FLOAT),
    INT64_LIST_PACKED(37, 3, zzaha.LONG),
    UINT64_LIST_PACKED(38, 3, zzaha.LONG),
    INT32_LIST_PACKED(39, 3, zzaha.INT),
    FIXED64_LIST_PACKED(40, 3, zzaha.LONG),
    FIXED32_LIST_PACKED(41, 3, zzaha.INT),
    BOOL_LIST_PACKED(42, 3, zzaha.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzaha.INT),
    ENUM_LIST_PACKED(44, 3, zzaha.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzaha.INT),
    SFIXED64_LIST_PACKED(46, 3, zzaha.LONG),
    SINT32_LIST_PACKED(47, 3, zzaha.INT),
    SINT64_LIST_PACKED(48, 3, zzaha.LONG),
    GROUP_LIST(49, 2, zzaha.MESSAGE),
    MAP(50, 4, zzaha.VOID);

    private static final zzagj[] zzZ;
    private final zzaha zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzagj[] values = values();
        zzZ = new zzagj[values.length];
        for (zzagj zzagjVar : values) {
            zzZ[zzagjVar.zzac] = zzagjVar;
        }
    }

    zzagj(int i, int i2, zzaha zzahaVar) {
        this.zzac = i;
        this.zzab = zzahaVar;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzahaVar.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzahaVar.zza();
        }
        if (i2 == 1) {
            zzaha zzahaVar2 = zzaha.VOID;
            zzahaVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
