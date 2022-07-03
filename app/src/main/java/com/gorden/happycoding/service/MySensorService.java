package com.gorden.happycoding.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.gorden.happycoding.R;


/**
 * 普通服务
 */
public class MySensorService extends Service implements SensorEventListener {

    public static final String TAG = "MySensorService";

    private SensorManager mSensorManager;
    private Sensor mSensor;
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        Log.e(TAG, "MySensorService Create");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this, mSensor);
        KillMediaPlayer();
        Log.e(TAG, "MySensorService Destroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //SQRT(x*x + y*y + z*z).
        double vector = Math.sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]);
        //9.8 m/s is basically not moving
        //3.0 m/s or less is basically falling.
        //20 m/s is landing ish, based on what I read.

//        Log.e(TAG, "MySensorService Vector: " + vector);

        if (vector <= 4.0) { // 3 m/s should be falling, I think...
            playSnd();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void playSnd() {
        if (mediaPlayer == null) { //first time
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.hmscream);
        } else if (mediaPlayer.isPlaying()) { //duh don't start it again.
            //Toast.makeText(getBaseContext(), "I'm playing already", Toast.LENGTH_SHORT).show();
            return;
        } else { //play it at least one, reset and play again.
            mediaPlayer.seekTo(0);
        }
        mediaPlayer.start();
    }


    void KillMediaPlayer() {
        if (mediaPlayer != null)
            mediaPlayer.release();
    }
}
