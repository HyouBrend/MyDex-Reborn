package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class zzap {
    private static volatile boolean zzez = false;
    private static volatile zzap zzfb;
    private final Map<zzb, zzbc.zzf<?, ?>> zzfd;
    private static final Class<?> zzfa = zzan();
    static final zzap zzfc = new zzap(true);

    private static Class<?> zzan() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    static final class zzb {
        private final int number;
        private final Object object;

        zzb(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzbVar = (zzb) obj;
            return this.object == zzbVar.object && this.number == zzbVar.number;
        }
    }

    public static zzap zzao() {
        zzap zzapVar = zzfb;
        if (zzapVar == null) {
            synchronized (zzap.class) {
                zzapVar = zzfb;
                if (zzapVar == null) {
                    zzapVar = zzaq.zzaq();
                    zzfb = zzapVar;
                }
            }
        }
        return zzapVar;
    }

    public final <ContainingType extends zzck> zzbc.zzf<ContainingType, ?> zzb(ContainingType containingtype, int i) {
        return (zzbc.zzf) this.zzfd.get(new zzb(containingtype, i));
    }

    zzap() {
        this.zzfd = new HashMap();
    }

    private zzap(boolean z) {
        this.zzfd = Collections.emptyMap();
    }
}
