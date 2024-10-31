package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzfb {
    private Bitmap zza;

    public final zzfd zza() {
        zzjp.zzk(this.zza != null, "Photo must be set to non-null value.");
        return new zzfd(this.zza, null);
    }

    public final zzfb zzb(Bitmap bitmap) {
        this.zza = bitmap;
        return this;
    }
}
