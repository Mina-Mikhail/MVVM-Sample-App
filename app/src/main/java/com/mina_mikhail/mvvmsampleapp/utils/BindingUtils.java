package com.mina_mikhail.mvvmsampleapp.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.mina_mikhail.mvvmsampleapp.R;
import com.mina_mikhail.mvvmsampleapp.utils.image_loader.GlideImageLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class BindingUtils {

  @BindingAdapter({ "load_drawable" })
  public static void loadDrawable(ImageView imageView, Drawable drawable) {
    imageView.setImageDrawable(drawable);
  }

  @BindingAdapter({ "load_background" })
  public static void loadBackground(View view, Drawable drawable) {
    view.setBackground(drawable);
  }

  @BindingAdapter({ "load_profile_image" })
  public static void loadProfileImage(ImageView imageView, String imageURL) {
    if (imageURL != null && !TextUtils.isEmpty(imageURL)) {
      Picasso.with(imageView.getContext())
          .load(imageURL)
          .placeholder(R.drawable.ic_user_round)
          .error(R.drawable.ic_user_round)
          .into(imageView, new Callback() {
            @Override
            public void onSuccess() {
              imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
              imageView
                  .setImageDrawable(
                      imageView.getContext().getResources().getDrawable(R.drawable.ic_user_round));
            }
          });
    } else {
      imageView.setVisibility(View.VISIBLE);
      imageView.setImageDrawable(
          imageView.getContext().getResources().getDrawable(R.drawable.ic_user_round));
    }
  }

  @BindingAdapter(value = { "load_image", "progress_view" }, requireAll = false)
  public static void loadImage(ImageView imageView, String imageURL,
      ProgressBar progressBar) {
    if (imageURL != null && !TextUtils.isEmpty(imageURL)) {
      RequestOptions options = new RequestOptions()
          .centerCrop()
          .placeholder(R.color.backgroundGray)
          .error(R.drawable.bg_no_image)
          .priority(Priority.HIGH);
      new GlideImageLoader(imageView, progressBar).load(imageURL, options, true);
    } else {
      if (progressBar != null) progressBar.setVisibility(View.GONE);
      imageView.setImageDrawable(null);
      imageView.setImageDrawable(
          imageView.getContext().getResources().getDrawable(R.drawable.bg_no_image));
      imageView.setVisibility(View.VISIBLE);
    }
  }
}