package com.mina_mikhail.mvvmsampleapp.utils.localization;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Locale;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public abstract class LocalizationActivity
    extends AppCompatActivity
    implements OnLocaleChangedListener {

  private LocalizationActivityDelegate localizationDelegate =
      new LocalizationActivityDelegate(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    localizationDelegate.addOnLocaleChangedListener(this);
    localizationDelegate.onCreate(savedInstanceState);
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void onResume() {
    super.onResume();
    localizationDelegate.onResume(this);
  }

  @Override
  public void recreate() {
    super.recreate();

    changeLayoutDirection();
  }

  private void changeLayoutDirection() {
    if (getCurrentLanguage().equals(LocalizationUtility.ENGLISH)) {
      getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    } else if (getCurrentLanguage().equals(LocalizationUtility.ARABIC)) {
      getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(localizationDelegate.attachBaseContext(newBase));
  }

  @Override
  public Context getApplicationContext() {
    return localizationDelegate.getApplicationContext(super.getApplicationContext());
  }

  @Override
  public Resources getResources() {
    return localizationDelegate.getResources(super.getResources());
  }

  public final void setLanguage(String language) {
    localizationDelegate.setLanguage(this, language);
  }

  public final void setLanguage(Locale locale) {
    localizationDelegate.setLanguage(this, locale);
  }

  public final void setDefaultLanguage(String language) {
    localizationDelegate.setDefaultLanguage(language);
  }

  public final void setDefaultLanguage(Locale locale) {
    localizationDelegate.setDefaultLanguage(locale);
  }

  public final String getCurrentLanguage() {
    return localizationDelegate.getLanguage(this).toString();
  }

  // Just override method locale change event
  @Override
  public void onBeforeLocaleChanged() {
  }

  @Override
  public void onAfterLocaleChanged() {
    //  Intent intent = getIntent();
    //  finish();
    //  startActivity(intent);
  }
}