package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzic {
    public static zzic zzg() {
        return zzr(3).zzf();
    }

    public static zzic zzk() {
        return zzr(2).zzf();
    }

    public static zzic zzl() {
        zzib zzr = zzr(10);
        zzr.zze(new Status(16));
        return zzr.zzf();
    }

    public static zzic zzo() {
        return zzr(1).zzf();
    }

    public static zzic zzp() {
        return zzr(4).zzf();
    }

    private static zzib zzr(int i) {
        zzhu zzhuVar = new zzhu();
        zzhuVar.zzg(i);
        return zzhuVar;
    }

    public abstract Status zza();

    public abstract AutocompletePrediction zzb();

    public abstract Place zzc();

    public abstract zzkh zzd();

    public abstract String zze();

    public abstract int zzf();

    public static zzic zzh(String str) {
        str.getClass();
        zzib zzr = zzr(6);
        zzr.zzd(str);
        return zzr.zzf();
    }

    public static zzic zzj(List list) {
        list.getClass();
        zzib zzr = zzr(5);
        zzr.zzc(list);
        return zzr.zzf();
    }

    public static zzic zzm(AutocompletePrediction autocompletePrediction, Status status) {
        status.getClass();
        zzib zzr = zzr(9);
        zzr.zzb(autocompletePrediction);
        zzr.zze(status);
        return zzr.zzf();
    }

    public static zzic zzn(Place place) {
        place.getClass();
        zzib zzr = zzr(8);
        zzr.zza(place);
        return zzr.zzf();
    }

    public static zzic zzq(Status status) {
        status.getClass();
        zzib zzr = zzr(10);
        zzr.zze(status);
        return zzr.zzf();
    }

    public static zzic zzi(String str, Status status) {
        str.getClass();
        status.getClass();
        zzib zzr = zzr(7);
        zzr.zzd(str);
        zzr.zze(status);
        return zzr.zzf();
    }
}
