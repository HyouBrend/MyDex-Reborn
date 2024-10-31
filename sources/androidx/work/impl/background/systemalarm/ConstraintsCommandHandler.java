package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ConstraintsCommandHandler {
    private static final String TAG = Logger.tagWithPrefix("ConstraintsCmdHandler");
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private final int mStartId;
    private final WorkConstraintsTrackerImpl mWorkConstraintsTracker;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstraintsCommandHandler(Context context, int startId, SystemAlarmDispatcher dispatcher) {
        this.mContext = context;
        this.mStartId = startId;
        this.mDispatcher = dispatcher;
        this.mWorkConstraintsTracker = new WorkConstraintsTrackerImpl(dispatcher.getWorkManager().getTrackers(), (WorkConstraintsCallback) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleConstraintsChanged() {
        List<WorkSpec> scheduledWork = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.updateAll(this.mContext, scheduledWork);
        this.mWorkConstraintsTracker.replace(scheduledWork);
        ArrayList<WorkSpec> arrayList = new ArrayList(scheduledWork.size());
        long currentTimeMillis = System.currentTimeMillis();
        for (WorkSpec workSpec : scheduledWork) {
            String str = workSpec.id;
            if (currentTimeMillis >= workSpec.calculateNextRunTime() && (!workSpec.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(str))) {
                arrayList.add(workSpec);
            }
        }
        for (WorkSpec workSpec2 : arrayList) {
            String str2 = workSpec2.id;
            Intent createDelayMetIntent = CommandHandler.createDelayMetIntent(this.mContext, WorkSpecKt.generationalId(workSpec2));
            Logger.get().debug(TAG, "Creating a delay_met command for workSpec with id (" + str2 + ")");
            this.mDispatcher.getTaskExecutor().getMainThreadExecutor().execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, createDelayMetIntent, this.mStartId));
        }
        this.mWorkConstraintsTracker.reset();
    }
}
