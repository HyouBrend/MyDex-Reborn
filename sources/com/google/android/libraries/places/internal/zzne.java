package com.google.android.libraries.places.internal;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzne extends AbstractSet {
    final int zza = -1;
    final /* synthetic */ zznf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzne(zznf zznfVar, int i) {
        this.zzb = zznfVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Object[] objArr;
        objArr = this.zzb.zzb;
        return Arrays.binarySearch(objArr, zzb(), zza(), obj, this.zza == -1 ? zznf.zza : zznh.zza) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zznd(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return zza() - zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zza() {
        int[] iArr;
        iArr = this.zzb.zzc;
        return iArr[this.zza + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzb() {
        int[] iArr;
        if (this.zza == -1) {
            return 0;
        }
        iArr = this.zzb.zzc;
        return iArr[0];
    }
}
