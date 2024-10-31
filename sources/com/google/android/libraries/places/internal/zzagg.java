package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzagg {
    private static final zzage zza = new zzagf();
    private static final zzage zzb;

    static {
        zzage zzageVar;
        try {
            zzageVar = (zzage) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzageVar = null;
        }
        zzb = zzageVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzage zza() {
        zzage zzageVar = zzb;
        if (zzageVar != null) {
            return zzageVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzage zzb() {
        return zza;
    }
}
