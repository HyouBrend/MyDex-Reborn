package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class InputSPEDto {

    @SerializedName("driverName")
    private String driverName;

    @SerializedName("driverPhone")
    private String driverPhone;

    @SerializedName("inputDate")
    private String inputDate;

    @SerializedName("policeNo")
    private String policeNo;

    @SerializedName("serialPhone")
    private String serialPhone;

    @SerializedName("speNo")
    private String speNo;

    public String getSpeNo() {
        return this.speNo;
    }

    public void setSpeNo(String str) {
        this.speNo = str;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String str) {
        this.driverName = str;
    }

    public String getDriverPhone() {
        return this.driverPhone;
    }

    public void setDriverPhone(String str) {
        this.driverPhone = str;
    }

    public String getPoliceNo() {
        return this.policeNo;
    }

    public void setPoliceNo(String str) {
        this.policeNo = str;
    }

    public String getSerialPhone() {
        return this.serialPhone;
    }

    public void setSerialPhone(String str) {
        this.serialPhone = str;
    }

    public String getInputDate() {
        return this.inputDate;
    }

    public void setInputDate(String str) {
        this.inputDate = str;
    }
}
