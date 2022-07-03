package com.gorden.happycoding.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Type;

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    public void baseToast(String msg) {
        Toast.makeText(this, TAG + msg, Toast.LENGTH_SHORT).show();
    }

    public void baseLogE(String msg) {
        Log.e(TAG, msg);
    }

    protected abstract ViewBinding getBinding();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        countViewCreateTime();

        super.onCreate(savedInstanceState);
        setContentView(getBinding().getRoot());
        initView();
    }

    private void countViewCreateTime() {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                View view = getDelegate().createView(parent, name, context, attrs);
                if (view instanceof Button) {
                    ((Button) view).setBackgroundColor(Color.RED);
                }
                return view;
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return null;
            }
        });
    }


}
