/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.receiver;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

import id.husni.covninfo.activity.MainActivity;
import id.husni.covninfo.R;

public class DailyReceiver extends BroadcastReceiver {

    private static final int DAILY_REQUEST_CODE = 100;
    private static final String CHANNEL_ID = "Daily Notification Channel ID" ;
    private static final CharSequence CHANNEL_NAME = "Daily Notification Channel NAME";
    private static final int REQUEST_CODE_CONTENT_INTENT = 200;

    private int notifId = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        //jika kondisi terpenuhi, akan mmenjalankan method dibawah, dan mereset notifId menjadi 1
        showDailyReminderNotif(context);
        notifId = 1;
    }
    //method untuk set notifikasi
    public void setDailyNotif(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, DailyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Toast.makeText(context, R.string.daily_reminder_setup, Toast.LENGTH_SHORT).show();
    }
    //method untuk batalkan reminder
    public void setCancelDailyNotif(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        Toast.makeText(context, R.string.daily_reminder_canceled, Toast.LENGTH_SHORT).show();
    }
    //method untuk menampilkan notifikasi
    private void showDailyReminderNotif(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent toMainActivity = new Intent(context, MainActivity.class);
        PendingIntent pendingIntentContent = PendingIntent.getActivity(context, REQUEST_CODE_CONTENT_INTENT, toMainActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(context.getResources().getString(R.string.notif_title))
                .setContentIntent(pendingIntentContent)
                .setSmallIcon(R.drawable.ic_healing)
                .setSound(alarmSound)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000,1000,1000,1000,1000});
            builder.setChannelId(CHANNEL_ID);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();
        if (notificationManager != null) {
            notificationManager.notify(notifId,notification);
        }
    }
}
