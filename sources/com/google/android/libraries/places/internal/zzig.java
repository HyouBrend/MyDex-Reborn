package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzig extends zzil {
    private final CancellationTokenSource zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzig(CancellationTokenSource cancellationTokenSource, String str) {
        this.zza = cancellationTokenSource;
        if (str == null) {
            throw new NullPointerException("Null placeId");
        }
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzil) {
            zzil zzilVar = (zzil) obj;
            if (this.zza.equals(zzilVar.zza()) && this.zzb.equals(zzilVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        return "PlaceRequest{source=" + this.zza.toString() + ", placeId=" + this.zzb + "}";
    }

    @Override // com.google.android.libraries.places.internal.zzin
    public final CancellationTokenSource zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzil
    public final String zzb() {
        return this.zzb;
    }
}
