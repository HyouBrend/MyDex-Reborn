package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.internal.zzaa;

/* loaded from: classes.dex */
public class zzm extends zzaa {
    private static final String TAG = "zzm";
    private final zzd zzal;
    private final zzc zzam;
    private final zzg zzan;
    private final zzf zzao;
    private final zze zzap;

    /* loaded from: classes.dex */
    public static abstract class zzb<R extends Result, A extends Api.Client> extends BaseImplementation.ApiMethodImpl<R, A> {
        public zzb(Api api, GoogleApiClient googleApiClient) {
            super((Api<?>) api, googleApiClient);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class zzf<A extends Api.Client> extends zzb<Status, A> {
        public zzf(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class zzg<A extends Api.Client> extends zzb<com.google.android.gms.internal.places.zzh, A> {
    }

    public zzm(zzd zzdVar) {
        this.zzal = zzdVar;
        this.zzam = null;
        this.zzan = null;
        this.zzao = null;
        this.zzap = null;
    }

    /* loaded from: classes.dex */
    public static abstract class zzc<A extends Api.Client> extends zzb<AutocompletePredictionBuffer, A> {
        public zzc(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return new AutocompletePredictionBuffer(DataHolder.empty(status.getStatusCode()));
        }
    }

    /* loaded from: classes.dex */
    public static abstract class zzd<A extends Api.Client> extends zzb<PlaceLikelihoodBuffer, A> {
        public zzd(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return new PlaceLikelihoodBuffer(DataHolder.empty(status.getStatusCode()), 100);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class zze<A extends Api.Client> extends zzb<PlaceBuffer, A> {
        public zze(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return new PlaceBuffer(DataHolder.empty(status.getStatusCode()));
        }
    }

    public zzm(zzc zzcVar) {
        this.zzal = null;
        this.zzam = zzcVar;
        this.zzan = null;
        this.zzao = null;
        this.zzap = null;
    }

    public zzm(zzf zzfVar) {
        this.zzal = null;
        this.zzam = null;
        this.zzan = null;
        this.zzao = zzfVar;
        this.zzap = null;
    }

    public zzm(zze zzeVar) {
        this.zzal = null;
        this.zzam = null;
        this.zzan = null;
        this.zzao = null;
        this.zzap = zzeVar;
    }

    @Override // com.google.android.gms.location.places.internal.zzx
    public final void zzb(DataHolder dataHolder) throws RemoteException {
        Preconditions.checkState(this.zzal != null, "placeEstimator cannot be null");
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onPlaceEstimated received null DataHolder", new Throwable());
            }
            this.zzal.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }
        Bundle metadata = dataHolder.getMetadata();
        this.zzal.setResult((zzd) new PlaceLikelihoodBuffer(dataHolder, metadata == null ? 100 : PlaceLikelihoodBuffer.zzb(metadata)));
    }

    @Override // com.google.android.gms.location.places.internal.zzx
    public final void zzc(DataHolder dataHolder) throws RemoteException {
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onAutocompletePrediction received null DataHolder", new Throwable());
            }
            this.zzam.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }
        this.zzam.setResult((zzc) new AutocompletePredictionBuffer(dataHolder));
    }

    @Override // com.google.android.gms.location.places.internal.zzx
    public final void zzd(DataHolder dataHolder) throws RemoteException {
        BasePendingResult basePendingResult = null;
        BaseImplementation.ResultHolder resultHolder = null;
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onPlaceUserDataFetched received null DataHolder", new Throwable());
            }
            resultHolder.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            throw null;
        }
        basePendingResult.setResult(new com.google.android.gms.internal.places.zzh(dataHolder));
        throw null;
    }

    @Override // com.google.android.gms.location.places.internal.zzx
    public final void zzb(Status status) throws RemoteException {
        this.zzao.setResult((zzf) status);
    }

    @Override // com.google.android.gms.location.places.internal.zzx
    public final void zze(DataHolder dataHolder) throws RemoteException {
        this.zzap.setResult((zze) new PlaceBuffer(dataHolder));
    }
}
