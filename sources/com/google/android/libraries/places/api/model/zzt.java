package com.google.android.libraries.places.api.model;

import android.net.Uri;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
abstract class zzt extends Place {
    private final Place.BooleanPlaceAttributeValue zzA;
    private final Place.BooleanPlaceAttributeValue zzB;
    private final Place.BooleanPlaceAttributeValue zzC;
    private final Place.BooleanPlaceAttributeValue zzD;
    private final Place.BooleanPlaceAttributeValue zzE;
    private final List zzF;
    private final Integer zzG;
    private final Integer zzH;
    private final LatLngBounds zzI;
    private final Uri zzJ;
    private final Place.BooleanPlaceAttributeValue zzK;
    private final String zza;
    private final AddressComponents zzb;
    private final List zzc;
    private final Place.BusinessStatus zzd;
    private final Place.BooleanPlaceAttributeValue zze;
    private final OpeningHours zzf;
    private final Place.BooleanPlaceAttributeValue zzg;
    private final Place.BooleanPlaceAttributeValue zzh;
    private final String zzi;
    private final String zzj;
    private final Integer zzk;
    private final String zzl;
    private final String zzm;
    private final LatLng zzn;
    private final String zzo;
    private final OpeningHours zzp;
    private final String zzq;
    private final List zzr;
    private final PlusCode zzs;
    private final Integer zzt;
    private final Double zzu;
    private final Place.BooleanPlaceAttributeValue zzv;
    private final List zzw;
    private final Place.BooleanPlaceAttributeValue zzx;
    private final Place.BooleanPlaceAttributeValue zzy;
    private final Place.BooleanPlaceAttributeValue zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(String str, AddressComponents addressComponents, List list, Place.BusinessStatus businessStatus, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue, OpeningHours openingHours, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue2, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue3, String str2, String str3, Integer num, String str4, String str5, LatLng latLng, String str6, OpeningHours openingHours2, String str7, List list2, PlusCode plusCode, Integer num2, Double d, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue4, List list3, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue5, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue6, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue7, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue8, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue9, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue10, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue11, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue12, List list4, Integer num3, Integer num4, LatLngBounds latLngBounds, Uri uri, Place.BooleanPlaceAttributeValue booleanPlaceAttributeValue13) {
        this.zza = str;
        this.zzb = addressComponents;
        this.zzc = list;
        this.zzd = businessStatus;
        if (booleanPlaceAttributeValue == null) {
            throw new NullPointerException("Null curbsidePickup");
        }
        this.zze = booleanPlaceAttributeValue;
        this.zzf = openingHours;
        if (booleanPlaceAttributeValue2 != null) {
            this.zzg = booleanPlaceAttributeValue2;
            if (booleanPlaceAttributeValue3 != null) {
                this.zzh = booleanPlaceAttributeValue3;
                this.zzi = str2;
                this.zzj = str3;
                this.zzk = num;
                this.zzl = str4;
                this.zzm = str5;
                this.zzn = latLng;
                this.zzo = str6;
                this.zzp = openingHours2;
                this.zzq = str7;
                this.zzr = list2;
                this.zzs = plusCode;
                this.zzt = num2;
                this.zzu = d;
                if (booleanPlaceAttributeValue4 != null) {
                    this.zzv = booleanPlaceAttributeValue4;
                    this.zzw = list3;
                    if (booleanPlaceAttributeValue5 != null) {
                        this.zzx = booleanPlaceAttributeValue5;
                        if (booleanPlaceAttributeValue6 != null) {
                            this.zzy = booleanPlaceAttributeValue6;
                            if (booleanPlaceAttributeValue7 != null) {
                                this.zzz = booleanPlaceAttributeValue7;
                                if (booleanPlaceAttributeValue8 != null) {
                                    this.zzA = booleanPlaceAttributeValue8;
                                    if (booleanPlaceAttributeValue9 != null) {
                                        this.zzB = booleanPlaceAttributeValue9;
                                        if (booleanPlaceAttributeValue10 != null) {
                                            this.zzC = booleanPlaceAttributeValue10;
                                            if (booleanPlaceAttributeValue11 != null) {
                                                this.zzD = booleanPlaceAttributeValue11;
                                                if (booleanPlaceAttributeValue12 != null) {
                                                    this.zzE = booleanPlaceAttributeValue12;
                                                    this.zzF = list4;
                                                    this.zzG = num3;
                                                    this.zzH = num4;
                                                    this.zzI = latLngBounds;
                                                    this.zzJ = uri;
                                                    if (booleanPlaceAttributeValue13 != null) {
                                                        this.zzK = booleanPlaceAttributeValue13;
                                                        return;
                                                    }
                                                    throw new NullPointerException("Null wheelchairAccessibleEntrance");
                                                }
                                                throw new NullPointerException("Null takeout");
                                            }
                                            throw new NullPointerException("Null servesWine");
                                        }
                                        throw new NullPointerException("Null servesVegetarianFood");
                                    }
                                    throw new NullPointerException("Null servesLunch");
                                }
                                throw new NullPointerException("Null servesDinner");
                            }
                            throw new NullPointerException("Null servesBrunch");
                        }
                        throw new NullPointerException("Null servesBreakfast");
                    }
                    throw new NullPointerException("Null servesBeer");
                }
                throw new NullPointerException("Null reservable");
            }
            throw new NullPointerException("Null dineIn");
        }
        throw new NullPointerException("Null delivery");
    }

    public final boolean equals(Object obj) {
        OpeningHours openingHours;
        String str;
        String str2;
        Integer num;
        String str3;
        String str4;
        LatLng latLng;
        String str5;
        OpeningHours openingHours2;
        String str6;
        List list;
        PlusCode plusCode;
        Integer num2;
        Double d;
        List list2;
        List list3;
        Integer num3;
        Integer num4;
        LatLngBounds latLngBounds;
        Uri uri;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Place) {
            Place place = (Place) obj;
            String str7 = this.zza;
            if (str7 != null ? str7.equals(place.getAddress()) : place.getAddress() == null) {
                AddressComponents addressComponents = this.zzb;
                if (addressComponents != null ? addressComponents.equals(place.getAddressComponents()) : place.getAddressComponents() == null) {
                    List list4 = this.zzc;
                    if (list4 != null ? list4.equals(place.getAttributions()) : place.getAttributions() == null) {
                        Place.BusinessStatus businessStatus = this.zzd;
                        if (businessStatus != null ? businessStatus.equals(place.getBusinessStatus()) : place.getBusinessStatus() == null) {
                            if (this.zze.equals(place.getCurbsidePickup()) && ((openingHours = this.zzf) != null ? openingHours.equals(place.getCurrentOpeningHours()) : place.getCurrentOpeningHours() == null) && this.zzg.equals(place.getDelivery()) && this.zzh.equals(place.getDineIn()) && ((str = this.zzi) != null ? str.equals(place.getEditorialSummary()) : place.getEditorialSummary() == null) && ((str2 = this.zzj) != null ? str2.equals(place.getEditorialSummaryLanguageCode()) : place.getEditorialSummaryLanguageCode() == null) && ((num = this.zzk) != null ? num.equals(place.getIconBackgroundColor()) : place.getIconBackgroundColor() == null) && ((str3 = this.zzl) != null ? str3.equals(place.getIconUrl()) : place.getIconUrl() == null) && ((str4 = this.zzm) != null ? str4.equals(place.getId()) : place.getId() == null) && ((latLng = this.zzn) != null ? latLng.equals(place.getLatLng()) : place.getLatLng() == null) && ((str5 = this.zzo) != null ? str5.equals(place.getName()) : place.getName() == null) && ((openingHours2 = this.zzp) != null ? openingHours2.equals(place.getOpeningHours()) : place.getOpeningHours() == null) && ((str6 = this.zzq) != null ? str6.equals(place.getPhoneNumber()) : place.getPhoneNumber() == null) && ((list = this.zzr) != null ? list.equals(place.getPhotoMetadatas()) : place.getPhotoMetadatas() == null) && ((plusCode = this.zzs) != null ? plusCode.equals(place.getPlusCode()) : place.getPlusCode() == null) && ((num2 = this.zzt) != null ? num2.equals(place.getPriceLevel()) : place.getPriceLevel() == null) && ((d = this.zzu) != null ? d.equals(place.getRating()) : place.getRating() == null) && this.zzv.equals(place.getReservable()) && ((list2 = this.zzw) != null ? list2.equals(place.getSecondaryOpeningHours()) : place.getSecondaryOpeningHours() == null) && this.zzx.equals(place.getServesBeer()) && this.zzy.equals(place.getServesBreakfast()) && this.zzz.equals(place.getServesBrunch()) && this.zzA.equals(place.getServesDinner()) && this.zzB.equals(place.getServesLunch()) && this.zzC.equals(place.getServesVegetarianFood()) && this.zzD.equals(place.getServesWine()) && this.zzE.equals(place.getTakeout()) && ((list3 = this.zzF) != null ? list3.equals(place.getTypes()) : place.getTypes() == null) && ((num3 = this.zzG) != null ? num3.equals(place.getUserRatingsTotal()) : place.getUserRatingsTotal() == null) && ((num4 = this.zzH) != null ? num4.equals(place.getUtcOffsetMinutes()) : place.getUtcOffsetMinutes() == null) && ((latLngBounds = this.zzI) != null ? latLngBounds.equals(place.getViewport()) : place.getViewport() == null) && ((uri = this.zzJ) != null ? uri.equals(place.getWebsiteUri()) : place.getWebsiteUri() == null) && this.zzK.equals(place.getWheelchairAccessibleEntrance())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getAddress() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public AddressComponents getAddressComponents() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public List<String> getAttributions() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BusinessStatus getBusinessStatus() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getCurbsidePickup() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public OpeningHours getCurrentOpeningHours() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getDelivery() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getDineIn() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getEditorialSummary() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getEditorialSummaryLanguageCode() {
        return this.zzj;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Integer getIconBackgroundColor() {
        return this.zzk;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getIconUrl() {
        return this.zzl;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getId() {
        return this.zzm;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public LatLng getLatLng() {
        return this.zzn;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getName() {
        return this.zzo;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public OpeningHours getOpeningHours() {
        return this.zzp;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public String getPhoneNumber() {
        return this.zzq;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public List<PhotoMetadata> getPhotoMetadatas() {
        return this.zzr;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public PlusCode getPlusCode() {
        return this.zzs;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Integer getPriceLevel() {
        return this.zzt;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Double getRating() {
        return this.zzu;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getReservable() {
        return this.zzv;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public List<OpeningHours> getSecondaryOpeningHours() {
        return this.zzw;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesBeer() {
        return this.zzx;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesBreakfast() {
        return this.zzy;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesBrunch() {
        return this.zzz;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesDinner() {
        return this.zzA;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesLunch() {
        return this.zzB;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesVegetarianFood() {
        return this.zzC;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getServesWine() {
        return this.zzD;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getTakeout() {
        return this.zzE;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public List<Place.Type> getTypes() {
        return this.zzF;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Integer getUserRatingsTotal() {
        return this.zzG;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Integer getUtcOffsetMinutes() {
        return this.zzH;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public LatLngBounds getViewport() {
        return this.zzI;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Uri getWebsiteUri() {
        return this.zzJ;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    public Place.BooleanPlaceAttributeValue getWheelchairAccessibleEntrance() {
        return this.zzK;
    }

    public final String toString() {
        return "Place{address=" + this.zza + ", addressComponents=" + String.valueOf(this.zzb) + ", attributions=" + String.valueOf(this.zzc) + ", businessStatus=" + String.valueOf(this.zzd) + ", curbsidePickup=" + this.zze.toString() + ", currentOpeningHours=" + String.valueOf(this.zzf) + ", delivery=" + this.zzg.toString() + ", dineIn=" + this.zzh.toString() + ", editorialSummary=" + this.zzi + ", editorialSummaryLanguageCode=" + this.zzj + ", iconBackgroundColor=" + this.zzk + ", iconUrl=" + this.zzl + ", id=" + this.zzm + ", latLng=" + String.valueOf(this.zzn) + ", name=" + this.zzo + ", openingHours=" + String.valueOf(this.zzp) + ", phoneNumber=" + this.zzq + ", photoMetadatas=" + String.valueOf(this.zzr) + ", plusCode=" + String.valueOf(this.zzs) + ", priceLevel=" + this.zzt + ", rating=" + this.zzu + ", reservable=" + this.zzv.toString() + ", secondaryOpeningHours=" + String.valueOf(this.zzw) + ", servesBeer=" + this.zzx.toString() + ", servesBreakfast=" + this.zzy.toString() + ", servesBrunch=" + this.zzz.toString() + ", servesDinner=" + this.zzA.toString() + ", servesLunch=" + this.zzB.toString() + ", servesVegetarianFood=" + this.zzC.toString() + ", servesWine=" + this.zzD.toString() + ", takeout=" + this.zzE.toString() + ", types=" + String.valueOf(this.zzF) + ", userRatingsTotal=" + this.zzG + ", utcOffsetMinutes=" + this.zzH + ", viewport=" + String.valueOf(this.zzI) + ", websiteUri=" + String.valueOf(this.zzJ) + ", wheelchairAccessibleEntrance=" + this.zzK.toString() + "}";
    }

    public final int hashCode() {
        String str = this.zza;
        int hashCode = str == null ? 0 : str.hashCode();
        AddressComponents addressComponents = this.zzb;
        int hashCode2 = addressComponents == null ? 0 : addressComponents.hashCode();
        int i = hashCode ^ 1000003;
        List list = this.zzc;
        int hashCode3 = ((((i * 1000003) ^ hashCode2) * 1000003) ^ (list == null ? 0 : list.hashCode())) * 1000003;
        Place.BusinessStatus businessStatus = this.zzd;
        int hashCode4 = (((hashCode3 ^ (businessStatus == null ? 0 : businessStatus.hashCode())) * 1000003) ^ this.zze.hashCode()) * 1000003;
        OpeningHours openingHours = this.zzf;
        int hashCode5 = (((((hashCode4 ^ (openingHours == null ? 0 : openingHours.hashCode())) * 1000003) ^ this.zzg.hashCode()) * 1000003) ^ this.zzh.hashCode()) * 1000003;
        String str2 = this.zzi;
        int hashCode6 = (hashCode5 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zzj;
        int hashCode7 = (hashCode6 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Integer num = this.zzk;
        int hashCode8 = (hashCode7 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str4 = this.zzl;
        int hashCode9 = (hashCode8 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.zzm;
        int hashCode10 = (hashCode9 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        LatLng latLng = this.zzn;
        int hashCode11 = (hashCode10 ^ (latLng == null ? 0 : latLng.hashCode())) * 1000003;
        String str6 = this.zzo;
        int hashCode12 = (hashCode11 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        OpeningHours openingHours2 = this.zzp;
        int hashCode13 = (hashCode12 ^ (openingHours2 == null ? 0 : openingHours2.hashCode())) * 1000003;
        String str7 = this.zzq;
        int hashCode14 = (hashCode13 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        List list2 = this.zzr;
        int hashCode15 = (hashCode14 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        PlusCode plusCode = this.zzs;
        int hashCode16 = (hashCode15 ^ (plusCode == null ? 0 : plusCode.hashCode())) * 1000003;
        Integer num2 = this.zzt;
        int hashCode17 = (hashCode16 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Double d = this.zzu;
        int hashCode18 = (((hashCode17 ^ (d == null ? 0 : d.hashCode())) * 1000003) ^ this.zzv.hashCode()) * 1000003;
        List list3 = this.zzw;
        int hashCode19 = (((((((((((((((((hashCode18 ^ (list3 == null ? 0 : list3.hashCode())) * 1000003) ^ this.zzx.hashCode()) * 1000003) ^ this.zzy.hashCode()) * 1000003) ^ this.zzz.hashCode()) * 1000003) ^ this.zzA.hashCode()) * 1000003) ^ this.zzB.hashCode()) * 1000003) ^ this.zzC.hashCode()) * 1000003) ^ this.zzD.hashCode()) * 1000003) ^ this.zzE.hashCode()) * 1000003;
        List list4 = this.zzF;
        int hashCode20 = (hashCode19 ^ (list4 == null ? 0 : list4.hashCode())) * 1000003;
        Integer num3 = this.zzG;
        int hashCode21 = (hashCode20 ^ (num3 == null ? 0 : num3.hashCode())) * 1000003;
        Integer num4 = this.zzH;
        int hashCode22 = (hashCode21 ^ (num4 == null ? 0 : num4.hashCode())) * 1000003;
        LatLngBounds latLngBounds = this.zzI;
        int hashCode23 = (hashCode22 ^ (latLngBounds == null ? 0 : latLngBounds.hashCode())) * 1000003;
        Uri uri = this.zzJ;
        return ((hashCode23 ^ (uri != null ? uri.hashCode() : 0)) * 1000003) ^ this.zzK.hashCode();
    }
}
