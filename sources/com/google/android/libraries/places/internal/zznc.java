package com.google.android.libraries.places.internal;

import java.util.Comparator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zznc implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
    }
}
