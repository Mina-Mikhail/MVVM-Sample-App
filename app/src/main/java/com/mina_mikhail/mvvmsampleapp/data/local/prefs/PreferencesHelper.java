package com.mina_mikhail.mvvmsampleapp.data.local.prefs;

import android.support.annotation.Nullable;
import com.mina_mikhail.mvvmsampleapp.data.model.api.AppSettings;
import com.mina_mikhail.mvvmsampleapp.data.model.api.User;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public interface PreferencesHelper {

  User getLoggedInUser();

  void setLoggedInUser(User user);

  boolean isLoggedIn();

  @Nullable
  String getToken();

  boolean isFirstTime();

  void setFirstTime(boolean isFirstTime);

  boolean isTokenSentBefore();

  void setPushTokenSent(boolean isSent);

  void setFirebaseToken(String token);

  String getFirebaseToken();

  void setAppSettings(AppSettings settings);

  AppSettings getAppSettings();

  void clearPreferences();
}