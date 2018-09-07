package com.example.jonathan.testaccelerometer.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.jonathan.testaccelerometer.TAMApplication;

import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

public class SensorManagerUtils {
  private static final String TAG = ConstantsUtils.APP_TAG + SensorManagerUtils.class.getSimpleName();

  private static SensorManagerUtils sSensorManagerUtils;

  private int mPhoneFaceState;
  private SensorManager mSensorManager;
  private SensorEventListener mAcceleroMeterListener;
  private Sensor mAcceleroMeterSensor;
  private boolean mIsAcceleroMeterPresent;


  private SensorManagerUtils() {
    Log.d(TAG, "SensorManagerUtils");

    mPhoneFaceState = ConstantsUtils.PHONE_FACE_STATE_UNKNOWN;

    mSensorManager = (SensorManager) TAMApplication.getContext().getSystemService(SENSOR_SERVICE);

    mAcceleroMeterListener = new SensorEventListener() {
      @Override
      public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged");    // !!! This is called very frequently, about 6 times per second.

        float zValue = event.values[2];

        if (zValue >= 0) {
          mPhoneFaceState= ConstantsUtils.PHONE_FACE_STATE_UP;
        } else {
          mPhoneFaceState= ConstantsUtils.PHONE_FACE_STATE_DOWN;
        }
      }

      @Override
      public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged");
      }
    };

    List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    if (sensorList.size() > 0) {
      mIsAcceleroMeterPresent = true;
      mAcceleroMeterSensor = sensorList.get(0);
      Log.d(TAG, "SensorManagerUtils: Calling registerListener");
      mSensorManager.registerListener(mAcceleroMeterListener, mAcceleroMeterSensor, SensorManager.SENSOR_DELAY_NORMAL);
    } else {
      mIsAcceleroMeterPresent = false;
    }
  }

  public static SensorManagerUtils getInstance() {
    if (sSensorManagerUtils == null) {
      sSensorManagerUtils = new SensorManagerUtils();
    }

    return sSensorManagerUtils;
  }

  public int getPhoneFaceState() {
    Log.v(TAG, "getPhoneFaceState: mPhoneFaceState=[" + mPhoneFaceState + "]");

    return mPhoneFaceState;
  }

  public int checkPhoneFaceStatePeriodically(final long sleepMillis, final int maxTrials) {
    Log.d(TAG, "checkPhoneFaceStatePeriodically: sleepMillis=[" + sleepMillis + "], maxTrials=[" + maxTrials + "]");

    int phoneFaceState = ConstantsUtils.PHONE_FACE_STATE_UNKNOWN;

    int count = 0;

    while (count < maxTrials) {
      count++;

      phoneFaceState = getPhoneFaceState();

      Log.v(TAG, "checkPhoneFaceStatePeriodically: phoneFaceState=[" + phoneFaceState + "], count=[" + count + "]");

      if (phoneFaceState != ConstantsUtils.PHONE_FACE_STATE_UNKNOWN) {
        break;
      }

      try {
        Thread.sleep(sleepMillis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return phoneFaceState;
  }
}
