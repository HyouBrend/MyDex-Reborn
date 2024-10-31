package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class AddressComponents implements Parcelable {
    public static AddressComponents newInstance(List<AddressComponent> list) {
        return new zzag(list);
    }

    public abstract List<AddressComponent> asList();
}
