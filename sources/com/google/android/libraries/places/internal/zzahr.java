package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzahr {
    public static final int zza(int i, Object obj, Object obj2) {
        zzahq zzahqVar = (zzahq) obj;
        if (zzahqVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzahqVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }
}
