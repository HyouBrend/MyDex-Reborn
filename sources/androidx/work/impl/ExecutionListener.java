package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

/* loaded from: classes.dex */
public interface ExecutionListener {
    void onExecuted(WorkGenerationalId id, boolean needsReschedule);
}