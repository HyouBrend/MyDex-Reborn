package com.google.android.libraries.places.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzmt extends zzmi {
    private static final Set zza;
    private static final zzma zzb;
    private static final zzmq zzc;
    private final String zzd;
    private final Level zze;
    private final Set zzf;
    private final zzma zzg;

    static {
        Set unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(zzlj.zza, zzlo.zza)));
        zza = unmodifiableSet;
        zzb = zzmd.zza(unmodifiableSet).zzd();
        zzc = new zzmq();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzmt(String str, String str2, boolean z, boolean z2, Level level, Set set, zzma zzmaVar, zzms zzmsVar) {
        super(str2);
        if (str2.length() > 23) {
            int i = -1;
            for (int length = str2.length() - 1; length >= 0; length--) {
                char charAt = str2.charAt(length);
                if (charAt == '.' || charAt == '$') {
                    i = length;
                    break;
                }
            }
            str2 = str2.substring(i + 1);
        }
        String concat = "".concat(String.valueOf(str2));
        this.zzd = concat.substring(0, Math.min(concat.length(), 23));
        this.zze = level;
        this.zzf = set;
        this.zzg = zzmaVar;
    }
}
