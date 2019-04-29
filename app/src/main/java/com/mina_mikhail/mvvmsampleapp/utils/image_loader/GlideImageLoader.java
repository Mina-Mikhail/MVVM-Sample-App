package com.mina_mikhail.mvvmsampleapp.utils.image_loader;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class GlideImageLoader {

  private ImageView mImageView;
  private ProgressBar mProgressBar;

  public GlideImageLoader(ImageView imageView, ProgressBar progressBar) {
    mImageView = imageView;
    mProgressBar = progressBar;
  }

  public void load(final String url, RequestOptions options, boolean cacheImage) {
    if (url == null || options == null) return;

    onConnecting();

    //set Listener & start
    ImageUtils.expect(url, new ImageUtils.UIonProgressListener() {
      @Override
      public void onProgress(long bytesRead, long expectedLength) {
        if (mProgressBar != null) {
          mProgressBar.setProgress((int) (100 * bytesRead / expectedLength));
        }
      }

      @Override
      public float getGranualityPercentage() {
        return 1.0f;
      }
    });

    //Get Image
    if (cacheImage) {
      Glide.with(mImageView.getContext())
          .load(url)
          .transition(withCrossFade())
          .apply(options)
          .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                Target<Drawable> target, boolean isFirstResource) {
              ImageUtils.forget(url);
              onFinished();
              return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                DataSource dataSource, boolean isFirstResource) {
              ImageUtils.forget(url);
              onFinished();
              return false;
            }
          })
          .into(mImageView);
    } else {
      Glide.with(mImageView.getContext())
          .load(url)
          .transition(withCrossFade())
          .apply(options.skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
          .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                Target<Drawable> target, boolean isFirstResource) {
              ImageUtils.forget(url);
              onFinished();
              return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                DataSource dataSource, boolean isFirstResource) {
              ImageUtils.forget(url);
              onFinished();
              return false;
            }
          })
          .into(mImageView);
    }
  }

  private void onConnecting() {
    if (mProgressBar != null) mProgressBar.setVisibility(View.VISIBLE);
  }

  private void onFinished() {
    if (mProgressBar != null && mImageView != null) {
      mProgressBar.setVisibility(View.GONE);
      mImageView.setVisibility(View.VISIBLE);
    }
  }
}