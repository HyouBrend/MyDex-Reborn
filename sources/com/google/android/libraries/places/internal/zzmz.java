package com.google.android.libraries.places.internal;

import java.util.Comparator;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzmz implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        zzng zza = zzng.zza(obj);
        zzng zza2 = zzng.zza(obj2);
        if (zza == zza2) {
            int ordinal = zza.ordinal();
            if (ordinal == 0) {
                return ((Boolean) obj).compareTo((Boolean) obj2);
            }
            if (ordinal == 1) {
                return ((String) obj).compareTo((String) obj2);
            }
            if (ordinal == 2) {
                return ((Long) obj).compareTo((Long) obj2);
            }
            if (ordinal != 3) {
                throw null;
            }
            return ((Double) obj).compareTo((Double) obj2);
        }
        return zza.compareTo(zza2);
    }
}
