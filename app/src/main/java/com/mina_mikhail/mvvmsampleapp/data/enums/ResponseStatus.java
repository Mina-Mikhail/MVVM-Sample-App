package com.mina_mikhail.mvvmsampleapp.data.enums;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class ResponseStatus {

  // 1 >> success
  //-1 >> bad request
  //-2 >> validation error
  //-3 >> specific for each api

  public static final int SUCCESS = 1;
  public static final int BAD_REQUEST = -1;
  public static final int VALIDATION_ERROR = -2;
}