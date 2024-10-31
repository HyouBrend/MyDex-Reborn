package com.google.android.libraries.places.widget.internal.ui;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zzde;
import com.google.android.libraries.places.internal.zzdh;
import com.google.android.libraries.places.internal.zzhh;
import com.google.android.libraries.places.internal.zzhi;
import com.google.android.libraries.places.internal.zzhm;
import com.google.android.libraries.places.internal.zzia;
import com.google.android.libraries.places.internal.zziy;
import com.google.android.libraries.places.internal.zziz;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzh extends FragmentFactory {
    private final int zza;
    private final PlacesClient zzb;
    private final zzia zzc;
    private final zziy zzd;
    private final zzde zze;

    public zzh(int i, Context context, zzia zziaVar) {
        this.zza = i;
        Context applicationContext = context.getApplicationContext();
        zzhh zzd = zzhi.zzd(applicationContext);
        zzd.zzd(2);
        zzhi zze = zzd.zze();
        zzhm zzhmVar = new zzhm(applicationContext);
        this.zzb = Places.zza(applicationContext, zze);
        this.zzc = zziaVar;
        this.zzd = new zziz(zzhmVar, zze);
        this.zze = new zzdh();
    }

    @Override // androidx.fragment.app.FragmentFactory
    public final Fragment instantiate(ClassLoader classLoader, String str) {
        if (loadFragmentClass(classLoader, str) == AutocompleteImplFragment.class) {
            return new AutocompleteImplFragment(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }
        return super.instantiate(classLoader, str);
    }
}
