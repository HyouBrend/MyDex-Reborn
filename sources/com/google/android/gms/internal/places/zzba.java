package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;

/* loaded from: classes.dex */
final class zzba implements zzch {
    private static final zzba zzib = new zzba();

    private zzba() {
    }

    public static zzba zzbb() {
        return zzib;
    }

    @Override // com.google.android.gms.internal.places.zzch
    public final boolean zzb(Class<?> cls) {
        return zzbc.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.places.zzch
    public final zzci zzc(Class<?> cls) {
        if (!zzbc.class.isAssignableFrom(cls)) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unsupported message type: ".concat(valueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzci) zzbc.zzd(cls.asSubclass(zzbc.class)).zzb(zzbc.zze.zzin, (Object) null, (Object) null);
        } catch (Exception e) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? "Unable to get message info for ".concat(valueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
