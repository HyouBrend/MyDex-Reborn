package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzjx extends zzks implements Serializable {
    final Comparator zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjx(Comparator comparator) {
        this.zza = comparator;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.zza.compare(obj, obj2);
    }

    @Override // java.util.Comparator
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzjx) {
            return this.zza.equals(((zzjx) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString();
    }
}
