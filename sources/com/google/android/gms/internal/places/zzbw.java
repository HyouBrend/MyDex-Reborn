package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class zzbw extends zzbu {
    private static final Class<?> zzkd = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbw() {
        super();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.places.zzbu
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzdy.zzp(obj, j);
        if (list instanceof zzbr) {
            unmodifiableList = ((zzbr) list).zzbz();
        } else {
            if (zzkd.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzcw) && (list instanceof zzbh)) {
                zzbh zzbhVar = (zzbh) list;
                if (zzbhVar.zzaa()) {
                    zzbhVar.zzab();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzdy.zzb(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.places.zzbu
    public final <E> void zzb(Object obj, Object obj2, long j) {
        zzbs zzbsVar;
        List zzd = zzd(obj2, j);
        int size = zzd.size();
        List zzd2 = zzd(obj, j);
        if (zzd2.isEmpty()) {
            if (zzd2 instanceof zzbr) {
                zzd2 = new zzbs(size);
            } else if ((zzd2 instanceof zzcw) && (zzd2 instanceof zzbh)) {
                zzd2 = ((zzbh) zzd2).zzh(size);
            } else {
                zzd2 = new ArrayList(size);
            }
            zzdy.zzb(obj, j, zzd2);
        } else {
            if (zzkd.isAssignableFrom(zzd2.getClass())) {
                ArrayList arrayList = new ArrayList(zzd2.size() + size);
                arrayList.addAll(zzd2);
                zzdy.zzb(obj, j, arrayList);
                zzbsVar = arrayList;
            } else if (zzd2 instanceof zzdt) {
                zzbs zzbsVar2 = new zzbs(zzd2.size() + size);
                zzbsVar2.addAll((zzdt) zzd2);
                zzdy.zzb(obj, j, zzbsVar2);
                zzbsVar = zzbsVar2;
            } else if ((zzd2 instanceof zzcw) && (zzd2 instanceof zzbh)) {
                zzbh zzbhVar = (zzbh) zzd2;
                if (!zzbhVar.zzaa()) {
                    zzd2 = zzbhVar.zzh(zzd2.size() + size);
                    zzdy.zzb(obj, j, zzd2);
                }
            }
            zzd2 = zzbsVar;
        }
        int size2 = zzd2.size();
        int size3 = zzd.size();
        if (size2 > 0 && size3 > 0) {
            zzd2.addAll(zzd);
        }
        if (size2 > 0) {
            zzd = zzd2;
        }
        zzdy.zzb(obj, j, zzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzdy.zzp(obj, j);
    }
}
