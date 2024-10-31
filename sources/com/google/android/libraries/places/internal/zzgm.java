package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzgm {
    private static final zzkk zza;

    static {
        zzkj zzkjVar = new zzkj();
        zzkjVar.zza(Place.Field.ADDRESS, "formatted_address");
        zzkjVar.zza(Place.Field.ADDRESS_COMPONENTS, "address_components");
        zzkjVar.zza(Place.Field.BUSINESS_STATUS, "business_status");
        zzkjVar.zza(Place.Field.CURBSIDE_PICKUP, "curbside_pickup");
        zzkjVar.zza(Place.Field.CURRENT_OPENING_HOURS, "current_opening_hours");
        zzkjVar.zza(Place.Field.DELIVERY, "delivery");
        zzkjVar.zza(Place.Field.DINE_IN, "dine_in");
        zzkjVar.zza(Place.Field.EDITORIAL_SUMMARY, "editorial_summary");
        zzkjVar.zza(Place.Field.ICON_BACKGROUND_COLOR, "icon_background_color");
        zzkjVar.zza(Place.Field.ICON_URL, "icon_mask_base_uri");
        zzkjVar.zza(Place.Field.ID, "place_id");
        zzkjVar.zza(Place.Field.LAT_LNG, "geometry/location");
        zzkjVar.zza(Place.Field.NAME, "name");
        zzkjVar.zza(Place.Field.OPENING_HOURS, "opening_hours");
        zzkjVar.zza(Place.Field.PHONE_NUMBER, "international_phone_number");
        zzkjVar.zza(Place.Field.PHOTO_METADATAS, "photos");
        zzkjVar.zza(Place.Field.PLUS_CODE, PlaceTypes.PLUS_CODE);
        zzkjVar.zza(Place.Field.PRICE_LEVEL, "price_level");
        zzkjVar.zza(Place.Field.RATING, "rating");
        zzkjVar.zza(Place.Field.RESERVABLE, "reservable");
        zzkjVar.zza(Place.Field.SECONDARY_OPENING_HOURS, "secondary_opening_hours");
        zzkjVar.zza(Place.Field.SERVES_BEER, "serves_beer");
        zzkjVar.zza(Place.Field.SERVES_BREAKFAST, "serves_breakfast");
        zzkjVar.zza(Place.Field.SERVES_BRUNCH, "serves_brunch");
        zzkjVar.zza(Place.Field.SERVES_DINNER, "serves_dinner");
        zzkjVar.zza(Place.Field.SERVES_LUNCH, "serves_lunch");
        zzkjVar.zza(Place.Field.SERVES_VEGETARIAN_FOOD, "serves_vegetarian_food");
        zzkjVar.zza(Place.Field.SERVES_WINE, "serves_wine");
        zzkjVar.zza(Place.Field.TAKEOUT, "takeout");
        zzkjVar.zza(Place.Field.TYPES, "types");
        zzkjVar.zza(Place.Field.USER_RATINGS_TOTAL, "user_ratings_total");
        zzkjVar.zza(Place.Field.UTC_OFFSET, "utc_offset");
        zzkjVar.zza(Place.Field.VIEWPORT, "geometry/viewport");
        zzkjVar.zza(Place.Field.WEBSITE_URI, "website");
        zzkjVar.zza(Place.Field.WHEELCHAIR_ACCESSIBLE_ENTRANCE, "wheelchair_accessible_entrance");
        zza = zzkjVar.zzb();
    }

    public static String zza(List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) zza.get((Place.Field) it.next());
            if (!TextUtils.isEmpty(str)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static List zzb(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) zza.get((Place.Field) it.next());
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
