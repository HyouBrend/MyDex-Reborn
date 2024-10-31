package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.zzm;

/* loaded from: classes.dex */
final class zzk extends zzm.zze<zzq> {
    private final /* synthetic */ AddPlaceRequest zzbk;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzk(zzh zzhVar, Api api, GoogleApiClient googleApiClient, AddPlaceRequest addPlaceRequest) {
        super(api, googleApiClient);
        this.zzbk = addPlaceRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new com.google.android.gms.location.places.zzm(this), this.zzbk);
    }
}
