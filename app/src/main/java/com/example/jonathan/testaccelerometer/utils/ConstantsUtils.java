package com.example.jonathan.testaccelerometer.utils;

public class ConstantsUtils {
  public static final String APP_TAG = " TAM ";

  public static final int PHONE_FACE_STATE_UNKNOWN = 0;
  public static final int PHONE_FACE_STATE_UP = 1;
  public static final int PHONE_FACE_STATE_DOWN = 2;

  public static final long CHECK_PHONE_FACE_STATE_SLEEP_MILLIS = 1000;
  public static final int CHECK_PHONE_FACE_STATE_MAX_TRIALS = 5;    // Usually within 1 after booted.

  public static final long CHECK_WIFI_ENABLED_MILLIS = 1000;
  public static final int CHECK_WIFI_ENABLED_MAX_TRIALS = 5;    // Usually within 1 after booted.

  public static final int WIFI_CONNECTION_JOB_ID = 1;    // Must be unique among all packages of the same uid.
  public static final int REBOOT_OVERRIDE_DEADLINE_MILLIS = 5000;

  public static final String SAMSUNGSURE_OPEN_MOBILE_HOTSPOT_SSID = "samsungsure";
}
