package com.google.android.libraries.places.internal;

import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzia implements Parcelable {
    public static zzhz zzn(AutocompleteActivityMode autocompleteActivityMode, List list, zzhy zzhyVar) {
        zzhq zzhqVar = new zzhq();
        zzhqVar.zza(new ArrayList());
        zzhqVar.zzl(new ArrayList());
        zzhqVar.zzf(autocompleteActivityMode);
        zzhqVar.zzh(list);
        zzhqVar.zzg(zzhyVar);
        zzhqVar.zzi(0);
        zzhqVar.zzj(0);
        return zzhqVar;
    }

    public abstract int zza();

    public abstract int zzb();

    public abstract LocationBias zzc();

    public abstract LocationRestriction zzd();

    @Deprecated
    public abstract TypeFilter zze();

    public abstract zzhy zzf();

    public abstract zzhz zzg();

    public abstract AutocompleteActivityMode zzh();

    public abstract zzkh zzi();

    public abstract zzkh zzj();

    public abstract zzkh zzk();

    public abstract String zzl();

    public abstract String zzm();
}
