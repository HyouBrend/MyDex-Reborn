package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhg extends zzhi {
    private final String zza;
    private final int zzb;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhg(String str, int i, int i2, zzhf zzhfVar) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzhi) {
            zzhi zzhiVar = (zzhi) obj;
            if (this.zza.equals(zzhiVar.zzb()) && this.zzb == zzhiVar.zza() && this.zzc == zzhiVar.zzc()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        return "ClientProfile{packageName=" + this.zza + ", versionCode=" + this.zzb + ", requestSource=" + (this.zzc != 1 ? "AUTOCOMPLETE_WIDGET" : "PROGRAMMATIC_API") + "}";
    }

    @Override // com.google.android.libraries.places.internal.zzhi
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzhi
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzhi
    public final int zzc() {
        return this.zzc;
    }
}
