package com.myor.mydex.entity;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class MonitoringDto {

    @SerializedName("destinationId")
    private String destinationId;

    @SerializedName("destinationName")
    private String destinationName;

    @SerializedName("destinationType")
    private String destinationType;

    @SerializedName("driver")
    private String driver;

    @SerializedName("driverPhone")
    private String driverPhone;

    @SerializedName("flagRoute")
    private String flagRoute;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("originId")
    private String originId;

    @SerializedName("originName")
    private String originName;

    @SerializedName("originSpe")
    private String originSpe;

    @SerializedName("policeNo")
    private String policeNo;

    @SerializedName("serialPhone")
    private String serialPhone;

    @SerializedName("speNo")
    private String speNo;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("timeArrive")
    private String timeArrive;

    @SerializedName("timeArriveWeb")
    private String timeArriveWeb;

    @SerializedName("timeCheckout")
    private String timeCheckout;

    @SerializedName("timeCheckoutSubdist")
    private String timeCheckoutSubdist;

    @SerializedName("timeCheckoutWeb")
    private String timeCheckoutWeb;

    @SerializedName("timeFinishBongkar")
    private String timeFinishBongkar;

    @SerializedName("timeFinishBongkarWeb")
    private String timeFinishBongkarWeb;

    @SerializedName("timeInput")
    private String timeInput;

    @SerializedName("timeInputWeb")
    private String timeInputWeb;

    @SerializedName("timeStartBongkar")
    private String timeStartBongkar;

    @SerializedName("timeStartBongkarWeb")
    private String timeStartBongkarWeb;

    public String getSpeNo() {
        return this.speNo;
    }

    public void setSpeNo(String str) {
        this.speNo = str;
    }

    public String getOriginSpe() {
        return this.originSpe;
    }

    public void setOriginSpe(String str) {
        this.originSpe = str;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String str) {
        this.driver = str;
    }

    public String getPoliceNo() {
        return this.policeNo;
    }

    public void setPoliceNo(String str) {
        this.policeNo = str;
    }

    public String getDriverPhone() {
        return this.driverPhone;
    }

    public void setDriverPhone(String str) {
        this.driverPhone = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getSerialPhone() {
        return this.serialPhone;
    }

    public void setSerialPhone(String str) {
        this.serialPhone = str;
    }

    public String getOriginId() {
        return this.originId;
    }

    public void setOriginId(String str) {
        this.originId = str;
    }

    public String getOriginName() {
        return this.originName;
    }

    public void setOriginName(String str) {
        this.originName = str;
    }

    public String getDestinationId() {
        return this.destinationId;
    }

    public void setDestinationId(String str) {
        this.destinationId = str;
    }

    public String getDestinationName() {
        return this.destinationName;
    }

    public void setDestinationName(String str) {
        this.destinationName = str;
    }

    public String getDestinationType() {
        return this.destinationType;
    }

    public void setDestinationType(String str) {
        this.destinationType = str;
    }

    public String getFlagRoute() {
        return this.flagRoute;
    }

    public void setFlagRoute(String str) {
        this.flagRoute = str;
    }

    public String getTimeInput() {
        return this.timeInput;
    }

    public void setTimeInput(String str) {
        this.timeInput = str;
    }

    public String getTimeCheckout() {
        return this.timeCheckout;
    }

    public void setTimeCheckout(String str) {
        this.timeCheckout = str;
    }

    public String getTimeArrive() {
        return this.timeArrive;
    }

    public void setTimeArrive(String str) {
        this.timeArrive = str;
    }

    public String getTimeStartBongkar() {
        return this.timeStartBongkar;
    }

    public void setTimeStartBongkar(String str) {
        this.timeStartBongkar = str;
    }

    public String getTimeFinishBongkar() {
        return this.timeFinishBongkar;
    }

    public void setTimeFinishBongkar(String str) {
        this.timeFinishBongkar = str;
    }

    public String getTimeCheckoutSubdist() {
        return this.timeCheckoutSubdist;
    }

    public void setTimeCheckoutSubdist(String str) {
        this.timeCheckoutSubdist = str;
    }

    public String getTimeInputWeb() {
        return this.timeInputWeb;
    }

    public void setTimeInputWeb(String str) {
        this.timeInputWeb = str;
    }

    public String getTimeCheckoutWeb() {
        return this.timeCheckoutWeb;
    }

    public void setTimeCheckoutWeb(String str) {
        this.timeCheckoutWeb = str;
    }

    public String getTimeArriveWeb() {
        return this.timeArriveWeb;
    }

    public void setTimeArriveWeb(String str) {
        this.timeArriveWeb = str;
    }

    public String getTimeStartBongkarWeb() {
        return this.timeStartBongkarWeb;
    }

    public void setTimeStartBongkarWeb(String str) {
        this.timeStartBongkarWeb = str;
    }

    public String getTimeFinishBongkarWeb() {
        return this.timeFinishBongkarWeb;
    }

    public void setTimeFinishBongkarWeb(String str) {
        this.timeFinishBongkarWeb = str;
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
}
