package com.google.android.libraries.places.internal;

import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzjz extends zzkc {
    @Override // com.google.android.libraries.places.internal.zzkc
    public final int hashCode() {
        return ~this.zza.hashCode();
    }

    public final String toString() {
        return InternalZipConstants.ZIP_FILE_SEPARATOR + this.zza.toString() + "\\";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzkc
    public final void zzc(StringBuilder sb) {
        sb.append('(');
        sb.append(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzkc
    public final void zzd(StringBuilder sb) {
        sb.append(this.zza);
        sb.append(']');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzkc
    public final boolean zze(Comparable comparable) {
        Comparable comparable2 = this.zza;
        int i = zzkt.zzc;
        return comparable2.compareTo(comparable) < 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjz(Comparable comparable) {
        super(comparable);
        comparable.getClass();
    }
}
