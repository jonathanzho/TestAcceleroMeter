package com.example.jonathan.testaccelerometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jonathan.testaccelerometer.utils.ConstantsUtils;
import com.example.jonathan.testaccelerometer.utils.JobSchedulerUtils;
import com.example.jonathan.testaccelerometer.utils.SensorManagerUtils;

public class BootCompletedReceiver extends BroadcastReceiver {
  private static final String TAG = ConstantsUtils.APP_TAG + BootCompletedReceiver.class.getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    String actionStr = intent.getAction();

    Log.d(TAG, "onReceive: actionStr=[" + actionStr + "]");

    if (Intent.ACTION_BOOT_COMPLETED.equals(actionStr)) {    // to avoid spoofed Action
      handleFaceDownWiFiConnection(context);
    } else {
      Log.e(TAG, "onReceive: Spoofed Action Intent received !!!");
    }
  }

  private void handleFaceDownWiFiConnection(Context context) {
    Log.d(TAG, "handleFaceDownWiFiConnection");

    SensorManagerUtils.getInstance();    // Early creation.

    JobSchedulerUtils.scheduleWiFiConnection(context);
  }
}
