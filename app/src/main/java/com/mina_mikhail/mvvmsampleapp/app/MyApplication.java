package com.mina_mikhail.mvvmsampleapp.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.mina_mikhail.mvvmsampleapp.BuildConfig;
import com.mina_mikhail.mvvmsampleapp.data.service.MyFirebaseMessagingService;
import com.mina_mikhail.mvvmsampleapp.utils.localization.LocalizationApplicationDelegate;
import io.fabric.sdk.android.Fabric;
import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class MyApplication
    extends Application {

  private LocalizationApplicationDelegate localizationDelegate =
      new LocalizationApplicationDelegate(this);

  private static MyApplication INSTANCE;

  public static MyApplication getInstance() {
    return INSTANCE;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE = this;

    setStrictMode();
    setTimber();
    initRXJava();
    startFirebaseNotificationService();

    Crashlytics crashlyticsKit = new Crashlytics.Builder()
        .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build();
    Fabric.with(this, crashlyticsKit);
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(localizationDelegate.attachBaseContext(base));
    MultiDex.install(this);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    localizationDelegate.onConfigurationChanged(this);
  }

  @Override
  public Context getApplicationContext() {
    return localizationDelegate.getApplicationContext(super.getApplicationContext());
  }

  private void setStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
          .detectDiskWrites()
          .detectNetwork()   // or .detectAll() for all detectable problems
          .penaltyLog()
          .build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
          .detectLeakedClosableObjects()
          .penaltyLog()
          .build());
    }
  }

  private void setTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  private void initRXJava() {
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer());
  }

  private void startFirebaseNotificationService() {
    if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)) {
      startService(new Intent(this, MyFirebaseMessagingService.class));
    }
  }
}