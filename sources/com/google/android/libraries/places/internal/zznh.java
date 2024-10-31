package com.google.android.libraries.places.internal;

import java.util.Collections;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zznh {
    private static final Comparator zza = new zzmz();
    private static final Comparator zzb = new zzna();
    private static final zznh zzc = new zznh(new zznf(Collections.emptyList()));
    private final zznf zzd;

    private zznh(zznf zznfVar) {
        this.zzd = zznfVar;
    }

    public static zznh zza() {
        return zzc;
    }

    public final boolean equals(@NullableDecl Object obj) {
        return (obj instanceof zznh) && ((zznh) obj).zzd.equals(this.zzd);
    }

    public final int hashCode() {
        return ~this.zzd.hashCode();
    }

    public final String toString() {
        return this.zzd.toString();
    }
}
