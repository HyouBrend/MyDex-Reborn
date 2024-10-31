package com.google.android.libraries.places.internal;

import android.content.Context;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.TransportRuntime;
import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzhm {
    private final Transport zza;

    public zzhm(Context context) {
        TransportRuntime.initialize(context.getApplicationContext());
        this.zza = TransportRuntime.getInstance().newFactory("cct").getTransport("LE", zznw.class, new Transformer() { // from class: com.google.android.libraries.places.internal.zzhl
            @Override // com.google.android.datatransport.Transformer
            public final Object apply(Object obj) {
                zznw zznwVar = (zznw) obj;
                try {
                    int zzv = zznwVar.zzv();
                    byte[] bArr = new byte[zzv];
                    zzagb zzz = zzagb.zzz(bArr, 0, zzv);
                    zznwVar.zzK(zzz);
                    zzz.zzA();
                    return bArr;
                } catch (IOException e) {
                    throw new RuntimeException("Serializing " + zznwVar.getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
                }
            }
        });
    }

    public final void zza(zznw zznwVar) {
        this.zza.send(Event.ofData(zznwVar));
    }
}
