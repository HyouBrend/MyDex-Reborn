package com.google.android.gms.location.places.ui;

import android.view.View;

/* loaded from: classes.dex */
final class zzf implements View.OnClickListener {
    private final /* synthetic */ SupportPlaceAutocompleteFragment zzdm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(SupportPlaceAutocompleteFragment supportPlaceAutocompleteFragment) {
        this.zzdm = supportPlaceAutocompleteFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzdm.setText("");
    }
}
