package com.myor.mydex.util;

import android.content.Context;
import android.media.MediaDrm;
import android.os.Build;
import android.provider.Settings;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes2.dex */
public class DeviceIdentifier {
    private Context context;

    public DeviceIdentifier(Context context) {
        this.context = context;
    }

    public String getUniqueID() {
        MediaDrm mediaDrm;
        String replaceAll;
        if (Build.VERSION.SDK_INT >= 28) {
            replaceAll = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
        } else {
            MediaDrm mediaDrm2 = null;
            try {
                mediaDrm = new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L));
            } catch (Exception unused) {
                mediaDrm = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                replaceAll = Arrays.toString(mediaDrm.getPropertyByteArray("deviceUniqueId")).replaceAll("[^a-zA-Z0-9]+", "");
                if (Build.VERSION.SDK_INT > 28) {
                    mediaDrm.close();
                } else {
                    mediaDrm.release();
                }
            } catch (Exception unused2) {
                if (Build.VERSION.SDK_INT > 28) {
                    mediaDrm.close();
                } else {
                    mediaDrm.release();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                mediaDrm2 = mediaDrm;
                if (Build.VERSION.SDK_INT > 28) {
                    mediaDrm2.close();
                } else {
                    mediaDrm2.release();
                }
                throw th;
            }
        }
        return replaceAll.substring(0, 15).toLowerCase() + new UtilsTools().getCurrentTimeSample2();
    }
}
