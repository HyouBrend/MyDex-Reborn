package com.google.android.libraries.places.internal;

import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzkq {
    public static List zza(List list, zzdl zzdlVar) {
        if (list instanceof RandomAccess) {
            return new zzkn(list, zzdlVar);
        }
        return new zzkp(list, zzdlVar);
    }
}
