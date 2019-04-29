package com.mina_mikhail.mvvmsampleapp.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.mina_mikhail.mvvmsampleapp.app.MyApplication;
import com.mina_mikhail.mvvmsampleapp.data.model.api.AppSettings;
import com.mina_mikhail.mvvmsampleapp.data.model.api.User;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class AppPreferencesHelper
    implements PreferencesHelper {

  // persist data until user uninstall the app
  private static final String PREF_APP_SETTINGS = "PREF_APP_SETTINGS";

  // persist data until user logout
  private static final String PREF_USER_SESSION = "PREF_USER_SESSION";

  private static final String KEY_USER_DATA = "KEY_USER_DATA";
  private static final String KEY_FIRST_TIME = "KEY_FIRST_TIME";
  private static final String KEY_PUSH_TOKEN_IS_SENT = "KEY_PUSH_TOKEN_IS_SENT";
  private static final String KEY_APP_SETTINGS = "KEY_APP_SETTINGS";
  private static final String KEY_FIREBASE_TOKEN = "KEY_FIREBASE_TOKEN";

  private final SharedPreferences mSessionPrefs;
  private final SharedPreferences mAppPrefs;
  private static AppPreferencesHelper INSTANCE;

  public AppPreferencesHelper() {
    mSessionPrefs =
        MyApplication.getInstance().getSharedPreferences(PREF_USER_SESSION, Context.MODE_PRIVATE);

    mAppPrefs =
        MyApplication.getInstance().getSharedPreferences(PREF_APP_SETTINGS, Context.MODE_PRIVATE);
  }

  public static AppPreferencesHelper getInstance() {
    if (INSTANCE == null) INSTANCE = new AppPreferencesHelper();
    return INSTANCE;
  }

  @Override
  public User getLoggedInUser() {
    String userData = mSessionPrefs.getString(KEY_USER_DATA, "");
    Gson g = new Gson();

    return g.fromJson(userData, User.class);
  }

  @Override
  public void setLoggedInUser(User user) {
    mSessionPrefs.edit().putString(KEY_USER_DATA, new Gson().toJson(user)).apply();
  }

  @Override
  public boolean isLoggedIn() {
    return getLoggedInUser() != null && getLoggedInUser().getId() != 0;
  }

  @Override
  @Nullable
  public String getToken() {
    String token = isLoggedIn()
        ? getLoggedInUser().getApiToken()
        : null;

    if (token == null) {
      return null;
    } else {
      return "Bearer " + token;
    }
  }

  @Override
  public boolean isFirstTime() {
    return mAppPrefs.getBoolean(KEY_FIRST_TIME, true);
  }

  @Override
  public void setFirstTime(boolean isFirstTime) {
    mAppPrefs.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply();
  }

  @Override
  public boolean isTokenSentBefore() {
    return mSessionPrefs.getBoolean(KEY_PUSH_TOKEN_IS_SENT, false);
  }

  @Override
  public void setPushTokenSent(boolean isSent) {
    mSessionPrefs.edit().putBoolean(KEY_PUSH_TOKEN_IS_SENT, isSent).apply();
  }

  @Override
  public String getFirebaseToken() {
    return mSessionPrefs.getString(KEY_FIREBASE_TOKEN, "");
  }

  @Override
  public void setFirebaseToken(String token) {
    mSessionPrefs.edit().putString(KEY_FIREBASE_TOKEN, token).apply();
  }

  @Override
  public void setAppSettings(AppSettings settings) {
    mAppPrefs.edit().putString(KEY_APP_SETTINGS, new Gson().toJson(settings)).apply();
  }

  @Override
  public AppSettings getAppSettings() {
    String appSettings = mAppPrefs.getString(KEY_APP_SETTINGS, "");
    Gson g = new Gson();

    return g.fromJson(appSettings, AppSettings.class);
  }

  @Override
  public void clearPreferences() {
    mSessionPrefs.edit().clear().apply();
  }
}