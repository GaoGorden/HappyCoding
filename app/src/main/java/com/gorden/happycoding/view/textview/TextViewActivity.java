package com.gorden.happycoding.view.textview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gorden.happycoding.R;

/*
研究此情况：点击“添加购物车”按钮TextView跑马灯动画会出现跳动（动画重置，滚动从头重新开始）
 */
public class TextViewActivity extends Activity {

    private int mNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_textview);
        findViewById(R.id.show_tv).setSelected(true);
        final TextView changeTv = findViewById(R.id.change_tv);
        changeTv.setText(getString(R.string.add_string, mNum));
        findViewById(R.id.click_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNum++;
                changeTv.setText(getString(R.string.add_string, mNum));
            }
        });
    }
}
