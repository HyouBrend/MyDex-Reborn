package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.DayOfWeek;
import com.google.android.libraries.places.api.model.LocalDate;
import com.google.android.libraries.places.api.model.LocalTime;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Period;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.SpecialDay;
import com.google.android.libraries.places.api.model.TimeOfWeek;
import com.google.android.libraries.places.internal.zzgj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzgg {
    private static final zzkk zza;
    private static final zzkk zzb;
    private static final zzkk zzc;

    static {
        zzkj zzkjVar = new zzkj();
        zzkjVar.zza("OPERATIONAL", Place.BusinessStatus.OPERATIONAL);
        zzkjVar.zza("CLOSED_TEMPORARILY", Place.BusinessStatus.CLOSED_TEMPORARILY);
        zzkjVar.zza("CLOSED_PERMANENTLY", Place.BusinessStatus.CLOSED_PERMANENTLY);
        zza = zzkjVar.zzb();
        zzkj zzkjVar2 = new zzkj();
        zzkjVar2.zza(PlaceTypes.ACCOUNTING, Place.Type.ACCOUNTING);
        zzkjVar2.zza(PlaceTypes.ADMINISTRATIVE_AREA_LEVEL_1, Place.Type.ADMINISTRATIVE_AREA_LEVEL_1);
        zzkjVar2.zza(PlaceTypes.ADMINISTRATIVE_AREA_LEVEL_2, Place.Type.ADMINISTRATIVE_AREA_LEVEL_2);
        zzkjVar2.zza(PlaceTypes.ADMINISTRATIVE_AREA_LEVEL_3, Place.Type.ADMINISTRATIVE_AREA_LEVEL_3);
        zzkjVar2.zza(PlaceTypes.ADMINISTRATIVE_AREA_LEVEL_4, Place.Type.ADMINISTRATIVE_AREA_LEVEL_4);
        zzkjVar2.zza(PlaceTypes.ADMINISTRATIVE_AREA_LEVEL_5, Place.Type.ADMINISTRATIVE_AREA_LEVEL_5);
        zzkjVar2.zza(PlaceTypes.AIRPORT, Place.Type.AIRPORT);
        zzkjVar2.zza(PlaceTypes.AMUSEMENT_PARK, Place.Type.AMUSEMENT_PARK);
        zzkjVar2.zza(PlaceTypes.AQUARIUM, Place.Type.AQUARIUM);
        zzkjVar2.zza(PlaceTypes.ARCHIPELAGO, Place.Type.ARCHIPELAGO);
        zzkjVar2.zza(PlaceTypes.ART_GALLERY, Place.Type.ART_GALLERY);
        zzkjVar2.zza(PlaceTypes.ATM, Place.Type.ATM);
        zzkjVar2.zza(PlaceTypes.BAKERY, Place.Type.BAKERY);
        zzkjVar2.zza(PlaceTypes.BANK, Place.Type.BANK);
        zzkjVar2.zza(PlaceTypes.BAR, Place.Type.BAR);
        zzkjVar2.zza(PlaceTypes.BEAUTY_SALON, Place.Type.BEAUTY_SALON);
        zzkjVar2.zza(PlaceTypes.BICYCLE_STORE, Place.Type.BICYCLE_STORE);
        zzkjVar2.zza(PlaceTypes.BOOK_STORE, Place.Type.BOOK_STORE);
        zzkjVar2.zza(PlaceTypes.BOWLING_ALLEY, Place.Type.BOWLING_ALLEY);
        zzkjVar2.zza(PlaceTypes.BUS_STATION, Place.Type.BUS_STATION);
        zzkjVar2.zza(PlaceTypes.CAFE, Place.Type.CAFE);
        zzkjVar2.zza(PlaceTypes.CAMPGROUND, Place.Type.CAMPGROUND);
        zzkjVar2.zza(PlaceTypes.CAR_DEALER, Place.Type.CAR_DEALER);
        zzkjVar2.zza(PlaceTypes.CAR_RENTAL, Place.Type.CAR_RENTAL);
        zzkjVar2.zza(PlaceTypes.CAR_REPAIR, Place.Type.CAR_REPAIR);
        zzkjVar2.zza(PlaceTypes.CAR_WASH, Place.Type.CAR_WASH);
        zzkjVar2.zza(PlaceTypes.CASINO, Place.Type.CASINO);
        zzkjVar2.zza(PlaceTypes.CEMETERY, Place.Type.CEMETERY);
        zzkjVar2.zza(PlaceTypes.CHURCH, Place.Type.CHURCH);
        zzkjVar2.zza(PlaceTypes.CITY_HALL, Place.Type.CITY_HALL);
        zzkjVar2.zza(PlaceTypes.CLOTHING_STORE, Place.Type.CLOTHING_STORE);
        zzkjVar2.zza(PlaceTypes.COLLOQUIAL_AREA, Place.Type.COLLOQUIAL_AREA);
        zzkjVar2.zza(PlaceTypes.CONTINENT, Place.Type.CONTINENT);
        zzkjVar2.zza(PlaceTypes.CONVENIENCE_STORE, Place.Type.CONVENIENCE_STORE);
        zzkjVar2.zza(PlaceTypes.COUNTRY, Place.Type.COUNTRY);
        zzkjVar2.zza(PlaceTypes.COURTHOUSE, Place.Type.COURTHOUSE);
        zzkjVar2.zza(PlaceTypes.DENTIST, Place.Type.DENTIST);
        zzkjVar2.zza(PlaceTypes.DEPARTMENT_STORE, Place.Type.DEPARTMENT_STORE);
        zzkjVar2.zza(PlaceTypes.DOCTOR, Place.Type.DOCTOR);
        zzkjVar2.zza(PlaceTypes.DRUGSTORE, Place.Type.DRUGSTORE);
        zzkjVar2.zza(PlaceTypes.ELECTRICIAN, Place.Type.ELECTRICIAN);
        zzkjVar2.zza(PlaceTypes.ELECTRONICS_STORE, Place.Type.ELECTRONICS_STORE);
        zzkjVar2.zza(PlaceTypes.EMBASSY, Place.Type.EMBASSY);
        zzkjVar2.zza(PlaceTypes.ESTABLISHMENT, Place.Type.ESTABLISHMENT);
        zzkjVar2.zza(PlaceTypes.FINANCE, Place.Type.FINANCE);
        zzkjVar2.zza(PlaceTypes.FIRE_STATION, Place.Type.FIRE_STATION);
        zzkjVar2.zza(PlaceTypes.FLOOR, Place.Type.FLOOR);
        zzkjVar2.zza(PlaceTypes.FLORIST, Place.Type.FLORIST);
        zzkjVar2.zza(PlaceTypes.FOOD, Place.Type.FOOD);
        zzkjVar2.zza(PlaceTypes.FUNERAL_HOME, Place.Type.FUNERAL_HOME);
        zzkjVar2.zza(PlaceTypes.FURNITURE_STORE, Place.Type.FURNITURE_STORE);
        zzkjVar2.zza(PlaceTypes.GAS_STATION, Place.Type.GAS_STATION);
        zzkjVar2.zza(PlaceTypes.GENERAL_CONTRACTOR, Place.Type.GENERAL_CONTRACTOR);
        zzkjVar2.zza(PlaceTypes.GEOCODE, Place.Type.GEOCODE);
        zzkjVar2.zza("grocery_or_supermarket", Place.Type.GROCERY_OR_SUPERMARKET);
        zzkjVar2.zza(PlaceTypes.GYM, Place.Type.GYM);
        zzkjVar2.zza(PlaceTypes.HAIR_CARE, Place.Type.HAIR_CARE);
        zzkjVar2.zza(PlaceTypes.HARDWARE_STORE, Place.Type.HARDWARE_STORE);
        zzkjVar2.zza(PlaceTypes.HEALTH, Place.Type.HEALTH);
        zzkjVar2.zza(PlaceTypes.HINDU_TEMPLE, Place.Type.HINDU_TEMPLE);
        zzkjVar2.zza(PlaceTypes.HOME_GOODS_STORE, Place.Type.HOME_GOODS_STORE);
        zzkjVar2.zza(PlaceTypes.HOSPITAL, Place.Type.HOSPITAL);
        zzkjVar2.zza(PlaceTypes.INSURANCE_AGENCY, Place.Type.INSURANCE_AGENCY);
        zzkjVar2.zza(PlaceTypes.INTERSECTION, Place.Type.INTERSECTION);
        zzkjVar2.zza(PlaceTypes.JEWELRY_STORE, Place.Type.JEWELRY_STORE);
        zzkjVar2.zza(PlaceTypes.LAUNDRY, Place.Type.LAUNDRY);
        zzkjVar2.zza(PlaceTypes.LAWYER, Place.Type.LAWYER);
        zzkjVar2.zza(PlaceTypes.LIBRARY, Place.Type.LIBRARY);
        zzkjVar2.zza(PlaceTypes.LIGHT_RAIL_STATION, Place.Type.LIGHT_RAIL_STATION);
        zzkjVar2.zza(PlaceTypes.LIQUOR_STORE, Place.Type.LIQUOR_STORE);
        zzkjVar2.zza(PlaceTypes.LOCAL_GOVERNMENT_OFFICE, Place.Type.LOCAL_GOVERNMENT_OFFICE);
        zzkjVar2.zza(PlaceTypes.LOCALITY, Place.Type.LOCALITY);
        zzkjVar2.zza(PlaceTypes.LOCKSMITH, Place.Type.LOCKSMITH);
        zzkjVar2.zza(PlaceTypes.LODGING, Place.Type.LODGING);
        zzkjVar2.zza(PlaceTypes.MEAL_DELIVERY, Place.Type.MEAL_DELIVERY);
        zzkjVar2.zza(PlaceTypes.MEAL_TAKEAWAY, Place.Type.MEAL_TAKEAWAY);
        zzkjVar2.zza(PlaceTypes.MOSQUE, Place.Type.MOSQUE);
        zzkjVar2.zza(PlaceTypes.MOVIE_RENTAL, Place.Type.MOVIE_RENTAL);
        zzkjVar2.zza(PlaceTypes.MOVIE_THEATER, Place.Type.MOVIE_THEATER);
        zzkjVar2.zza(PlaceTypes.MOVING_COMPANY, Place.Type.MOVING_COMPANY);
        zzkjVar2.zza(PlaceTypes.MUSEUM, Place.Type.MUSEUM);
        zzkjVar2.zza(PlaceTypes.NATURAL_FEATURE, Place.Type.NATURAL_FEATURE);
        zzkjVar2.zza(PlaceTypes.NEIGHBORHOOD, Place.Type.NEIGHBORHOOD);
        zzkjVar2.zza(PlaceTypes.NIGHT_CLUB, Place.Type.NIGHT_CLUB);
        zzkjVar2.zza(PlaceTypes.PAINTER, Place.Type.PAINTER);
        zzkjVar2.zza(PlaceTypes.PARK, Place.Type.PARK);
        zzkjVar2.zza(PlaceTypes.PARKING, Place.Type.PARKING);
        zzkjVar2.zza(PlaceTypes.PET_STORE, Place.Type.PET_STORE);
        zzkjVar2.zza(PlaceTypes.PHARMACY, Place.Type.PHARMACY);
        zzkjVar2.zza(PlaceTypes.PHYSIOTHERAPIST, Place.Type.PHYSIOTHERAPIST);
        zzkjVar2.zza(PlaceTypes.PLACE_OF_WORSHIP, Place.Type.PLACE_OF_WORSHIP);
        zzkjVar2.zza(PlaceTypes.PLUMBER, Place.Type.PLUMBER);
        zzkjVar2.zza(PlaceTypes.PLUS_CODE, Place.Type.PLUS_CODE);
        zzkjVar2.zza(PlaceTypes.POINT_OF_INTEREST, Place.Type.POINT_OF_INTEREST);
        zzkjVar2.zza(PlaceTypes.POLICE, Place.Type.POLICE);
        zzkjVar2.zza(PlaceTypes.POLITICAL, Place.Type.POLITICAL);
        zzkjVar2.zza(PlaceTypes.POST_BOX, Place.Type.POST_BOX);
        zzkjVar2.zza(PlaceTypes.POST_OFFICE, Place.Type.POST_OFFICE);
        zzkjVar2.zza(PlaceTypes.POSTAL_CODE_PREFIX, Place.Type.POSTAL_CODE_PREFIX);
        zzkjVar2.zza(PlaceTypes.POSTAL_CODE_SUFFIX, Place.Type.POSTAL_CODE_SUFFIX);
        zzkjVar2.zza(PlaceTypes.POSTAL_CODE, Place.Type.POSTAL_CODE);
        zzkjVar2.zza(PlaceTypes.POSTAL_TOWN, Place.Type.POSTAL_TOWN);
        zzkjVar2.zza(PlaceTypes.PREMISE, Place.Type.PREMISE);
        zzkjVar2.zza(PlaceTypes.PRIMARY_SCHOOL, Place.Type.PRIMARY_SCHOOL);
        zzkjVar2.zza(PlaceTypes.REAL_ESTATE_AGENCY, Place.Type.REAL_ESTATE_AGENCY);
        zzkjVar2.zza(PlaceTypes.RESTAURANT, Place.Type.RESTAURANT);
        zzkjVar2.zza(PlaceTypes.ROOFING_CONTRACTOR, Place.Type.ROOFING_CONTRACTOR);
        zzkjVar2.zza(PlaceTypes.ROOM, Place.Type.ROOM);
        zzkjVar2.zza(PlaceTypes.ROUTE, Place.Type.ROUTE);
        zzkjVar2.zza(PlaceTypes.RV_PARK, Place.Type.RV_PARK);
        zzkjVar2.zza(PlaceTypes.SCHOOL, Place.Type.SCHOOL);
        zzkjVar2.zza(PlaceTypes.SECONDARY_SCHOOL, Place.Type.SECONDARY_SCHOOL);
        zzkjVar2.zza(PlaceTypes.SHOE_STORE, Place.Type.SHOE_STORE);
        zzkjVar2.zza(PlaceTypes.SHOPPING_MALL, Place.Type.SHOPPING_MALL);
        zzkjVar2.zza(PlaceTypes.SPA, Place.Type.SPA);
        zzkjVar2.zza(PlaceTypes.STADIUM, Place.Type.STADIUM);
        zzkjVar2.zza(PlaceTypes.STORAGE, Place.Type.STORAGE);
        zzkjVar2.zza(PlaceTypes.STORE, Place.Type.STORE);
        zzkjVar2.zza(PlaceTypes.STREET_ADDRESS, Place.Type.STREET_ADDRESS);
        zzkjVar2.zza(PlaceTypes.STREET_NUMBER, Place.Type.STREET_NUMBER);
        zzkjVar2.zza(PlaceTypes.SUBLOCALITY_LEVEL_1, Place.Type.SUBLOCALITY_LEVEL_1);
        zzkjVar2.zza(PlaceTypes.SUBLOCALITY_LEVEL_2, Place.Type.SUBLOCALITY_LEVEL_2);
        zzkjVar2.zza(PlaceTypes.SUBLOCALITY_LEVEL_3, Place.Type.SUBLOCALITY_LEVEL_3);
        zzkjVar2.zza(PlaceTypes.SUBLOCALITY_LEVEL_4, Place.Type.SUBLOCALITY_LEVEL_4);
        zzkjVar2.zza(PlaceTypes.SUBLOCALITY_LEVEL_5, Place.Type.SUBLOCALITY_LEVEL_5);
        zzkjVar2.zza(PlaceTypes.SUBLOCALITY, Place.Type.SUBLOCALITY);
        zzkjVar2.zza(PlaceTypes.SUBPREMISE, Place.Type.SUBPREMISE);
        zzkjVar2.zza(PlaceTypes.SUBWAY_STATION, Place.Type.SUBWAY_STATION);
        zzkjVar2.zza(PlaceTypes.SUPERMARKET, Place.Type.SUPERMARKET);
        zzkjVar2.zza(PlaceTypes.SYNAGOGUE, Place.Type.SYNAGOGUE);
        zzkjVar2.zza(PlaceTypes.TAXI_STAND, Place.Type.TAXI_STAND);
        zzkjVar2.zza(PlaceTypes.TOURIST_ATTRACTION, Place.Type.TOURIST_ATTRACTION);
        zzkjVar2.zza(PlaceTypes.TOWN_SQUARE, Place.Type.TOWN_SQUARE);
        zzkjVar2.zza(PlaceTypes.TRAIN_STATION, Place.Type.TRAIN_STATION);
        zzkjVar2.zza(PlaceTypes.TRANSIT_STATION, Place.Type.TRANSIT_STATION);
        zzkjVar2.zza(PlaceTypes.TRAVEL_AGENCY, Place.Type.TRAVEL_AGENCY);
        zzkjVar2.zza(PlaceTypes.UNIVERSITY, Place.Type.UNIVERSITY);
        zzkjVar2.zza(PlaceTypes.VETERINARY_CARE, Place.Type.VETERINARY_CARE);
        zzkjVar2.zza(PlaceTypes.ZOO, Place.Type.ZOO);
        zzb = zzkjVar2.zzb();
        zzkj zzkjVar3 = new zzkj();
        zzkjVar3.zza("ACCESS", OpeningHours.HoursType.ACCESS);
        zzkjVar3.zza("BREAKFAST", OpeningHours.HoursType.BREAKFAST);
        zzkjVar3.zza("BRUNCH", OpeningHours.HoursType.BRUNCH);
        zzkjVar3.zza("DELIVERY", OpeningHours.HoursType.DELIVERY);
        zzkjVar3.zza("DINNER", OpeningHours.HoursType.DINNER);
        zzkjVar3.zza("DRIVE_THROUGH", OpeningHours.HoursType.DRIVE_THROUGH);
        zzkjVar3.zza("HAPPY_HOUR", OpeningHours.HoursType.HAPPY_HOUR);
        zzkjVar3.zza("KITCHEN", OpeningHours.HoursType.KITCHEN);
        zzkjVar3.zza("LUNCH", OpeningHours.HoursType.LUNCH);
        zzkjVar3.zza("ONLINE_SERVICE_HOURS", OpeningHours.HoursType.ONLINE_SERVICE_HOURS);
        zzkjVar3.zza("PICKUP", OpeningHours.HoursType.PICKUP);
        zzkjVar3.zza("SENIOR_HOURS", OpeningHours.HoursType.SENIOR_HOURS);
        zzkjVar3.zza("TAKEOUT", OpeningHours.HoursType.TAKEOUT);
        zzc = zzkjVar3.zzb();
    }

    static LocalDate zza(String str) {
        if (str == null) {
            return null;
        }
        try {
            return LocalDate.newInstance(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)), Integer.parseInt(str.substring(8, 10)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Unable to convert %s to LocalDate; date should be in format YYYY-MM-DD.", str), e);
        }
    }

    static Place.BooleanPlaceAttributeValue zzb(Boolean bool) {
        if (bool == null) {
            return Place.BooleanPlaceAttributeValue.UNKNOWN;
        }
        if (bool.booleanValue()) {
            return Place.BooleanPlaceAttributeValue.TRUE;
        }
        return Place.BooleanPlaceAttributeValue.FALSE;
    }

    static TimeOfWeek zzc(zzgj.zzd.zzc zzcVar) {
        DayOfWeek dayOfWeek;
        LocalDate localDate = null;
        if (zzcVar == null) {
            return null;
        }
        try {
            Integer zzb2 = zzcVar.zzb();
            zzjp.zzc(zzb2, "Unable to convert Pablo response to TimeOfWeek: The \"day\" field is missing.");
            String zzd = zzcVar.zzd();
            zzjp.zzc(zzd, "Unable to convert Pablo response to TimeOfWeek: The \"time\" field is missing.");
            boolean z = true;
            String format = String.format("Unable to convert %s to LocalTime, must be of format \"hhmm\".", zzd);
            if (zzd.length() != 4) {
                z = false;
            }
            zzjp.zze(z, format);
            try {
                LocalTime newInstance = LocalTime.newInstance(Integer.parseInt(zzd.substring(0, 2)), Integer.parseInt(zzd.substring(2, 4)));
                try {
                    localDate = zza(zzcVar.zzc());
                } catch (IllegalArgumentException unused) {
                }
                switch (zzb2.intValue()) {
                    case 0:
                        dayOfWeek = DayOfWeek.SUNDAY;
                        break;
                    case 1:
                        dayOfWeek = DayOfWeek.MONDAY;
                        break;
                    case 2:
                        dayOfWeek = DayOfWeek.TUESDAY;
                        break;
                    case 3:
                        dayOfWeek = DayOfWeek.WEDNESDAY;
                        break;
                    case 4:
                        dayOfWeek = DayOfWeek.THURSDAY;
                        break;
                    case 5:
                        dayOfWeek = DayOfWeek.FRIDAY;
                        break;
                    case 6:
                        dayOfWeek = DayOfWeek.SATURDAY;
                        break;
                    default:
                        throw new IllegalArgumentException("pabloDayOfWeek can only be an integer between 0 and 6");
                }
                TimeOfWeek.Builder builder = TimeOfWeek.builder(dayOfWeek, newInstance);
                builder.setDate(localDate);
                builder.setTruncated(Boolean.TRUE.equals(zzcVar.zza()));
                return builder.build();
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(format, e);
            }
        } catch (NullPointerException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List zzd(List list) {
        return list != null ? list : new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static List zze(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        zzlf listIterator = ((zzkh) list).listIterator(0);
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            zzkk zzkkVar = zzb;
            if (zzkkVar.containsKey(str)) {
                arrayList.add((Place.Type) zzkkVar.get(str));
            } else {
                z = true;
            }
        }
        if (z) {
            arrayList.add(Place.Type.OTHER);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.google.android.libraries.places.api.model.Place zzf(com.google.android.libraries.places.internal.zzgj r11, java.util.List r12) throws com.google.android.gms.common.api.ApiException {
        /*
            Method dump skipped, instructions count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzgg.zzf(com.google.android.libraries.places.internal.zzgj, java.util.List):com.google.android.libraries.places.api.model.Place");
    }

    private static ApiException zzg(String str) {
        return new ApiException(new Status(8, "Unexpected server error: ".concat(String.valueOf(str))));
    }

    private static LatLng zzh(zzgj.zzc.zza zzaVar) {
        if (zzaVar == null) {
            return null;
        }
        Double zza2 = zzaVar.zza();
        Double zzb2 = zzaVar.zzb();
        if (zza2 == null || zzb2 == null) {
            return null;
        }
        return new LatLng(zza2.doubleValue(), zzb2.doubleValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static OpeningHours zzi(zzgj.zzd zzdVar) {
        ArrayList arrayList;
        SpecialDay build;
        Period period;
        if (zzdVar == null) {
            return null;
        }
        OpeningHours.Builder builder = OpeningHours.builder();
        zzkh zza2 = zzdVar.zza();
        if (zza2 != null) {
            arrayList = new ArrayList();
            zzlf listIterator = zza2.listIterator(0);
            while (listIterator.hasNext()) {
                zzgj.zzd.zza zzaVar = (zzgj.zzd.zza) listIterator.next();
                if (zzaVar != null) {
                    Period.Builder builder2 = Period.builder();
                    builder2.setOpen(zzc(zzaVar.zzb()));
                    builder2.setClose(zzc(zzaVar.zza()));
                    period = builder2.build();
                } else {
                    period = null;
                }
                zzj(arrayList, period);
            }
        } else {
            arrayList = null;
        }
        builder.setPeriods(zzd(arrayList));
        builder.setWeekdayText(zzd(zzdVar.zzc()));
        builder.setHoursType((OpeningHours.HoursType) zzc.getOrDefault(zzdVar.zzd(), null));
        zzkh zzb2 = zzdVar.zzb();
        ArrayList arrayList2 = new ArrayList();
        if (zzb2 != null) {
            zzlf listIterator2 = zzb2.listIterator(0);
            while (listIterator2.hasNext()) {
                zzgj.zzd.zzb zzbVar = (zzgj.zzd.zzb) listIterator2.next();
                if (zzbVar != null) {
                    try {
                        LocalDate zza3 = zza(zzbVar.zzb());
                        zza3.getClass();
                        SpecialDay.Builder builder3 = SpecialDay.builder(zza3);
                        builder3.setExceptional(Boolean.TRUE.equals(zzbVar.zza()));
                        build = builder3.build();
                    } catch (IllegalArgumentException | NullPointerException unused) {
                    }
                    zzj(arrayList2, build);
                }
                build = null;
                zzj(arrayList2, build);
            }
        }
        builder.setSpecialDays(arrayList2);
        return builder.build();
    }

    private static boolean zzj(Collection collection, Object obj) {
        if (obj != null) {
            return collection.add(obj);
        }
        return false;
    }
}
