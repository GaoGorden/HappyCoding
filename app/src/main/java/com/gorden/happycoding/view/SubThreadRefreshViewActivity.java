package com.gorden.happycoding.view;

import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.gorden.happycoding.base.BaseActivity;
import com.gorden.happycoding.databinding.ActivitySubThreadRefreshViewBinding;

/**
 * 验证子线程能否更新 UI ？为什么？
 */
public class SubThreadRefreshViewActivity extends BaseActivity {


    private ActivitySubThreadRefreshViewBinding binding;

    @Override
    protected ViewBinding getBinding() {
        binding = ActivitySubThreadRefreshViewBinding.inflate(getLayoutInflater());
        return binding;
    }

    @Override
    protected void initView() {
        // 虽然有 requestLayout ，但是不会传递到 viewRootImpl 去。因为 mParent == null
        Thread thread = new Thread(() -> {
            binding.subThreadRefreshTextview.setText("子线程修改");
            baseLogE(Thread.currentThread().getName());
        });
        thread.start();


//        binding.subThreadRefreshTextview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (thread.isAlive()) {
//                    thread.run();
//                } else {
//                    thread.start();
//                }
//            }
//        });

        binding.subThreadRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> binding.subThreadRefreshTextview.setText("按钮子线程修改")).start();
            }
        });
    }

}
