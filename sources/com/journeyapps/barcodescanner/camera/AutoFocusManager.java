package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes2.dex */
public final class AutoFocusManager {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private static final String TAG = "AutoFocusManager";
    private int MESSAGE_FOCUS = 1;
    private final Camera.AutoFocusCallback autoFocusCallback;
    private final Camera camera;
    private final Handler.Callback focusHandlerCallback;
    private boolean focusing;
    private Handler handler;
    private boolean stopped;
    private final boolean useAutoFocus;

    static {
        ArrayList arrayList = new ArrayList(2);
        FOCUS_MODES_CALLING_AF = arrayList;
        arrayList.add(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
        arrayList.add("macro");
    }

    public AutoFocusManager(Camera camera, CameraSettings cameraSettings) {
        Handler.Callback callback = new Handler.Callback() { // from class: com.journeyapps.barcodescanner.camera.AutoFocusManager.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message.what != AutoFocusManager.this.MESSAGE_FOCUS) {
                    return false;
                }
                AutoFocusManager.this.focus();
                return true;
            }
        };
        this.focusHandlerCallback = callback;
        this.autoFocusCallback = new Camera.AutoFocusCallback() { // from class: com.journeyapps.barcodescanner.camera.AutoFocusManager.2
            @Override // android.hardware.Camera.AutoFocusCallback
            public void onAutoFocus(boolean z, Camera camera2) {
                AutoFocusManager.this.handler.post(new Runnable() { // from class: com.journeyapps.barcodescanner.camera.AutoFocusManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AutoFocusManager.this.focusing = false;
                        AutoFocusManager.this.autoFocusAgainLater();
                    }
                });
            }
        };
        this.handler = new Handler(callback);
        this.camera = camera;
        String focusMode = camera.getParameters().getFocusMode();
        boolean z = cameraSettings.isAutoFocusEnabled() && FOCUS_MODES_CALLING_AF.contains(focusMode);
        this.useAutoFocus = z;
        Log.i(TAG, "Current focus mode '" + focusMode + "'; use auto focus? " + z);
        start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void autoFocusAgainLater() {
        if (!this.stopped && !this.handler.hasMessages(this.MESSAGE_FOCUS)) {
            Handler handler = this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(this.MESSAGE_FOCUS), AUTO_FOCUS_INTERVAL_MS);
        }
    }

    public void start() {
        this.stopped = false;
        focus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void focus() {
        if (!this.useAutoFocus || this.stopped || this.focusing) {
            return;
        }
        try {
            this.camera.autoFocus(this.autoFocusCallback);
            this.focusing = true;
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected exception while focusing", e);
            autoFocusAgainLater();
        }
    }

    private void cancelOutstandingTask() {
        this.handler.removeMessages(this.MESSAGE_FOCUS);
    }

    public void stop() {
        this.stopped = true;
        this.focusing = false;
        cancelOutstandingTask();
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
