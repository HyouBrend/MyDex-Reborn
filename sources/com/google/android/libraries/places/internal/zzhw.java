package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhw extends zzic {
    private final String zza;
    private final zzkh zzb;
    private final Place zzc;
    private final AutocompletePrediction zzd;
    private final Status zze;
    private final int zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhw(int i, String str, zzkh zzkhVar, Place place, AutocompletePrediction autocompletePrediction, Status status, zzhv zzhvVar) {
        this.zzf = i;
        this.zza = str;
        this.zzb = zzkhVar;
        this.zzc = place;
        this.zzd = autocompletePrediction;
        this.zze = status;
    }

    public final boolean equals(Object obj) {
        String str;
        zzkh zzkhVar;
        Place place;
        AutocompletePrediction autocompletePrediction;
        Status status;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzic) {
            zzic zzicVar = (zzic) obj;
            if (this.zzf == zzicVar.zzf() && ((str = this.zza) != null ? str.equals(zzicVar.zze()) : zzicVar.zze() == null) && ((zzkhVar = this.zzb) != null ? zzkhVar.equals(zzicVar.zzd()) : zzicVar.zzd() == null) && ((place = this.zzc) != null ? place.equals(zzicVar.zzc()) : zzicVar.zzc() == null) && ((autocompletePrediction = this.zzd) != null ? autocompletePrediction.equals(zzicVar.zzb()) : zzicVar.zzb() == null) && ((status = this.zze) != null ? status.equals(zzicVar.zza()) : zzicVar.zza() == null)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        String str;
        switch (this.zzf) {
            case 1:
                str = "START";
                break;
            case 2:
                str = "RESET";
                break;
            case 3:
                str = "LOADING";
                break;
            case 4:
                str = "TRY_AGAIN_PROGRESS_LOADING";
                break;
            case 5:
                str = "SUCCESS_PREDICTIONS";
                break;
            case 6:
                str = "FAILURE_NO_PREDICTIONS";
                break;
            case 7:
                str = "FAILURE_PREDICTIONS";
                break;
            case 8:
                str = "SUCCESS_SELECTION";
                break;
            case 9:
                str = "FAILURE_SELECTION";
                break;
            default:
                str = "FAILURE_UNRESOLVABLE";
                break;
        }
        return "AutocompleteState{type=" + str + ", query=" + this.zza + ", predictions=" + String.valueOf(this.zzb) + ", place=" + String.valueOf(this.zzc) + ", prediction=" + String.valueOf(this.zzd) + ", status=" + String.valueOf(this.zze) + "}";
    }

    @Override // com.google.android.libraries.places.internal.zzic
    public final Status zza() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.internal.zzic
    public final AutocompletePrediction zzb() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzic
    public final Place zzc() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzic
    public final zzkh zzd() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzic
    public final String zze() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzic
    public final int zzf() {
        return this.zzf;
    }

    public final int hashCode() {
        int i = this.zzf ^ 1000003;
        String str = this.zza;
        int hashCode = ((i * 1000003) ^ (str == null ? 0 : str.hashCode())) * 1000003;
        zzkh zzkhVar = this.zzb;
        int hashCode2 = (hashCode ^ (zzkhVar == null ? 0 : zzkhVar.hashCode())) * 1000003;
        Place place = this.zzc;
        int hashCode3 = (hashCode2 ^ (place == null ? 0 : place.hashCode())) * 1000003;
        AutocompletePrediction autocompletePrediction = this.zzd;
        int hashCode4 = (hashCode3 ^ (autocompletePrediction == null ? 0 : autocompletePrediction.hashCode())) * 1000003;
        Status status = this.zze;
        return hashCode4 ^ (status != null ? status.hashCode() : 0);
    }
}
