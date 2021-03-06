package com.gorden.happycoding.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";
    // Random number generator
    private final Random mRandom = new Random();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "Service Created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Service Destroyed");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        int max = params.getExtras().getInt("max", 6);  //something low so I know it didn't work.
        Log.e(TAG, "max is " + max);

        // Process work here...  we'll pretend by sleeping for 3 seconds.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.e(TAG, e.toString());
        }

        Toast.makeText(getApplicationContext(), "Job: number is " + mRandom.nextInt(max), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Job: I'm working on something...");

        //since there seems to be threshold on recurring.  say 10 to 30 minutes, based on simple tests.
        //you could just reschedule the job here.  Then the time frame can be much shorter.
        scheduleJob(getApplicationContext(),max, true);

        // Return true as there's more work to be done with this job.
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "on stop job: " + params.getJobId());

        // Return false to drop the job.
        return false;
    }

    public static void scheduleJob(Context context, int max, boolean recurring) {

        ComponentName serviceComponent = new ComponentName(context, MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);

        if (recurring) {
            builder.setPeriodic(2 * 1000); //only once every 15 seconds.
            //builder.setPersisted(true);  //will persist across reboots.
            //except this runs in about 10 to 30 minute intervals...  Likely a min threshold here.
            Log.e(TAG, "set recurring");
        } else {  //just set it for once, between 10 to 30 seconds from now.
            builder.setMinimumLatency(1000); // wait at least
            builder.setOverrideDeadline(3 * 1000); // maximum delay
            Log.e(TAG, "set once");
        }

        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        //builder.setRequiresBatteryNotLow(true);  //only when the batter is not low.  API 26+
        //set some data via a persistablebundle.
        PersistableBundle extras = new PersistableBundle();
        extras.putInt("max", max);
        builder.setExtras(extras);

        JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            jobScheduler = context.getSystemService(JobScheduler.class);
        }
        jobScheduler.schedule(builder.build());
    }

    // cancel all the jobs.
    public static void cancelJob(Context context) {
        JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            jobScheduler = context.getSystemService(JobScheduler.class);
        }
        jobScheduler.cancelAll();
    }
}
