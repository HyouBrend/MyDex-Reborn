package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class Reason {

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("reasonId")
    private String reasonId;

    @SerializedName("reasonName")
    private String reasonName;

    @SerializedName("typeId")
    private String typeId;

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String str) {
        this.typeId = str;
    }

    public String getReasonId() {
        return this.reasonId;
    }

    public void setReasonId(String str) {
        this.reasonId = str;
    }

    public String getReasonName() {
        return this.reasonName;
    }

    public void setReasonName(String str) {
        this.reasonName = str;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String str) {
        this.keterangan = str;
    }
}
