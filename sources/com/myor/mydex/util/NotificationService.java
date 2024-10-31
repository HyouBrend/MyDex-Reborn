package com.myor.mydex.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.format.DateFormat;
import androidx.core.app.NotificationCompat;
import com.myor.mydex.R;
import java.util.Date;

/* loaded from: classes2.dex */
public class NotificationService {
    public static final int notifikasi = 1;
    private static NotificationService sInstance;
    public final String NOTIFICATION_CHANNEL_ID = "MYDEX";
    private Context context;
    public int textLogo;

    public NotificationService(Context context) {
        this.context = context;
    }

    public static NotificationService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NotificationService(context.getApplicationContext());
        }
        return sInstance;
    }

    public void syncNotification(String str) {
        String charSequence = DateFormat.format("dd-MMM-yy hh:mm a", new Date()).toString();
        this.textLogo = this.context.getResources().getIdentifier("@drawable/success_notif", null, this.context.getPackageName());
        if (Build.VERSION.SDK_INT >= 26) {
            String string = this.context.getResources().getString(R.string.notification_channel_name);
            String string2 = this.context.getResources().getString(R.string.notification_channel_description);
            NotificationChannel notificationChannel = new NotificationChannel("MYDEX", string, 3);
            notificationChannel.setDescription(string2);
            NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(1, new Notification.Builder(this.context, "MYDEX").setSmallIcon(this.textLogo).setAutoCancel(true).setContentTitle(str).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.textLogo)).setContentText(charSequence).setChannelId("MYDEX").build());
            return;
        }
        ((NotificationManager) this.context.getSystemService("notification")).notify(1, new NotificationCompat.Builder(this.context).setSmallIcon(this.textLogo).setAutoCancel(true).setContentTitle(str).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.textLogo)).setContentText(charSequence).build());
    }

    public void successLocation(String str) {
        String charSequence = DateFormat.format("dd-MMM-yy hh:mm a", new Date()).toString();
        this.textLogo = this.context.getResources().getIdentifier("@drawable/success_notif", null, this.context.getPackageName());
        if (Build.VERSION.SDK_INT >= 26) {
            String string = this.context.getResources().getString(R.string.notification_channel_name);
            String string2 = this.context.getResources().getString(R.string.notification_channel_description);
            NotificationChannel notificationChannel = new NotificationChannel("MYDEX", string, 3);
            notificationChannel.setDescription(string2);
            NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(1, new Notification.Builder(this.context, "MYDEX").setSmallIcon(this.textLogo).setAutoCancel(true).setContentTitle(str).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.textLogo)).setContentText(charSequence).setChannelId("MYDEX").build());
            return;
        }
        ((NotificationManager) this.context.getSystemService("notification")).notify(1, new NotificationCompat.Builder(this.context).setSmallIcon(this.textLogo).setAutoCancel(true).setContentTitle(str).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.textLogo)).setContentText(charSequence).build());
    }

    public void failedNotification(String str) {
        String charSequence = DateFormat.format("dd-MMM-yy hh:mm a", new Date()).toString();
        this.textLogo = this.context.getResources().getIdentifier("@drawable/cancel_notif", null, this.context.getPackageName());
        if (Build.VERSION.SDK_INT >= 26) {
            String string = this.context.getResources().getString(R.string.notification_channel_name);
            String string2 = this.context.getResources().getString(R.string.notification_channel_description);
            NotificationChannel notificationChannel = new NotificationChannel("MYDEX", string, 3);
            notificationChannel.setDescription(string2);
            NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(1, new Notification.Builder(this.context, "MYDEX").setSmallIcon(this.textLogo).setAutoCancel(true).setContentTitle(str).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.textLogo)).setContentText(charSequence).setChannelId("MYDEX").build());
            return;
        }
        ((NotificationManager) this.context.getSystemService("notification")).notify(1, new NotificationCompat.Builder(this.context).setSmallIcon(this.textLogo).setAutoCancel(true).setContentTitle(str).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.textLogo)).setContentText(charSequence).build());
    }
}
