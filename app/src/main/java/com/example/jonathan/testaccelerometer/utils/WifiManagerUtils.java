package com.example.jonathan.testaccelerometer.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiManagerUtils {
  private static final String TAG = ConstantsUtils.APP_TAG + WifiManagerUtils.class.getSimpleName();

  public static void setWifiEnabled(final Context context, final boolean enabled) {
    Log.d(TAG, "setWifiEnabled: enabled=[" + enabled + "]");

    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    if (wifiManager != null) {
      wifiManager.setWifiEnabled(enabled);
    }
  }

  public static boolean getWifiEnabled(final Context context) {
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    boolean enabled = false;
    if (wifiManager != null) {
      enabled = wifiManager.isWifiEnabled();
    }

    Log.v(TAG, "getWifiEnabled; enabled=[" + enabled + "]");

    return enabled;
  }

  public static int getWifiState(final Context context) {
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    int state = 0;
    if (wifiManager != null) {
      state = wifiManager.getWifiState();
    }

    Log.v(TAG, "getWifiState; state=[" + state + "]");

    return state;
  }
}
