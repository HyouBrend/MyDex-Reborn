package com.google.android.libraries.places.api.model;

import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzb extends AddressComponent {
    private final String zza;
    private final String zzb;
    private final List zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(String str, String str2, List list) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.zza = str;
        this.zzb = str2;
        if (list != null) {
            this.zzc = list;
            return;
        }
        throw new NullPointerException("Null types");
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddressComponent) {
            AddressComponent addressComponent = (AddressComponent) obj;
            if (this.zza.equals(addressComponent.getName()) && ((str = this.zzb) != null ? str.equals(addressComponent.getShortName()) : addressComponent.getShortName() == null) && this.zzc.equals(addressComponent.getTypes())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent
    public final String getName() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent
    public final String getShortName() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent
    public final List<String> getTypes() {
        return this.zzc;
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode() ^ 1000003;
        String str = this.zzb;
        return (((hashCode * 1000003) ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.zzc.hashCode();
    }

    public final String toString() {
        return "AddressComponent{name=" + this.zza + ", shortName=" + this.zzb + ", types=" + this.zzc.toString() + "}";
    }
}
