package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzjh extends zzjk {
    final /* synthetic */ zzjk zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjh(zzjk zzjkVar, zzjk zzjkVar2) {
        super(zzjkVar2, null);
        this.zza = zzjkVar;
    }

    @Override // com.google.android.libraries.places.internal.zzjk
    public final Appendable zza(Appendable appendable, Iterator it) throws IOException {
        String str;
        zzjp.zzc(it, "parts");
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (next != null) {
                appendable.append(zzjk.zzf(next));
                break;
            }
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            if (next2 != null) {
                str = this.zza.zza;
                appendable.append(str);
                appendable.append(zzjk.zzf(next2));
            }
        }
        return appendable;
    }
}
