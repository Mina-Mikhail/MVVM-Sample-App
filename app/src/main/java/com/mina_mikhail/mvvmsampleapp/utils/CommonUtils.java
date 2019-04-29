package com.mina_mikhail.mvvmsampleapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.Patterns;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.mina_mikhail.mvvmsampleapp.R;
import com.mina_mikhail.mvvmsampleapp.ui.base.BaseActivity;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public final class CommonUtils {

  public static ProgressDialog showLoadingDialog(BaseActivity activity) {
    if (activity == null || activity.isFinishing()) {
      return null;
    }

    ProgressDialog progressDialog = new ProgressDialog(activity);
    progressDialog.show();
    if (progressDialog.getWindow() != null) {
      progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    progressDialog.setContentView(R.layout.progress_dialog);

    ProgressBar progressBar = progressDialog.findViewById(R.id.loading);
    Sprite wave = new Wave();
    wave.setColor(activity.getResources().getColor(R.color.colorAccent));
    progressBar.setIndeterminateDrawable(wave);

    progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    return progressDialog;
  }

  public static void hideLoadingDialog(ProgressDialog mProgressDialog, BaseActivity activity) {
    if (activity != null && !activity.isFinishing() && mProgressDialog != null && mProgressDialog
        .isShowing()) {
      mProgressDialog.dismiss();
    }
  }

  public static String convert24HrTo12Hr(int hourOfDay, int minute) {
    if (String.valueOf(Locale.getDefault().getLanguage()).equals("en")) {
      return ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute)
          : minute) + " " +
          ((hourOfDay >= 12) ? "PM" : "AM");
    } else if (String.valueOf(Locale.getDefault().getLanguage()).equals("ar")) {
      return ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute)
          : minute) + " " +
          ((hourOfDay >= 12) ? "م" : "ص");
    }

    return null;
  }

  public static String convertDateTimeToTimesAgo(String timeStamp) {
    // To convert date from style like (2017-09-01 21:35:40) into -> times ago style
    final SimpleDateFormat inputFormat =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    Date date = null;
    try {
      date = inputFormat.parse(timeStamp);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // the new date style
    return (String) DateUtils
        .getRelativeTimeSpanString(date.getTime(), Calendar.getInstance().getTimeInMillis(),
            DateUtils.MINUTE_IN_MILLIS);
  }

  @SuppressLint("all")
  public static String getDeviceId(Context context) {
    return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  public static boolean isEmailValid(String email) {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  //public static void restartApp(Activity activity) {
  //  Intent intent = new Intent(activity, SplashActivity.class);
  //  ComponentName cn = intent.getComponent();
  //  Intent mainIntent = Intent.makeRestartActivityTask(cn);
  //  activity.startActivity(mainIntent);
  //  activity.finish();
  //}
  //
  //public static void goToLogin(Activity activity) {
  //  Intent intent = new Intent(activity, LogInActivity.class);
  //  ComponentName cn = intent.getComponent();
  //  Intent mainIntent = Intent.makeRestartActivityTask(cn);
  //  activity.startActivity(mainIntent);
  //  activity.finish();
  //}

  public static void openGoogleMap(Context context, double lat, double lng) {
    Intent intent = new Intent(Intent.ACTION_VIEW,
        Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lng));
    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  public static void openGooglePlayStore(Context context) {
    String appPackageName =
        context.getPackageName(); // getPackageName() from Context or Activity object
    try {
      context.startActivity(new Intent(Intent.ACTION_VIEW,
          Uri.parse("market://details?id=" + appPackageName)));
    } catch (android.content.ActivityNotFoundException anfe) {
      context.startActivity(new Intent(Intent.ACTION_VIEW,
          Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
    }
  }

  public static void setTabsFont(Activity activity, TabLayout tabLayout, String fontName) {
    assert tabLayout != null;
    for (int i = 0; i < tabLayout.getTabCount(); i++) {
      TabLayout.Tab tab = tabLayout.getTabAt(i);
      applyFontToTabItem(tab, activity, fontName);
    }
  }

  public static String getCurrentDataTime() {
    DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    String date = dfDate.format(Calendar.getInstance().getTime());
    DateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    String time = dfTime.format(Calendar.getInstance().getTime());
    return date + " " + time;
  }

  public static String getCurrentDate() {
    DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    return dfDate.format(Calendar.getInstance().getTime());
  }

  public static String getCurrentTime() {
    DateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    return dfTime.format(Calendar.getInstance().getTime());
  }

  public static int getCurrentTimeInt() {
    return (int) (new Date().getTime() / 1000);
  }

  private static void applyFontToTabItem(TabLayout.Tab tab, Activity activity, String fontName) {
    Typeface font = Typeface.createFromAsset(activity.getAssets(), fontName);
    SpannableString mNewTitle = new SpannableString(tab.getText());
    mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(),
        Spannable.SPAN_INCLUSIVE_INCLUSIVE);
    tab.setText(mNewTitle);
  }

  public static String arabicNumberToEnglish(String number) {
    char[] chars = new char[number.length()];
    for (int i = 0; i < number.length(); i++) {
      char ch = number.charAt(i);
      if (ch >= 0x0660 && ch <= 0x0669) {
        ch -= 0x0660 - '0';
      } else if (ch >= 0x06f0 && ch <= 0x06F9) ch -= 0x06f0 - '0';
      chars[i] = ch;
    }
    return new String(chars);
  }

  // https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
  // Converts the number to K, M suffix
  // Ex: 5500 will be displayed as 5.5k
  public static String convertToSuffix(long count) {
    if (count < 1000) return "" + count;
    int exp = (int) (Math.log(count) / Math.log(1000));
    return String.format(Locale.US, "%.1f%c",
        count / Math.pow(1000, exp),
        "kmgtpe".charAt(exp - 1));
  }

  public static String convertFileIntoBase64(String imagePath) {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(imagePath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    byte[] bytes;
    byte[] buffer = new byte[8192];
    int bytesRead;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        output.write(buffer, 0, bytesRead);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    bytes = output.toByteArray();

    return Base64.encodeToString(bytes, Base64.DEFAULT);
  }

  public static void launchUrl(String url, Context context) {
    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
    CustomTabsIntent customTabsIntent = builder.build();
    if (!url.contains("http")) {
      url = "http://" + url;
    }
    customTabsIntent.launchUrl(context, Uri.parse(url));
  }

  public static String getVideoIdFromYoutubeUrl(String url) {
    String videoId = null;
    String regex =
        "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(url);
    if (matcher.find()) {
      videoId = matcher.group(1);
    }
    return videoId;
  }
}