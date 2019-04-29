package com.mina_mikhail.mvvmsampleapp.utils.localization;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class LocalizationUtility {

  public static final String ENGLISH = "en";
  public static final String ARABIC = "ar";
  public static final String ENGLISH_ID = "1";
  public static final String ARABIC_ID = "2";

  public static Locale getLocaleFromConfiguration(Configuration configuration) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return configuration.getLocales().get(0);
    } else {
      return configuration.locale;
    }
  }

  public static Context applyLocalizationContext(Context baseContext) {
    Locale baseLocale = LocalizationUtility
        .getLocaleFromConfiguration(baseContext.getResources().getConfiguration());
    Locale currentLocale = LocalizationPreferences.getLanguage(baseContext);
    if (!baseLocale.toString().equalsIgnoreCase(currentLocale.toString())) {
      LocalizationContext context = new LocalizationContext(baseContext);
      Configuration config = context.getResources().getConfiguration();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocale(currentLocale);
        LocaleList localeList = new LocaleList(currentLocale);
        LocaleList.setDefault(localeList);
        config.setLocales(localeList);
        return context.createConfigurationContext(config);
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        config.setLocale(currentLocale);
        return context.createConfigurationContext(config);
      } else {
        return context;
      }
    } else {
      return baseContext;
    }
  }
}