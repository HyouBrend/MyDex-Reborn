package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhq extends zzhz {
    private AutocompleteActivityMode zza;
    private zzkh zzb;
    private zzhy zzc;
    private String zzd;
    private String zze;
    private LocationBias zzf;
    private LocationRestriction zzg;
    private zzkh zzh;
    private TypeFilter zzi;
    private zzkh zzj;
    private int zzk;
    private int zzl;
    private byte zzm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhq() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhq(zzia zziaVar, zzhp zzhpVar) {
        this.zza = zziaVar.zzh();
        this.zzb = zziaVar.zzj();
        this.zzc = zziaVar.zzf();
        this.zzd = zziaVar.zzm();
        this.zze = zziaVar.zzl();
        this.zzf = zziaVar.zzc();
        this.zzg = zziaVar.zzd();
        this.zzh = zziaVar.zzi();
        this.zzi = zziaVar.zze();
        this.zzj = zziaVar.zzk();
        this.zzk = zziaVar.zza();
        this.zzl = zziaVar.zzb();
        this.zzm = (byte) 3;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zza(List list) {
        this.zzh = zzkh.zzj(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzb(String str) {
        this.zze = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzc(String str) {
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzd(LocationBias locationBias) {
        this.zzf = locationBias;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zze(LocationRestriction locationRestriction) {
        this.zzg = locationRestriction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzf(AutocompleteActivityMode autocompleteActivityMode) {
        if (autocompleteActivityMode == null) {
            throw new NullPointerException("Null mode");
        }
        this.zza = autocompleteActivityMode;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzg(zzhy zzhyVar) {
        if (zzhyVar == null) {
            throw new NullPointerException("Null origin");
        }
        this.zzc = zzhyVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzh(List list) {
        this.zzb = zzkh.zzj(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzi(int i) {
        this.zzk = i;
        this.zzm = (byte) (this.zzm | 1);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzj(int i) {
        this.zzl = i;
        this.zzm = (byte) (this.zzm | 2);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzk(TypeFilter typeFilter) {
        this.zzi = typeFilter;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzhz zzl(List list) {
        this.zzj = zzkh.zzj(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhz
    public final zzia zzm() {
        AutocompleteActivityMode autocompleteActivityMode;
        zzkh zzkhVar;
        zzhy zzhyVar;
        zzkh zzkhVar2;
        zzkh zzkhVar3;
        if (this.zzm != 3 || (autocompleteActivityMode = this.zza) == null || (zzkhVar = this.zzb) == null || (zzhyVar = this.zzc) == null || (zzkhVar2 = this.zzh) == null || (zzkhVar3 = this.zzj) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" mode");
            }
            if (this.zzb == null) {
                sb.append(" placeFields");
            }
            if (this.zzc == null) {
                sb.append(" origin");
            }
            if (this.zzh == null) {
                sb.append(" countries");
            }
            if (this.zzj == null) {
                sb.append(" typesFilter");
            }
            if ((this.zzm & 1) == 0) {
                sb.append(" primaryColor");
            }
            if ((this.zzm & 2) == 0) {
                sb.append(" primaryColorDark");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzht(autocompleteActivityMode, zzkhVar, zzhyVar, this.zzd, this.zze, this.zzf, this.zzg, zzkhVar2, this.zzi, zzkhVar3, this.zzk, this.zzl);
    }
}
