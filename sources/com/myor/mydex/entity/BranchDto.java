package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Comparator;

/* loaded from: classes2.dex */
public class BranchDto {

    @SerializedName("barcode")
    private String barcode;

    @SerializedName("branchId")
    private String branchId;

    @SerializedName("branchIdM1")
    private String branchIdM1;

    @SerializedName("branchIdM2")
    private String branchIdM2;

    @SerializedName("branchIdM3")
    private String branchIdM3;

    @SerializedName("branchName")
    private String branchName;

    @SerializedName("branchType")
    private String branchType;

    @SerializedName("flagRoute")
    private String flagRoute;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;
    private double radius;

    /* loaded from: classes2.dex */
    public static class SortByRadius implements Comparator<BranchDto> {
        @Override // java.util.Comparator
        public int compare(BranchDto branchDto, BranchDto branchDto2) {
            return ((int) branchDto.radius) - ((int) branchDto2.radius);
        }
    }

    public String getBranchId() {
        return this.branchId;
    }

    public void setBranchId(String str) {
        this.branchId = str;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String str) {
        this.branchName = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String str) {
        this.barcode = str;
    }

    public String getFlagRoute() {
        return this.flagRoute;
    }

    public void setFlagRoute(String str) {
        this.flagRoute = str;
    }

    public String getBranchType() {
        return this.branchType;
    }

    public void setBranchType(String str) {
        this.branchType = str;
    }

    public String getBranchIdM1() {
        return this.branchIdM1;
    }

    public void setBranchIdM1(String str) {
        this.branchIdM1 = str;
    }

    public String getBranchIdM2() {
        return this.branchIdM2;
    }

    public void setBranchIdM2(String str) {
        this.branchIdM2 = str;
    }

    public String getBranchIdM3() {
        return this.branchIdM3;
    }

    public void setBranchIdM3(String str) {
        this.branchIdM3 = str;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double d) {
        this.radius = d;
    }
}
