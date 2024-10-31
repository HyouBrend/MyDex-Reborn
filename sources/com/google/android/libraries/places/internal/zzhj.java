package com.google.android.libraries.places.internal;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhj extends LinkedHashMap {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhj(int i, float f, boolean z) {
        super(16, 0.75f, true);
    }

    @Override // java.util.LinkedHashMap
    protected final boolean removeEldestEntry(Map.Entry entry) {
        return size() > 10;
    }
}
