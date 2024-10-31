package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zzm;
import java.util.Arrays;

/* loaded from: classes.dex */
final class zzl extends zzm.zze<zzq> {
    private final /* synthetic */ String[] zzbl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzl(zzh zzhVar, Api api, GoogleApiClient googleApiClient, String[] strArr) {
        super(api, googleApiClient);
        this.zzbl = strArr;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new com.google.android.gms.location.places.zzm(this), Arrays.asList(this.zzbl));
    }
}
