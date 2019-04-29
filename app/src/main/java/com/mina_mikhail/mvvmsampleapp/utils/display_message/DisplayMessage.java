package com.mina_mikhail.mvvmsampleapp.utils.display_message;

import android.arch.lifecycle.LifecycleOwner;
import com.mina_mikhail.mvvmsampleapp.utils.SingleLiveEvent;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class DisplayMessage
    extends SingleLiveEvent<Message> {

  public void observe(LifecycleOwner owner, final MessageObserver observer) {
    super.observe(owner, t -> {
      if (t == null) {
        return;
      }
      observer.onNewMessage(t);
    });
  }

  public interface MessageObserver {
    /**
     * Called when there is a new message to be shown.
     *
     * @param message The new message
     */
    void onNewMessage(Message message);
  }
}