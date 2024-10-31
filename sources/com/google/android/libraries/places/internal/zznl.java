package com.google.android.libraries.places.internal;

import java.io.Closeable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zznl implements Closeable {
    private static final ThreadLocal zza = new zznk();
    private int zzb = 0;

    public static int zza() {
        return ((zznl) zza.get()).zzb;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        int i = this.zzb;
        if (i <= 0) {
            throw new AssertionError("Mismatched calls to RecursionDepth (possible error in core library)");
        }
        this.zzb = i - 1;
    }
}
