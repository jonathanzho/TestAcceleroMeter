package com.example.jonathan.testaccelerometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jonathan.testaccelerometer.utils.ConstantsUtils;
import com.example.jonathan.testaccelerometer.utils.SensorManagerUtils;
import com.example.jonathan.testaccelerometer.utils.WifiManagerUtils;

import static com.example.jonathan.testaccelerometer.utils.SensorManagerUtils.getInstance;

public class BootCompletedReceiver extends BroadcastReceiver {
  private static final String TAG = ConstantsUtils.APP_TAG + BootCompletedReceiver.class.getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "onReceive:");

    Intent launchSelfIntent = new Intent(context, MainActivity.class);
    launchSelfIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Log.v(TAG, "onReceive: starting launcher Activity for package=[" + context.getPackageName() + "]");
    context.startActivity(launchSelfIntent);

    handleFaceDownWiFiConnection();
  }

  private void handleFaceDownWiFiConnection() {
    Log.d(TAG, "handleFaceDownWiFiConnection");

    int phoneFaceState = SensorManagerUtils.getInstance().checkPhoneFaceStatePeriodically(
        ConstantsUtils.CHECK_PHONE_FACE_STATE_SLEEP_MILLIS,
        ConstantsUtils.CHECK_PHONE_FACE_STATE_MAX_TRIALS);

    if (phoneFaceState == ConstantsUtils.PHONE_FACE_STATE_DOWN) {
      boolean wifiEnabled = WifiManagerUtils.getWifiEnabled(TAMApplication.getContext());

      if (!wifiEnabled) {
        WifiManagerUtils.setWifiEnabled(TAMApplication.getContext(), true);

        // TODO: connect to the specific wifi
      }
    }
  }
}
