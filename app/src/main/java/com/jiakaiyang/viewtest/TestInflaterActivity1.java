package com.jiakaiyang.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by admin on 2016/5/21.
 */
public class TestInflaterActivity1 extends AppCompatActivity {
    private long onCreateTime;
    private TextView textView;
    public static View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateTime = System.currentTimeMillis();
        ViewGroup contentParent = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        contentParent.removeAllViews();
        if(rootView!= null){
            if(rootView.getParent() != null){
                ViewGroup viewGroup = (ViewGroup) rootView.getParent();
                viewGroup.removeView(rootView);
                contentParent.addView(rootView);
            }else{
                contentParent.addView(rootView);
            }
        }
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
