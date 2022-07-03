package com.gorden.happycoding.optimize;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.gorden.happycoding.R;

public class AsyncLayoutInflaterActivity extends AppCompatActivity {

    public final String TAG = AsyncLayoutInflaterActivity.this.getClass().getSimpleName();

    long start;
    long end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start = System.currentTimeMillis();

//        AsyncLayoutInflater inflater = new AsyncLayoutInflater(this);
//        inflater.inflate(R.layout.activity_async_layout_inflater, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
//            @Override
//            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
//                setContentView(view);
//            }
//        });

        setContentView(R.layout.activity_async_layout_inflater);
    }

    @Override
    protected void onResume() {
        super.onResume();
        end = System.currentTimeMillis();
        Log.e(TAG, "花费时间: " + (end - start));
    }
}