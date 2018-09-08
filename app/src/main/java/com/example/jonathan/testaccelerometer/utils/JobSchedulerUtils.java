package com.example.jonathan.testaccelerometer.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import com.example.jonathan.testaccelerometer.FaceDownWifiConnectionJobService;

public class JobSchedulerUtils {
  private static final String TAG = ConstantsUtils.APP_TAG + JobSchedulerUtils.class.getSimpleName();

  public static void scheduleWiFiConnection(Context context) {
    Log.d(TAG, "scheduleWiFiConnection: FACE_DOWN_WIFI_CONNECTION_OVERRIDE_DEADLINE_MILLIS=[" + ConstantsUtils.FACE_DOWN_WIFI_CONNECTION_OVERRIDE_DEADLINE_MILLIS + "]");

    ComponentName wiFiConnectionJobServiceComponent = new ComponentName(context, FaceDownWifiConnectionJobService.class);
    JobInfo.Builder builder = new JobInfo.Builder(ConstantsUtils.FACE_DOWN_WIFI_CONNECTION_JOB_ID,
        wiFiConnectionJobServiceComponent);
    builder.setOverrideDeadline(ConstantsUtils.FACE_DOWN_WIFI_CONNECTION_OVERRIDE_DEADLINE_MILLIS);
    JobInfo jobInfo = builder.build();

    schedule(context, jobInfo);
  }

  private static void schedulePeriodicJob(Context context, Class<?> cls, long periodInMillis, int jobId) {
    Log.d(TAG, "schedulePeriodicJob: periodInMillis=[" + periodInMillis + "], jobId=[" + jobId + "]");

    ComponentName periodicJobServiceComponent = new ComponentName(context, cls);
    JobInfo.Builder builder = new JobInfo.Builder(jobId,
      periodicJobServiceComponent);
    builder.setPeriodic(periodInMillis);
    JobInfo jobInfo = builder.build();

    schedule(context, jobInfo);
  }

  private static void schedule(Context context, JobInfo jobInfo) {
    Log.d(TAG, "schedule");

    try {
      JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
      jobScheduler.schedule(jobInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean getJobServiceScheduled( final Context context, final int jobId) {
    Log.d(TAG, "getJobServiceScheduled: jobId=[" + jobId + "]");

    JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

    boolean isJobScheduled = false;

    for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
      if (jobInfo.getId() == jobId) {
        isJobScheduled = true;
        break;
      }
    }

    Log.v(TAG, "getJobServiceScheduled: isJobScheduled=[" + isJobScheduled + "]");

    return isJobScheduled;
  }
}