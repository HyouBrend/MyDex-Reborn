package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzaij {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzaiy zzc;
    private static final zzaiy zzd;

    static {
        Class<?> cls;
        Class<?> cls2;
        zzaiy zzaiyVar = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzaiyVar = (zzaiy) cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzaiyVar;
        zzd = new zzaja();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzA(zzaiy zzaiyVar, Object obj, Object obj2) {
        zzaiyVar.zzf(obj, zzaiyVar.zzd(zzaiyVar.zzc(obj), zzaiyVar.zzc(obj2)));
    }

    public static void zzB(Class cls) {
        Class cls2;
        if (!zzago.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzC(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzc(i, list, z);
    }

    public static void zzD(int i, List list, zzajp zzajpVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zze(i, list);
    }

    public static void zzE(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzg(i, list, z);
    }

    public static void zzF(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzi(i, list, z);
    }

    public static void zzG(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzk(i, list, z);
    }

    public static void zzH(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzm(i, list, z);
    }

    public static void zzI(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzo(i, list, z);
    }

    public static void zzJ(int i, List list, zzajp zzajpVar, zzaih zzaihVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzagc) zzajpVar).zzp(i, list.get(i2), zzaihVar);
        }
    }

    public static void zzK(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzr(i, list, z);
    }

    public static void zzL(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzt(i, list, z);
    }

    public static void zzM(int i, List list, zzajp zzajpVar, zzaih zzaihVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzagc) zzajpVar).zzu(i, list.get(i2), zzaihVar);
        }
    }

    public static void zzN(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzw(i, list, z);
    }

    public static void zzO(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzy(i, list, z);
    }

    public static void zzP(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzA(i, list, z);
    }

    public static void zzQ(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzC(i, list, z);
    }

    public static void zzR(int i, List list, zzajp zzajpVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzE(i, list);
    }

    public static void zzS(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzG(i, list, z);
    }

    public static void zzT(int i, List list, zzajp zzajpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajpVar.zzI(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzU(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzagb.zzx(i << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzx = size * zzagb.zzx(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int zzd2 = ((zzaft) list.get(i2)).zzd();
            zzx += zzagb.zzx(zzd2) + zzd2;
        }
        return zzx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzagp) {
            zzagp zzagpVar = (zzagp) list;
            i = 0;
            while (i2 < size) {
                i += zzagb.zzu(zzagpVar.zzd(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzagb.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzagb.zzx(i << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzagb.zzx(i << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int i, List list, zzaih zzaihVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzagb.zzt(i, (zzahw) list.get(i3), zzaihVar);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzagp) {
            zzagp zzagpVar = (zzagp) list;
            i = 0;
            while (i2 < size) {
                i += zzagb.zzu(zzagpVar.zzd(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzagb.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzahl) {
            zzahl zzahlVar = (zzahl) list;
            i = 0;
            while (i2 < size) {
                i += zzagb.zzy(zzahlVar.zzd(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzagb.zzy(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(int i, Object obj, zzaih zzaihVar) {
        if (obj instanceof zzahc) {
            int i2 = zzagb.zzb;
            int zza2 = ((zzahc) obj).zza();
            return zzagb.zzx(i << 3) + zzagb.zzx(zza2) + zza2;
        }
        return zzagb.zzx(i << 3) + zzagb.zzv((zzahw) obj, zzaihVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i, List list, zzaih zzaihVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzx = zzagb.zzx(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzahc) {
                int zza2 = ((zzahc) obj).zza();
                zzx += zzagb.zzx(zza2) + zza2;
            } else {
                zzx += zzagb.zzv((zzahw) obj, zzaihVar);
            }
        }
        return zzx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzagp) {
            zzagp zzagpVar = (zzagp) list;
            i = 0;
            while (i2 < size) {
                int zzd2 = zzagpVar.zzd(i2);
                i += zzagb.zzx((zzd2 >> 31) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzagb.zzx((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzahl) {
            zzahl zzahlVar = (zzahl) list;
            i = 0;
            while (i2 < size) {
                long zzd2 = zzahlVar.zzd(i2);
                i += zzagb.zzy((zzd2 >> 63) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzagb.zzy((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        boolean z = list instanceof zzahe;
        int zzx = zzagb.zzx(i << 3) * size;
        if (z) {
            zzahe zzaheVar = (zzahe) list;
            while (i2 < size) {
                Object zze = zzaheVar.zze(i2);
                if (zze instanceof zzaft) {
                    int zzd2 = ((zzaft) zze).zzd();
                    zzx += zzagb.zzx(zzd2) + zzd2;
                } else {
                    zzx += zzagb.zzw((String) zze);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzaft) {
                    int zzd3 = ((zzaft) obj).zzd();
                    zzx += zzagb.zzx(zzd3) + zzd3;
                } else {
                    zzx += zzagb.zzw((String) obj);
                }
                i2++;
            }
        }
        return zzx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzagp) {
            zzagp zzagpVar = (zzagp) list;
            i = 0;
            while (i2 < size) {
                i += zzagb.zzx(zzagpVar.zzd(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzagb.zzx(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzagb.zzx(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzahl) {
            zzahl zzahlVar = (zzahl) list;
            i = 0;
            while (i2 < size) {
                i += zzagb.zzy(zzahlVar.zzd(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzagb.zzy(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzaiy zzy() {
        return zzc;
    }

    public static zzaiy zzz() {
        return zzd;
    }
}
