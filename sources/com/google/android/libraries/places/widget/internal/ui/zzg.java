package com.google.android.libraries.places.widget.internal.ui;

import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.internal.zzhk;
import com.google.android.libraries.places.internal.zziu;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzg extends RecyclerView.OnScrollListener {
    final /* synthetic */ AutocompleteImplFragment zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(AutocompleteImplFragment autocompleteImplFragment) {
        this.zza = autocompleteImplFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
        zziu zziuVar;
        EditText editText;
        if (i == 1) {
            try {
                zziuVar = this.zza.zze;
                zziuVar.zzg();
                editText = this.zza.zzg;
                editText.clearFocus();
            } catch (Error | RuntimeException e) {
                zzhk.zzb(e);
                throw e;
            }
        }
    }
}
