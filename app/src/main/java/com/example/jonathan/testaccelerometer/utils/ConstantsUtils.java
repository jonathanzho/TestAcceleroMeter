package com.example.jonathan.testaccelerometer.utils;

public class ConstantsUtils {
  public static final String APP_TAG = " TAM ";

  public static final int PHONE_FACE_STATE_UNKNOWN = 0;
  public static final int PHONE_FACE_STATE_UP = 1;
  public static final int PHONE_FACE_STATE_DOWN = 2;

  public static final long CHECK_PHONE_FACE_STATE_SLEEP_MILLIS = 1000;
  public static final int CHECK_PHONE_FACE_STATE_MAX_TRIALS = 10;    // Usually within 1 after booted.

  public static final int WIFI_CONNECTION_JOB_ID = 1;    // Must be unique among all packages of the same uid.
  // Starting Android N, period should be greater than 15 minutes.
  public static final long WIFI_CONNECTION_PERIOD_MILLIS = 15 * 60 * 1000;
  //public static final long WIFI_CONNECTION_PERIOD_MILLIS = 20 * 1000;    // for easier testing with Android M and earlier
}
