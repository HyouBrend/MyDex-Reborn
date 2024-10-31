package com.google.android.libraries.places.api.model;

import android.util.Log;
import com.google.android.libraries.places.api.model.Period;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzkj;
import com.google.android.libraries.places.internal.zzkk;
import com.google.android.libraries.places.internal.zzkt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzbq {
    public static final /* synthetic */ int zza = 0;
    private static final zzkk zzb;
    private static final LocalTime zzc;

    static {
        zzkj zzkjVar = new zzkj();
        zzkjVar.zza(1, DayOfWeek.SUNDAY);
        zzkjVar.zza(2, DayOfWeek.MONDAY);
        zzkjVar.zza(3, DayOfWeek.TUESDAY);
        zzkjVar.zza(4, DayOfWeek.WEDNESDAY);
        zzkjVar.zza(5, DayOfWeek.THURSDAY);
        zzkjVar.zza(6, DayOfWeek.FRIDAY);
        zzkjVar.zza(7, DayOfWeek.SATURDAY);
        zzb = zzkjVar.zzb();
        zzc = LocalTime.newInstance(23, 59);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Boolean zza(com.google.android.libraries.places.api.model.Place r15, long r16) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.api.model.zzbq.zza(com.google.android.libraries.places.api.model.Place, long):java.lang.Boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static Boolean zzb(Place place, long j) {
        Place.BusinessStatus businessStatus = place.getBusinessStatus();
        OpeningHours openingHours = place.getOpeningHours();
        Integer utcOffsetMinutes = place.getUtcOffsetMinutes();
        if (businessStatus != null && businessStatus != Place.BusinessStatus.OPERATIONAL) {
            return false;
        }
        if (openingHours == null || utcOffsetMinutes == null) {
            return null;
        }
        List<Period> periods = openingHours.getPeriods();
        if (!periods.isEmpty()) {
            if (zzf(periods)) {
                return true;
            }
            for (Period period : periods) {
                if (period.getOpen() == null || period.getClose() == null) {
                    return null;
                }
            }
            TimeZone zze = zze(utcOffsetMinutes.intValue());
            if (zze == null) {
                return null;
            }
            Calendar calendar = Calendar.getInstance(zze);
            calendar.setTimeInMillis(j);
            DayOfWeek dayOfWeek = (DayOfWeek) zzb.get(Integer.valueOf(calendar.get(7)));
            LocalTime newInstance = LocalTime.newInstance(calendar.get(11), calendar.get(12));
            List list = (List) zzd(periods).get(dayOfWeek);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((zzkt) it.next()).zzd(newInstance)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static Object zzc(Map map, Object obj, Object obj2) {
        return map.containsKey(obj) ? map.get(obj) : obj2;
    }

    private static Map zzd(List list) {
        EnumMap enumMap = new EnumMap(DayOfWeek.class);
        if (list.isEmpty()) {
            return enumMap;
        }
        Period period = (Period) list.get(0);
        int i = 0;
        while (period != null) {
            TimeOfWeek open = period.getOpen();
            TimeOfWeek close = period.getClose();
            if (open == null || close == null) {
                i++;
                period = i >= list.size() ? null : (Period) list.get(i);
            } else {
                DayOfWeek day = open.getDay();
                LocalTime time = open.getTime();
                if (open.getDay() == close.getDay()) {
                    LocalTime time2 = close.getTime();
                    List list2 = (List) zzc(enumMap, day, new ArrayList());
                    list2.add(zzkt.zzc(time, time2));
                    enumMap.put((EnumMap) day, (DayOfWeek) list2);
                    i++;
                    if (i < list.size()) {
                        period = (Period) list.get(i);
                    }
                } else {
                    LocalTime localTime = zzc;
                    List list3 = (List) zzc(enumMap, day, new ArrayList());
                    list3.add(zzkt.zzb(time, localTime));
                    enumMap.put((EnumMap) day, (DayOfWeek) list3);
                    TimeOfWeek newInstance = TimeOfWeek.newInstance(DayOfWeek.values()[(day.ordinal() + 1) % 7], LocalTime.newInstance(0, 0));
                    TimeOfWeek close2 = period.getClose();
                    Period.Builder builder = Period.builder();
                    builder.setOpen(newInstance);
                    builder.setClose(close2);
                    period = builder.build();
                }
            }
        }
        return enumMap;
    }

    private static TimeZone zze(int i) {
        String[] availableIDs = TimeZone.getAvailableIDs((int) TimeUnit.MINUTES.toMillis(i));
        if (availableIDs == null || availableIDs.length <= 0) {
            Log.w("Places", String.format("Cannot find timezone that associates with utcOffsetMinutes %d from Place object.", Integer.valueOf(i)));
            return null;
        }
        return TimeZone.getTimeZone(availableIDs[0]);
    }

    private static boolean zzf(List list) {
        if (list.size() != 1) {
            return false;
        }
        Period period = (Period) list.get(0);
        TimeOfWeek open = period.getOpen();
        return period.getClose() == null && open != null && open.getDay() == DayOfWeek.SUNDAY && open.getTime().getHours() == 0 && open.getTime().getMinutes() == 0;
    }
}
