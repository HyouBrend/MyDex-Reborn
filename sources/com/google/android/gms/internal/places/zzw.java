package com.google.android.gms.internal.places;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.UByte;

/* loaded from: classes.dex */
public abstract class zzw implements Serializable, Iterable<Byte> {
    public static final zzw zzeg = new zzag(zzbd.zziz);
    private static final zzac zzeh;
    private static final Comparator<zzw> zzej;
    private int zzei = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzb(byte b) {
        return b & UByte.MAX_VALUE;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    public abstract boolean zzae();

    protected abstract int zzb(int i, int i2, int i3);

    public abstract zzw zzb(int i, int i2);

    protected abstract String zzb(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(zzt zztVar) throws IOException;

    protected abstract void zzb(byte[] bArr, int i, int i2, int i3);

    public abstract byte zzi(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzj(int i);

    public static zzw zzc(byte[] bArr, int i, int i2) {
        zzc(i, i + i2, bArr.length);
        return new zzag(zzeh.zzd(bArr, i, i2));
    }

    public static zzw zzi(String str) {
        return new zzag(str.getBytes(zzbd.UTF_8));
    }

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return zzbd.zziz;
        }
        byte[] bArr = new byte[size];
        zzb(bArr, 0, 0, size);
        return bArr;
    }

    public final String zzad() {
        return size() == 0 ? "" : zzb(zzbd.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzei;
        if (i == 0) {
            int size = size();
            i = zzb(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzei = i;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzae zzk(int i) {
        return new zzae(i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzaf() {
        return this.zzei;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("End index: ");
        sb3.append(i2);
        sb3.append(" >= ");
        sb3.append(i3);
        throw new IndexOutOfBoundsException(sb3.toString());
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzv(this);
    }

    static {
        zzv zzvVar = null;
        zzeh = zzp.zzy() ? new zzaf(zzvVar) : new zzaa(zzvVar);
        zzej = new zzy();
    }
}
