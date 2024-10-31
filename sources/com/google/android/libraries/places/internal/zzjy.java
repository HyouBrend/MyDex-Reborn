package com.google.android.libraries.places.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzjy extends zzkc {
    private static final zzjy zzb = new zzjy();

    private zzjy() {
        super("");
    }

    @Override // com.google.android.libraries.places.internal.zzkc, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return zza((zzkc) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzkc
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "+∞";
    }

    @Override // com.google.android.libraries.places.internal.zzkc
    public final int zza(zzkc zzkcVar) {
        return zzkcVar == this ? 0 : 1;
    }

    @Override // com.google.android.libraries.places.internal.zzkc
    final void zzc(StringBuilder sb) {
        throw new AssertionError();
    }

    @Override // com.google.android.libraries.places.internal.zzkc
    final void zzd(StringBuilder sb) {
        sb.append("+∞)");
    }

    @Override // com.google.android.libraries.places.internal.zzkc
    final boolean zze(Comparable comparable) {
        return false;
    }
}
