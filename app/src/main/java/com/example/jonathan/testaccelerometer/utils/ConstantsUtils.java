package com.example.jonathan.testaccelerometer.utils;

public class ConstantsUtils {
  public static final String APP_TAG = " TAM ";

  public static final int PHONE_FACE_STATE_UNKNOWN = 0;
  public static final int PHONE_FACE_STATE_UP = 1;
  public static final int PHONE_FACE_STATE_DOWN = 2;

  public static final long CHECK_PHONE_FACE_STATE_SLEEP_MILLIS = 1000;
  public static final int CHECK_PHONE_FACE_STATE_MAX_TRIALS = 10;    // Usually within 1 after booted.
}
