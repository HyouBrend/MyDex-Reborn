package com.google.android.libraries.places.widget;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzhk;
import com.google.android.libraries.places.internal.zzhy;
import com.google.android.libraries.places.internal.zzhz;
import com.google.android.libraries.places.internal.zzia;
import com.google.android.libraries.places.internal.zzid;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class Autocomplete {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static class IntentBuilder {
        private final zzhz zza;

        public IntentBuilder(zzia zziaVar) {
            this.zza = zziaVar.zzg();
        }

        public IntentBuilder(AutocompleteActivityMode autocompleteActivityMode, List<Place.Field> list) {
            this.zza = zzia.zzn(autocompleteActivityMode, list, zzhy.INTENT);
        }

        public Intent build(Context context) {
            try {
                Intent intent = new Intent(context, (Class<?>) AutocompleteActivity.class);
                zzhz zzhzVar = this.zza;
                Resources.Theme theme = context.getTheme();
                TypedValue typedValue = new TypedValue();
                if (theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)) {
                    zzhzVar.zzi(typedValue.data);
                }
                TypedValue typedValue2 = new TypedValue();
                if (theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true)) {
                    zzhzVar.zzj(typedValue2.data);
                }
                intent.putExtra("places/AutocompleteOptions", this.zza.zzm());
                return intent;
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }

        public IntentBuilder setCountries(List<String> list) {
            this.zza.zza(list);
            return this;
        }

        @Deprecated
        public IntentBuilder setCountry(String str) {
            this.zza.zzn(str);
            return this;
        }

        public IntentBuilder setHint(String str) {
            this.zza.zzb(str);
            return this;
        }

        public IntentBuilder setInitialQuery(String str) {
            this.zza.zzc(str);
            return this;
        }

        public IntentBuilder setLocationBias(LocationBias locationBias) {
            this.zza.zzd(locationBias);
            return this;
        }

        public IntentBuilder setLocationRestriction(LocationRestriction locationRestriction) {
            this.zza.zze(locationRestriction);
            return this;
        }

        @Deprecated
        public IntentBuilder setTypeFilter(TypeFilter typeFilter) {
            this.zza.zzk(typeFilter);
            return this;
        }

        public IntentBuilder setTypesFilter(List<String> list) {
            this.zza.zzl(list);
            return this;
        }

        public final IntentBuilder zza(zzhy zzhyVar) {
            this.zza.zzg(zzhyVar);
            return this;
        }
    }

    private Autocomplete() {
    }

    public static Place getPlaceFromIntent(Intent intent) {
        return zzid.zzb(intent);
    }

    public static Status getStatusFromIntent(Intent intent) {
        return zzid.zza(intent);
    }
}
