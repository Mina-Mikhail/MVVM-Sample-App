package com.mina_mikhail.mvvmsampleapp.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import com.mina_mikhail.mvvmsampleapp.BR;
import com.mina_mikhail.mvvmsampleapp.R;
import com.mina_mikhail.mvvmsampleapp.databinding.ActivitySplashBinding;
import com.mina_mikhail.mvvmsampleapp.ui.base.BaseActivity;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class SplashActivity
    extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

  private SplashViewModel mViewModel;

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_splash;
  }

  @Override
  public SplashViewModel getViewModel() {
    return mViewModel;
  }

  @Override
  protected void setUpViewModel() {
    mViewModel = new SplashViewModel();
    mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
    getViewDataBinding().setViewModel(getViewModel());
    initBaseObservables();
  }

  @Override
  protected void setUpViews() {

  }

  @Override
  protected void setUpObservables() {
    getViewModel().shouldOpenTutorial().observe(this, aVoid -> {

    });

    getViewModel().shouldOpenLogin().observe(this, aVoid -> {

    });

    getViewModel().shouldOpenHome().observe(this, aVoid -> {

    });
  }
}