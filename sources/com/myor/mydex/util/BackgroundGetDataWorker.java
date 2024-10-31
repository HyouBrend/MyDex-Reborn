package com.myor.mydex.util;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.myor.mydex.apiclient.ApiClient;
import com.myor.mydex.entity.MonitoringDto;
import com.myor.mydex.entity.MonitoringH;
import com.myor.mydex.entity.ResponseDto;
import com.myor.mydex.repository.SpeRepo;
import com.myor.mydex.rest.SpeRest;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class BackgroundGetDataWorker extends Worker {
    private ApiClient apiClient;
    private Context context;
    private double latitude;
    private double longitude;
    private SpeRepo speRepo;
    private SpeRest speRest;

    public BackgroundGetDataWorker(Context context, WorkerParameters workerParameters, Context context2) {
        super(context, workerParameters);
        this.latitude = Utils.DOUBLE_EPSILON;
        this.longitude = Utils.DOUBLE_EPSILON;
        this.context = context2;
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        this.apiClient = new ApiClient(this.context);
        this.speRepo = new SpeRepo(this.context);
        Log.i("RunServiceWorker", "DO WORK");
        this.speRest = (SpeRest) this.apiClient.getClientToWsMobile(this.context).create(SpeRest.class);
        syncMasterBackground();
        return ListenableWorker.Result.success();
    }

    public void syncMasterBackground() {
        List<MonitoringH> dataMonitoring = this.speRepo.getDataMonitoring();
        String speNo = dataMonitoring.get(0).getSpeNo();
        int parseInt = Integer.parseInt(dataMonitoring.get(0).getStatus());
        MonitoringDto monitoringDto = new MonitoringDto();
        if (parseInt < 2) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            LocationRequest create = LocationRequest.create();
            create.setPriority(100);
            create.setInterval(1800000L);
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location result = fusedLocationProviderClient.getLastLocation().getResult();
                if (result != null) {
                    this.latitude = result.getLatitude();
                    this.longitude = result.getLongitude();
                } else {
                    this.latitude = Utils.DOUBLE_EPSILON;
                    this.longitude = Utils.DOUBLE_EPSILON;
                }
                monitoringDto.setLatitude(String.valueOf(this.latitude));
                monitoringDto.setLongitude(String.valueOf(this.longitude));
                monitoringDto.setSpeNo(speNo);
                this.speRest.updateLocation(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.util.BackgroundGetDataWorker.1
                    @Override // retrofit2.Callback
                    public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                        if (response.isSuccessful()) {
                            Log.i("RunServiceWorker", "Success Update Location");
                            NotificationService.getInstance(BackgroundGetDataWorker.this.getApplicationContext()).successLocation("Success Update Location");
                        } else {
                            Log.i("RunServiceWorker", "Failed Upload Location");
                            NotificationService.getInstance(BackgroundGetDataWorker.this.getApplicationContext()).failedNotification("Failed Update Location");
                        }
                    }

                    @Override // retrofit2.Callback
                    public void onFailure(Call<ResponseDto> call, Throwable th) {
                        th.getMessage();
                        Log.i("RunServiceWorker", "Failed Update Location");
                        NotificationService.getInstance(BackgroundGetDataWorker.this.getApplicationContext()).failedNotification("Failed Update Location");
                    }
                });
            }
        }
    }
}
