package com.google.android.libraries.places.api.model;

import android.net.Uri;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzs extends Place.Builder {
    private Place.BooleanPlaceAttributeValue zzA;
    private Place.BooleanPlaceAttributeValue zzB;
    private Place.BooleanPlaceAttributeValue zzC;
    private Place.BooleanPlaceAttributeValue zzD;
    private Place.BooleanPlaceAttributeValue zzE;
    private List zzF;
    private Integer zzG;
    private Integer zzH;
    private LatLngBounds zzI;
    private Uri zzJ;
    private Place.BooleanPlaceAttributeValue zzK;
    private String zza;
    private AddressComponents zzb;
    private List zzc;
    private Place.BusinessStatus zzd;
    private Place.BooleanPlaceAttributeValue zze;
    private OpeningHours zzf;
    private Place.BooleanPlaceAttributeValue zzg;
    private Place.BooleanPlaceAttributeValue zzh;
    private String zzi;
    private String zzj;
    private Integer zzk;
    private String zzl;
    private String zzm;
    private LatLng zzn;
    private String zzo;
    private OpeningHours zzp;
    private String zzq;
    private List zzr;
    private PlusCode zzs;
    private Integer zzt;
    private Double zzu;
    private Place.BooleanPlaceAttributeValue zzv;
    private List zzw;
    private Place.BooleanPlaceAttributeValue zzx;
    private Place.BooleanPlaceAttributeValue zzy;
    private Place.BooleanPlaceAttributeValue zzz;

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getAddress() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final AddressComponents getAddressComponents() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<String> getAttributions() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BusinessStatus getBusinessStatus() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getCurbsidePickup() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zze;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"curbsidePickup\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final OpeningHours getCurrentOpeningHours() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getDelivery() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzg;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"delivery\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getDineIn() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzh;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"dineIn\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getEditorialSummary() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getEditorialSummaryLanguageCode() {
        return this.zzj;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getIconBackgroundColor() {
        return this.zzk;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getIconUrl() {
        return this.zzl;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getId() {
        return this.zzm;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final LatLng getLatLng() {
        return this.zzn;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getName() {
        return this.zzo;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final OpeningHours getOpeningHours() {
        return this.zzp;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getPhoneNumber() {
        return this.zzq;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<PhotoMetadata> getPhotoMetadatas() {
        return this.zzr;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final PlusCode getPlusCode() {
        return this.zzs;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getPriceLevel() {
        return this.zzt;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Double getRating() {
        return this.zzu;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getReservable() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzv;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"reservable\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<OpeningHours> getSecondaryOpeningHours() {
        return this.zzw;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesBeer() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzx;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesBeer\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesBreakfast() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzy;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesBreakfast\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesBrunch() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzz;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesBrunch\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesDinner() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzA;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesDinner\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesLunch() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzB;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesLunch\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesVegetarianFood() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzC;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesVegetarianFood\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getServesWine() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzD;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"servesWine\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getTakeout() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzE;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"takeout\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<Place.Type> getTypes() {
        return this.zzF;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getUserRatingsTotal() {
        return this.zzG;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getUtcOffsetMinutes() {
        return this.zzH;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final LatLngBounds getViewport() {
        return this.zzI;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Uri getWebsiteUri() {
        return this.zzJ;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BooleanPlaceAttributeValue getWheelchairAccessibleEntrance() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue = this.zzK;
        if (booleanPlaceAttributeValue != null) {
            return booleanPlaceAttributeValue;
        }
        throw new IllegalStateException("Property \"wheelchairAccessibleEntrance\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setAddress(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setAddressComponents(AddressComponents addressComponents) {
        this.zzb = addressComponents;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setAttributions(List<String> list) {
        this.zzc = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setBusinessStatus(Place.BusinessStatus businessStatus) {
        this.zzd = businessStatus;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setCurbsidePickup(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null curbsidePickup");
        }
        this.zze = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setCurrentOpeningHours(OpeningHours openingHours) {
        this.zzf = openingHours;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setDelivery(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null delivery");
        }
        this.zzg = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setDineIn(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null dineIn");
        }
        this.zzh = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setEditorialSummary(String str) {
        this.zzi = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setEditorialSummaryLanguageCode(String str) {
        this.zzj = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setIconBackgroundColor(Integer num) {
        this.zzk = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setIconUrl(String str) {
        this.zzl = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setId(String str) {
        this.zzm = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setLatLng(LatLng latLng) {
        this.zzn = latLng;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setName(String str) {
        this.zzo = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setOpeningHours(OpeningHours openingHours) {
        this.zzp = openingHours;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPhoneNumber(String str) {
        this.zzq = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPhotoMetadatas(List<PhotoMetadata> list) {
        this.zzr = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPlusCode(PlusCode plusCode) {
        this.zzs = plusCode;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPriceLevel(Integer num) {
        this.zzt = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setRating(Double d) {
        this.zzu = d;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setReservable(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null reservable");
        }
        this.zzv = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setSecondaryOpeningHours(List<OpeningHours> list) {
        this.zzw = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesBeer(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesBeer");
        }
        this.zzx = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesBreakfast(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesBreakfast");
        }
        this.zzy = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesBrunch(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesBrunch");
        }
        this.zzz = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesDinner(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesDinner");
        }
        this.zzA = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesLunch(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesLunch");
        }
        this.zzB = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesVegetarianFood(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesVegetarianFood");
        }
        this.zzC = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setServesWine(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null servesWine");
        }
        this.zzD = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setTakeout(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null takeout");
        }
        this.zzE = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setTypes(List<Place.Type> list) {
        this.zzF = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setUserRatingsTotal(Integer num) {
        this.zzG = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setUtcOffsetMinutes(Integer num) {
        this.zzH = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setViewport(LatLngBounds latLngBounds) {
        this.zzI = latLngBounds;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setWebsiteUri(Uri uri) {
        this.zzJ = uri;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setWheelchairAccessibleEntrance(Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue) {
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null wheelchairAccessibleEntrance");
        }
        this.zzK = booleanPlaceAttributeValue;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    final Place zza() {
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue2;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue3;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue4;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue5;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue6;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue7;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue8;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue9;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue10;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue11;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue12;
        Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue13 = this.zze;
        if (booleanPlaceAttributeValue13 == null || (booleanPlaceAttributeValue = this.zzg) == null || (booleanPlaceAttributeValue2 = this.zzh) == null || (booleanPlaceAttributeValue3 = this.zzv) == null || (booleanPlaceAttributeValue4 = this.zzx) == null || (booleanPlaceAttributeValue5 = this.zzy) == null || (booleanPlaceAttributeValue6 = this.zzz) == null || (booleanPlaceAttributeValue7 = this.zzA) == null || (booleanPlaceAttributeValue8 = this.zzB) == null || (booleanPlaceAttributeValue9 = this.zzC) == null || (booleanPlaceAttributeValue10 = this.zzD) == null || (booleanPlaceAttributeValue11 = this.zzE) == null || (booleanPlaceAttributeValue12 = this.zzK) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zze == null) {
                sb.append(" curbsidePickup");
            }
            if (this.zzg == null) {
                sb.append(" delivery");
            }
            if (this.zzh == null) {
                sb.append(" dineIn");
            }
            if (this.zzv == null) {
                sb.append(" reservable");
            }
            if (this.zzx == null) {
                sb.append(" servesBeer");
            }
            if (this.zzy == null) {
                sb.append(" servesBreakfast");
            }
            if (this.zzz == null) {
                sb.append(" servesBrunch");
            }
            if (this.zzA == null) {
                sb.append(" servesDinner");
            }
            if (this.zzB == null) {
                sb.append(" servesLunch");
            }
            if (this.zzC == null) {
                sb.append(" servesVegetarianFood");
            }
            if (this.zzD == null) {
                sb.append(" servesWine");
            }
            if (this.zzE == null) {
                sb.append(" takeout");
            }
            if (this.zzK == null) {
                sb.append(" wheelchairAccessibleEntrance");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzay(this.zza, this.zzb, this.zzc, this.zzd, booleanPlaceAttributeValue13, this.zzf, booleanPlaceAttributeValue, booleanPlaceAttributeValue2, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu, booleanPlaceAttributeValue3, this.zzw, booleanPlaceAttributeValue4, booleanPlaceAttributeValue5, booleanPlaceAttributeValue6, booleanPlaceAttributeValue7, booleanPlaceAttributeValue8, booleanPlaceAttributeValue9, booleanPlaceAttributeValue10, booleanPlaceAttributeValue11, this.zzF, this.zzG, this.zzH, this.zzI, this.zzJ, booleanPlaceAttributeValue12);
    }
}
