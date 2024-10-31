package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class MonitoringD {

    @SerializedName("doNo")
    private String doNo;

    @SerializedName("pcode")
    private String pcode;

    @SerializedName("pcodeName")
    private String pcodeName;

    @SerializedName("speNo")
    private String speNo;

    @SerializedName("xqty")
    private String xqty;

    public String getSpeNo() {
        return this.speNo;
    }

    public void setSpeNo(String str) {
        this.speNo = str;
    }

    public String getDoNo() {
        return this.doNo;
    }

    public void setDoNo(String str) {
        this.doNo = str;
    }

    public String getPcode() {
        return this.pcode;
    }

    public void setPcode(String str) {
        this.pcode = str;
    }

    public String getPcodeName() {
        return this.pcodeName;
    }

    public void setPcodeName(String str) {
        this.pcodeName = str;
    }

    public String getXqty() {
        return this.xqty;
    }

    public void setXqty(String str) {
        this.xqty = str;
    }
}
