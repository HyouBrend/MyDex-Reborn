package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzhc;
import com.google.android.libraries.places.internal.zzjp;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class IsOpenRequest implements zzhc {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public IsOpenRequest build() {
            IsOpenRequest zza = zza();
            Place place = zza.getPlace();
            if (place != null) {
                zzjp.zze(place.getId() != null, "Place must have a valid place id.");
            }
            return zza;
        }

        public abstract CancellationToken getCancellationToken();

        public abstract Place getPlace();

        public abstract String getPlaceId();

        public abstract long getUtcTimeMillis();

        public abstract Builder setCancellationToken(CancellationToken cancellationToken);

        public abstract Builder setPlace(Place place);

        public abstract Builder setPlaceId(String str);

        public abstract Builder setUtcTimeMillis(long j);

        abstract IsOpenRequest zza();
    }

    public static Builder builder(Place place) {
        zzq zzqVar = new zzq();
        zzqVar.setPlace(place);
        zzqVar.setUtcTimeMillis(System.currentTimeMillis());
        return zzqVar;
    }

    public static IsOpenRequest newInstance(Place place) {
        return builder(place).build();
    }

    @Override // com.google.android.libraries.places.internal.zzhc
    public abstract CancellationToken getCancellationToken();

    public abstract Place getPlace();

    public abstract String getPlaceId();

    public abstract long getUtcTimeMillis();

    public static IsOpenRequest newInstance(Place place, long j) {
        return builder(place, j).build();
    }

    public static Builder builder(Place place, long j) {
        zzq zzqVar = new zzq();
        zzqVar.setPlace(place);
        zzqVar.setUtcTimeMillis(j);
        return zzqVar;
    }

    public static IsOpenRequest newInstance(String str) {
        return builder(str).build();
    }

    public static Builder builder(String str) {
        zzq zzqVar = new zzq();
        zzqVar.setPlaceId(str);
        zzqVar.setUtcTimeMillis(System.currentTimeMillis());
        return zzqVar;
    }

    public static IsOpenRequest newInstance(String str, long j) {
        return builder(str, j).build();
    }

    public static Builder builder(String str, long j) {
        zzq zzqVar = new zzq();
        zzqVar.setPlaceId(str);
        zzqVar.setUtcTimeMillis(j);
        return zzqVar;
    }
}
