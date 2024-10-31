package com.google.android.libraries.places.api.model;

import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class SpecialDay implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract SpecialDay build();

        public abstract LocalDate getDate();

        public abstract boolean isExceptional();

        public abstract Builder setDate(LocalDate localDate);

        public abstract Builder setExceptional(boolean z);
    }

    public static Builder builder(LocalDate localDate) {
        zzz zzzVar = new zzz();
        zzzVar.setDate(localDate);
        zzzVar.setExceptional(false);
        return zzzVar;
    }

    public abstract LocalDate getDate();

    public abstract boolean isExceptional();
}
