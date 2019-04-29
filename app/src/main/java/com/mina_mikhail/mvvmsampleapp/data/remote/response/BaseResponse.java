package com.mina_mikhail.mvvmsampleapp.data.remote.response;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class BaseResponse {

  private String message;
  private int status;

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }
}