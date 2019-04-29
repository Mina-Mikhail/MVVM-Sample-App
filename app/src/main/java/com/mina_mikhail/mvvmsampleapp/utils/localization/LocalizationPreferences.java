package com.mina_mikhail.mvvmsampleapp.utils.localization;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.mina_mikhail.mvvmsampleapp.app.MyApplication;
import java.util.Locale;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class LocalizationPreferences {

  private static final String PREFERENCE_LANGUAGE = "pref_language";
  private static final String KEY_LANGUAGE = "key_language";
  private static Locale DEFAULT_LANGUAGE = Locale.ENGLISH;

  public static Locale getDefaultLanguage() {
    return DEFAULT_LANGUAGE;
  }

  public static void setDefaultLanguage(Locale locale) {
    DEFAULT_LANGUAGE = locale;
  }

  public static void setLanguage(Context context, Locale locale) {
    Locale.setDefault(locale);
    SharedPreferences.Editor editor = getLanguagePreference(context).edit();
    editor.putString(KEY_LANGUAGE, locale.toString());
    editor.apply();
  }

  public static Locale getLanguage(Context context) {
    String[] language =
        getLanguagePreference(context).getString(KEY_LANGUAGE, DEFAULT_LANGUAGE.toString())
            .split("_");
    Locale locale;
    if (language.length == 1) {
      locale = new Locale(language[0]);
    } else if (language.length == 2) {
      locale = new Locale(language[0], language[1].toUpperCase());
    } else {
      locale = DEFAULT_LANGUAGE;
    }
    return locale;
  }

  public static String getLangId() {
    String locale = getLanguagePreference(MyApplication.getInstance())
        .getString(KEY_LANGUAGE, DEFAULT_LANGUAGE.toString());

    if (locale.equals(DEFAULT_LANGUAGE.toString())) {
      return LocalizationUtility.ENGLISH_ID;
    } else {
      return LocalizationUtility.ARABIC_ID;
    }
  }

  private static SharedPreferences getLanguagePreference(Context context) {
    return context.getSharedPreferences(PREFERENCE_LANGUAGE, Activity.MODE_PRIVATE);
  }
}