package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import com.google.android.libraries.places.internal.zzjp;
import com.google.android.libraries.places.internal.zzkt;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class LocalTime implements Parcelable, Comparable<LocalTime> {
    public static LocalTime newInstance(int i, int i2) {
        try {
            zzk zzkVar = new zzk();
            zzkVar.zza(i);
            zzkVar.zzb(i2);
            LocalTime zzc = zzkVar.zzc();
            int hours = zzc.getHours();
            zzjp.zzl(zzkt.zzb(0, 23).zzd(Integer.valueOf(hours)), "Hours must not be out-of-range: 0 to 23, but was: %s.", hours);
            int minutes = zzc.getMinutes();
            zzjp.zzl(zzkt.zzb(0, 59).zzd(Integer.valueOf(minutes)), "Minutes must not be out-of-range: 0 to 59, but was: %s.", minutes);
            return zzc;
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalTime localTime) {
        int hours;
        int hours2;
        zzjp.zzc(localTime, "compare must not be null.");
        if (this == localTime) {
            return 0;
        }
        if (getHours() == localTime.getHours()) {
            hours = getMinutes();
            hours2 = localTime.getMinutes();
        } else {
            hours = getHours();
            hours2 = localTime.getHours();
        }
        return hours - hours2;
    }

    public abstract int getHours();

    public abstract int getMinutes();
}
