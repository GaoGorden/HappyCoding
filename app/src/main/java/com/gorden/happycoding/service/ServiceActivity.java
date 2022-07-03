package com.gorden.happycoding.service;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gorden.happycoding.R;

/*
https://github.com/JimSeker/service
 */

public class ServiceActivity extends AppCompatActivity {

    public static String id1 = "测试频道_01";
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startForegroundService(View view) {

        createChannel();

        Intent number5 = new Intent(getBaseContext(), MyForegroundService.class);
        number5.putExtra("times", 5);

        //If the API is below 26, then you have to use this
        //startService(number5);
        startForegroundService(number5);
        finish(); // 即使 开启服务的 Activity 已经关闭，Service 依旧可以正常运行，那如果关闭APP呢？Service 将会退出
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = new NotificationChannel(id1, "前台服务频道",  //name of the channel
                NotificationManager.IMPORTANCE_LOW);   //importance level
        //important level: default is is high on the phone.  high is urgent on the phone.  low is medium, so none is low?
        // Configure the notification channel.
        mChannel.setDescription("前台服务频道，用于测试");
        mChannel.enableLights(true);
        // Sets the notification light color for notifications posted to this channel, if the device supports this feature.
        mChannel.setShowBadge(true);
        nm.createNotificationChannel(mChannel);
    }

    public void startSensorService(View view) {

        if (started) {
            stopService(new Intent(getApplicationContext(), MySensorService.class));
            ((Button) view).setText("开启Sensor服务");
            started = false;
        } else {
            startService(new Intent(getApplicationContext(), MySensorService.class));
            ((Button) view).setText("关闭Sensor服务");
            started = true;
        }
    }

    public void startJobIntentService(View view) {
        Intent intent = new Intent(getApplicationContext(), MyJobIntentService.class);  //is any of that needed?  idk.
        //note, putExtra remembers type and I need this to be an integer.  so get an integer first.
        intent.putExtra("times", 5);  //should do error checking here!
        MyJobIntentService.enqueueWork(getApplicationContext(), intent);
    }

    public void scheduleJobIntentService(View view) {
        MyJobService.scheduleJob(this, 5, false);
    }

    public void scheduleWithRecurringJobIntentService(View view) {
        MyJobService.scheduleJob(this, 5, true);
    }

    public void cancelJobService(View view) {
        MyJobService.cancelJob(this);
    }
}