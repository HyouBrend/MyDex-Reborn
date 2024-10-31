package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzmd {
    private static final zzlz zza = new zzmb();
    private static final zzly zzb = new zzmc();

    public static zzlv zza(Set set) {
        zzlv zzlvVar = new zzlv(zza, null);
        zzlvVar.zza(zzb);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzlvVar.zzg((zzlm) it.next());
        }
        return zzlvVar;
    }
}
