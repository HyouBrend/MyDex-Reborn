package com.google.android.libraries.places.internal;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzji {
    public static final Appendable zza(Appendable appendable, Iterator it, zzjk zzjkVar, String str) throws IOException {
        String str2;
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            appendable.append(zzjk.zzf(entry.getKey()));
            appendable.append(SimpleComparison.EQUAL_TO_OPERATION);
            appendable.append(zzjk.zzf(entry.getValue()));
            while (it.hasNext()) {
                str2 = zzjkVar.zza;
                appendable.append(str2);
                Map.Entry entry2 = (Map.Entry) it.next();
                appendable.append(zzjk.zzf(entry2.getKey()));
                appendable.append(SimpleComparison.EQUAL_TO_OPERATION);
                appendable.append(zzjk.zzf(entry2.getValue()));
            }
        }
        return appendable;
    }
}
