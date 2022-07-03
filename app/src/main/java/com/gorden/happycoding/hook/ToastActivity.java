package com.gorden.happycoding.hook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gorden.happycoding.databinding.ActivityToastBinding;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ToastActivity extends AppCompatActivity {
    private ActivityToastBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toastShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Gorden";
                Toast toast = Toast.makeText(ToastActivity.this, text, Toast.LENGTH_SHORT);
                // 我们需要修改toast 中 service的enqueueToast（）方法

                // 获取我们自己的service
                try {
                    Method getService = Toast.class.getDeclaredMethod("getService", (Class<?>) null);
                    getService.setAccessible(true);
                    Object mService = getService.invoke(null);

                    Object proxy = Proxy.newProxyInstance(Thread.class.getClassLoader(), new Class[]{/*INotificationManager.class*/}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                            if(method.getName().equals("enqueueToast")){
// 获取TN的class
                                Class<?> tnClass = Class.forName(Toast.class.getName() + "$TN");
                                // 获取mNextView的Field
                                Field mNextViewField = tnClass.getDeclaredField("mNextView");
                                mNextViewField.setAccessible(true);
                                // 获取mNextView实例
                                LinearLayout mNextView = (LinearLayout) mNextViewField.get(args[2]);
                                // 获取textview
                                TextView childView = (TextView) mNextView.getChildAt(0);
                                // 获取文本内容
                                CharSequence text = childView.getText();
                                // 替换文本并赋值
                                childView.setText(text.toString().replace("HappyCoding：", ""));
                            }

                            return method.invoke(mService, args);
                        }
                    });

                    @SuppressLint("SoonBlockedPrivateApi") Field sService = Toast.class.getDeclaredField("sService");
                    sService.setAccessible(true);
                    sService.set(toast, proxy);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                toast.show();

            }
        });
    }
}
