package com.google.android.libraries.places.internal;

import java.util.Comparator;
import kotlin.UByte;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzafl implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzaft zzaftVar = (zzaft) obj;
        zzaft zzaftVar2 = (zzaft) obj2;
        zzafk zzafkVar = new zzafk(zzaftVar);
        zzafk zzafkVar2 = new zzafk(zzaftVar2);
        while (zzafkVar.hasNext() && zzafkVar2.hasNext()) {
            int compareTo = Integer.valueOf(zzafkVar.zza() & UByte.MAX_VALUE).compareTo(Integer.valueOf(zzafkVar2.zza() & UByte.MAX_VALUE));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzaftVar.zzd()).compareTo(Integer.valueOf(zzaftVar2.zzd()));
    }
}
