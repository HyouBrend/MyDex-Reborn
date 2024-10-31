package com.myor.mydex.util;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
public class LocationDataWorker extends Worker {
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private static final String TAG = "MyWorker";
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private ApiClient apiClient;
    private double latitude;
    private double longitude;
    private Context mContext;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation;
    private LocationCallback mLocationCallback;
    private SpeRepo speRepo;
    private SpeRest speRest;

    public LocationDataWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.latitude = Utils.DOUBLE_EPSILON;
        this.longitude = Utils.DOUBLE_EPSILON;
        this.mContext = context;
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        this.apiClient = new ApiClient(this.mContext);
        this.speRepo = new SpeRepo(this.mContext);
        this.speRest = (SpeRest) this.apiClient.getClientToWsMobile(this.mContext).create(SpeRest.class);
        Log.d(TAG, "doWork: Done");
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.mContext);
        this.mLocationCallback = new LocationCallback() { // from class: com.myor.mydex.util.LocationDataWorker.1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
            }
        };
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(100);
        try {
            this.mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() { // from class: com.myor.mydex.util.LocationDataWorker.2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Location> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        LocationDataWorker.this.mLocation = task.getResult();
                        Log.d(LocationDataWorker.TAG, "Location : " + LocationDataWorker.this.mLocation);
                        List<MonitoringH> dataMonitoring = LocationDataWorker.this.speRepo.getDataMonitoring();
                        if (dataMonitoring.size() > 0) {
                            String speNo = dataMonitoring.get(0).getSpeNo();
                            int parseInt = Integer.parseInt(dataMonitoring.get(0).getStatus());
                            MonitoringDto monitoringDto = new MonitoringDto();
                            if (parseInt < 2) {
                                try {
                                    LocationDataWorker locationDataWorker = LocationDataWorker.this;
                                    locationDataWorker.latitude = locationDataWorker.mLocation.getLatitude();
                                    LocationDataWorker locationDataWorker2 = LocationDataWorker.this;
                                    locationDataWorker2.longitude = locationDataWorker2.mLocation.getLongitude();
                                } catch (Exception unused) {
                                    LocationDataWorker.this.latitude = Utils.DOUBLE_EPSILON;
                                    LocationDataWorker.this.longitude = Utils.DOUBLE_EPSILON;
                                }
                                monitoringDto.setLatitude(String.valueOf(LocationDataWorker.this.latitude));
                                monitoringDto.setLongitude(String.valueOf(LocationDataWorker.this.longitude));
                                monitoringDto.setSpeNo(speNo);
                                LocationDataWorker.this.speRest.updateLocation(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.util.LocationDataWorker.2.1
                                    @Override // retrofit2.Callback
                                    public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                                        if (response.isSuccessful()) {
                                            Log.i("RunServiceWorker", "Success Update Location");
                                            Log.d(LocationDataWorker.TAG, "Success Update Location");
                                            NotificationService.getInstance(LocationDataWorker.this.getApplicationContext()).successLocation("Success Update Location");
                                        } else {
                                            Log.i("RunServiceWorker", "Failed Upload Location");
                                            Log.d(LocationDataWorker.TAG, "Failed Upload Location");
                                            NotificationService.getInstance(LocationDataWorker.this.getApplicationContext()).failedNotification("Failed Update Location");
                                        }
                                    }

                                    @Override // retrofit2.Callback
                                    public void onFailure(Call<ResponseDto> call, Throwable th) {
                                        th.getMessage();
                                        Log.i("RunServiceWorker", "Failed Update Location");
                                        Log.d(LocationDataWorker.TAG, "Failed Update Location");
                                        NotificationService.getInstance(LocationDataWorker.this.getApplicationContext()).failedNotification("Failed Update Location");
                                    }
                                });
                            }
                            LocationDataWorker.this.mFusedLocationClient.removeLocationUpdates(LocationDataWorker.this.mLocationCallback);
                            return;
                        }
                        return;
                    }
                    Log.w(LocationDataWorker.TAG, "Failed to get location.");
                }
            });
        } catch (SecurityException e) {
            Log.e(TAG, "Lost location permission." + e);
        }
        try {
            this.mFusedLocationClient.requestLocationUpdates(locationRequest, null);
        } catch (SecurityException e2) {
            Log.e(TAG, "Lost location permission. Could not request updates. " + e2);
        }
        return ListenableWorker.Result.success();
    }
}
