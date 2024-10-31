package com.google.android.libraries.places.api.model;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzc extends AddressComponents {
    private final List zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(List list) {
        if (list == null) {
            throw new NullPointerException("Null asList");
        }
        this.zza = list;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponents
    public final List<AddressComponent> asList() {
        return this.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddressComponents) {
            return this.zza.equals(((AddressComponents) obj).asList());
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "AddressComponents{asList=" + this.zza.toString() + "}";
    }
}
