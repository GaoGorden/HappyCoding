package com.gorden.happycoding.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.gorden.happycoding.R;

import java.util.Random;


/**
 * 前台服务
 *
 * 前台服务是用户主动意识到的一种服务，因此在内存不足时，系统也不会考虑将其终止。前台服务必须为状态栏提供通知，
 * 将其放在运行中的标题下方。这意味着除非将服务停止或从前台移除，否则不能清除该通知。
 */
public class MyForegroundService extends Service {

    public static final String TAG = "MyForegroundService";

    private ServiceHandler mServiceHandler;
    private Random random;
    private int serviceId;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Notification notification = getNotification("前台服务"+ serviceId + "正在运行");
            startForeground(msg.arg1, notification);  //the ID as same as the notification id.  can't be zero.

            Log.e(TAG, "should be foreground now. id number is " + msg.arg1);
            // Normally we would do some work here, like download a file.
            // For our example, we just sleep for 5 seconds then display toasts.
            //setup how many messages
            int times = 1, i;

            Bundle extras = msg.getData();
            if (extras != null) {
                times = extras.getInt("times", 1);  //default is one
            }
            //loop that many times, sleeping for 2 seconds.
            for (i = 0; i < times; i++) {
                synchronized (this) {
                    try {
                        wait(2000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.toString());
                    }
                }
                String info = i + " random " + random.nextInt(100);
                Log.e("intentServer", info);
                //make a toast
                //unable to ensure the toasts will always show, so use a handler and post it for later.
                // Toast.makeText(MyForeGroundService.this, info, Toast.LENGTH_SHORT).show();
                toast(info);
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
//            stopSelf(msg.arg1);  //notification will go away as well.
        }
    }

    final Handler mHandler = new Handler();

    // Helper for showing tests
    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyForegroundService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCreate() {
        Log.e(TAG, "Service Create : onCreate" + serviceId);
        random = new Random();

        HandlerThread thread = new HandlerThread("MyForegroundService", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        Looper looper = thread.getLooper();
        mServiceHandler = new ServiceHandler(looper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Starting : onStartCommand " + startId + "  thread:" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();

        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;//needed for stop.
        serviceId = startId;

        if (intent != null) {
            msg.setData(intent.getExtras());
            mServiceHandler.sendMessage(msg);
        } else {
            Toast.makeText(this, "The Intent to start is null?!", Toast.LENGTH_SHORT).show();
        }

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "Service Closing : onDestroy" + serviceId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Notification getNotification(String message) {

        return new NotificationCompat.Builder(getApplicationContext(), ServiceActivity.id1)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)  //persistent notification!
                .setChannelId(ServiceActivity.id1)
                .setContentTitle("Service")   //Title message top row.
                .setContentText(message)  //message when looking at the notification, second row
                .build();  //finally build and return a Notification.
    }
}
