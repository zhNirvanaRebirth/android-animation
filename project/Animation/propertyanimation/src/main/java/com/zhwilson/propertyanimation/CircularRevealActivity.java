package com.zhwilson.propertyanimation;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

public class CircularRevealActivity extends AppCompatActivity {
    ImageView circularRevealImg;
    boolean show = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        circularRevealImg = findViewById(R.id.img);
        findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = circularRevealImg.getWidth();
                int height = circularRevealImg.getHeight();
                float radius = Math.max(width, height);
                Animator animator = ViewAnimationUtils.createCircularReveal(circularRevealImg, width/2, height/2, show ? radius : 0f, show ? 0f : radius);
                animator.setDuration(3000);
                animator.start();
                show = !show;
            }
        });
    }
}
