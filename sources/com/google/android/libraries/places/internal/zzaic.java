package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzaic {
    private static final zzaib zza;
    private static final zzaib zzb;

    static {
        zzaib zzaibVar;
        try {
            zzaibVar = (zzaib) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzaibVar = null;
        }
        zza = zzaibVar;
        zzb = new zzaib();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaib zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaib zzb() {
        return zzb;
    }
}
