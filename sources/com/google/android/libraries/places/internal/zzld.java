package com.google.android.libraries.places.internal;

import java.util.ListIterator;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzld extends zzlc implements ListIterator {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzld(ListIterator listIterator) {
        super(listIterator);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzb.hasPrevious();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzb.nextIndex();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    @Override // java.util.ListIterator
    public final Object previous() {
        return zza(this.zzb.previous());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzb.previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
