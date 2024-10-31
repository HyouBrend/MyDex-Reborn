package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzif extends zzik {
    private final CancellationTokenSource zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzif(CancellationTokenSource cancellationTokenSource, String str) {
        this.zza = cancellationTokenSource;
        if (str == null) {
            throw new NullPointerException("Null query");
        }
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzik) {
            zzik zzikVar = (zzik) obj;
            if (this.zza.equals(zzikVar.zza()) && this.zzb.equals(zzikVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        return "AutocompleteRequest{source=" + this.zza.toString() + ", query=" + this.zzb + "}";
    }

    @Override // com.google.android.libraries.places.internal.zzin
    public final CancellationTokenSource zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzik
    public final String zzb() {
        return this.zzb;
    }
}
