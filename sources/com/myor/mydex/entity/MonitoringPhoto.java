package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class MonitoringPhoto {

    @SerializedName("addressArrive")
    private String addressArrive;

    @SerializedName("addressStartBongkar")
    private String addressStartBongkar;

    @SerializedName("photoArrive")
    private String photoArrive;

    @SerializedName("photoStartBongkar")
    private String photoStartBongkar;

    @SerializedName("speNo")
    private String speNo;

    @SerializedName("timeArrive")
    private String timeArrive;

    @SerializedName("timeStartBongkar")
    private String timeStartBongkar;

    public String getSpeNo() {
        return this.speNo;
    }

    public void setSpeNo(String str) {
        this.speNo = str;
    }

    public String getPhotoArrive() {
        return this.photoArrive;
    }

    public void setPhotoArrive(String str) {
        this.photoArrive = str;
    }

    public String getTimeArrive() {
        return this.timeArrive;
    }

    public void setTimeArrive(String str) {
        this.timeArrive = str;
    }

    public String getAddressArrive() {
        return this.addressArrive;
    }

    public void setAddressArrive(String str) {
        this.addressArrive = str;
    }

    public String getPhotoStartBongkar() {
        return this.photoStartBongkar;
    }

    public void setPhotoStartBongkar(String str) {
        this.photoStartBongkar = str;
    }

    public String getTimeStartBongkar() {
        return this.timeStartBongkar;
    }

    public void setTimeStartBongkar(String str) {
        this.timeStartBongkar = str;
    }

    public String getAddressStartBongkar() {
        return this.addressStartBongkar;
    }

    public void setAddressStartBongkar(String str) {
        this.addressStartBongkar = str;
    }
}
