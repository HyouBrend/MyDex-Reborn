package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzjp;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class PhotoMetadata implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public PhotoMetadata build() {
            PhotoMetadata zzb = zzb();
            int width = zzb.getWidth();
            zzjp.zzl(width >= 0, "Width must not be < 0, but was: %s.", width);
            int height = zzb.getHeight();
            zzjp.zzl(height >= 0, "Height must not be < 0, but was: %s.", height);
            zzjp.zzk(!TextUtils.isEmpty(zzb.zza()), "PhotoReference must not be null or empty.");
            return zzb;
        }

        public abstract String getAttributions();

        public abstract int getHeight();

        public abstract int getWidth();

        public abstract Builder setAttributions(String str);

        public abstract Builder setHeight(int i);

        public abstract Builder setWidth(int i);

        abstract PhotoMetadata zzb();
    }

    public static Builder builder(String str) {
        zzq zzqVar = new zzq();
        zzqVar.zza(str);
        zzqVar.setWidth(0);
        zzqVar.setHeight(0);
        zzqVar.setAttributions("");
        return zzqVar;
    }

    public abstract String getAttributions();

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract String zza();
}
