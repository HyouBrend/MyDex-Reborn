package com.myor.mydex.rest;

import com.myor.mydex.entity.InputSPEDto;
import com.myor.mydex.entity.MonitoringDto;
import com.myor.mydex.entity.MonitoringPhoto;
import com.myor.mydex.entity.ResponseDto;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/* loaded from: classes2.dex */
public interface SpeRest {
    public static final String pathSync = "spe";

    @POST("spe/data-photo")
    Call<ResponseDto> dataPhoto(@Body MonitoringPhoto monitoringPhoto);

    @POST("spe/save")
    Call<ResponseDto> saveData(@Body InputSPEDto inputSPEDto);

    @POST("spe/sync")
    Call<ResponseDto> syncData(@Body InputSPEDto inputSPEDto);

    @POST("spe/update")
    Call<ResponseDto> updateData(@Body MonitoringDto monitoringDto);

    @POST("spe/update-location")
    Call<ResponseDto> updateLocation(@Body MonitoringDto monitoringDto);

    @POST("spe/update-reason/{speNo}/{reasonId}/{reasonType}")
    Call<ResponseDto> updateReason(@Path("speNo") String str, @Path("reasonId") String str2, @Path("reasonType") String str3);

    @POST("spe/upload/{spe_no}")
    @Multipart
    Call<ResponseBody> uploadPhoto(@Path("spe_no") String str, @Part List<MultipartBody.Part> list);
}
