package com.mina_mikhail.mvvmsampleapp.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mina_mikhail.mvvmsampleapp.R;
import com.mina_mikhail.mvvmsampleapp.ui.base.BaseActivity;
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;
import java.util.Objects;

//TODO: DON'T FORGET TO ADD THIS LINES IN PROGUARD IN CASE YOU ENABLED IN RELEASE
//-dontwarn android.support.v8.renderscript.*
//-keep class com.photoembroidery.tat.olsennoise.** {*;}
//-keep class android.support.v8.renderscript.** {*;}
//-keepclassmembers class android.support.v8.renderscript.RenderScript {
//    native *** rsn*(...);
//    native *** n*(...);
//}

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class AnimatedDialogHelper {

  private BaseActivity context;
  private String title;
  private int titleResId;
  private int titleColorResId;
  private int titleColor;
  private String content;
  private int contentResId;
  private int contentColorResId;
  private int contentColor;
  private String positiveButton;
  private int positiveButtonResId;
  private int positiveButtonBackground;
  private int positiveButtonTextColorResId;
  private int positiveButtonTextColor;
  private OnClickListener positiveListener;
  private String negativeButton;
  private int negativeButtonResId;
  private int negativeButtonBackground;
  private int negativeButtonTextColorResId;
  private int negativeButtonTextColor;
  private OnClickListener negativeListener;
  private boolean canCanceledOnTouchOutside;
  private boolean canCancel;

  private AnimatedDialogHelper(final Builder builder) {
    context = builder.context;
    title = builder.title;
    titleResId = builder.titleResId;
    titleColorResId = builder.titleColorResId;
    titleColor = builder.titleColor;
    content = builder.content;
    contentResId = builder.contentResId;
    contentColorResId = builder.contentColorResId;
    contentColor = builder.contentColor;
    positiveButton = builder.positiveButton;
    positiveListener = builder.positiveListener;
    positiveButtonResId = builder.positiveButtonResId;
    positiveButtonBackground = builder.positiveButtonBackground;
    positiveButtonTextColorResId = builder.positiveButtonTextColorResId;
    positiveButtonTextColor = builder.positiveButtonTextColor;
    negativeButton = builder.negativeButton;
    negativeListener = builder.negativeListener;
    negativeButtonResId = builder.negativeButtonResId;
    negativeButtonBackground = builder.negativeButtonBackground;
    negativeButtonTextColorResId = builder.negativeButtonTextColorResId;
    negativeButtonTextColor = builder.negativeButtonTextColor;
    canCanceledOnTouchOutside = builder.canCanceledOnTouchOutside;
    canCancel = builder.canCancel;

    showPopUp();
  }

  private void showPopUp() {
    DialogFragment dialogFragment = new DialogFragment(context);
    dialogFragment.setTitle(title);
    dialogFragment.setTitle(titleResId);
    dialogFragment.setTitleColorResource(titleColorResId);
    dialogFragment.setTitleColor(titleColor);
    dialogFragment.setContent(content);
    dialogFragment.setContent(contentResId);
    dialogFragment.setContentColorResource(contentColorResId);
    dialogFragment.setContentColor(contentColor);
    dialogFragment.setPositiveButton(positiveButton, positiveListener);
    dialogFragment.setPositiveButton(positiveButtonResId, positiveListener);
    dialogFragment.setPositiveButtonBackground(positiveButtonBackground);
    dialogFragment.setPositiveButtonTextColorResource(positiveButtonTextColorResId);
    dialogFragment.setPositiveButtonTextColor(positiveButtonTextColor);
    dialogFragment.setNegativeButton(negativeButton, negativeListener);
    dialogFragment.setNegativeButton(negativeButtonResId, negativeListener);
    dialogFragment.setNegativeButtonBackground(negativeButtonBackground);
    dialogFragment.setNegativeButtonTextColorResource(negativeButtonTextColorResId);
    dialogFragment.setNegativeButtonTextColor(negativeButtonTextColor);
    dialogFragment.setCanceledOnTouchOutside(canCanceledOnTouchOutside);
    dialogFragment.setCancelable(canCancel);

    if (context.getSupportFragmentManager() != null) {
      dialogFragment.show(context.getSupportFragmentManager(), "AnimatedDialog");
    }
  }

  public static class DialogFragment
      extends SupportBlurDialogFragment {

    private BaseActivity context;
    private String title;
    private int titleResId;
    private int titleColorResId;
    private int titleColor;
    private String content;
    private int contentResId;
    private int contentColorResId;
    private int contentColor;
    private String positiveButton;
    private int positiveButtonResId;
    private int positiveButtonBackground;
    private int positiveButtonTextColorResId;
    private int positiveButtonTextColor;
    private OnClickListener positiveListener;
    private String negativeButton;
    private int negativeButtonResId;
    private int negativeButtonBackground;
    private int negativeButtonTextColorResId;
    private int negativeButtonTextColor;
    private OnClickListener negativeListener;
    private boolean canCanceledOnTouchOutside;
    private boolean canCancel;

    @BindView(R.id.title_tv)
    TextView titleTv;

    @BindView(R.id.content_tv)
    TextView contentTv;

    @BindView(R.id.positive_btn)
    Button positiveBtn;

    @BindView(R.id.negative_btn)
    Button negativeBtn;

    public DialogFragment() {
    }

    @SuppressLint("ValidFragment")
    public DialogFragment(BaseActivity context) {
      this.context = context;
    }

    @Override
    protected boolean isActionBarBlurred() {
      return true;
    }

    @Override
    protected boolean isRenderScriptEnable() {
      return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    @Override
    protected boolean isDimmingEnable() {
      return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
      View view = getActivity().getLayoutInflater().inflate(R.layout.pop_up_general, null);
      ButterKnife.bind(this, view);

      if (title != null && !TextUtils.isEmpty(title)) {
        titleTv.setText(title);
        titleTv.setVisibility(View.VISIBLE);
      } else if (titleResId != 0 && !TextUtils.isEmpty(context.getString(titleResId))) {
        titleTv.setText(context.getString(titleResId));
        titleTv.setVisibility(View.VISIBLE);
      } else {
        titleTv.setVisibility(View.GONE);
      }

      if (content != null && !TextUtils.isEmpty(content)) {
        contentTv.setText(content);
        contentTv.setVisibility(View.VISIBLE);
      } else if (contentResId != 0 && !TextUtils.isEmpty(context.getString(contentResId))) {
        contentTv.setText(context.getString(contentResId));
        contentTv.setVisibility(View.VISIBLE);
      } else {
        contentTv.setVisibility(View.GONE);
      }

      if (positiveButton != null && !TextUtils.isEmpty(positiveButton)) {
        positiveBtn.setText(positiveButton);
        positiveBtn.setVisibility(View.VISIBLE);
      } else if (positiveButtonResId != 0 && !TextUtils
          .isEmpty(context.getString(positiveButtonResId))) {
        positiveBtn.setText(context.getString(positiveButtonResId));
        positiveBtn.setVisibility(View.VISIBLE);
      } else {
        positiveBtn.setVisibility(View.GONE);
      }

      if (negativeButton != null && !TextUtils.isEmpty(negativeButton)) {
        negativeBtn.setText(negativeButton);
        negativeBtn.setVisibility(View.VISIBLE);
      } else if (negativeButtonResId != 0 && !TextUtils
          .isEmpty(context.getString(negativeButtonResId))) {
        negativeBtn.setText(context.getString(negativeButtonResId));
        negativeBtn.setVisibility(View.VISIBLE);
      } else {
        negativeBtn.setVisibility(View.GONE);
      }

      if (titleColorResId != 0 && context.getResources().getColor(titleColorResId) != 0) {
        titleTv.setTextColor(context.getResources().getColor(titleColorResId));
      } else if (titleColor != 0) {
        titleTv.setTextColor(titleColor);
      }

      if (contentColorResId != 0 && context.getResources().getColor(contentColorResId) != 0) {
        contentTv.setTextColor(context.getResources().getColor(contentColorResId));
      } else if (contentColor != 0) {
        contentTv.setTextColor(contentColor);
      }

      if (positiveButtonBackground != 0
          && context.getResources().getDrawable(positiveButtonBackground) != null) {
        positiveBtn.setBackground(context.getResources().getDrawable(positiveButtonBackground));
      }

      if (negativeButtonBackground != 0
          && context.getResources().getDrawable(negativeButtonBackground) != null) {
        negativeBtn.setBackground(context.getResources().getDrawable(negativeButtonBackground));
      }

      if (positiveButtonTextColorResId != 0
          && context.getResources().getColor(positiveButtonTextColorResId) != 0) {
        positiveBtn.setTextColor(context.getResources().getColor(positiveButtonTextColorResId));
      } else if (positiveButtonTextColor != 0) {
        positiveBtn.setTextColor(positiveButtonTextColor);
      }

      if (negativeButtonTextColorResId != 0
          && context.getResources().getColor(negativeButtonTextColorResId) != 0) {
        negativeBtn.setTextColor(context.getResources().getColor(negativeButtonTextColorResId));
      } else if (negativeButtonTextColor != 0) {
        negativeBtn.setTextColor(negativeButtonTextColor);
      }

      builder.setView(view);
      AlertDialog dialog = builder.create();
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setCanceledOnTouchOutside(canCanceledOnTouchOutside);
      dialog.setCancelable(canCancel);
      if (dialog.getWindow() != null) {
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
      }
      return dialog;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public void setTitle(@StringRes int titleResId) {
      this.titleResId = titleResId;
    }

    public void setTitleColorResource(@ColorRes int titleColorResId) {
      this.titleColorResId = titleColorResId;
    }

    public void setTitleColor(int titleColor) {
      this.titleColor = titleColor;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public void setContent(@StringRes int contentResId) {
      this.contentResId = contentResId;
    }

    public void setContentColorResource(@ColorRes int contentColorResId) {
      this.contentColorResId = contentColorResId;
    }

    public void setContentColor(int contentColor) {
      this.contentColor = contentColor;
    }

    public void setPositiveButton(String positiveButton, OnClickListener positiveListener) {
      this.positiveButton = positiveButton;
      this.positiveListener = positiveListener;
    }

    public void setPositiveButton(@StringRes int positiveButtonResId,
        OnClickListener positiveListener) {
      this.positiveButtonResId = positiveButtonResId;
      this.positiveListener = positiveListener;
    }

    public void setPositiveButtonBackground(@DrawableRes int positiveButtonBackground) {
      this.positiveButtonBackground = positiveButtonBackground;
    }

    public void setPositiveButtonTextColorResource(@ColorRes int positiveButtonTextColorResId) {
      this.positiveButtonTextColorResId = positiveButtonTextColorResId;
    }

    public void setPositiveButtonTextColor(int positiveButtonTextColor) {
      this.positiveButtonTextColor = positiveButtonTextColor;
    }

    public void setNegativeButton(String negativeButton, OnClickListener negativeListener) {
      this.negativeButton = negativeButton;
      this.negativeListener = negativeListener;
    }

    public void setNegativeButton(@StringRes int negativeButtonResId,
        OnClickListener negativeListener) {
      this.negativeButtonResId = negativeButtonResId;
      this.negativeListener = negativeListener;
    }

    public void setNegativeButtonBackground(@DrawableRes int negativeButtonBackground) {
      this.negativeButtonBackground = negativeButtonBackground;
    }

    public void setNegativeButtonTextColorResource(@ColorRes int negativeButtonTextColorResId) {
      this.negativeButtonTextColorResId = negativeButtonTextColorResId;
    }

    public void setNegativeButtonTextColor(int negativeButtonTextColor) {
      this.negativeButtonTextColor = negativeButtonTextColor;
    }

    public void setCanceledOnTouchOutside(Boolean canCanceledOnTouchOutside) {
      this.canCanceledOnTouchOutside = canCanceledOnTouchOutside;
    }

    public void setCancelable(Boolean canCancel) {
      this.canCancel = canCancel;
    }

    @OnClick(R.id.negative_btn)
    void onNegativeBtnClicked() {
      if (negativeListener == null) {
        dismiss();
      } else {
        negativeListener.onClick(this);
      }
    }

    @OnClick(R.id.positive_btn)
    void onPositiveBtnClicked() {
      if (positiveListener != null) {
        positiveListener.onClick(this);
      }
    }

    public void dismiss() {
      super.dismiss();
    }
  }

  /**
   * Interface used to allow the creator of a dialog to run some code when an
   * item on the dialog is clicked.
   */
  public interface OnClickListener {
    /**
     * This method will be invoked when a button in the dialog is clicked.
     */
    void onClick(DialogFragment popUp);
  }

  public static class Builder {
    private BaseActivity context;
    private String title;
    private int titleResId;
    private int titleColor;
    private int titleColorResId;
    private String content;
    private int contentResId;
    private int contentColorResId;
    private int contentColor;
    private String positiveButton;
    private int positiveButtonResId;
    private int positiveButtonBackground;
    private int positiveButtonTextColorResId;
    private int positiveButtonTextColor;
    private OnClickListener positiveListener;
    private String negativeButton;
    private int negativeButtonResId;
    private int negativeButtonBackground;
    private int negativeButtonTextColorResId;
    private int negativeButtonTextColor;
    private OnClickListener negativeListener;
    private boolean canCanceledOnTouchOutside;
    private boolean canCancel = true;

    public Builder(BaseActivity context) {
      this.context = context;
    }

    public Builder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder setTitle(@StringRes int titleResId) {
      this.titleResId = titleResId;
      return this;
    }

    public Builder setTitleColorResource(@ColorRes int titleColorResId) {
      this.titleColorResId = titleColorResId;
      return this;
    }

    public Builder setTitleColor(int titleColor) {
      this.titleColor = titleColor;
      return this;
    }

    public Builder setContent(String content) {
      this.content = content;
      return this;
    }

    public Builder setContent(@StringRes int contentResId) {
      this.contentResId = contentResId;
      return this;
    }

    public Builder setContentColorResource(@ColorRes int contentColorResId) {
      this.contentColorResId = contentColorResId;
      return this;
    }

    public Builder setContentColor(int contentColor) {
      this.contentColor = contentColor;
      return this;
    }

    public Builder setPositiveButton(String positiveButton,
        OnClickListener positiveListener) {
      this.positiveButton = positiveButton;
      this.positiveListener = positiveListener;
      return this;
    }

    public Builder setPositiveButton(@StringRes int positiveButtonResId,
        OnClickListener positiveListener) {
      this.positiveButtonResId = positiveButtonResId;
      this.positiveListener = positiveListener;
      return this;
    }

    public Builder setPositiveButtonBackground(
        @DrawableRes int positiveButtonBackground) {
      this.positiveButtonBackground = positiveButtonBackground;
      return this;
    }

    public Builder setPositiveButtonTextColorResource(
        @ColorRes int positiveButtonTextColorResId) {
      this.positiveButtonTextColorResId = positiveButtonTextColorResId;
      return this;
    }

    public Builder setPositiveButtonTextColor(int positiveButtonTextColor) {
      this.positiveButtonTextColor = positiveButtonTextColor;
      return this;
    }

    public Builder setNegativeButton(String negativeButton,
        OnClickListener negativeListener) {
      this.negativeButton = negativeButton;
      this.negativeListener = negativeListener;
      return this;
    }

    public Builder setNegativeButton(@StringRes int negativeButtonResId,
        OnClickListener negativeListener) {
      this.negativeButtonResId = negativeButtonResId;
      this.negativeListener = negativeListener;
      return this;
    }

    public Builder setNegativeButtonBackground(
        @DrawableRes int negativeButtonBackground) {
      this.negativeButtonBackground = negativeButtonBackground;
      return this;
    }

    public Builder setNegativeButtonTextColorResource(
        @ColorRes int negativeButtonTextColorResId) {
      this.negativeButtonTextColorResId = negativeButtonTextColorResId;
      return this;
    }

    public Builder setNegativeButtonTextColor(int negativeButtonTextColor) {
      this.negativeButtonTextColor = negativeButtonTextColor;
      return this;
    }

    public Builder setCanceledOnTouchOutside(
        boolean canCanceledOnTouchOutside) {
      this.canCanceledOnTouchOutside = canCanceledOnTouchOutside;
      return this;
    }

    public Builder setCancelable(boolean canCancel) {
      this.canCancel = canCancel;
      return this;
    }

    public AnimatedDialogHelper create() {
      return new AnimatedDialogHelper(this);
    }
  }
}
