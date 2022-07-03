package com.gorden.happycoding.framework;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.gorden.happycoding.R;

public class FrameworkActivity extends AppCompatActivity {

    private final String TAG = "FrameworkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_framework);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.e(TAG, "onCreate persistentState");
        Log.e(TAG, "persistentState" + persistentState.getString("framework"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        // 没有说一定要在 create 中调用
//        setContentView(R.layout.activity_framework);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.e(TAG, "savedInstanceState" + savedInstanceState.getString("framework"));
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if (persistentState != null) {
            Log.e(TAG, "persistentState" + persistentState.getString("framework"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e(TAG, "onSaveInstanceState");
        outState.putString("framework", "测试1");
        outPersistentState.putString("framework", "测试2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    public void startProcessA(View view) {
        startActivity(new Intent(this, ProcessAActivity.class));
    }

    public void finish(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}