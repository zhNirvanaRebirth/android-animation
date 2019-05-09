package com.zhwilson.layoutupdateanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LayoutUpdateActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_update);
        findViewById(R.id.auto_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LayoutUpdateActivity.this, AutoLayoutUpdateAnimationActivity.class));
            }
        });
        findViewById(R.id.transition_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LayoutUpdateActivity.this, LayoutChangeWithTransitionActivity.class));
            }
        });
        findViewById(R.id.custom_transition_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LayoutUpdateActivity.this, LayoutChangeWithCustomTransitionActivity.class));
            }
        });
        findViewById(R.id.slide_viewpager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LayoutUpdateActivity.this, SlideViewPagerActivity.class));
            }
        });
    }
}
