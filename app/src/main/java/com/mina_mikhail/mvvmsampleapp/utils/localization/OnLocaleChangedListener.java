package com.mina_mikhail.mvvmsampleapp.utils.localization;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public interface OnLocaleChangedListener {
  void onBeforeLocaleChanged();

  void onAfterLocaleChanged();
}