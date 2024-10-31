package com.myor.mydex.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class ResponseDto {

    @SerializedName("errorCode")
    private String errorCode;

    @SerializedName("errorMessage")
    private String errorMessage;

    @SerializedName("mReason")
    private List<Reason> mReason;

    @SerializedName("mWarehouse")
    private List<BranchDto> mWarehouse;

    @SerializedName("mXkey")
    private List<Xkey> mXkey;

    @SerializedName("monitoring")
    private MonitoringDto monitoring;

    @SerializedName("monitoringDetail")
    private List<MonitoringD> monitoringDetail;

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public MonitoringDto getMonitoring() {
        return this.monitoring;
    }

    public void setMonitoring(MonitoringDto monitoringDto) {
        this.monitoring = monitoringDto;
    }

    public List<MonitoringD> getMonitoringDetail() {
        return this.monitoringDetail;
    }

    public void setMonitoringDetail(List<MonitoringD> list) {
        this.monitoringDetail = list;
    }

    public List<Reason> getmReason() {
        return this.mReason;
    }

    public void setmReason(List<Reason> list) {
        this.mReason = list;
    }

    public List<Xkey> getmXkey() {
        return this.mXkey;
    }

    public void setmXkey(List<Xkey> list) {
        this.mXkey = list;
    }

    public List<BranchDto> getmWarehouse() {
        return this.mWarehouse;
    }

    public void setmWarehouse(List<BranchDto> list) {
        this.mWarehouse = list;
    }
}
