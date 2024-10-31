package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.SpannableString;
import android.text.style.CharacterStyle;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzkh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class AutocompletePrediction implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public AutocompletePrediction build() {
            AutocompletePrediction zze = zze();
            setPlaceTypes(zzkh.zzj(zze.getPlaceTypes()));
            List zzd = zze.zzd();
            if (zzd != null) {
                zza(zzkh.zzj(zzd));
            }
            List zze2 = zze.zze();
            if (zze2 != null) {
                zzc(zzkh.zzj(zze2));
            }
            List zzf = zze.zzf();
            if (zzf != null) {
                zzd(zzkh.zzj(zzf));
            }
            return zze();
        }

        public abstract Integer getDistanceMeters();

        public abstract String getFullText();

        public abstract List<Place.Type> getPlaceTypes();

        public abstract String getPrimaryText();

        public abstract String getSecondaryText();

        public abstract Builder setDistanceMeters(Integer num);

        public abstract Builder setFullText(String str);

        public abstract Builder setPlaceTypes(List<Place.Type> list);

        public abstract Builder setPrimaryText(String str);

        public abstract Builder setSecondaryText(String str);

        public abstract Builder zza(List list);

        public abstract Builder zzc(List list);

        public abstract Builder zzd(List list);

        abstract AutocompletePrediction zze();
    }

    public static Builder builder(String str) {
        zzd zzdVar = new zzd();
        zzdVar.zzb(str);
        zzdVar.setPlaceTypes(new ArrayList());
        zzdVar.setFullText("");
        zzdVar.setPrimaryText("");
        zzdVar.setSecondaryText("");
        return zzdVar;
    }

    private static final SpannableString zzg(String str, List list, CharacterStyle characterStyle) {
        SpannableString spannableString = new SpannableString(str);
        if (str.length() != 0 && characterStyle != null && list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzbk zzbkVar = (zzbk) it.next();
                spannableString.setSpan(CharacterStyle.wrap(characterStyle), zzbkVar.zzb(), zzbkVar.zzb() + zzbkVar.zza(), 0);
            }
        }
        return spannableString;
    }

    public abstract Integer getDistanceMeters();

    public SpannableString getFullText(CharacterStyle characterStyle) {
        return zzg(zza(), zzd(), characterStyle);
    }

    public abstract String getPlaceId();

    public abstract List<Place.Type> getPlaceTypes();

    public SpannableString getPrimaryText(CharacterStyle characterStyle) {
        return zzg(zzb(), zze(), characterStyle);
    }

    public SpannableString getSecondaryText(CharacterStyle characterStyle) {
        return zzg(zzc(), zzf(), characterStyle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String zzc();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List zze();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List zzf();
}
