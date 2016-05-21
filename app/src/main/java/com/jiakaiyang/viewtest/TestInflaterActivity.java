package com.jiakaiyang.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by admin on 2016/5/21.
 */
public class TestInflaterActivity extends AppCompatActivity {
    private long onCreateTime;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateTime = System.currentTimeMillis();
        setContentView(R.layout.test_loader_act);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView = (TextView) findViewById(R.id.text);
        final View testView  = findViewById(R.id.last_image);
        testView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                long i = System.currentTimeMillis() - onCreateTime;
                textView.setText(String.valueOf(i));
                testView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
