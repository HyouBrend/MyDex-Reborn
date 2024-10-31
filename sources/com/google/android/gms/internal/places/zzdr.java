package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzdr {
    private static final zzdr zzmh = new zzdr(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzdy;
    private int zzii;
    private Object[] zzkt;
    private int[] zzmi;

    public static zzdr zzdh() {
        return zzmh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzdr zzdi() {
        return new zzdr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzdr zzb(zzdr zzdrVar, zzdr zzdrVar2) {
        int i = zzdrVar.count + zzdrVar2.count;
        int[] copyOf = Arrays.copyOf(zzdrVar.zzmi, i);
        System.arraycopy(zzdrVar2.zzmi, 0, copyOf, zzdrVar.count, zzdrVar2.count);
        Object[] copyOf2 = Arrays.copyOf(zzdrVar.zzkt, i);
        System.arraycopy(zzdrVar2.zzkt, 0, copyOf2, zzdrVar.count, zzdrVar2.count);
        return new zzdr(i, copyOf, copyOf2, true);
    }

    private zzdr() {
        this(0, new int[8], new Object[8], true);
    }

    private zzdr(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzii = -1;
        this.count = i;
        this.zzmi = iArr;
        this.zzkt = objArr;
        this.zzdy = z;
    }

    public final void zzab() {
        this.zzdy = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzel zzelVar) throws IOException {
        if (zzelVar.zzam() == zzbc.zze.zzix) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzelVar.zzb(this.zzmi[i] >>> 3, this.zzkt[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzelVar.zzb(this.zzmi[i2] >>> 3, this.zzkt[i2]);
        }
    }

    public final void zzc(zzel zzelVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzelVar.zzam() == zzbc.zze.zziw) {
            for (int i = 0; i < this.count; i++) {
                zzc(this.zzmi[i], this.zzkt[i], zzelVar);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            zzc(this.zzmi[i2], this.zzkt[i2], zzelVar);
        }
    }

    private static void zzc(int i, Object obj, zzel zzelVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzelVar.zzj(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zzelVar.zzd(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zzelVar.zzb(i2, (zzw) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zzelVar.zzg(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzbk.zzbs());
        }
        if (zzelVar.zzam() == zzbc.zze.zziw) {
            zzelVar.zzaa(i2);
            ((zzdr) obj).zzc(zzelVar);
            zzelVar.zzab(i2);
        } else {
            zzelVar.zzab(i2);
            ((zzdr) obj).zzc(zzelVar);
            zzelVar.zzaa(i2);
        }
    }

    public final int zzdj() {
        int i = this.zzii;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzaj.zze(this.zzmi[i3] >>> 3, (zzw) this.zzkt[i3]);
        }
        this.zzii = i2;
        return i2;
    }

    public final int zzbh() {
        int zzf;
        int i = this.zzii;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzmi[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zzf = zzaj.zzf(i5, ((Long) this.zzkt[i3]).longValue());
            } else if (i6 == 1) {
                zzf = zzaj.zzh(i5, ((Long) this.zzkt[i3]).longValue());
            } else if (i6 == 2) {
                zzf = zzaj.zzd(i5, (zzw) this.zzkt[i3]);
            } else if (i6 == 3) {
                zzf = (zzaj.zzr(i5) << 1) + ((zzdr) this.zzkt[i3]).zzbh();
            } else if (i6 == 5) {
                zzf = zzaj.zzk(i5, ((Integer) this.zzkt[i3]).intValue());
            } else {
                throw new IllegalStateException(zzbk.zzbs());
            }
            i2 += zzf;
        }
        this.zzii = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzdr)) {
            return false;
        }
        zzdr zzdrVar = (zzdr) obj;
        int i = this.count;
        if (i == zzdrVar.count) {
            int[] iArr = this.zzmi;
            int[] iArr2 = zzdrVar.zzmi;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                }
                if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (z) {
                Object[] objArr = this.zzkt;
                Object[] objArr2 = zzdrVar.zzkt;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    }
                    if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    }
                    i4++;
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzmi;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzkt;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzcl.zzb(sb, i, String.valueOf(this.zzmi[i2] >>> 3), this.zzkt[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(int i, Object obj) {
        if (!this.zzdy) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.count;
        int[] iArr = this.zzmi;
        if (i2 == iArr.length) {
            int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
            this.zzmi = Arrays.copyOf(iArr, i3);
            this.zzkt = Arrays.copyOf(this.zzkt, i3);
        }
        int[] iArr2 = this.zzmi;
        int i4 = this.count;
        iArr2[i4] = i;
        this.zzkt[i4] = obj;
        this.count = i4 + 1;
    }
}
