package com.mina_mikhail.mvvmsampleapp.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public abstract class BaseViewHolder
    extends RecyclerView.ViewHolder {

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void onBind(int position);

  public abstract void unbind();

  public abstract void clearAnimation();
}
