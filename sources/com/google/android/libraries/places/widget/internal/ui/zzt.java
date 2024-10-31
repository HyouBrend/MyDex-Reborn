package com.google.android.libraries.places.widget.internal.ui;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzhk;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzt extends RecyclerView.ViewHolder {
    private final TextView zza;
    private final TextView zzb;
    private AutocompletePrediction zzc;
    private boolean zzd;

    public zzt(final zzb zzbVar, View view) {
        super(view);
        this.zza = (TextView) view.findViewById(R.id.places_autocomplete_prediction_primary_text);
        this.zzb = (TextView) view.findViewById(R.id.places_autocomplete_prediction_secondary_text);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.internal.ui.zzs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                zzt.this.zzc(zzbVar, view2);
            }
        });
    }

    public final void zza(AutocompletePrediction autocompletePrediction, boolean z) {
        this.zzc = autocompletePrediction;
        this.zzd = z;
        this.zza.setText(autocompletePrediction.getPrimaryText(new ForegroundColorSpan(ContextCompat.getColor(this.itemView.getContext(), R.color.places_autocomplete_prediction_primary_text_highlight))));
        SpannableString secondaryText = autocompletePrediction.getSecondaryText(null);
        this.zzb.setText(secondaryText);
        if (secondaryText.length() == 0) {
            this.zzb.setVisibility(8);
            this.zza.setGravity(16);
        } else {
            this.zzb.setVisibility(0);
            this.zza.setGravity(80);
        }
    }

    public final boolean zzb() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzb zzbVar, View view) {
        AutocompletePrediction autocompletePrediction = this.zzc;
        if (autocompletePrediction != null) {
            try {
                zzbVar.zza.zze(autocompletePrediction, getAdapterPosition());
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
    }
}
