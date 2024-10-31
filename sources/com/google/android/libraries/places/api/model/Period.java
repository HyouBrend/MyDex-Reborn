package com.google.android.libraries.places.api.model;

import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class Period implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract Period build();

        public abstract TimeOfWeek getClose();

        public abstract TimeOfWeek getOpen();

        public abstract Builder setClose(TimeOfWeek timeOfWeek);

        public abstract Builder setOpen(TimeOfWeek timeOfWeek);
    }

    public static Builder builder() {
        return new zzo();
    }

    public abstract TimeOfWeek getClose();

    public abstract TimeOfWeek getOpen();
}
