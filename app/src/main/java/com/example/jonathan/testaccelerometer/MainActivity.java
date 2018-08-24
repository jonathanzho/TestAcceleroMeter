package com.example.jonathan.testaccelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jonathan.testaccelerometer.utils.ConstantsUtils;
import com.example.jonathan.testaccelerometer.utils.WifiManagerUtils;

import java.util.List;

public class MainActivity extends Activity {
  private static final String TAG = ConstantsUtils.APP_TAG + MainActivity.class.getSimpleName();

  private SensorManager mSensorManager;
  private Sensor mAcceleroMeterSensor;
  private SensorEventListener mAcceleroMeterListener;
  private boolean mIsAcceleroMeterPresent;

  private TextView mFaceTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mFaceTextView = (TextView) findViewById(R.id.faceTextView);

    mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    if (sensorList.size() > 0) {
      mIsAcceleroMeterPresent = true;
      mAcceleroMeterSensor = sensorList.get(0);
    } else {
      mIsAcceleroMeterPresent = false;
      mFaceTextView.setText("No accelerometer found !");
    }

    mAcceleroMeterListener = new SensorEventListener() {
      @Override
      public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged");

        float zValue = event.values[2];

        if (zValue >= 0) {
          mFaceTextView.setText("Face UP");
        } else {
          mFaceTextView.setText("Face DOWN");
        }
      }

      @Override
      public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged");
      }
    };

    Log.v(TAG, "onCreate: end");
  }

  @Override
  protected void onResume() {
    Log.d(TAG, "onResume");

    super.onResume();

    if (mIsAcceleroMeterPresent) {
      mSensorManager.registerListener(mAcceleroMeterListener, mAcceleroMeterSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    boolean isWifiEnabled = WifiManagerUtils.getWifiEnabled(this);
    if (!isWifiEnabled) {
      WifiManagerUtils.setWifiEnabled(this,true);
    }
  }

  @Override
  protected void onPause() {
    Log.d(TAG, "onPause");

    super.onPause();

    if (mIsAcceleroMeterPresent) {
      mSensorManager.unregisterListener(mAcceleroMeterListener);
    }
  }
}
