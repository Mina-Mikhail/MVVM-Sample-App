package com.mina_mikhail.mvvmsampleapp.data.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.mina_mikhail.mvvmsampleapp.R;
import com.mina_mikhail.mvvmsampleapp.data.model.other.BaseNotificationResponse;
import com.mina_mikhail.mvvmsampleapp.utils.NotificationUtils;
import timber.log.Timber;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class MyFirebaseMessagingService
    extends FirebaseMessagingService {

  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    if (remoteMessage.getData().size() > 0) {
      // in case we send notification from console with data {"key":"value"}
      // in case we send notification from backend {your_custom_object}
      Timber.d("Message data payload: " + remoteMessage.getData());

      BaseNotificationResponse baseNotificationResponse = new Gson()
          .fromJson(new Gson().toJson(remoteMessage.getData()), BaseNotificationResponse.class);

      String body = baseNotificationResponse.getBody();

      sendNotification(getString(R.string.app_name), body);
    } else if (remoteMessage.getNotification() != null) {
      // in case we send notification from console without data
      Timber.d("Message notification payload: " + remoteMessage.getNotification());
      String title = remoteMessage.getNotification().getTitle();
      String body = remoteMessage.getNotification().getBody();
      //show notification
      sendNotification(title, body);
    }
  }

  private void sendNotification(String title, String body) {
    Intent intent = new Intent();
    int notificationId = (int) System.currentTimeMillis();
    Bitmap largeIcon = null;

    //intent = new Intent(this, NotificationsActivity.class);

    PendingIntent pendingIntent =
        PendingIntent.getActivity(this, notificationId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT);

    Notification notification =
        NotificationUtils.buildNotification(this, largeIcon, title, body, pendingIntent);
    NotificationUtils.showNotification(this, notification, notificationId);
  }
}