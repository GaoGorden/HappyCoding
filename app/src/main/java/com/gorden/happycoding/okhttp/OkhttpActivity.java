package com.gorden.happycoding.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gorden.happycoding.databinding.ActivityNetBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends Activity {

    private static final String TAG = OkhttpActivity.class.getSimpleName();
    ActivityNetBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        get();

        post();

        if(getParent()!= null){
            Toast.makeText(this, getParent().getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        }
    }

    private void post() {
        OkHttpClient client = new OkHttpClient();

        // 1。 提交各种类型的数据
//        MediaType.parse("image/png");
//        MediaType.parse("application/json;charst=utf-8");
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charst=utf-8"), "Hello gorden");

        // 2。 提交表单
        // 表单的 contentType 在 FormBody内部已经定义了
//        RequestBody formbody = new FormBody.Builder().add("usrename", "gorden").add("password", "124").build();

        // 3。 当我们提交多种类型时，则需要使用 multipartBody

//        MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("username", "Gorden")
//                .addFormDataPart("picture", "pic.png", fileBody);

//        Toast.makeText(NetActivity.this, formbody.contentType().toString(), Toast.LENGTH_SHORT).show();

        Request request = new Request.Builder().url("https://api.github.com/markdown/raw").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "okHttpPost enqueue: \n onResponse:"+ response.toString() +"\n body:" +response.body().string());
            }
        });
    }

    private void get() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://www.baidu.com").get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(OkhttpActivity.this, "okhttp error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.netContent.setText(res);
                        Toast.makeText(OkhttpActivity.this, res, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
