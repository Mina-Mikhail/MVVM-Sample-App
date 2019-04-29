package com.mina_mikhail.mvvmsampleapp.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.mina_mikhail.mvvmsampleapp.data.local.prefs.AppPreferencesHelper;
import com.mina_mikhail.mvvmsampleapp.utils.localization.LocalizationPreferences;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class ApiClient {
  private static final String BASE_URL = "";

  private static ApiClient instance;

  private ApiInterface apiService;

  private boolean sendToken = true;

  private ApiClient() {
    Interceptor headersInterceptor = chain -> {
      Request newRequest = chain.request();

      HttpUrl url = newRequest.url().newBuilder()
          .addQueryParameter("lang_id", LocalizationPreferences.getLangId()).build();

      String token = AppPreferencesHelper.getInstance().getToken();
      if (token != null && sendToken) {
        newRequest = newRequest.newBuilder().addHeader("Authorization", token).url(url).build();
      } else {
        newRequest = newRequest.newBuilder().url(url).build();
      }

      return chain.proceed(newRequest);
    };

    OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS).addInterceptor(
            headersInterceptor).addNetworkInterceptor(new StethoInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
            GsonConverterFactory.create()).baseUrl(BASE_URL).build();

    apiService = retrofit.create(ApiInterface.class);
  }

  public static ApiClient getInstance() {
    if (instance == null) {
      instance = new ApiClient();
    }
    return instance;
  }

  public ApiInterface getApiService() {
    return apiService;
  }

  public ApiClient setSendToken(boolean sendToken) {
    this.sendToken = sendToken;
    return this;
  }
}