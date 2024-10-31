package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zzbb extends zzq<Float> implements zzbh<Float>, zzcw, RandomAccess {
    private static final zzbb zzic;
    private int size;
    private float[] zzid;

    zzbb() {
        this(new float[10], 0);
    }

    private zzbb(float[] fArr, int i) {
        this.zzid = fArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzac();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.zzid;
        System.arraycopy(fArr, i2, fArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbb)) {
            return super.equals(obj);
        }
        zzbb zzbbVar = (zzbb) obj;
        if (this.size != zzbbVar.size) {
            return false;
        }
        float[] fArr = zzbbVar.zzid;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzid[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzid[i2]);
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzf(float f) {
        zzd(this.size, f);
    }

    private final void zzd(int i, float f) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        float[] fArr = this.zzid;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzid, i, fArr2, i + 1, this.size - i);
            this.zzid = fArr2;
        }
        this.zzid[i] = f;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzbb)) {
            return super.addAll(collection);
        }
        zzbb zzbbVar = (zzbb) collection;
        int i = zzbbVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        float[] fArr = this.zzid;
        if (i3 > fArr.length) {
            this.zzid = Arrays.copyOf(fArr, i3);
        }
        System.arraycopy(zzbbVar.zzid, 0, this.zzid, this.size, zzbbVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzid[i]))) {
                float[] fArr = this.zzid;
                System.arraycopy(fArr, i + 1, fArr, i, (this.size - i) - 1);
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
        float floatValue = ((Float) obj).floatValue();
        zzac();
        zzf(i);
        float[] fArr = this.zzid;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        float[] fArr = this.zzid;
        float f = fArr[i];
        if (i < this.size - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (r2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.places.zzq, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzd(i, ((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.places.zzbh
    public final /* synthetic */ zzbh<Float> zzh(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzbb(Arrays.copyOf(this.zzid, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzf(i);
        return Float.valueOf(this.zzid[i]);
    }

    static {
        zzbb zzbbVar = new zzbb(new float[0], 0);
        zzic = zzbbVar;
        zzbbVar.zzab();
    }
}
