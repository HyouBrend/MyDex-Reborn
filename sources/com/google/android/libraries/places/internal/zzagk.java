package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzagk implements zzahu {
    private static final zzagk zza = new zzagk();

    private zzagk() {
    }

    public static zzagk zza() {
        return zza;
    }

    @Override // com.google.android.libraries.places.internal.zzahu
    public final zzaht zzb(Class cls) {
        if (!zzago.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzaht) zzago.zzx(cls.asSubclass(zzago.class)).zzb(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzahu
    public final boolean zzc(Class cls) {
        return zzago.class.isAssignableFrom(cls);
    }
}
