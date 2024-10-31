package com.google.android.libraries.places.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzkh extends zzke implements List, RandomAccess {
    private static final zzlf zza = new zzkf(zzkv.zza, 0);
    public static final /* synthetic */ int zzd = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzkh zzi(Object[] objArr, int i) {
        if (i == 0) {
            return zzkv.zza;
        }
        return new zzkv(objArr, i);
    }

    public static zzkh zzj(Collection collection) {
        if (collection instanceof zzke) {
            zzkh zzd2 = ((zzke) collection).zzd();
            if (!zzd2.zzf()) {
                return zzd2;
            }
            Object[] array = zzd2.toArray();
            return zzi(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        zzkr.zza(array2, length);
        return zzi(array2, length);
    }

    public static zzkh zzk(Object[] objArr) {
        if (objArr.length == 0) {
            return zzkv.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzkr.zza(objArr2, length);
        return zzi(objArr2, length);
    }

    public static zzkh zzl() {
        return zzkv.zza;
    }

    public static zzkh zzm(Object obj) {
        Object[] objArr = {obj};
        zzkr.zza(objArr, 1);
        return zzi(objArr, 1);
    }

    public static zzkh zzn(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzkr.zza(objArr, 2);
        return zzi(objArr, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzkh zzo(Comparator comparator, Iterable iterable) {
        Object[] array = iterable.toArray();
        int length = array.length;
        zzkr.zza(array, length);
        Arrays.sort(array, comparator);
        return zzi(array, length);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (zzjl.zza(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !zzjl.zza(it.next(), it2.next())) {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzke
    int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // com.google.android.libraries.places.internal.zzke
    @Deprecated
    public final zzkh zzd() {
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzke
    /* renamed from: zze */
    public final zzle iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public zzkh subList(int i, int i2) {
        zzjp.zzi(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 != 0) {
            return new zzkg(this, i, i3);
        }
        return zzkv.zza;
    }

    @Override // java.util.List
    /* renamed from: zzp, reason: merged with bridge method [inline-methods] */
    public final zzlf listIterator(int i) {
        zzjp.zzb(i, size(), "index");
        return isEmpty() ? zza : new zzkf(this, i);
    }
}
