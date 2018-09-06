package com.example.jonathan.testaccelerometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jonathan.testaccelerometer.utils.ConstantsUtils;

public class BootCompletedReceiver extends BroadcastReceiver {
  private static final String TAG = ConstantsUtils.APP_TAG + BootCompletedReceiver.class.getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "onReceive:");

    Intent launchSelfIntent = new Intent(context, MainActivity.class);
    launchSelfIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    Log.v(TAG, "onReceive: starting launcher Activity for package=[" + context.getPackageName() + "]");

    context.startActivity(launchSelfIntent);
  }
}