package com.google.android.libraries.places.widget.internal.ui;

import androidx.recyclerview.widget.DiffUtil;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzhk;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzq extends DiffUtil.ItemCallback {
    private zzq() {
    }

    public static final boolean zza(AutocompletePrediction autocompletePrediction, AutocompletePrediction autocompletePrediction2) {
        try {
            return autocompletePrediction.getPlaceId().equals(autocompletePrediction2.getPlaceId());
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final /* synthetic */ boolean areContentsTheSame(Object obj, Object obj2) {
        return ((AutocompletePrediction) obj).equals((AutocompletePrediction) obj2);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final /* bridge */ /* synthetic */ boolean areItemsTheSame(Object obj, Object obj2) {
        return zza((AutocompletePrediction) obj, (AutocompletePrediction) obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzq(zzp zzpVar) {
    }
}
