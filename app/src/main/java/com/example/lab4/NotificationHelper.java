package com.example.lab4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

//Create Notifications including Notification channels
public class NotificationHelper extends ContextWrapper {
    public static final String channel1_Id = "channel1ID";
    public static final String channel1_Name = "Write Message";
    public static final String channel2_Id = "channel2ID";
    public static final String channel2_Name = "Write Message";
    public static final String channel3_Id = "channel3ID";
    public static final String channel3_Name = "Set Alarm";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        //Because API level >26 check the version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {

        //create channel1
        NotificationChannel channel1 = new NotificationChannel(channel1_Id, channel1_Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel1);
        //create channel2
        NotificationChannel channel2 = new NotificationChannel(channel2_Id, channel2_Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.colorPrimary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel2);
        //create channel3
        NotificationChannel channel3 = new NotificationChannel(channel3_Id, channel3_Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.colorPrimary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel3);
    }

    //Create channel if pre-exists
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }


    //build notificaations for both channels
    public NotificationCompat.Builder getChannel1Notification(String title, String msg){
        return new NotificationCompat.Builder(getApplicationContext(),channel1_Id)
               .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_alert);
    }

    public NotificationCompat.Builder getChannel2Notification(String title, String msg){
        return new NotificationCompat.Builder(getApplicationContext(),channel2_Id)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_alert);
    }

    //build notification for alarm channel
    public NotificationCompat.Builder getChannel3Notification(String title, String msg){
        return new NotificationCompat.Builder(getApplicationContext(),channel3_Id)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_alarm);
    }
}
