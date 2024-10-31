package com.google.android.libraries.places.internal;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.net.PlacesClient;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzgw implements zzgz {
    private final zzhb zza;
    private final Context zzb;
    private final zzhi zzc;
    private final zzgw zzd = this;
    private final zzajv zze = zzaju.zza(zzdg.zza());

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgw(Context context, zzhb zzhbVar, zzhi zzhiVar, zzgv zzgvVar) {
        this.zza = zzhbVar;
        this.zzb = context;
        this.zzc = zzhiVar;
    }

    private final zzdw zzb() {
        return zzdx.zza(new zzhm(this.zzb), this.zzc, this.zza);
    }

    @Override // com.google.android.libraries.places.internal.zzgz
    public final PlacesClient zza() {
        zzhb zzhbVar = this.zza;
        zzho zzhoVar = new zzho(this.zzb);
        Context applicationContext = this.zzb.getApplicationContext();
        zzajt.zza(applicationContext);
        RequestQueue newRequestQueue = Volley.newRequestQueue(applicationContext);
        zzajt.zza(newRequestQueue);
        zzee zza = zzef.zza(newRequestQueue, new zzfs());
        Context applicationContext2 = this.zzb.getApplicationContext();
        zzajt.zza(applicationContext2);
        RequestQueue newRequestQueue2 = Volley.newRequestQueue(applicationContext2);
        zzajt.zza(newRequestQueue2);
        zzgd zza2 = zzge.zza(zzhbVar, zzhoVar, zza, zzel.zza(newRequestQueue2), zzb(), (zzde) this.zze.zzb(), zzff.zza(), zzfj.zza(zzgh.zza()), zzfn.zza(), zzfr.zza(zzgh.zza()));
        Context applicationContext3 = this.zzb.getApplicationContext();
        zzajt.zza(applicationContext3);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext3);
        zzajt.zza(fusedLocationProviderClient);
        zzdp zza3 = zzdq.zza(fusedLocationProviderClient, new zzgs(new zzgo()));
        Context applicationContext4 = this.zzb.getApplicationContext();
        zzajt.zza(applicationContext4);
        return zzey.zza(zza2, zza3, zzdv.zza(applicationContext4, (zzde) this.zze.zzb()), zzb(), (zzde) this.zze.zzb());
    }
}
