package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhu extends zzib {
    private String zza;
    private zzkh zzb;
    private Place zzc;
    private AutocompletePrediction zzd;
    private Status zze;
    private int zzf;

    @Override // com.google.android.libraries.places.internal.zzib
    public final zzib zza(Place place) {
        this.zzc = place;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzib
    public final zzib zzb(AutocompletePrediction autocompletePrediction) {
        this.zzd = autocompletePrediction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzib
    public final zzib zzc(List list) {
        this.zzb = zzkh.zzj(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzib
    public final zzib zzd(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzib
    public final zzib zze(Status status) {
        this.zze = status;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzib
    public final zzic zzf() {
        int i = this.zzf;
        if (i != 0) {
            return new zzhw(i, this.zza, this.zzb, this.zzc, this.zzd, this.zze, null);
        }
        throw new IllegalStateException("Missing required properties: type");
    }

    public final zzib zzg(int i) {
        this.zzf = i;
        return this;
    }
}
