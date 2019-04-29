package com.mina_mikhail.mvvmsampleapp.ui.base;

import android.arch.lifecycle.ViewModel;
import com.mina_mikhail.mvvmsampleapp.data.local.prefs.AppPreferencesHelper;
import com.mina_mikhail.mvvmsampleapp.data.remote.NetworkHelper;
import com.mina_mikhail.mvvmsampleapp.utils.DisplayLoader;
import com.mina_mikhail.mvvmsampleapp.utils.SingleLiveEvent;
import com.mina_mikhail.mvvmsampleapp.utils.display_message.DisplayMessage;
import io.reactivex.disposables.CompositeDisposable;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class BaseViewModel
    extends ViewModel {

  private AppPreferencesHelper preferencesHelper;
  private CompositeDisposable mCompositeDisposable;
  private DisplayMessage message;
  private DisplayLoader loader;
  private SingleLiveEvent<Void> apiFail;
  private SingleLiveEvent<Void> hideKeyboard;
  public NetworkHelper helper;

  public BaseViewModel() {
    preferencesHelper = new AppPreferencesHelper();
    mCompositeDisposable = new CompositeDisposable();
    message = new DisplayMessage();
    loader = new DisplayLoader();
    apiFail = new SingleLiveEvent<>();
    hideKeyboard = new SingleLiveEvent<>();
  }

  public CompositeDisposable getDisposable() {
    return mCompositeDisposable;
  }

  public AppPreferencesHelper getPreferencesHelper() {
    return preferencesHelper;
  }

  public DisplayMessage getMessageObserver() {
    return message;
  }

  public DisplayLoader getLoaderObserver() {
    return loader;
  }

  public SingleLiveEvent<Void> apiFail() {
    return apiFail;
  }

  public SingleLiveEvent<Void> hideKeyboard() {
    return hideKeyboard;
  }

  public void onDispose() {
    if (!mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
      mCompositeDisposable.dispose();
    }
    if (helper != null) {
      helper.dispose();
    }
  }
}