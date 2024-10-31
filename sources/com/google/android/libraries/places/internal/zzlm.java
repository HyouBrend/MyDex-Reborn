package com.google.android.libraries.places.internal;

import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public class zzlm {
    private final String zza;
    private final Class zzb;
    private final boolean zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzlm(String str, Class cls, boolean z) {
        this(str, cls, z, true);
    }

    public static zzlm zza(String str, Class cls) {
        return new zzlm(str, cls, false, false);
    }

    public final String toString() {
        return getClass().getName() + InternalZipConstants.ZIP_FILE_SEPARATOR + this.zza + "[" + this.zzb.getName() + "]";
    }

    public final boolean zzb() {
        return this.zzc;
    }

    private zzlm(String str, Class cls, boolean z, boolean z2) {
        zznj.zzb(str);
        this.zza = str;
        this.zzb = cls;
        this.zzc = z;
        System.identityHashCode(this);
        for (int i = 0; i < 5; i++) {
        }
    }
}
