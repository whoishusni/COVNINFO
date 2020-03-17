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
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

import id.husni.covninfo.R;

public class DailyReceiver extends BroadcastReceiver {

    private static final int DAILY_REQUEST_CODE = 100;
    private static final String CHANNEL_ID = "Daily Notification Channel ID" ;
    private static final CharSequence CHANNEL_NAME = "Daily Notification Channel NAME";

    @Override
    public void onReceive(Context context, Intent intent) {
        showDailyReminderNotif(context);
    }

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

    private void showDailyReminderNotif(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentTitle(context.getResources().getString(R.string.notif_title))
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
        int notifId = 3;
        Notification notification = builder.build();
        if (notificationManager != null) {
            notificationManager.notify(notifId,notification);
        }
    }
}
