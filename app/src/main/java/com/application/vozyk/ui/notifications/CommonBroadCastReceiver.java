package com.application.vozyk.ui.notifications;


import static com.application.vozyk.ui.notifications.AlarmManagerHandler.CHANNEL_ID;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.application.vozyk.R;
import com.application.vozyk.ui.notifications.AlarmManagerHandler;


public class CommonBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("CommonBroadCastReceiver", "Receiver Started");

        boolean flag = intent.getBooleanExtra("flag", true);
        if (flag) {
            String DoctorName = intent.getStringExtra("DoctorName");
            String reason = intent.getStringExtra("reason");
            showNotificationDoc(context, DoctorName, reason);
        } else {
            String TestName = intent.getStringExtra("TestName");
            showNotificationLab(context, TestName);
        }

    }

    public void showNotificationDoc(Context context, String DoctorName, String reason) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.voz)
                .setContentTitle("Voz Alert")
                .setContentText("You have an Appointment")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Please go to " + DoctorName + " on time for " + reason))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(AlarmManagerHandler.setUniqueNotificationId(), builder.build());
    }

    public void showNotificationLab(Context context, String TestName) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.voz)
                .setContentTitle("Voz Alert")
                .setContentText("You have a Lab Test")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Please go for " + TestName + " test"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(AlarmManagerHandler.setUniqueNotificationId(), builder.build());
    }
}
