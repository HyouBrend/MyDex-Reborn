package com.google.android.libraries.places.internal;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzkc implements Comparable, Serializable {
    final Comparable zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkc(Comparable comparable) {
        this.zza = comparable;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzkc) {
            try {
                if (compareTo((zzkc) obj) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract int hashCode();

    @Override // java.lang.Comparable
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public int compareTo(zzkc zzkcVar) {
        zzka zzkaVar;
        zzjy zzjyVar;
        zzkaVar = zzka.zzb;
        if (zzkcVar != zzkaVar) {
            zzjyVar = zzjy.zzb;
            if (zzkcVar == zzjyVar) {
                return -1;
            }
            Comparable comparable = this.zza;
            Comparable comparable2 = zzkcVar.zza;
            int i = zzkt.zzc;
            int compareTo = comparable.compareTo(comparable2);
            if (compareTo != 0) {
                return compareTo;
            }
            boolean z = this instanceof zzjz;
            if (z == (zzkcVar instanceof zzjz)) {
                return 0;
            }
            if (!z) {
                return -1;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzc(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zze(Comparable comparable);
}
