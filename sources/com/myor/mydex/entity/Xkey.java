package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class Xkey {

    @SerializedName("memoint")
    private String memoint;

    @SerializedName("memoint2")
    private String memoint2;

    @SerializedName("memonama")
    private String memonama;

    @SerializedName("memostring")
    private String memostring;

    @SerializedName("memostring2")
    private String memostring2;

    public String getMemonama() {
        return this.memonama;
    }

    public void setMemonama(String str) {
        this.memonama = str;
    }

    public String getMemoint() {
        return this.memoint;
    }

    public void setMemoint(String str) {
        this.memoint = str;
    }

    public String getMemostring() {
        return this.memostring;
    }

    public void setMemostring(String str) {
        this.memostring = str;
    }

    public String getMemoint2() {
        return this.memoint2;
    }

    public void setMemoint2(String str) {
        this.memoint2 = str;
    }

    public String getMemostring2() {
        return this.memostring2;
    }

    public void setMemostring2(String str) {
        this.memostring2 = str;
    }
}
