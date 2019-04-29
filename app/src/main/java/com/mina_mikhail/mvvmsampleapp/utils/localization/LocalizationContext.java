package com.mina_mikhail.mvvmsampleapp.utils.localization;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class LocalizationContext
    extends ContextWrapper {

  public LocalizationContext(Context base) {
    super(base);
  }

  @Override
  public Resources getResources() {
    Configuration conf = super.getResources().getConfiguration();
    conf.locale = LocalizationPreferences.getLanguage(this);
    DisplayMetrics metrics = super.getResources().getDisplayMetrics();
    return new Resources(getAssets(), metrics, conf);
  }
}