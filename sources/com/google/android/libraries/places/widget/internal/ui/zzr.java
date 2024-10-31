package com.google.android.libraries.places.widget.internal.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzhk;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzr extends ListAdapter {
    private int zza;
    private boolean zzb;
    private final zzb zzc;

    public zzr(zzb zzbVar) {
        super(new zzq(null));
        this.zzb = true;
        this.zzc = zzbVar;
    }

    @Override // androidx.recyclerview.widget.ListAdapter
    public final void submitList(List list) {
        try {
            int i = 0;
            this.zzb = (this.zza != 0 || list == null || list.isEmpty()) ? false : true;
            if (list != null) {
                i = list.size();
            }
            this.zza = i;
            super.submitList(list);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzt onCreateViewHolder(ViewGroup viewGroup, int i) {
        try {
            return new zzt(this.zzc, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.places_autocomplete_prediction, viewGroup, false));
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final void onBindViewHolder(zzt zztVar, int i) {
        try {
            zztVar.zza((AutocompletePrediction) getItem(i), this.zzb);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }
}
