package com.google.android.datatransport;

/* compiled from: com.google.android.datatransport:transport-api@@2.2.0 */
/* loaded from: classes.dex */
public interface Transport<T> {
    void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback);

    void send(Event<T> event);
}
