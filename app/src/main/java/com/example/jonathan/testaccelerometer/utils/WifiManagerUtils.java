package com.example.jonathan.testaccelerometer.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
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

    Log.v(TAG, "getWifiEnabled: enabled=[" + enabled + "]");

    return enabled;
  }

  public static boolean checkWifiEnabledPeriodically(final Context context, final long sleepMillis, final int maxTrials) {
    Log.d(TAG, "checkWifiEnabledPeriodically: sleepMillis=[" + sleepMillis + "], maxTrials=[" + maxTrials + "]");

    boolean wifiEnabled = false;

    int count = 0;

    while (count < maxTrials) {
      count++;

      wifiEnabled = getWifiEnabled(context);

      Log.v(TAG, "checkWifiEnabledPeriodically: wifiEnabled=[" + wifiEnabled + "], count=[" + count + "]");

      if (wifiEnabled) {
        break;
      }

      try {
        Thread.sleep(sleepMillis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return wifiEnabled;
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

  public static void connect(final Context context, final String networkSSID) {
    Log.d(TAG, "connect: networkSSID=[" + networkSSID + "]");

    WifiConfiguration wifiConfig = new WifiConfiguration();
    wifiConfig.SSID = String.format("\"%s\"", networkSSID);
    wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    int netId = wifiManager.addNetwork(wifiConfig);
    wifiManager.disconnect();
    wifiManager.enableNetwork(netId, true);
    wifiManager.reconnect();
  }
}
