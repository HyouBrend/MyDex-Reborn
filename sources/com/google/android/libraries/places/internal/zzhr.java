package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzhr extends zzia {
    private final AutocompleteActivityMode zza;
    private final zzkh zzb;
    private final zzhy zzc;
    private final String zzd;
    private final String zze;
    private final LocationBias zzf;
    private final LocationRestriction zzg;
    private final zzkh zzh;
    private final TypeFilter zzi;
    private final zzkh zzj;
    private final int zzk;
    private final int zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhr(AutocompleteActivityMode autocompleteActivityMode, zzkh zzkhVar, zzhy zzhyVar, String str, String str2, LocationBias locationBias, LocationRestriction locationRestriction, zzkh zzkhVar2, TypeFilter typeFilter, zzkh zzkhVar3, int i, int i2) {
        if (autocompleteActivityMode == null) {
            throw new NullPointerException("Null mode");
        }
        this.zza = autocompleteActivityMode;
        if (zzkhVar != null) {
            this.zzb = zzkhVar;
            if (zzhyVar != null) {
                this.zzc = zzhyVar;
                this.zzd = str;
                this.zze = str2;
                this.zzf = locationBias;
                this.zzg = locationRestriction;
                if (zzkhVar2 != null) {
                    this.zzh = zzkhVar2;
                    this.zzi = typeFilter;
                    if (zzkhVar3 != null) {
                        this.zzj = zzkhVar3;
                        this.zzk = i;
                        this.zzl = i2;
                        return;
                    }
                    throw new NullPointerException("Null typesFilter");
                }
                throw new NullPointerException("Null countries");
            }
            throw new NullPointerException("Null origin");
        }
        throw new NullPointerException("Null placeFields");
    }

    public final boolean equals(Object obj) {
        String str;
        String str2;
        LocationBias locationBias;
        LocationRestriction locationRestriction;
        TypeFilter typeFilter;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzia) {
            zzia zziaVar = (zzia) obj;
            if (this.zza.equals(zziaVar.zzh()) && this.zzb.equals(zziaVar.zzj()) && this.zzc.equals(zziaVar.zzf()) && ((str = this.zzd) != null ? str.equals(zziaVar.zzm()) : zziaVar.zzm() == null) && ((str2 = this.zze) != null ? str2.equals(zziaVar.zzl()) : zziaVar.zzl() == null) && ((locationBias = this.zzf) != null ? locationBias.equals(zziaVar.zzc()) : zziaVar.zzc() == null) && ((locationRestriction = this.zzg) != null ? locationRestriction.equals(zziaVar.zzd()) : zziaVar.zzd() == null) && this.zzh.equals(zziaVar.zzi()) && ((typeFilter = this.zzi) != null ? typeFilter.equals(zziaVar.zze()) : zziaVar.zze() == null) && this.zzj.equals(zziaVar.zzk()) && this.zzk == zziaVar.zza() && this.zzl == zziaVar.zzb()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode();
        String str = this.zzd;
        int hashCode2 = ((hashCode * 1000003) ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zze;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        LocationBias locationBias = this.zzf;
        int hashCode4 = (hashCode3 ^ (locationBias == null ? 0 : locationBias.hashCode())) * 1000003;
        LocationRestriction locationRestriction = this.zzg;
        int hashCode5 = (((hashCode4 ^ (locationRestriction == null ? 0 : locationRestriction.hashCode())) * 1000003) ^ this.zzh.hashCode()) * 1000003;
        TypeFilter typeFilter = this.zzi;
        return ((((((hashCode5 ^ (typeFilter != null ? typeFilter.hashCode() : 0)) * 1000003) ^ this.zzj.hashCode()) * 1000003) ^ this.zzk) * 1000003) ^ this.zzl;
    }

    public final String toString() {
        return "AutocompleteOptions{mode=" + this.zza.toString() + ", placeFields=" + this.zzb.toString() + ", origin=" + this.zzc.toString() + ", initialQuery=" + this.zzd + ", hint=" + this.zze + ", locationBias=" + String.valueOf(this.zzf) + ", locationRestriction=" + String.valueOf(this.zzg) + ", countries=" + this.zzh.toString() + ", typeFilter=" + String.valueOf(this.zzi) + ", typesFilter=" + this.zzj.toString() + ", primaryColor=" + this.zzk + ", primaryColorDark=" + this.zzl + "}";
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final int zza() {
        return this.zzk;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final int zzb() {
        return this.zzl;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final LocationBias zzc() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final LocationRestriction zzd() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    @Deprecated
    public final TypeFilter zze() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final zzhy zzf() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final zzhz zzg() {
        return new zzhq(this, null);
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final AutocompleteActivityMode zzh() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final zzkh zzi() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final zzkh zzj() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final zzkh zzk() {
        return this.zzj;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final String zzl() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.internal.zzia
    public final String zzm() {
        return this.zzd;
    }
}
