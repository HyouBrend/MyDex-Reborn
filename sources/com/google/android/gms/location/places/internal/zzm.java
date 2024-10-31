package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzm extends com.google.android.gms.location.places.zze<zzq> {
    private final /* synthetic */ String zzbm;
    private final /* synthetic */ int zzbn;
    private final /* synthetic */ int zzbo;
    private final /* synthetic */ int zzbp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzm(zzh zzhVar, Api api, GoogleApiClient googleApiClient, String str, int i, int i2, int i3) {
        super(api, googleApiClient);
        this.zzbm = str;
        this.zzbn = i;
        this.zzbo = i2;
        this.zzbp = i3;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new com.google.android.gms.location.places.zzf(this), this.zzbm, this.zzbn, this.zzbo, this.zzbp);
    }
}
