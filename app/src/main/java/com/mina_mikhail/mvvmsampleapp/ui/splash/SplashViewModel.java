package com.mina_mikhail.mvvmsampleapp.ui.splash;

import android.arch.lifecycle.LiveData;
import android.os.Handler;
import com.mina_mikhail.mvvmsampleapp.ui.base.BaseViewModel;
import com.mina_mikhail.mvvmsampleapp.utils.SingleLiveEvent;


/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class SplashViewModel
    extends BaseViewModel {

  private SingleLiveEvent<Void> shouldOpenTutorial;
  private SingleLiveEvent<Void> shouldOpenLogin;
  private SingleLiveEvent<Void> shouldOpenHome;

  public SplashViewModel() {
    shouldOpenTutorial = new SingleLiveEvent<>();
    shouldOpenLogin = new SingleLiveEvent<>();
    shouldOpenHome = new SingleLiveEvent<>();

    getLoaderObserver().setValue(true);
    decideNavigation();
  }

  private void decideNavigation() {
    new Handler().postDelayed(() -> {
      getLoaderObserver().setValue(false);
      if (getPreferencesHelper().isFirstTime()) {
        shouldOpenTutorial.call();
      } else {
        openApp();
      }
    }, 2500);
  }

  void openApp() {
    if (getPreferencesHelper().isLoggedIn()) {
      shouldOpenHome.call();
    } else {
      shouldOpenLogin.call();
    }
  }

  LiveData<Void> shouldOpenTutorial() {
    return shouldOpenTutorial;
  }

  LiveData<Void> shouldOpenLogin() {
    return shouldOpenLogin;
  }

  LiveData<Void> shouldOpenHome() {
    return shouldOpenHome;
  }
}