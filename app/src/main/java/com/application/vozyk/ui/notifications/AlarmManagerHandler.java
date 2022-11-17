package com.application.vozyk.ui.notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class AlarmManagerHandler extends AppCompatActivity {
    public static final String CHANNEL_ID = "10";

    public static void addAlert(Context context, int hour, int minute, String medicineName, int notificationId, String Food) {

    Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        if (cal.getTimeInMillis() <= System.currentTimeMillis()) {
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
    }

    long time = cal.getTimeInMillis();

    Intent intent = new Intent(context, MyBroadcastReceiver.class)
            .putExtra("MedicineName", medicineName)
            .putExtra("Food", Food)
            .putExtra("time", time)
            .putExtra("notificationId", notificationId)
            .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);


        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        try {
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    } catch (Exception e) {
        Log.d("AlarmManagerError", e.toString());
        Toast.makeText(context.getApplicationContext(), "We cannot setup reminder on your Device", Toast.LENGTH_LONG).show();
    }
}

    public static void addAlert(Context context, long time, String medicineName, String Food, int notificationId) {
        Calendar cal = Calendar.getInstance();
        cal.getTimeInMillis();
        Intent intent = new Intent(context, MyBroadcastReceiver.class)
                .putExtra("MedicineName", medicineName)
                .putExtra("Food", Food)
                .putExtra("time", time)
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() + AlarmManager.INTERVAL_DAY, pendingIntent);


    }

    public static void createNotificationChannel(Context context) {
        CharSequence name = "Medicine Reminder";
        String description = "This is to send notification about Medicine reminders";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
    public static void cancelAlarm(Context context, MedicineRecordHandler mrh) {
        Intent intent = new Intent(context, MyBroadcastReceiver.class);

        for (TIME.AlarmBundle i : mrh.getReminder()) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i.getNotificationID(), intent, PendingIntent.FLAG_MUTABLE);
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }

    }
    public static void initAlarm(MedicineRecordHandler mrh, Context context) {

        for (TIME.AlarmBundle i : mrh.getReminder()) {
            int hour = Integer.parseInt(i.getTime().substring(0, 2));
            int minutes = Integer.parseInt(i.getTime().substring(2, 4));
            String Food;

            if (mrh.getBeforeFood())
                Food = "before food";
            else
                Food = "after food";
            AlarmManagerHandler.addAlert(context, hour, minutes, mrh.getName(), i.getNotificationID(), Food);
        }
    }
    public static int setUniqueNotificationId() {
        return ((int) ((Math.random() * (99999 - 11111)) + 11111)) + ((int) ((Math.random() * (9999 - 1111)) + 1111));
    }


}
