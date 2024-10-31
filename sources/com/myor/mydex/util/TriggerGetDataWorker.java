package com.myor.mydex.util;

import android.content.Context;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class TriggerGetDataWorker {
    private Context context;
    private String tagWorkerSync = "worker_sync_data";
    private String tagUtilData = "worker_delete_data";

    public TriggerGetDataWorker(Context context) {
        this.context = context;
    }

    public void runSchedulerSyncDataAutomatic() {
        closeTriggerRunning();
        runServiceWorker();
    }

    public void closeTriggerRunning() {
        Log.d("RunServiceWorker", "Cancel Running");
        WorkManager.getInstance(this.context).cancelAllWorkByTag(this.tagWorkerSync);
    }

    public void runServiceWorker() {
        PeriodicWorkRequest build = new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) LocationDataWorker.class, 15L, TimeUnit.MINUTES).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).addTag(this.tagWorkerSync).build();
        WorkManager.getInstance(this.context);
        WorkManager.getInstance(this.context).enqueueUniquePeriodicWork(this.tagWorkerSync, ExistingPeriodicWorkPolicy.UPDATE, build);
        Log.i("RunServiceWorker", build.getId() + " Data");
        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) BackgroundUtilWorker.class, 90L, TimeUnit.MINUTES);
        builder.addTag(this.tagUtilData);
        builder.build();
        WorkManager.getInstance(this.context).enqueueUniquePeriodicWork(this.tagUtilData, ExistingPeriodicWorkPolicy.REPLACE, builder.build());
    }
}
