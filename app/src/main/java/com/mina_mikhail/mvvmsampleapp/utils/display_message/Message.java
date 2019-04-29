package com.mina_mikhail.mvvmsampleapp.utils.display_message;

import android.support.annotation.StringRes;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class Message {

  private int type;
  @StringRes
  private int messageResourceId;
  private String messageText;

  public Message(int type, int messageResourceId) {
    this.type = type;
    this.messageResourceId = messageResourceId;
    this.messageText = null;
  }

  public Message(int type, String messageText) {
    this.type = type;
    this.messageText = messageText;
    this.messageResourceId = -1;
  }

  public int getType() {
    return type;
  }

  public int getMessageResourceId() {
    return messageResourceId;
  }

  public String getMessageText() {
    return messageText;
  }
}
