package com.google.android.gms.location.places.ui;

import android.view.View;

/* loaded from: classes.dex */
final class zze implements View.OnClickListener {
    private final /* synthetic */ PlaceAutocompleteFragment zzdl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(PlaceAutocompleteFragment placeAutocompleteFragment) {
        this.zzdl = placeAutocompleteFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        z = this.zzdl.zzdh;
        if (z) {
            return;
        }
        this.zzdl.zzn();
    }
}
