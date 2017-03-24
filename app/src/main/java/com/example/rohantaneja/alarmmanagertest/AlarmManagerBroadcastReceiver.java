package com.example.rohantaneja.alarmmanagertest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by rohantaneja on 24/03/17.
 */

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Toast.makeText(context, "onReceive()", Toast.LENGTH_SHORT).show();

        //assigned a unique id to notifications
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;

        Notification mNotification = new Notification.Builder(context)
                .setContentTitle("Alarm")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
//                .setSound(soundUri)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(m, mNotification);
    }

    public void setAlarm(Context context, long alarmTime) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, alarmTime, pi);
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
