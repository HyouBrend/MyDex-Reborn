package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzahs {
    private static final zzahr zza;
    private static final zzahr zzb;

    static {
        zzahr zzahrVar;
        try {
            zzahrVar = (zzahr) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzahrVar = null;
        }
        zza = zzahrVar;
        zzb = new zzahr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzahr zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzahr zzb() {
        return zzb;
    }
}
