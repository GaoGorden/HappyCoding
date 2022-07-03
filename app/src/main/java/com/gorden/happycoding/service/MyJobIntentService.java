package com.gorden.happycoding.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

import java.util.Random;

/**
 * 任务结束自动关闭
 * 自动开启子线程执行任务
 * 开启队列保存任务
 */
public class MyJobIntentService extends JobIntentService {
    /**
     * Unique job ID for this service.
     */
    static final int JOB_ID = 1000;
    final String TAG = "MyJobIntentService";

    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Convenience method for enqueuing work in to this service.
     */
    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "MyJobIntentService Create");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e(TAG, "MyJobIntentService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        // We have received work to do.  The system or framework is already
        // holding a wake lock for us at this point, so we can just go.
        Log.e(TAG, "MyJobIntentService onHandleWork: currentThread:" + Thread.currentThread().getName());

        int top = intent.getIntExtra("times", 5);  //default is 5.
        int num;

        for (int i = 0; i < top; i++) {
            num = mGenerator.nextInt(100);
            toast(i + " Random number is " + num);
            Log.e(TAG, i + " Random number is " + num);
            try {
                Thread.sleep(2000);  // 1000 is one second, ten seconds would be 10000
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "All work complete");
        Log.e(TAG, "MyJobIntentService onDestroy");
        toast("All work complete");
    }

    final Handler mHandler = new Handler();

    // Helper for showing tests
    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyJobIntentService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
