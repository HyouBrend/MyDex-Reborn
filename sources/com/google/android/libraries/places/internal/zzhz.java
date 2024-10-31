package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzhz {
    public abstract zzhz zza(List list);

    public abstract zzhz zzb(String str);

    public abstract zzhz zzc(String str);

    public abstract zzhz zzd(LocationBias locationBias);

    public abstract zzhz zze(LocationRestriction locationRestriction);

    public abstract zzhz zzf(AutocompleteActivityMode autocompleteActivityMode);

    public abstract zzhz zzg(zzhy zzhyVar);

    public abstract zzhz zzh(List list);

    public abstract zzhz zzi(int i);

    public abstract zzhz zzj(int i);

    @Deprecated
    public abstract zzhz zzk(TypeFilter typeFilter);

    public abstract zzhz zzl(List list);

    public abstract zzia zzm();

    @Deprecated
    public final zzhz zzn(String str) {
        return zza(str == null ? zzkh.zzl() : zzkh.zzm(str));
    }
}
