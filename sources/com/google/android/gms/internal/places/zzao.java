package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zzao extends zzq<Double> implements zzbh<Double>, zzcw, RandomAccess {
    private static final zzao zzex;
    private int size;
    private double[] zzey;

    zzao() {
        this(new double[10], 0);
    }

    private zzao(double[] dArr, int i) {
        this.zzey = dArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzac();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zzey;
        System.arraycopy(dArr, i2, dArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzao)) {
            return super.equals(obj);
        }
        zzao zzaoVar = (zzao) obj;
        if (this.size != zzaoVar.size) {
            return false;
        }
        double[] dArr = zzaoVar.zzey;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzey[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbd.zzl(Double.doubleToLongBits(this.zzey[i2]));
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzd(double d) {
        zzd(this.size, d);
    }

    private final void zzd(int i, double d) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        double[] dArr = this.zzey;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzey, i, dArr2, i + 1, this.size - i);
            this.zzey = dArr2;
        }
        this.zzey[i] = d;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzao)) {
            return super.addAll(collection);
        }
        zzao zzaoVar = (zzao) collection;
        int i = zzaoVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        double[] dArr = this.zzey;
        if (i3 > dArr.length) {
            this.zzey = Arrays.copyOf(dArr, i3);
        }
        System.arraycopy(zzaoVar.zzey, 0, this.zzey, this.size, zzaoVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzey[i]))) {
                double[] dArr = this.zzey;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzac();
        zzf(i);
        double[] dArr = this.zzey;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        double[] dArr = this.zzey;
        double d = dArr[i];
        if (i < this.size - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (r3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzd(i, ((Double) obj).doubleValue());
    }

    @Override // com.google.android.gms.internal.places.zzbh
    public final /* synthetic */ zzbh<Double> zzh(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzao(Arrays.copyOf(this.zzey, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzf(i);
        return Double.valueOf(this.zzey[i]);
    }

    static {
        zzao zzaoVar = new zzao(new double[0], 0);
        zzex = zzaoVar;
        zzaoVar.zzab();
    }
}
