package com.google.android.libraries.places.internal;

import java.util.Iterator;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzajc implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzajd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzajc(zzajd zzajdVar) {
        zzahe zzaheVar;
        this.zzb = zzajdVar;
        zzaheVar = zzajdVar.zza;
        this.zza = zzaheVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
