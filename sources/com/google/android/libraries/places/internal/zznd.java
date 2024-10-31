package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zznd implements Iterator {
    final /* synthetic */ zzne zza;
    private int zzb = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznd(zzne zzneVar) {
        this.zza = zzneVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb;
        zzne zzneVar = this.zza;
        return i < zzneVar.zza() - zzneVar.zzb();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object[] objArr;
        int i = this.zzb;
        zzne zzneVar = this.zza;
        if (i < zzneVar.zza() - zzneVar.zzb()) {
            zzne zzneVar2 = this.zza;
            objArr = zzneVar2.zzb.zzb;
            Object obj = objArr[zzneVar2.zzb() + i];
            this.zzb = i + 1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
