package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "m_driver")
/* loaded from: classes2.dex */
public class DriverDto {

    @SerializedName("driverName")
    @DatabaseField(columnName = "driver_name")
    private String driverName;

    @SerializedName("driverPhone")
    @DatabaseField(columnName = "driver_phone")
    private String driverPhone;

    @SerializedName("policeNo")
    @DatabaseField(columnName = "police_no")
    private String policeNo;

    @SerializedName("serialPhone")
    @DatabaseField(columnName = "serial_phone")
    private String serialPhone;

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

    public String getSerialPhone() {
        return this.serialPhone;
    }

    public void setSerialPhone(String str) {
        this.serialPhone = str;
    }

    public String getPoliceNo() {
        return this.policeNo;
    }

    public void setPoliceNo(String str) {
        this.policeNo = str;
    }
}
