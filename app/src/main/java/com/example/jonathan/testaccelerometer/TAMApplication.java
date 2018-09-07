package com.example.jonathan.testaccelerometer;

import android.app.Application;
import android.content.Context;

public class TAMApplication extends Application {
  private static TAMApplication sTAMApplication;

  public TAMApplication() {
    super();

    sTAMApplication = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
  }

  public static Context getContext() {
    return sTAMApplication;
  }
}
