package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzahg extends zzahk {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzahg() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzahg(zzahf zzahfVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzahk
    public final void zza(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzaji.zzf(obj, j);
        if (list instanceof zzahe) {
            unmodifiableList = ((zzahe) list).zzd();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if (!(list instanceof zzaid) || !(list instanceof zzagw)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else {
                zzagw zzagwVar = (zzagw) list;
                if (zzagwVar.zzc()) {
                    zzagwVar.zzb();
                    return;
                }
                return;
            }
        }
        zzaji.zzs(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.libraries.places.internal.zzahk
    public final void zzb(Object obj, Object obj2, long j) {
        zzahd zzahdVar;
        List list = (List) zzaji.zzf(obj2, j);
        int size = list.size();
        List list2 = (List) zzaji.zzf(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzahe) {
                list2 = new zzahd(size);
            } else if (!(list2 instanceof zzaid) || !(list2 instanceof zzagw)) {
                list2 = new ArrayList(size);
            } else {
                list2 = ((zzagw) list2).zzf(size);
            }
            zzaji.zzs(obj, j, list2);
        } else {
            if (zza.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size);
                arrayList.addAll(list2);
                zzaji.zzs(obj, j, arrayList);
                zzahdVar = arrayList;
            } else if (list2 instanceof zzajd) {
                zzahd zzahdVar2 = new zzahd(list2.size() + size);
                zzahdVar2.addAll(zzahdVar2.size(), (zzajd) list2);
                zzaji.zzs(obj, j, zzahdVar2);
                zzahdVar = zzahdVar2;
            } else if ((list2 instanceof zzaid) && (list2 instanceof zzagw)) {
                zzagw zzagwVar = (zzagw) list2;
                if (!zzagwVar.zzc()) {
                    list2 = zzagwVar.zzf(list2.size() + size);
                    zzaji.zzs(obj, j, list2);
                }
            }
            list2 = zzahdVar;
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        zzaji.zzs(obj, j, list);
    }
}
