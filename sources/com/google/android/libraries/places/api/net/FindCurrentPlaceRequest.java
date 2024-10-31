package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzhc;
import com.google.android.libraries.places.internal.zzkh;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class FindCurrentPlaceRequest implements zzhc {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public FindCurrentPlaceRequest build() {
            zza(zzkh.zzj(zzb().getPlaceFields()));
            return zzb();
        }

        public abstract CancellationToken getCancellationToken();

        public abstract Builder setCancellationToken(CancellationToken cancellationToken);

        abstract Builder zza(List list);

        abstract FindCurrentPlaceRequest zzb();
    }

    public static Builder builder(List<Place.Field> list) {
        zzm zzmVar = new zzm();
        zzmVar.zza(list);
        return zzmVar;
    }

    public static FindCurrentPlaceRequest newInstance(List<Place.Field> list) {
        return builder(list).build();
    }

    @Override // com.google.android.libraries.places.internal.zzhc
    public abstract CancellationToken getCancellationToken();

    public abstract List<Place.Field> getPlaceFields();
}
