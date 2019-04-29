package com.mina_mikhail.mvvmsampleapp.utils;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.mina_mikhail.mvvmsampleapp.R;

public class NotificationUtils {

  public static Notification buildNotification(Context context, Bitmap largeIcon, String title,
      String body, PendingIntent pendingIntent) {

    if (largeIcon == null) {
      largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
    }

    Uri RingTone = getDefaultRingTone();
    long[] vibration = new long[] { 500, 500, 500, 500 };

    NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(context, "chanel-ID");
    noBuilder.setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(largeIcon)
        .setContentTitle(title)
        .setContentText(body)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .setStyle(new NotificationCompat.BigTextStyle().bigText(body));

    noBuilder
        .setVibrate(vibration)
        .setSound(RingTone);

    return noBuilder.build();
  }

  public static void showNotification(Context context, Notification notification,
      int notificationId) {

    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel mChannel =
          new NotificationChannel("chanel-ID", "chanel-Name", NotificationManager.IMPORTANCE_HIGH);
      if (notificationManager != null) {
        notificationManager.createNotificationChannel(mChannel);
      }
    }

    if (notificationManager != null) {
      notificationManager.notify(notificationId, notification);
    }
  }

  private static Uri getDefaultRingTone() {
    return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
  }
}