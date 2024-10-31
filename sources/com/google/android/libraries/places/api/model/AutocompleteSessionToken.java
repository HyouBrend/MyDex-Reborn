package com.google.android.libraries.places.api.model;

import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.UUID;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class AutocompleteSessionToken implements Parcelable {
    public static AutocompleteSessionToken newInstance() {
        return new zzam(new ParcelUuid(UUID.randomUUID()));
    }

    public final String toString() {
        return zza().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ParcelUuid zza();
}
