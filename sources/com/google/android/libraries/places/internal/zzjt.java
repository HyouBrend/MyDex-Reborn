package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzjt {
    private final zzjf zza;
    private final zzjr zzb;

    private zzjt(zzjr zzjrVar) {
        zzje zzjeVar = zzje.zza;
        this.zzb = zzjrVar;
        this.zza = zzjeVar;
    }

    public static zzjt zzb(char c) {
        return new zzjt(new zzjr(new zzjc('.')));
    }

    public final List zzc(CharSequence charSequence) {
        zzjq zzjqVar = new zzjq(this.zzb, this, "3.2.0");
        ArrayList arrayList = new ArrayList();
        while (zzjqVar.hasNext()) {
            arrayList.add((String) zzjqVar.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
