package com.example.jonathan.testaccelerometer;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.util.Log;

import com.example.jonathan.testaccelerometer.utils.ConstantsUtils;
import com.example.jonathan.testaccelerometer.utils.SensorManagerUtils;
import com.example.jonathan.testaccelerometer.utils.WifiManagerUtils;

public class FaceDownWifiConnectionJobService extends JobService {
  private static final String TAG = ConstantsUtils.APP_TAG + FaceDownWifiConnectionJobService.class.getSimpleName();

  @Override
  public void onCreate() {
    Log.d(TAG, "onCreate");    // If (continueToRunService = false), a new object will be created periodically.

    super.onCreate();
  }

  @Override
  public void onDestroy() {
    Log.d(TAG, "onDestroy");    // If (continueToRunService = false), the current object will be destroyed periodically.

    super.onDestroy();
  }

  public boolean onStartJob(final JobParameters jobParameters) {
    Log.d(TAG, "onStartJob");

    final Context context = getApplicationContext();
    performJob(context, jobParameters);

    return false;
  }

  public boolean onStopJob(JobParameters jobParameters) {
    Log.w(TAG, "onStopJob. Usually this should not be called !");

    return true;
  }

  public void performJob(final Context context, final JobParameters jobParameters) {
    Log.d(TAG, "performJob");

    int phoneFaceState = SensorManagerUtils.getInstance().checkPhoneFaceStatePeriodically(
        ConstantsUtils.CHECK_PHONE_FACE_STATE_SLEEP_MILLIS,
        ConstantsUtils.CHECK_PHONE_FACE_STATE_MAX_TRIALS);

    if (phoneFaceState == ConstantsUtils.PHONE_FACE_STATE_DOWN) {
      boolean wifiEnabled = WifiManagerUtils.getWifiEnabled(TAMApplication.getContext());

      if (!wifiEnabled) {
        WifiManagerUtils.setWifiEnabled(TAMApplication.getContext(), true);
      } else {
        Log.v(TAG, "performJob: WiFi is already enabled. Exit.");
        return;
      }

      wifiEnabled = WifiManagerUtils.checkWifiEnabledPeriodically(context,
          ConstantsUtils.CHECK_WIFI_ENABLED_SLEEP_MILLIS,
          ConstantsUtils.CHECK_WIFI_ENABLED_MAX_TRIALS);

      if (wifiEnabled) {
        WifiManagerUtils.connectToOpenWifi(context, ConstantsUtils.SAMSUNGSURE_OPEN_MOBILE_HOTSPOT_SSID);
      } else {
        Log.e(TAG, "performJob: WiFi is not enabled after request. Exit !!!");
        return;
      }
    } else {
      Log.v(TAG, "performJob: Phone face is up. Exit.");
      return;
    }

    Log.v(TAG, "performJob: end");
  }
}