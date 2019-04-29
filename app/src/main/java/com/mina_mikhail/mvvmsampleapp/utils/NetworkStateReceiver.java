package com.mina_mikhail.mvvmsampleapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public class NetworkStateReceiver
    extends BroadcastReceiver {

  private NetworkStateReceiverListener listener;
  private Boolean connected;

  public NetworkStateReceiver() {
    connected = null;
  }

  /**
   * @param context Context - Application context
   * @param intent Intent - Manages application actions on network state changes
   */
  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent == null || intent.getExtras() == null) return;

    connected = NetworkUtils.isNetworkConnected(context);
    notifyState();
  }

  /**
   * Notify the network state
   */
  private void notifyState() {
    if (connected == null || listener == null) return;

    if (connected) {
      listener.networkAvailable();
    } else {
      listener.networkUnavailable();
    }
  }

  /**
   * Add listener once it is needed
   *
   * @param listener NetworkStateReceiverListener - receives network state change
   */
  public void startListening(NetworkStateReceiverListener listener) {
    this.listener = listener;
    notifyState();
  }

  /**
   * Remove the listener once it is not needed anymore
   */
  public void stopListening() {
    this.listener = null;
    this.connected = null;
  }

  /**
   * Set interface to communicate with Main methods
   */
  public interface NetworkStateReceiverListener {
    void networkAvailable();

    void networkUnavailable();
  }
}