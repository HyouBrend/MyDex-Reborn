package com.google.android.gms.internal.places;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdc {
    private static final Class<?> zzlv = zzdc();
    private static final zzds<?, ?> zzlw = zzf(false);
    private static final zzds<?, ?> zzlx = zzf(true);
    private static final zzds<?, ?> zzly = new zzdu();

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzbc.class.isAssignableFrom(cls) && (cls2 = zzlv) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzb(int i, List<Double> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzh(i, list, z);
    }

    public static void zzc(int i, List<Float> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzg(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zze(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzo(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzf(i, list, z);
    }

    public static void zzh(int i, List<Long> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzm(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzb(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzk(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzn(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzc(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzl(i, list, z);
    }

    public static void zzn(int i, List<Integer> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzi(i, list, z);
    }

    public static void zzo(int i, List<Boolean> list, zzel zzelVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzj(i, list, z);
    }

    public static void zzb(int i, List<String> list, zzel zzelVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzb(i, list);
    }

    public static void zzc(int i, List<zzw> list, zzel zzelVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzc(i, list);
    }

    public static void zzb(int i, List<?> list, zzel zzelVar, zzda zzdaVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzb(i, list, zzdaVar);
    }

    public static void zzc(int i, List<?> list, zzel zzelVar, zzda zzdaVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzelVar.zzc(i, list, zzdaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzby) {
            zzby zzbyVar = (zzby) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzf(zzbyVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zze(list) + (list.size() * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzby) {
            zzby zzbyVar = (zzby) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzg(zzbyVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzby) {
            zzby zzbyVar = (zzby) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzh(zzbyVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzh(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbeVar = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzx(zzbeVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbeVar = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzs(zzbeVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzs(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbeVar = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzt(zzbeVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzt(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbeVar = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzu(zzbeVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzaj.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzaj.zzr(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List<?> list) {
        return list.size() << 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaj.zzk(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(List<?> list) {
        return list.size() << 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaj.zzh(i, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(List<?> list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaj.zzd(i, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<?> list) {
        int zzk;
        int zzk2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzaj.zzr(i) * size;
        if (list instanceof zzbr) {
            zzbr zzbrVar = (zzbr) list;
            while (i2 < size) {
                Object zzae = zzbrVar.zzae(i2);
                if (zzae instanceof zzw) {
                    zzk2 = zzaj.zzc((zzw) zzae);
                } else {
                    zzk2 = zzaj.zzk((String) zzae);
                }
                zzr += zzk2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzw) {
                    zzk = zzaj.zzc((zzw) obj);
                } else {
                    zzk = zzaj.zzk((String) obj);
                }
                zzr += zzk;
                i2++;
            }
        }
        return zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, Object obj, zzda zzdaVar) {
        if (obj instanceof zzbp) {
            return zzaj.zzb(i, (zzbp) obj);
        }
        return zzaj.zzc(i, (zzck) obj, zzdaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<?> list, zzda zzdaVar) {
        int zzb;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzaj.zzr(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzbp) {
                zzb = zzaj.zzb((zzbp) obj);
            } else {
                zzb = zzaj.zzb((zzck) obj, zzdaVar);
            }
            zzr += zzb;
        }
        return zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(int i, List<zzw> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzaj.zzr(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzr += zzaj.zzc(list.get(i2));
        }
        return zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(int i, List<zzck> list, zzda zzdaVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzaj.zzd(i, list.get(i3), zzdaVar);
        }
        return i2;
    }

    public static zzds<?, ?> zzcz() {
        return zzlw;
    }

    public static zzds<?, ?> zzda() {
        return zzlx;
    }

    public static zzds<?, ?> zzdb() {
        return zzly;
    }

    private static zzds<?, ?> zzf(boolean z) {
        try {
            Class<?> zzdd = zzdd();
            if (zzdd == null) {
                return null;
            }
            return (zzds) zzdd.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdc() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdd() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zzb(zzcd zzcdVar, T t, T t2, long j) {
        zzdy.zzb(t, j, zzcdVar.zzc(zzdy.zzp(t, j), zzdy.zzp(t2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzax<FT>> void zzb(zzar<FT> zzarVar, T t, T t2) {
        zzav<FT> zzb = zzarVar.zzb(t2);
        if (zzb.zzfj.isEmpty()) {
            return;
        }
        zzarVar.zzc(t).zzb(zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zzb(zzds<UT, UB> zzdsVar, T t, T t2) {
        zzdsVar.zzf(t, zzdsVar.zzh(zzdsVar.zzr(t), zzdsVar.zzr(t2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zzb(int i, List<Integer> list, zzbf zzbfVar, UB ub, zzds<UT, UB> zzdsVar) {
        if (zzbfVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzbfVar.zzad(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zzb(i, intValue, ub, zzdsVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzbfVar.zzad(intValue2)) {
                    ub = (UB) zzb(i, intValue2, ub, zzdsVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    private static <UT, UB> UB zzb(int i, int i2, UB ub, zzds<UT, UB> zzdsVar) {
        if (ub == null) {
            ub = zzdsVar.zzdk();
        }
        zzdsVar.zzb((zzds<UT, UB>) ub, i, i2);
        return ub;
    }
}
