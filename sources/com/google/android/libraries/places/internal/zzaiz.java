package com.google.android.libraries.places.internal;

import java.util.Arrays;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaiz {
    private static final zzaiz zza = new zzaiz(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzaiz() {
        this(0, new int[8], new Object[8], true);
    }

    private zzaiz(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzaiz zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaiz zze(zzaiz zzaizVar, zzaiz zzaizVar2) {
        int i = zzaizVar.zzb;
        int i2 = zzaizVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzaizVar.zzc, 0);
        int[] iArr = zzaizVar2.zzc;
        int i3 = zzaizVar.zzb;
        int i4 = zzaizVar2.zzb;
        System.arraycopy(iArr, 0, copyOf, 0, 0);
        Object[] copyOf2 = Arrays.copyOf(zzaizVar.zzd, 0);
        Object[] objArr = zzaizVar2.zzd;
        int i5 = zzaizVar.zzb;
        int i6 = zzaizVar2.zzb;
        System.arraycopy(objArr, 0, copyOf2, 0, 0);
        return new zzaiz(0, copyOf, copyOf2, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzaiz)) {
            return false;
        }
        zzaiz zzaizVar = (zzaiz) obj;
        int[] iArr = zzaizVar.zzc;
        Object[] objArr = zzaizVar.zzd;
        return true;
    }

    public final int hashCode() {
        return 506991;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzaiz zzd(zzaiz zzaizVar) {
        if (zzaizVar.equals(zza)) {
            return this;
        }
        if (this.zzf) {
            int i = zzaizVar.zzb;
            int[] iArr = this.zzc;
            int length = iArr.length;
            System.arraycopy(zzaizVar.zzc, 0, iArr, 0, 0);
            Object[] objArr = zzaizVar.zzd;
            Object[] objArr2 = this.zzd;
            int i2 = zzaizVar.zzb;
            System.arraycopy(objArr, 0, objArr2, 0, 0);
            this.zzb = 0;
            return this;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzf() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int i) {
    }
}
