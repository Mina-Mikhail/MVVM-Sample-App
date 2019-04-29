package com.mina_mikhail.mvvmsampleapp.utils.localization;

import android.app.Application;
import android.content.Context;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class LocalizationApplicationDelegate {

  private Application application;

  public LocalizationApplicationDelegate(Application application) {
    this.application = application;
  }

  public void onConfigurationChanged(Context context) {
    LocalizationUtility.applyLocalizationContext(context);
  }

  public Context attachBaseContext(Context context) {
    return LocalizationUtility.applyLocalizationContext(context);
  }

  public Context getApplicationContext(Context applicationContext) {
    return LocalizationUtility.applyLocalizationContext(applicationContext);
  }
}