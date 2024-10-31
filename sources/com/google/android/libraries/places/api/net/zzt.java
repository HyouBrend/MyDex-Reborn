package com.google.android.libraries.places.api.net;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzt extends IsOpenResponse {
    private final Boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(Boolean bool) {
        this.zza = bool;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IsOpenResponse)) {
            return false;
        }
        IsOpenResponse isOpenResponse = (IsOpenResponse) obj;
        Boolean bool = this.zza;
        return bool == null ? isOpenResponse.isOpen() == null : bool.equals(isOpenResponse.isOpen());
    }

    public final int hashCode() {
        Boolean bool = this.zza;
        return (bool == null ? 0 : bool.hashCode()) ^ 1000003;
    }

    @Override // com.google.android.libraries.places.api.net.IsOpenResponse
    public final Boolean isOpen() {
        return this.zza;
    }

    public final String toString() {
        return "IsOpenResponse{isOpen=" + this.zza + "}";
    }
}
