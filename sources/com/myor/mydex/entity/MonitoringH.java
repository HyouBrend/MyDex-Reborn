package com.myor.mydex.entity;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "t_monitoring_h")
/* loaded from: classes2.dex */
public class MonitoringH {

    @SerializedName("destinationId")
    @DatabaseField(columnName = "destination_id")
    private String destinationId;

    @SerializedName("destinationName")
    @DatabaseField(columnName = "destination_name")
    private String destinationName;

    @SerializedName("destinationType")
    @DatabaseField(columnName = "destination_type")
    private String destinationType;

    @SerializedName("driver")
    @DatabaseField(columnName = "driver")
    private String driver;

    @SerializedName("driverPhone")
    @DatabaseField(columnName = "driver_phone")
    private String driverPhone;

    @SerializedName("flagRoute")
    @DatabaseField(columnName = "flag_route")
    private String flagRoute;

    @SerializedName("originId")
    @DatabaseField(columnName = "origin_id")
    private String originId;

    @SerializedName("originName")
    @DatabaseField(columnName = "origin_name")
    private String originName;

    @SerializedName("policeNo")
    @DatabaseField(columnName = "police_no")
    private String policeNo;

    @SerializedName("serialPhone")
    @DatabaseField(columnName = "serial_phone")
    private String serialPhone;

    @SerializedName("speNo")
    @DatabaseField(columnName = "spe_no")
    private String speNo;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @DatabaseField(columnName = NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("timeArrive")
    @DatabaseField(columnName = "time_arrive")
    private String timeArrive;

    @SerializedName("timeCheckout")
    @DatabaseField(columnName = "time_checkout")
    private String timeCheckout;

    @SerializedName("timeCheckoutSubdist")
    @DatabaseField(columnName = "time_checkout_subdist")
    private String timeCheckoutSubdist;

    @SerializedName("timeFinishBongkar")
    @DatabaseField(columnName = "time_finish_bongkar")
    private String timeFinishBongkar;

    @SerializedName("timeInput")
    @DatabaseField(columnName = "time_input")
    private String timeInput;

    @SerializedName("timeStartBongkar")
    @DatabaseField(columnName = "time_start_bongkar")
    private String timeStartBongkar;

    public String getSpeNo() {
        return this.speNo;
    }

    public void setSpeNo(String str) {
        this.speNo = str;
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
}
