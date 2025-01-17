package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class zzc extends AbstractSafeParcelable implements AutocompletePrediction {
    public static final Parcelable.Creator<zzc> CREATOR = new zze();
    private static final List<zzb> zzbb = Collections.emptyList();
    private final String placeId;
    private final String zzbc;
    private final List<zzb> zzbd;
    private final int zzbe;
    private final String zzbf;
    private final List<zzb> zzbg;
    private final String zzbh;
    private final List<zzb> zzbi;
    private final List<Integer> zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(String str, List<Integer> list, int i, String str2, List<zzb> list2, String str3, List<zzb> list3, String str4, List<zzb> list4) {
        this.placeId = str;
        this.zzg = list;
        this.zzbe = i;
        this.zzbc = str2;
        this.zzbd = list2;
        this.zzbf = str3;
        this.zzbg = list3;
        this.zzbh = str4;
        this.zzbi = list4;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ AutocompletePrediction freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.location.places.AutocompletePrediction
    public final String getPlaceId() {
        return this.placeId;
    }

    @Override // com.google.android.gms.location.places.AutocompletePrediction
    public final List<Integer> getPlaceTypes() {
        return this.zzg;
    }

    @Override // com.google.android.gms.location.places.AutocompletePrediction
    public final CharSequence getFullText(CharacterStyle characterStyle) {
        return zzi.zzb(this.zzbc, this.zzbd, characterStyle);
    }

    @Override // com.google.android.gms.location.places.AutocompletePrediction
    public final CharSequence getPrimaryText(CharacterStyle characterStyle) {
        return zzi.zzb(this.zzbf, this.zzbg, characterStyle);
    }

    @Override // com.google.android.gms.location.places.AutocompletePrediction
    public final CharSequence getSecondaryText(CharacterStyle characterStyle) {
        return zzi.zzb(this.zzbh, this.zzbi, characterStyle);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzbc, false);
        SafeParcelWriter.writeString(parcel, 2, this.placeId, false);
        SafeParcelWriter.writeIntegerList(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzbd, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzbe);
        SafeParcelWriter.writeString(parcel, 6, this.zzbf, false);
        SafeParcelWriter.writeTypedList(parcel, 7, this.zzbg, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzbh, false);
        SafeParcelWriter.writeTypedList(parcel, 9, this.zzbi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("placeId", this.placeId).add("placeTypes", this.zzg).add("fullText", this.zzbc).add("fullTextMatchedSubstrings", this.zzbd).add("primaryText", this.zzbf).add("primaryTextMatchedSubstrings", this.zzbg).add("secondaryText", this.zzbh).add("secondaryTextMatchedSubstrings", this.zzbi).toString();
    }

    public final int hashCode() {
        return Objects.hashCode(this.placeId, this.zzg, Integer.valueOf(this.zzbe), this.zzbc, this.zzbd, this.zzbf, this.zzbg, this.zzbh, this.zzbi);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzcVar = (zzc) obj;
        return Objects.equal(this.placeId, zzcVar.placeId) && Objects.equal(this.zzg, zzcVar.zzg) && Objects.equal(Integer.valueOf(this.zzbe), Integer.valueOf(zzcVar.zzbe)) && Objects.equal(this.zzbc, zzcVar.zzbc) && Objects.equal(this.zzbd, zzcVar.zzbd) && Objects.equal(this.zzbf, zzcVar.zzbf) && Objects.equal(this.zzbg, zzcVar.zzbg) && Objects.equal(this.zzbh, zzcVar.zzbh) && Objects.equal(this.zzbi, zzcVar.zzbi);
    }
}
