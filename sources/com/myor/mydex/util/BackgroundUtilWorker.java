package com.myor.mydex.util;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.myor.mydex.database.DatabaseHelper;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
public class BackgroundUtilWorker extends Worker {
    private Context context;

    public BackgroundUtilWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.context = context;
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -15);
        Date time = calendar.getTime();
        for (File file : new File(DatabaseHelper.PHOTO_PATH_SDCARD).listFiles()) {
            if (file.getName().contains(".jpg") && file.isFile()) {
                try {
                    if (new Date(file.lastModified()).before(time)) {
                        file.delete();
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
        return ListenableWorker.Result.success();
    }
}
