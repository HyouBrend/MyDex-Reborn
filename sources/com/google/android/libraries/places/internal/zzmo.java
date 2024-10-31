package com.google.android.libraries.places.internal;

import android.os.Build;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.text.Typography;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzmo extends zzmi {
    private static final AtomicReference zza = new AtomicReference();
    private static final AtomicLong zzb = new AtomicLong();
    private static final ConcurrentLinkedQueue zzc = new ConcurrentLinkedQueue();
    private volatile zzlp zzd;

    private zzmo(String str) {
        super(str);
        boolean z = true;
        boolean z2 = Build.FINGERPRINT == null || "robolectric".equals(Build.FINGERPRINT);
        boolean z3 = "goldfish".equals(Build.HARDWARE) || "ranchu".equals(Build.HARDWARE);
        if (!"eng".equals(Build.TYPE) && !"userdebug".equals(Build.TYPE)) {
            z = false;
        }
        if (z2 || z3) {
            this.zzd = new zzmj().zza(zza());
        } else if (z) {
            this.zzd = new zzmq().zzb(false).zza(zza());
        } else {
            this.zzd = null;
        }
    }

    public static zzlp zzb(String str) {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((zzmk) atomicReference.get()).zza(str);
        }
        zzmo zzmoVar = new zzmo(str.replace(Typography.dollar, '.'));
        zzmm.zza.offer(zzmoVar);
        if (atomicReference.get() != null) {
            while (true) {
                zzmo zzmoVar2 = (zzmo) zzmm.zza.poll();
                if (zzmoVar2 == null) {
                    break;
                }
                zzmoVar2.zzd = ((zzmk) zza.get()).zza(zzmoVar2.zza());
            }
            if (((zzmn) zzc.poll()) != null) {
                zzb.getAndDecrement();
                throw null;
            }
        }
        return zzmoVar;
    }
}
