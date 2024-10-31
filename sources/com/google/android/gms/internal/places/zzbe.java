package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzbe extends zzq<Integer> implements zzbi, zzcw, RandomAccess {
    private static final zzbe zzjc;
    private int size;
    private int[] zzjd;

    public static zzbe zzbo() {
        return zzjc;
    }

    zzbe() {
        this(new int[10], 0);
    }

    private zzbe(int[] iArr, int i) {
        this.zzjd = iArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzac();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.zzjd;
        System.arraycopy(iArr, i2, iArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbe)) {
            return super.equals(obj);
        }
        zzbe zzbeVar = (zzbe) obj;
        if (this.size != zzbeVar.size) {
            return false;
        }
        int[] iArr = zzbeVar.zzjd;
        for (int i = 0; i < this.size; i++) {
            if (this.zzjd[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzjd[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzf(i);
        return this.zzjd[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzac(int i) {
        zzp(this.size, i);
    }

    private final void zzp(int i, int i2) {
        int i3;
        zzac();
        if (i < 0 || i > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        int[] iArr = this.zzjd;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        } else {
            int[] iArr2 = new int[((i3 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzjd, i, iArr2, i + 1, this.size - i);
            this.zzjd = iArr2;
        }
        this.zzjd[i] = i2;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzbe)) {
            return super.addAll(collection);
        }
        zzbe zzbeVar = (zzbe) collection;
        int i = zzbeVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        int[] iArr = this.zzjd;
        if (i3 > iArr.length) {
            this.zzjd = Arrays.copyOf(iArr, i3);
        }
        System.arraycopy(zzbeVar.zzjd, 0, this.zzjd, this.size, zzbeVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzjd[i]))) {
                int[] iArr = this.zzjd;
                System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
    }

    private final String zzg(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzac();
        zzf(i);
        int[] iArr = this.zzjd;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        int[] iArr = this.zzjd;
        int i2 = iArr[i];
        if (i < this.size - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (r2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzp(i, ((Integer) obj).intValue());
    }

    @Override // com.google.android.gms.internal.places.zzbh
    public final /* synthetic */ zzbh<Integer> zzh(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzbe(Arrays.copyOf(this.zzjd, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzbe zzbeVar = new zzbe(new int[0], 0);
        zzjc = zzbeVar;
        zzbeVar.zzab();
    }
}
