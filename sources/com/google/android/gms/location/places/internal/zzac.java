package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.zzm;

/* loaded from: classes.dex */
final class zzac extends zzm.zzd<zzae> {
    private final /* synthetic */ PlaceFilter zzbv;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzac(zzz zzzVar, Api api, GoogleApiClient googleApiClient, PlaceFilter placeFilter) {
        super(api, googleApiClient);
        this.zzbv = placeFilter;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzae) anyClient).zzb(new com.google.android.gms.location.places.zzm(this), this.zzbv);
    }
}
