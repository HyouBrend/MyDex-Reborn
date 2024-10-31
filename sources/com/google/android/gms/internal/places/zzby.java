package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zzby extends zzq<Long> implements zzbh<Long>, zzcw, RandomAccess {
    private static final zzby zzkg;
    private int size;
    private long[] zzkh;

    zzby() {
        this(new long[10], 0);
    }

    private zzby(long[] jArr, int i) {
        this.zzkh = jArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzac();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.zzkh;
        System.arraycopy(jArr, i2, jArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzby)) {
            return super.equals(obj);
        }
        zzby zzbyVar = (zzby) obj;
        if (this.size != zzbyVar.size) {
            return false;
        }
        long[] jArr = zzbyVar.zzkh;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkh[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbd.zzl(this.zzkh[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzf(i);
        return this.zzkh[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzm(long j) {
        zzl(this.size, j);
    }

    private final void zzl(int i, long j) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        long[] jArr = this.zzkh;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[((i2 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzkh, i, jArr2, i + 1, this.size - i);
            this.zzkh = jArr2;
        }
        this.zzkh[i] = j;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzby)) {
            return super.addAll(collection);
        }
        zzby zzbyVar = (zzby) collection;
        int i = zzbyVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        long[] jArr = this.zzkh;
        if (i3 > jArr.length) {
            this.zzkh = Arrays.copyOf(jArr, i3);
        }
        System.arraycopy(zzbyVar.zzkh, 0, this.zzkh, this.size, zzbyVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzkh[i]))) {
                long[] jArr = this.zzkh;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
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
        long longValue = ((Long) obj).longValue();
        zzac();
        zzf(i);
        long[] jArr = this.zzkh;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        long[] jArr = this.zzkh;
        long j = jArr[i];
        if (i < this.size - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (r3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzl(i, ((Long) obj).longValue());
    }

    @Override // com.google.android.gms.internal.places.zzbh
    public final /* synthetic */ zzbh<Long> zzh(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzby(Arrays.copyOf(this.zzkh, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzby zzbyVar = new zzby(new long[0], 0);
        zzkg = zzbyVar;
        zzbyVar.zzab();
    }
}
