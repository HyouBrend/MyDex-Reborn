package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zzu extends zzq<Boolean> implements zzbh<Boolean>, zzcw, RandomAccess {
    private static final zzu zzed;
    private int size;
    private boolean[] zzee;

    zzu() {
        this(new boolean[10], 0);
    }

    private zzu(boolean[] zArr, int i) {
        this.zzee = zArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzac();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.zzee;
        System.arraycopy(zArr, i2, zArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzu)) {
            return super.equals(obj);
        }
        zzu zzuVar = (zzu) obj;
        if (this.size != zzuVar.size) {
            return false;
        }
        boolean[] zArr = zzuVar.zzee;
        for (int i = 0; i < this.size; i++) {
            if (this.zzee[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbd.zze(this.zzee[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zzb(this.size, z);
    }

    private final void zzb(int i, boolean z) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        boolean[] zArr = this.zzee;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzee, i, zArr2, i + 1, this.size - i);
            this.zzee = zArr2;
        }
        this.zzee[i] = z;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzu)) {
            return super.addAll(collection);
        }
        zzu zzuVar = (zzu) collection;
        int i = zzuVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        boolean[] zArr = this.zzee;
        if (i3 > zArr.length) {
            this.zzee = Arrays.copyOf(zArr, i3);
        }
        System.arraycopy(zzuVar.zzee, 0, this.zzee, this.size, zzuVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzee[i]))) {
                boolean[] zArr = this.zzee;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzac();
        zzf(i);
        boolean[] zArr = this.zzee;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        boolean[] zArr = this.zzee;
        boolean z = zArr[i];
        if (i < this.size - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (r2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzb(i, ((Boolean) obj).booleanValue());
    }

    @Override // com.google.android.gms.internal.places.zzbh
    public final /* synthetic */ zzbh<Boolean> zzh(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzu(Arrays.copyOf(this.zzee, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzf(i);
        return Boolean.valueOf(this.zzee[i]);
    }

    static {
        zzu zzuVar = new zzu(new boolean[0], 0);
        zzed = zzuVar;
        zzuVar.zzab();
    }
}
