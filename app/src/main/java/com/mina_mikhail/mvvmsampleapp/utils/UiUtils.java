package com.mina_mikhail.mvvmsampleapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mina_mikhail.mvvmsampleapp.R;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public final class UiUtils {

  public static void setUpToolBar(AppCompatActivity activity, Toolbar toolbar, String title,
      int navigationIcon, OnBackPressedListener listener) {
    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);

    if (navigationIcon != 0) {
      toolbar.setNavigationIcon(activity.getResources().getDrawable(navigationIcon));
      if (listener == null) {
        toolbar.setNavigationOnClickListener(view -> activity.onBackPressed());
      } else {
        toolbar.setNavigationOnClickListener(view -> listener.onBackPressed());
      }
    } else {
      toolbar.setNavigationIcon(null);
    }

    activity.setTitle("");
    toolbarTitle.setText(title);
  }

  interface OnBackPressedListener {
    void onBackPressed();
  }

  public static void setUpToolBar(AppCompatActivity activity, Toolbar toolbar, String title) {
    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);

    toolbar.setNavigationIcon(null);

    activity.setTitle("");
    toolbarTitle.setText(title);
  }

  public static void configRecyclerView(RecyclerView recyclerView,
      boolean isVertical) {
    recyclerView.setHasFixedSize(true);
    recyclerView.setNestedScrollingEnabled(false);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    if (isVertical) {
      recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    } else {
      recyclerView.setLayoutManager(
          new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL,
              false));
    }
  }

  public static void configGridRecyclerView(RecyclerView recyclerView, int spanCount) {
    recyclerView.setHasFixedSize(true);
    recyclerView.setNestedScrollingEnabled(false);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    recyclerView.setLayoutManager(
        new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
  }

  public static void verifyStringAndSet(TextView textView, String text) {
    if (text != null && text.length() > 0) {
      textView.setText(text.trim());
    } else {
      textView.setText("");
    }
  }

  public static void setProgressBarColor(Context context, ProgressBar progressBar, int color) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
      DrawableCompat
          .setTint(wrapDrawable.mutate(), ContextCompat.getColor(context, color));
      progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
    } else {
      progressBar
          .getIndeterminateDrawable()
          .setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN);
    }
  }

  public static Bitmap getBitmapFromView(View view) {
    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(bitmap);
    view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    view.draw(c);
    return bitmap;
  }

  public static void setOptionMenuItemColor(MenuItem menuItem, int color) {
    Drawable drawable = menuItem.getIcon();
    if (drawable != null) {
      drawable.mutate();
      drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }
  }
}