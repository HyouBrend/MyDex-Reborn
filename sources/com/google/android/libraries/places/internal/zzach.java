package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzach extends zzago implements zzahx {
    private static final zzach zzb;
    private zzagw zzd = zzago.zzB();

    static {
        zzach zzachVar = new zzach();
        zzb = zzachVar;
        zzago.zzI(zzach.class, zzachVar);
    }

    private zzach() {
    }

    public static zzacg zza() {
        return (zzacg) zzb.zzw();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzd(zzach zzachVar, Iterable iterable) {
        zzagw zzagwVar = zzachVar.zzd;
        if (!zzagwVar.zzc()) {
            zzachVar.zzd = zzago.zzC(zzagwVar);
        }
        List list = zzachVar.zzd;
        byte[] bArr = zzagx.zzd;
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
        }
        int size = list.size();
        for (Object obj : iterable) {
            if (obj != null) {
                list.add(obj);
            } else {
                String str = "Element at index " + (list.size() - size) + " is null.";
                int size2 = list.size();
                while (true) {
                    size2--;
                    if (size2 < size) {
                        break;
                    } else {
                        list.remove(size2);
                    }
                }
                throw new NullPointerException(str);
            }
        }
    }

    @Override // com.google.android.libraries.places.internal.zzago
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        }
        if (i2 == 3) {
            return new zzach();
        }
        if (i2 == 4) {
            return new zzacg(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
