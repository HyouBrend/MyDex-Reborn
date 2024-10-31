package com.google.android.libraries.places.api.net;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class IsOpenResponse {
    public static IsOpenResponse newInstance(Boolean bool) {
        return new zzt(bool);
    }

    public abstract Boolean isOpen();
}
