package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzjp;
import com.google.android.libraries.places.internal.zzkh;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class AddressComponent implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public AddressComponent build() {
            AddressComponent zzc = zzc();
            zzjp.zzk(!zzc.getName().isEmpty(), "Name must not be empty.");
            List<String> types = zzc.getTypes();
            Iterator<String> it = types.iterator();
            while (it.hasNext()) {
                zzjp.zzk(!TextUtils.isEmpty(it.next()), "Types must not contain null or empty values.");
            }
            zzb(zzkh.zzj(types));
            return zzc();
        }

        public abstract String getShortName();

        public abstract Builder setShortName(String str);

        abstract Builder zzb(List list);

        abstract AddressComponent zzc();
    }

    public static Builder builder(String str, List<String> list) {
        zza zzaVar = new zza();
        zzaVar.zza(str);
        zzaVar.zzb(list);
        return zzaVar;
    }

    public abstract String getName();

    public abstract String getShortName();

    public abstract List<String> getTypes();
}
