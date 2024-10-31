package com.google.android.libraries.places.internal;

import java.util.Set;
import java.util.logging.Level;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzmr extends zzmi {
    private final Level zza;
    private final Set zzb;
    private final zzma zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzmr(String str, @NullableDecl String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        super(str2);
        Set set;
        zzma zzmaVar;
        Level level = Level.ALL;
        set = zzmt.zza;
        zzmaVar = zzmt.zzb;
        this.zza = level;
        this.zzb = set;
        this.zzc = zzmaVar;
    }
}
