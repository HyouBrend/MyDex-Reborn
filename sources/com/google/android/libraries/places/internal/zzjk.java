package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public class zzjk {
    private final String zza;

    private zzjk(String str) {
        this.zza = str;
    }

    public static zzjk zzb(String str) {
        return new zzjk(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CharSequence zzf(@CheckForNull Object obj) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public Appendable zza(Appendable appendable, Iterator it) throws IOException {
        if (it.hasNext()) {
            appendable.append(zzf(it.next()));
            while (it.hasNext()) {
                appendable.append(this.zza);
                appendable.append(zzf(it.next()));
            }
        }
        return appendable;
    }

    public final zzjk zzc() {
        return new zzjh(this, this);
    }

    public final String zze(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            zza(sb, it);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
