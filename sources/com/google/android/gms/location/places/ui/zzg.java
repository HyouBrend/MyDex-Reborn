package com.google.android.gms.location.places.ui;

import android.view.View;

/* loaded from: classes.dex */
final class zzg implements View.OnClickListener {
    private final /* synthetic */ SupportPlaceAutocompleteFragment zzdm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(SupportPlaceAutocompleteFragment supportPlaceAutocompleteFragment) {
        this.zzdm = supportPlaceAutocompleteFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        z = this.zzdm.zzdh;
        if (z) {
            return;
        }
        this.zzdm.zzn();
    }
}
