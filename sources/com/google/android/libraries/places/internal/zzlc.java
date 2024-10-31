package com.google.android.libraries.places.internal;

import java.util.Iterator;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzlc implements Iterator {
    final Iterator zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlc(Iterator it) {
        it.getClass();
        this.zzb = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return zza(this.zzb.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zzb.remove();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zza(Object obj);
}
