package com.mina_mikhail.mvvmsampleapp.ui.base;

import android.content.Context;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mina_mikhail.mvvmsampleapp.utils.NetworkStateReceiver;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel>
    extends Fragment
    implements NetworkStateReceiver.NetworkStateReceiverListener {

  private NetworkStateReceiver networkStateReceiver;
  private BaseActivity mActivity;
  private View mRootView;
  private T mViewDataBinding;
  private V mViewModel;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof BaseActivity) {
      BaseActivity activity = (BaseActivity) context;
      this.mActivity = activity;
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mViewModel = getViewModel();
    setHasOptionsMenu(false);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
    mRootView = mViewDataBinding.getRoot();
    return mRootView;
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
    mViewDataBinding.executePendingBindings();

    setUpViewModel();
    setUpObservables();
    setUpViews();
  }

  public BaseActivity getBaseActivity() {
    return mActivity;
  }

  public T getViewDataBinding() {
    return mViewDataBinding;
  }

  public void hideKeyboard() {
    if (mActivity != null) {
      mActivity.hideKeyboard();
    }
  }

  public boolean isNetworkConnected() {
    return mActivity != null && mActivity.isNetworkConnected();
  }

  protected void initBaseObservables() {
    if (mActivity != null) {
      mActivity.initBaseObservables();
    }
  }

  public FragmentActivity getMyActivity() {
    return getActivity();
  }

  public Context getMyContext() {
    return getContext();
  }

  public void onApiFail() {
    mActivity.onApiFail();
  }

  public void onError(String message) {
    mActivity.onError(message);
  }

  public void onError(@StringRes int resId) {
    mActivity.onError(resId);
  }

  public void showMessage(String message) {
    mActivity.showMessage(message);
  }

  public void showMessage(@StringRes int resId) {
    mActivity.showMessage(resId);
  }

  public void showLoading() {
    mActivity.showLoading();
  }

  public void hideLoading() {
    mActivity.hideLoading();
  }

  public Context getMyAppContext() {
    return getContext().getApplicationContext();
  }

  public int getAppLanguageInt() {
    return mActivity.getAppLanguageInt();
  }

  public void setAppLanguage(String language) {
    mActivity.setAppLanguage(language);
  }

  public String getAppLanguageString() {
    return mActivity.getAppLanguageString();
  }

  public void afterLanguageChanged() {
    mActivity.afterLanguageChanged();
  }

  protected abstract void setUpViewModel();

  protected abstract void setUpViews();

  protected abstract void setUpObservables();

  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  public abstract int getBindingVariable();

  /**
   * @return layout resource id
   */
  public abstract
  @LayoutRes
  int getLayoutId();

  /**
   * Override for set view model
   *
   * @return view model instance
   */
  public abstract V getViewModel();

  @Override
  public void onDetach() {
    mActivity = null;
    super.onDetach();
  }

  @Override
  public void networkAvailable() {

  }

  @Override
  public void networkUnavailable() {

  }

  @Override
  public void onResume() {
    super.onResume();

    // Register the network state receiver to listen to network state change
    if (networkStateReceiver == null) {
      networkStateReceiver = new NetworkStateReceiver();
      networkStateReceiver.startListening(this);
      getBaseActivity().registerReceiver(networkStateReceiver,
          new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }
  }

  @Override
  public void onDestroy() {
    if (getViewModel() != null) {
      getViewModel().onDispose();
    }

    // Remove network state receiver and listener as we don't need them at this point
    if (networkStateReceiver != null) {
      networkStateReceiver.stopListening();
      getBaseActivity().unregisterReceiver(networkStateReceiver);
      networkStateReceiver = null;
    }

    super.onDestroy();
  }
}