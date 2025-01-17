package com.google.android.gms.location.places;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class zzb extends AbstractSafeParcelable {
    public abstract Set<String> getPlaceIds();

    public boolean isRestrictedToPlacesOpenNow() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> List<E> zzb(Collection<E> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList(collection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> zzb(List<E> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(list));
    }
}
