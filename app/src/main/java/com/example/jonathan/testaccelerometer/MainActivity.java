package com.example.jonathan.testaccelerometer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jonathan.testaccelerometer.utils.ConstantsUtils;

public class MainActivity extends Activity {
  private static final String TAG = ConstantsUtils.APP_TAG + MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onResume() {
    Log.d(TAG, "onResume");

    super.onResume();
  }

  @Override
  protected void onPause() {
    Log.d(TAG, "onPause");

    super.onPause();
  }
}
