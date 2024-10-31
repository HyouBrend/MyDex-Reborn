package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.AddressComponent;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zza extends AddressComponent.Builder {
    private String zza;
    private String zzb;
    private List zzc;

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    public final String getShortName() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    public final AddressComponent.Builder setShortName(String str) {
        this.zzb = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AddressComponent.Builder zza(String str) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    final AddressComponent.Builder zzb(List list) {
        if (list == null) {
            throw new NullPointerException("Null types");
        }
        this.zzc = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AddressComponent.Builder
    final AddressComponent zzc() {
        List list;
        String str = this.zza;
        if (str == null || (list = this.zzc) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" name");
            }
            if (this.zzc == null) {
                sb.append(" types");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzae(str, this.zzb, list);
    }
}
