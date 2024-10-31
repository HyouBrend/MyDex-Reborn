package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
final class zzn extends zzm.zzc<zzq> {
    private final /* synthetic */ String val$query;
    private final /* synthetic */ LatLngBounds zzbq;
    private final /* synthetic */ AutocompleteFilter zzbr;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzn(zzh zzhVar, Api api, GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        super(api, googleApiClient);
        this.val$query = str;
        this.zzbq = latLngBounds;
        this.zzbr = autocompleteFilter;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new com.google.android.gms.location.places.zzm(this), this.val$query, this.zzbq, 1, this.zzbr);
    }
}
